package com.so_learn.课时5;

import com.github.unidbg.Emulator;
import com.github.unidbg.arm.ArmHook;
import com.github.unidbg.arm.HookStatus;
import com.github.unidbg.arm.context.RegisterContext;
import com.github.unidbg.hook.HookListener;
import com.github.unidbg.memory.SvcMemory;
import com.github.unidbg.pointer.UnidbgPointer;

public class EnvHook implements HookListener {

    private final Emulator<?> emulator;

    public EnvHook(Emulator<?> emulator) {
        this.emulator = emulator;
    }

    @Override
    public long hook(SvcMemory svcMemory, String libraryName, String symbolName, final long old) {
        if ("libc.so".equals(libraryName) && "getenv".equals(symbolName)) {
            if (emulator.is32Bit()) {
                return svcMemory.registerSvc(new ArmHook() {
                    @Override
                    protected HookStatus hook(Emulator<?> emulator) {
                        return getenv(old);
                    }
                }).peer;
            }
        }
        return 0;
    }

    private HookStatus getenv(long old) {
        RegisterContext context = emulator.getContext();
        UnidbgPointer pointer = context.getPointerArg(0);
        String key = pointer.getString(0);
        switch (key){
            case "PATH":{
                pointer.setString(0, "5");
                return HookStatus.LR(emulator, pointer.peer);
            }
        }
        return HookStatus.RET(emulator, old);
    }
}