package com.github.unidbg.linux;

import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.hook.HookListener;
import com.github.unidbg.memory.SvcMemory;
import com.github.unidbg.pointer.UnidbgPointer;
import com.sun.jna.Pointer;
import net.fornwall.jelf.ElfSymbol;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class ModuleSymbol {

    static final long WEAK_BASE = -1;

    final String soName;
    private final long load_base;
    final ElfSymbol symbol;
    final Pointer relocationAddr;
    final String toSoName;
    final long offset;

    ModuleSymbol(String soName, long load_base, ElfSymbol symbol, Pointer relocationAddr, String toSoName, long offset) {
        this.soName = soName;
        this.load_base = load_base;
        this.symbol = symbol;
        this.relocationAddr = relocationAddr;
        this.toSoName = toSoName;
        this.offset = offset;
    }

    ModuleSymbol resolve(Collection<Module> modules, boolean resolveWeak, List<HookListener> listeners, SvcMemory svcMemory) throws IOException {
        final String symbolName = symbol.getName();
        for (Module m : modules) {
            LinuxModule module = (LinuxModule) m;
            Long symbolHook = module.hookMap.get(symbolName);
            if (symbolHook != null) {
                return new ModuleSymbol(soName, WEAK_BASE, symbol, relocationAddr, module.name, symbolHook);
            }

            ElfSymbol elfSymbol = module.getELFSymbolByName(symbolName);
            if (elfSymbol != null && !elfSymbol.isUndef()) {
                switch (elfSymbol.getBinding()) {
                    case ElfSymbol.BINDING_GLOBAL:
                    case ElfSymbol.BINDING_WEAK:
                        for (HookListener listener : listeners) {
                            long hook = listener.hook(svcMemory, module.name, symbolName, module.base + elfSymbol.value + offset);
                            if (hook > 0) {
                                module.hookMap.put(symbolName, hook);
                                return new ModuleSymbol(soName, WEAK_BASE, elfSymbol, relocationAddr, module.name, hook);
                            }
                        }
                        return new ModuleSymbol(soName, module.base, elfSymbol, relocationAddr, module.name, offset);
                }
            }
        }

        if (resolveWeak && symbol.getBinding() == ElfSymbol.BINDING_WEAK) {
            return new ModuleSymbol(soName, WEAK_BASE, symbol, relocationAddr, "0", 0);
        }

        if ("dlopen".equals(symbolName) ||
                "dlclose".equals(symbolName) ||
                "dlsym".equals(symbolName) ||
                "dlerror".equals(symbolName) ||
                "dladdr".equals(symbolName) ||
                "android_update_LD_LIBRARY_PATH".equals(symbolName) ||
                "android_get_LD_LIBRARY_PATH".equals(symbolName) ||
                "dl_iterate_phdr".equals(symbolName) ||
                "android_dlopen_ext".equals(symbolName) ||
                "android_set_application_target_sdk_version".equals(symbolName) ||
                "android_get_application_target_sdk_version".equals(symbolName) ||
                "android_init_namespaces".equals(symbolName) ||
                "android_create_namespace".equals(symbolName) ||
                "dlvsym".equals(symbolName) ||
                "android_dlwarning".equals(symbolName) ||
                "dl_unwind_find_exidx".equals(symbolName)) {
            if (resolveWeak) {
                for (HookListener listener : listeners) {
                    long hook = listener.hook(svcMemory, "libdl.so", symbolName, offset);
                    if (hook > 0) {
                        return new ModuleSymbol(soName, WEAK_BASE, symbol, relocationAddr, "libdl.so", hook);
                    }
                }
            }
        }

        return null;
    }

    void relocation(Emulator<?> emulator, LinuxModule module, ElfSymbol symbol) {
        final long value;
        if (load_base == WEAK_BASE) {
            value = offset;
        } else {
            value = module.base + (symbol == null ? 0 : symbol.value) + offset;
        }
        relocationAddr.setPointer(0, UnidbgPointer.pointer(emulator, value));
    }

    void relocation(Emulator<?> emulator, LinuxModule owner) throws IOException {
        if (symbol != null) {
            owner.resolvedSymbols.put(symbol.getName(), this);
        }
        final long value;
        if (load_base == WEAK_BASE) {
            value = offset;
        } else {
            value = load_base + (symbol == null ? 0 : symbol.value) + offset;
        }
        relocationAddr.setPointer(0, UnidbgPointer.pointer(emulator, value));
    }

    public ElfSymbol getSymbol() {
        return symbol;
    }

    Pointer getRelocationAddr() {
        return relocationAddr;
    }

}
