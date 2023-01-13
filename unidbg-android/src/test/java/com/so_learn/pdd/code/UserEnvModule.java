package com.so_learn.pdd.code;

import com.github.unidbg.Emulator;
import com.github.unidbg.arm.ArmSvc;
import com.github.unidbg.arm.context.RegisterContext;
import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.memory.MemoryBlock;
import com.github.unidbg.memory.SvcMemory;
import com.github.unidbg.pointer.UnidbgPointer;
import com.github.unidbg.virtualmodule.VirtualModule;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class UserEnvModule extends VirtualModule<VM> {
    protected UserEnvModule(Emulator<?> emulator) {
        super(emulator, null, "libUserEnv.so");
    }

    @Override
    protected void onInitialize(Emulator<?> emulator, VM extra, Map<String, UnidbgPointer> symbols) {
        SvcMemory svcMemory = emulator.getSvcMemory();
        symbols.put("GetUserEnvStr", svcMemory.registerSvc(new ArmSvc() {
            @Override
            public long handle(Emulator<?> emulator) {
                RegisterContext context = emulator.getContext();
                int arg0 = context.getIntArg(0);
                int arg1 = context.getIntArg(1);
                MemoryBlock replaceBlock = emulator.getMemory().malloc(0x100, true);
                UnidbgPointer replacePtr = replaceBlock.getPointer();
                String pathValue = "21m4UbAl6CYLAiRpLGYy/aogOApL8qwEb733+DsT9h/8mE/P2j8ii4Vk/oNCE3UDIhvJDTaaErIjobaaFhZPMzp33T8LVMDlUsgizw7t5rOz1xb7aQsXhgG6eSHFM8fqCJSOuA6DXHNlagqvNh5NxaTb2UxTa+isdWRGwZUIUdd/e83Jf5fw2IkS9iSkXb8p0FrgbRl75yk=";
                replacePtr.write(0, pathValue.getBytes(StandardCharsets.UTF_8), 0, pathValue.length());
                return replacePtr.peer;
            };
        }));
        symbols.put("FreeUserEnvStr", svcMemory.registerSvc(new ArmSvc() {
            @Override
            public long handle(Emulator<?> emulator) {
                return 0;
            }
        }));
        symbols.put("InitUserEnv", svcMemory.registerSvc(new ArmSvc() {
            @Override
            public long handle(Emulator<?> emulator) {
                throw new UnsupportedOperationException();
            }
        }));
    }
}
