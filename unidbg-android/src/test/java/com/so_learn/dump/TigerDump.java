package com.so_learn.dump;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.arm.backend.Unicorn2Factory;
import com.github.unidbg.debugger.BreakPointCallback;
import com.github.unidbg.file.FileResult;
import com.github.unidbg.file.IOResolver;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.AbstractJni;
import com.github.unidbg.linux.android.dvm.DalvikModule;
import com.github.unidbg.linux.android.dvm.DvmObject;
import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.linux.android.dvm.array.ByteArray;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.utils.Inspector;
import unicorn.ArmConst;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TigerDump extends AbstractJni implements IOResolver {
    @Override
    public FileResult resolve(Emulator emulator, String pathname, int oflags) {
        System.out.println("File open:"+pathname);
        return null;
    }

    private final AndroidEmulator emulator;
    private final VM vm;
    private Module module;
    public final String dumpPath = "unidbg-android/src/test/resources/wtoken/dump/";
    public long moduleBase = 0xb99d5000L;
    public long offset = 0x25a9C;


    TigerDump() {
        emulator = AndroidEmulatorBuilder.for32Bit()
                .addBackendFactory(new Unicorn2Factory(false))
                .setProcessName("com.nike.omega")
                .build();
        Memory memory = emulator.getMemory();
        memory.setLibraryResolver(new AndroidResolver(23));
        emulator.getSyscallHandler().setEnableThreadDispatcher(true);
        emulator.getSyscallHandler().addIOResolver(this);
        emulator.getBackend().registerEmuCountHook(100000); // 设置执行多少条指令切换一次线程
        vm = emulator.createDalvikVM();
        vm.setJni(this);
        vm.setVerbose(true);
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/resources/wtoken/apk/libtiger_tally.so"), false);
        module = dm.getModule();
        initContext();
        patch();
    }


    //
    public void patch(){
        // patch SO
        byte[] originCode = emulator.getBackend().mem_read(module.base + offset, 0x10);
        emulator.getBackend().mem_write(moduleBase + offset, originCode);


        emulator.attach().addBreakPoint(0xb99e71d8L, new BreakPointCallback() {
            @Override
            public boolean onHit(Emulator<?> emulator, long address) {
                emulator.getBackend().reg_write(ArmConst.UC_ARM_REG_PC, module.findSymbolByName("malloc").getAddress());
                return true;
            }
        });

        emulator.attach().addBreakPoint(0xb99e7250L, new BreakPointCallback() {
            @Override
            public boolean onHit(Emulator<?> emulator, long address) {
                emulator.getBackend().reg_write(ArmConst.UC_ARM_REG_PC, module.findSymbolByName("memcpy").getAddress());
                return true;
            }
        });

        emulator.attach().addBreakPoint(0xb99e725cL, new BreakPointCallback() {
            @Override
            public boolean onHit(Emulator<?> emulator, long address) {
                emulator.getBackend().reg_write(ArmConst.UC_ARM_REG_PC, module.findSymbolByName("free").getAddress());
                return true;
            }
        });

        emulator.attach().addBreakPoint(0xb99e7478L, new BreakPointCallback() {
            @Override
            public boolean onHit(Emulator<?> emulator, long address) {
                emulator.getBackend().reg_write(ArmConst.UC_ARM_REG_PC, module.findSymbolByName("memset").getAddress());
                return true;
            }
        });

        emulator.attach().addBreakPoint(0xb99e73a0L, new BreakPointCallback() {
            @Override
            public boolean onHit(Emulator<?> emulator, long address) {
                emulator.getBackend().reg_write(ArmConst.UC_ARM_REG_PC, module.findSymbolByName("gettimeofday").getAddress());
                return true;
            }
        });

        emulator.attach().addBreakPoint(0xb99e73b8L, new BreakPointCallback() {
            @Override
            public boolean onHit(Emulator<?> emulator, long address) {
                emulator.getBackend().reg_write(ArmConst.UC_ARM_REG_PC, module.findSymbolByName("sprintf").getAddress());
                return true;
            }
        });

        emulator.attach().addBreakPoint(0xb99e728cL, new BreakPointCallback() {
            @Override
            public boolean onHit(Emulator<?> emulator, long address) {
                emulator.getBackend().reg_write(ArmConst.UC_ARM_REG_PC, module.findSymbolByName("memmove").getAddress());
                return true;
            }
        });

        emulator.attach().addBreakPoint(0xb99e7178L, new BreakPointCallback() {
            @Override
            public boolean onHit(Emulator<?> emulator, long address) {
                emulator.getBackend().reg_write(ArmConst.UC_ARM_REG_PC, module.findSymbolByName("strlen").getAddress());
                return true;
            }
        });


        emulator.attach().addBreakPoint(moduleBase + 0x12218, new BreakPointCallback() {
            @Override
            public boolean onHit(Emulator<?> emulator, long address) {
                emulator.getBackend().reg_write(ArmConst.UC_ARM_REG_PC, module.findSymbolByName("__aeabi_memclr").getAddress());
                return true;
            }
        });


        emulator.attach().addBreakPoint(moduleBase + 0x1259c, new BreakPointCallback() {
            @Override
            public boolean onHit(Emulator<?> emulator, long address) {
                emulator.getBackend().reg_write(ArmConst.UC_ARM_REG_PC, module.findSymbolByName("realloc").getAddress());
                return true;
            }
        });


    }

    public void initContext(){
        File file = new File(dumpPath);
        File[] fs = file.listFiles();
        assert fs != null;
        for(File f:fs) {
            if (!f.isDirectory()){
                String name = f.getName();
                mapAndWrite(name);
            }
        }
    }

    public byte[] restoreBinary(String name){
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(dumpPath+name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public void mapAndWrite(String name){
        byte[] data = restoreBinary(name);
        String[] base_and_end = name.replaceAll(".bin", "").replaceAll("0x", "").split("_");
        long start = Long.parseLong(base_and_end[0], 16);
        long end = Long.parseLong(base_and_end[1], 16);
        emulator.getBackend().mem_map(start, end - start, 7);
        emulator.getBackend().mem_write(start, data);
    }


    private void callTarget() {
        emulator.traceRead(moduleBase+0xEA2E8, moduleBase+0xEA2E8);

        // start trace
        String traceFile = "D:\\dumpway\\tiger\\dump\\trace\\trace1.log";
        try {
            PrintStream traceStream = new PrintStream(new FileOutputStream(traceFile), true);
            emulator.traceCode(moduleBase,moduleBase+module.size).setRedirect(traceStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv());
        DvmObject<?> thiz = vm.resolveClass("com/aliyun/TigerTally/TigerTallyAPI").newObject(null);
        list.add(vm.addLocalObject(thiz));
        list.add(1);
        ByteArray barr = new ByteArray(vm, "da965a94-97da-4730-b7d3-3d16e4061489".getBytes(StandardCharsets.UTF_8));
        list.add(vm.addLocalObject(barr));
        //  开始模拟执行
        Number result = Module.emulateFunction(emulator, moduleBase + offset + 1, list.toArray());
        String ret = vm.getObject(result.intValue()).getValue().toString();
        System.out.println("result:"+ret);
        Inspector.inspect(ret.getBytes(), "ret");
    }

    public static void main(String[] args) {
        TigerDump tigerDump = new TigerDump();
        tigerDump.callTarget();
    }


}
