package com.so_learn.使用Unidbg分析Frida反调试;

import com.github.unidbg.AbstractEmulator;
import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.android.AndroidTest;
import com.github.unidbg.android.ThreadTest;
import com.github.unidbg.arm.backend.DynarmicFactory;
import com.github.unidbg.arm.backend.Unicorn2Factory;
import com.github.unidbg.file.FileResult;
import com.github.unidbg.file.IOResolver;
import com.github.unidbg.file.linux.AndroidFileIO;
import com.github.unidbg.linux.ARM32SyscallHandler;
import com.github.unidbg.linux.android.*;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.linux.file.ByteArrayFileIO;
import com.github.unidbg.linux.file.DirectoryFileIO;
import com.github.unidbg.linux.file.MapsFileIO;
import com.github.unidbg.linux.file.SimpleFileIO;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.memory.SvcMemory;
import com.github.unidbg.unix.UnixSyscallHandler;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class vdog extends AbstractJni implements IOResolver{
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    private vdog() {
        emulator = AndroidEmulatorBuilder.for32Bit()
                .setRootDir(new File("target/rootfs"))
                .addBackendFactory(new Unicorn2Factory(false))
                .setProcessName("test").build();

        emulator.getBackend().registerEmuCountHook(10000); // 设置执行多少条指令切换一次线程
        final Memory memory = emulator.getMemory();
        memory.setLibraryResolver(new AndroidResolver(23));

        File apkFile = new File("unidbg-android/src/test/java/com/so_learn/使用Unidbg分析Frida反调试/459032971C6EFC9DEECA8734C99C34BB.apk");
        vm = emulator.createDalvikVM(apkFile);
        vm.setJni(this);
        vm.setVerbose(true);

        emulator.getSyscallHandler().setVerbose(true);
        emulator.getSyscallHandler().setEnableThreadDispatcher(true);

        emulator.getSyscallHandler().addIOResolver(this);
        SystemPropertyHook systemPropertyHook = new
                SystemPropertyHook(emulator);
        systemPropertyHook.setPropertyProvider(key -> {
            System.out.println("fuck systemkey:"+key);
            switch (key){
                case "ro.build.version.sdk":
                    return "23";
                    case "persist.sys.dalvik.vm.lib":
                    case "persist.sys.dalvik.vm.lib.2":
                    case "release_or_codename":
                        return "";
            }
            return "";
        });
        memory.addHookListener(systemPropertyHook);


        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/使用Unidbg分析Frida反调试/libvdog.so"), true);
        module = dm.getModule();
//        dm.callJNI_OnLoad(emulator);
        vm.setJni(this);
    }

    private void destroy() throws IOException {
        emulator.close();
        System.out.println("destroy");
    }

    public static void main(String[] args) throws Exception {
        Logger.getLogger(ARM32SyscallHandler.class).setLevel(Level.INFO);
        Logger.getLogger(AbstractEmulator.class).setLevel(Level.INFO);
        vdog test = new vdog();
        test.destroy();
    }

    @Override
    public FileResult resolve(Emulator emulator, String pathname, int oflags) {
        final int attachPid = emulator.getPid();
        System.out.println("--------------------pathName:"+pathname);
//        if (("/proc/" + attachPid + "/cmdline").equals(pathname)) {
//            return FileResult.<AndroidFileIO>success(new ByteArrayFileIO(oflags, pathname, "com.sfexpress.merchant".getBytes()));
//        }
//        if (("/proc/self/maps").equals(pathname)) {
//            return FileResult.success(new SimpleFileIO(oflags, new File("unidbg-android/src/test/java/com/so_learn/使用Unidbg分析Frida反调试/maps"), pathname));
//        }
//        if (("/proc/" + attachPid + "/status").equals(pathname)) {
//            return FileResult.success(new SimpleFileIO(oflags, new File("unidbg-android/src/test/java/com/so_learn/使用Unidbg分析Frida反调试/status"), pathname));
//        }
//        if (("/proc/" + attachPid + "/task").equals(pathname)||"/proc/self/task".equals(pathname)) {
//            return FileResult.<AndroidFileIO>success(new DirectoryFileIO(oflags, pathname,
//                    new DirectoryFileIO.DirectoryEntry(true, "unidbg-android/src/test/java/com/so_learn/使用Unidbg分析Frida反调试/task")));
//        }
//
//        //不会补
//        if (("/proc/self/fd").equals(pathname)||("/proc/self/fd/").equals(pathname)) {
////            return FileResult.<AndroidFileIO>success(new DirectoryFileIO(oflags, "",
////                    new DirectoryFileIO.DirectoryEntry(true, "unidbg-android/src/test/java/com/so_learn/使用Unidbg分析Frida反调试/fd")));
//                    //            int fd = Integer.parseInt("unidbg-android/src/test/java/com/so_learn/使用Unidbg分析Frida反调试/fd");
//            return FileResult.success(new SimpleFileIO(oflags, new File("unidbg-android/src/test/java/com/so_learn/使用Unidbg分析Frida反调试/fd/1"), pathname));
//        }
        return null;
    }
}
