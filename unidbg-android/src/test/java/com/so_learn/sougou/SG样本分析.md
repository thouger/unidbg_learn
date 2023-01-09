### 一、前言

一个球友的样本，做一下大致的算法还原，这篇主要想表达和分享这些东西

- 分析简单的样本时，只需要分析参数，验证返回值就行了，了解加解密算法则事半功倍，否则事倍功半
- Unidbg 在算法分析上的简单使用

写到一半时意识到，没有掌握密码学的知识，很难跟上分析思路，所以讲粗一些，在八月的密码学实战中细致分析一下哎。

### 二、准备

样本链接：https://pan.baidu.com/s/1Ey7I5iEGSa3K6zNiwWt3kg 
提取码：0ux5 

![image-20210801134807732](C:\Users\pr0214\AppData\Roaming\Typora\typora-user-images\image-20210801134807732.png)

![image-20210801133847501](pic\1.png)

 这是目标函数，我们需要传入三个字符串。它的模拟执行比较简单，我们直接放一下，因为本篇的重点是算法还原，**在执行我们的目标函数前，需要先执行init函数，它是SO的初始化函数，在其中会做签名校验，目的是防止App被重打包**。

这个样本的初始化函数很容易识别出来，**init**嘛，但复杂的样本，其初始化也会很复杂，遇到了具体样本我们再说。看一下代码吧！

```java
package com.sg;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Module;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class encrypt extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    encrypt() {
        // 创建模拟器实例,进程名建议依照实际进程名填写，可以规避针对进程名的校验
        emulator = AndroidEmulatorBuilder.for32Bit().setProcessName("com.sina.oasis").build();
        // 获取模拟器的内存操作接口
        final Memory memory = emulator.getMemory();
        // 设置系统类库解析
        memory.setLibraryResolver(new AndroidResolver(23));
        // 创建Android虚拟机,传入APK，Unidbg可以替我们做部分签名校验的工作
        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/resources/sg/sougou.apk"));
        // 加载目标SO
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/resources/sg/libSCoreTools.so"), true); // 加载so到虚拟内存
        //获取本SO模块的句柄,后续需要用它
        module = dm.getModule();
        vm.setJni(this); // 设置JNI
        vm.setVerbose(true); // 打印日志
    }

    public static void main(String[] args) {
        encrypt demo = new encrypt();
        // 初始化函数
        demo.native_init();
        // 目标函数
        demo.encryptDemo();

    }

    public void native_init(){
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv()); // 第一个参数是env
        list.add(0); // 第二个参数，实例方法是jobject，静态方法是jclass，直接填0，一般用不到。
        DvmObject<?> context = vm.resolveClass("android/content/Context").newObject(null); // context
        list.add(vm.addLocalObject(context));
        module.callFunction(emulator, 0x9565, list.toArray());
    };


    public void encryptDemo(){
        List<Object> list = new ArrayList<>(10);
        // arg1 env
        list.add(vm.getJNIEnv());
        // arg2 jobject/jclass 一般用不到 可以直接填0
        list.add(0);
        String str = "http://app.weixin.sogou.com/api/searchapp";
        String str2 = "type=2&ie=utf8&page=1&query=%E5%A5%8B%E9%A3%9E%E5%AE%89%E5%85%A8&select_count=1&tsn=1&usip=";
        String str3 = "lilac";
        list.add(vm.addLocalObject(new StringObject(vm,str)));
        list.add(vm.addLocalObject(new StringObject(vm,str2)));
        list.add(vm.addLocalObject(new StringObject(vm,str3)));
        Number number = module.callFunction(emulator,0x9ca1,list.toArray())[0];
        String result = vm.getObject(number.intValue()).getValue().toString();
        System.out.println(result);
    }
}
```

![image-20210801135832406](pic\2.png)



### 三、算法还原

这个样本的符号都在，还原非常简单，我们就当熟悉一下Unidbg在算法分析上的API吧。简单样本的分析过程其实很单调，猜测流程中关键函数的作用，并通过Hook去验证这种猜想，基本就OK了。

![image-20210801140522999](pic\3.png)

首先，把a1转成JNIEnv，方便看JNI函数

![image-20210801140618724](pic\4.png)

![image-20210801140637841](pic\5.png)

算法分析的要点在于**抓主干**，通过函数名可以看出，j_Sc_EncryptWallEncode就是此函数的主干。有人可能会问，我感觉sub_22558是主干，为啥是**j_Sc_EncryptWallEncode**呢？

——那也没啥关系，稍微走一点弯路并不碍事，分析的样本越多，走的弯路就越少，直觉就越好。

这个函数的入参有四个，参数123是我们的入参，参数4是空的内存块，按照C/C++开发的习惯，参数4这个空buffer就是用来装运算结果的，那函数的返回值是什么呢？一般是运行是否成功的信号，比方说，成功就返回1，失败就返回0。

我们首先通过Hook验证猜想，看看它的入参，以及参数4的buffer在函数前后的变化。

##### 3.1 Unidbg中的Hook

我们首先从整体**理论层面**上阐述一下Unidbg所支持的HOOK工具和形式，Unidbg是一个横跨Android和IOS的模拟执行框架，支持多种市面上的Hook框架，现讨论它在Android上的Hook支持。

目前，在Android上它支持**Dobby**，**HookZz**，**Whale**，**Xhook**这四个Hook框架，HookZz是Dobby的前身，之所以要分成两个Hook框架，是因为Unidbg作者认为HookZz对32位支持较好，之后的Dobby对64位支持较好。

除此之外，Unidbg在默认引擎下（即Unicorn backend），支持Unicorn自带的各种Hook（指令级Hook，块级Hook，内存读写Hook，异常Hook） 以及 Unidbg 封装后的 Console Debugger。

接下来介绍算法分析中的HOOK思路

- 快速验证用Console Debugger
- 持久化用HookZz
- 稳定Hook 用Unicorn Hook

这三类Hook是Unidbg算法分析的主力之一，另一个主力API我们以后介绍。在这三种Hook中，我最要强调的是基于Unicorn封装和设计的Console Debugger，是Console Debugger让Unidbg成为一个适合算法分析和协议还原的强大工具。

以Hook j_Sc_EncryptWallEncode为例

首先想一下，如果Frida去做这件事会怎样

```javascript
function hookFunction(){
    var base_addr = Module.findBaseAddress("libSCoreTools.so");
    // 0xA284 是Sc_EncryptWallEncode的实现地址，thumb模式+1
    var real_addr = base_addr.add(0xA285);
    Interceptor.attach(real_addr, {
        onEnter: function (args) {
            var str1 = args[0].readCString();
            console.log("参数1"+str1);
            var str2 = args[1].readCString();
            console.log("参数2"+str2);
            var str3 = args[2].readCString();
            console.log("参数3"+str3);
            this.buffer = args[3];
        },
        onLeave: function (retval) {
            // 打印函数结束后的buffer
            console.log(hexdump(this.buffer));
        }
    });
}
```

 在有些时候，j_Sc_EncryptWallEncode 可能在程序中运行许多次，但我们只想观察此次此地的执行情况，那我们可能会使用inline hook，需要注意，inline hook 的时机是目标指令执行前。

![image-20210801195821731](pic\6.png)

那么我们可以在9d24这个地址，查看寄存器R0、R1、R2的值，因为按照ARM的ATPCS调用约定：

> 参数1~参数4 分别保存到 R0~R3 寄存器中 ，剩下的参数从右往左依次入栈，被调用者实现栈平衡，返回值存放在 R0 中。

```javascript
function hookInline(){
    var base_addr = Module.findBaseAddress("libSCoreTools.so");
    var real_addr = base_addr.add(0xA285);
    Interceptor.attach(real_addr, {
        onEnter: function (args) {
            console.log("参数1:"+this.context.r0.readCString());
            console.log("参数2:"+this.context.r1.readCString());
            console.log("参数3:"+this.context.r2.readCString());
        }
    });

}
```



接下来我们看一下在Unidbg中如何实现上述效果

首先是HookZz 实现函数Hook，为什么我们没选择Dobby或者Whale呢？因为我们的样本是ARM32，先前也说了，HookZz在32位更稳定，而且HookZz支持Inline Hook，Hook替换、修改等，功能上最为强大。

```java
// HookZz hook Sc_EncryptWallEncode
public void hookEncryptWallEncode(){
    // 获取HookZz对象
    IHookZz hookZz = HookZz.getInstance(emulator); // 加载HookZz，支持inline hook，文档看https://github.com/jmpews/HookZz
    // enable hook
    hookZz.enable_arm_arm64_b_branch(); // 测试enable_arm_arm64_b_branch
    hookZz.wrap(module.base + 0xA284 + 1, new WrapCallback<HookZzArm32RegisterContext>() {
        Pointer buffer;
        @Override
        // 方法执行前
        public void preCall(Emulator<?> emulator, HookZzArm32RegisterContext ctx, HookEntryInfo info) {
            System.out.println("HookZz hook EncryptWallEncode");
            Pointer input1 = ctx.getPointerArg(0);
            Pointer input2 = ctx.getPointerArg(1);
            Pointer input3 = ctx.getPointerArg(2);
            // getString的参数i代表index,即input[i:]
            System.out.println("参数1："+input1.getString(0));
            System.out.println("参数2："+input2.getString(0));
            System.out.println("参数3："+input3.getString(0));

            buffer = ctx.getPointerArg(3);
        };

        @Override
        // 方法执行后
        public void postCall(Emulator<?> emulator, HookZzArm32RegisterContext ctx, HookEntryInfo info) {
            // getByteArray参数1是起始index，参数2是长度，我们不知道结果多长，就先设置0x100吧
            byte[] outputhex = buffer.getByteArray(0, 0x100);
            Inspector.inspect(outputhex, "EncryptWallEncode output");
        }
    });
    hookZz.disable_arm_arm64_b_branch();

}
```

再看一下Unidbg完整的代码

```java
package com.sg;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.hook.hookzz.*;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.utils.Inspector;
import com.sun.jna.Pointer;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class encrypt extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    encrypt() {
        // 创建模拟器实例,进程名建议依照实际进程名填写，可以规避针对进程名的校验
        emulator = AndroidEmulatorBuilder.for32Bit().setProcessName("com.sina.oasis").build();
        // 获取模拟器的内存操作接口
        final Memory memory = emulator.getMemory();
        // 设置系统类库解析
        memory.setLibraryResolver(new AndroidResolver(23));
        // 创建Android虚拟机,传入APK，Unidbg可以替我们做部分签名校验的工作
        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/resources/sg/sougou.apk"));
        // 加载目标SO
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/resources/sg/libSCoreTools.so"), true); // 加载so到虚拟内存
        //获取本SO模块的句柄,后续需要用它
        module = dm.getModule();
        vm.setJni(this); // 设置JNI
        vm.setVerbose(true); // 打印日志
    }

    public static void main(String[] args) {
        encrypt demo = new encrypt();
        demo.hookEncryptWallEncode();
        // 初始化函数
        demo.native_init();
        // 目标函数
        demo.encryptDemo();

    }

    public void native_init(){
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv()); // 第一个参数是env
        list.add(0); // 第二个参数，实例方法是jobject，静态方法是jclass，直接填0，一般用不到。
        DvmObject<?> context = vm.resolveClass("android/content/Context").newObject(null); // context
        list.add(vm.addLocalObject(context));
        module.callFunction(emulator, 0x9565, list.toArray());
    };


    public void encryptDemo(){
        List<Object> list = new ArrayList<>(10);
        // arg1 env
        list.add(vm.getJNIEnv());
        // arg2 jobject/jclass 一般用不到 可以直接填0
        list.add(0);
        String str = "http://app.weixin.sogou.com/api/searchapp";
        String str2 = "type=2&ie=utf8&page=1&query=%E5%A5%8B%E9%A3%9E%E5%AE%89%E5%85%A8&select_count=1&tsn=1&usip=";
        String str3 = "lilac";
        list.add(vm.addLocalObject(new StringObject(vm,str)));
        list.add(vm.addLocalObject(new StringObject(vm,str2)));
        list.add(vm.addLocalObject(new StringObject(vm,str3)));
        Number number = module.callFunction(emulator,0x9ca1,list.toArray())[0];
        String result = vm.getObject(number.intValue()).getValue().toString();
        System.out.println(result);
    }

    // HookZz hook Sc_EncryptWallEncode
    public void hookEncryptWallEncode(){
        // 获取HookZz对象
        IHookZz hookZz = HookZz.getInstance(emulator); // 加载HookZz，支持inline hook，文档看https://github.com/jmpews/HookZz
        // enable hook
        hookZz.enable_arm_arm64_b_branch(); // 测试enable_arm_arm64_b_branch
        hookZz.wrap(module.base + 0xA284 + 1, new WrapCallback<HookZzArm32RegisterContext>() {
            Pointer buffer;
            @Override
            // 方法执行前
            public void preCall(Emulator<?> emulator, HookZzArm32RegisterContext ctx, HookEntryInfo info) {
                System.out.println("HookZz hook EncryptWallEncode");
                Pointer input1 = ctx.getPointerArg(0);
                Pointer input2 = ctx.getPointerArg(1);
                Pointer input3 = ctx.getPointerArg(2);
                // getString的参数i代表index,即input[i:]
                System.out.println("参数1："+input1.getString(0));
                System.out.println("参数2："+input2.getString(0));
                System.out.println("参数3："+input3.getString(0));

                buffer = ctx.getPointerArg(3);
            };

            @Override
            // 方法执行后
            public void postCall(Emulator<?> emulator, HookZzArm32RegisterContext ctx, HookEntryInfo info) {
                // getByteArray参数1是起始index，参数2是长度，我们不知道结果多长，就先设置0x100吧
                byte[] outputhex = buffer.getByteArray(0, 0x100);
                Inspector.inspect(outputhex, "EncryptWallEncode output");
            }
        });
        hookZz.disable_arm_arm64_b_branch();

    }
}
```

测试一下

![image-20210801202551969](pic\7.png)

验证发现，参数123确实就是我们的输入，而参数4即buffer的输出就是最终结果。所以全部逻辑就在这个函数里面。

接下来使用HookZz inline hook，实现同Frida Inline Hook同等效果，顺便，我们到0x9d28把函数结束后的buffer内容也打印一下。

```java
package com.sg;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.android.struct.File32;
import com.github.unidbg.arm.context.Arm32RegisterContext;
import com.github.unidbg.hook.hookzz.*;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.pointer.UnidbgPointer;
import com.github.unidbg.utils.Inspector;
import com.sun.jna.Pointer;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class encrypt extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    private Pointer buffer;

    encrypt() {
        // 创建模拟器实例,进程名建议依照实际进程名填写，可以规避针对进程名的校验
        emulator = AndroidEmulatorBuilder.for32Bit().setProcessName("com.sina.oasis").build();
        // 获取模拟器的内存操作接口
        final Memory memory = emulator.getMemory();
        // 设置系统类库解析
        memory.setLibraryResolver(new AndroidResolver(23));
        // 创建Android虚拟机,传入APK，Unidbg可以替我们做部分签名校验的工作
        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/resources/sg/sougou.apk"));
        // 加载目标SO
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/resources/sg/libSCoreTools.so"), true); // 加载so到虚拟内存
        //获取本SO模块的句柄,后续需要用它
        module = dm.getModule();
        vm.setJni(this); // 设置JNI
        vm.setVerbose(true); // 打印日志
    }

    public static void main(String[] args) {
        encrypt demo = new encrypt();
//        demo.hookEncryptWallEncode();
        demo.inlinehookEncryptWallEncode();
        // 初始化函数
        demo.native_init();
        // 目标函数
        demo.encryptDemo();

    }

    public void native_init(){
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv()); // 第一个参数是env
        list.add(0); // 第二个参数，实例方法是jobject，静态方法是jclass，直接填0，一般用不到。
        DvmObject<?> context = vm.resolveClass("android/content/Context").newObject(null); // context
        list.add(vm.addLocalObject(context));
        module.callFunction(emulator, 0x9565, list.toArray());
    };


    public void encryptDemo(){
        List<Object> list = new ArrayList<>(10);
        // arg1 env
        list.add(vm.getJNIEnv());
        // arg2 jobject/jclass 一般用不到 可以直接填0
        list.add(0);
        String str = "http://app.weixin.sogou.com/api/searchapp";
        String str2 = "type=2&ie=utf8&page=1&query=%E5%A5%8B%E9%A3%9E%E5%AE%89%E5%85%A8&select_count=1&tsn=1&usip=";
        String str3 = "lilac";
        list.add(vm.addLocalObject(new StringObject(vm,str)));
        list.add(vm.addLocalObject(new StringObject(vm,str2)));
        list.add(vm.addLocalObject(new StringObject(vm,str3)));
        Number number = module.callFunction(emulator,0x9ca1,list.toArray())[0];
        String result = vm.getObject(number.intValue()).getValue().toString();
        System.out.println(result);
    }

    // HookZz hook Sc_EncryptWallEncode
    public void hookEncryptWallEncode(){
        // 获取HookZz对象
        IHookZz hookZz = HookZz.getInstance(emulator); // 加载HookZz，支持inline hook，文档看https://github.com/jmpews/HookZz
        // enable hook
        hookZz.enable_arm_arm64_b_branch(); // 测试enable_arm_arm64_b_branch
        hookZz.wrap(module.base + 0xA284 + 1, new WrapCallback<HookZzArm32RegisterContext>() {
            Pointer buffer;
            @Override
            // 方法执行前
            public void preCall(Emulator<?> emulator, HookZzArm32RegisterContext ctx, HookEntryInfo info) {
                System.out.println("HookZz hook EncryptWallEncode");
                Pointer input1 = ctx.getPointerArg(0);
                Pointer input2 = ctx.getPointerArg(1);
                Pointer input3 = ctx.getPointerArg(2);
                // getString的参数i代表index,即input[i:]
                System.out.println("参数1："+input1.getString(0));
                System.out.println("参数2："+input2.getString(0));
                System.out.println("参数3："+input3.getString(0));

                buffer = ctx.getPointerArg(3);
            };

            @Override
            // 方法执行后
            public void postCall(Emulator<?> emulator, HookZzArm32RegisterContext ctx, HookEntryInfo info) {
                // getByteArray参数1是起始index，参数2是长度，我们不知道结果多长，就先设置0x100吧
                byte[] outputhex = buffer.getByteArray(0, 0x100);
                Inspector.inspect(outputhex, "EncryptWallEncode output");
            }
        });
        hookZz.disable_arm_arm64_b_branch();

    }

    public void inlinehookEncryptWallEncode(){
        IHookZz hookZz = HookZz.getInstance(emulator);
        hookZz.enable_arm_arm64_b_branch();

        hookZz.instrument(module.base + 0x9d24 + 1, new InstrumentCallback<Arm32RegisterContext>() {
            @Override
            public void dbiCall(Emulator<?> emulator, Arm32RegisterContext ctx, HookEntryInfo info) {
                System.out.println("HookZz inline hook EncryptWallEncode");
                Pointer input1 = ctx.getPointerArg(0);
                Pointer input2 = ctx.getPointerArg(1);
                Pointer input3 = ctx.getPointerArg(2);
                // getString的参数i代表index,即input[i:]
                System.out.println("参数1："+input1.getString(0));
                System.out.println("参数2："+input2.getString(0));
                System.out.println("参数3："+input3.getString(0));

                buffer = ctx.getPointerArg(3);
            }
        });

        hookZz.instrument(module.base + 0x9d28 + 1, new InstrumentCallback<Arm32RegisterContext>() {
            @Override
            public void dbiCall(Emulator<?> emulator, Arm32RegisterContext ctx, HookEntryInfo info) {
                Inspector.inspect(buffer.getByteArray(0,0x100), "inline hook EncryptWallEncode");
            }
        });



    }
}
```

![image-20210801210745411](pic\8.png)

凭心而论，Unidbg中使用HookZz hook以及inline hook 函数的代码量和繁琐度，甚至比Frida更麻烦一些。

接下来看一下使用Unicorn原生API对此处进行inline hook

```java
package com.sg;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.android.struct.File32;
import com.github.unidbg.arm.backend.Backend;
import com.github.unidbg.arm.backend.CodeHook;
import com.github.unidbg.arm.context.Arm32RegisterContext;
import com.github.unidbg.arm.context.RegisterContext;
import com.github.unidbg.hook.hookzz.*;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.pointer.UnidbgPointer;
import com.github.unidbg.utils.Inspector;
import com.sun.jna.Pointer;
import unicorn.ArmConst;
import unicorn.Unicorn;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class encrypt extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    private Pointer buffer;

    encrypt() {
        // 创建模拟器实例,进程名建议依照实际进程名填写，可以规避针对进程名的校验
        emulator = AndroidEmulatorBuilder.for32Bit().setProcessName("com.sina.oasis").build();
        // 获取模拟器的内存操作接口
        final Memory memory = emulator.getMemory();
        // 设置系统类库解析
        memory.setLibraryResolver(new AndroidResolver(23));
        // 创建Android虚拟机,传入APK，Unidbg可以替我们做部分签名校验的工作
        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/resources/sg/sougou.apk"));
        // 加载目标SO
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/resources/sg/libSCoreTools.so"), true); // 加载so到虚拟内存
        //获取本SO模块的句柄,后续需要用它
        module = dm.getModule();
        vm.setJni(this); // 设置JNI
        vm.setVerbose(true); // 打印日志
    }

    public static void main(String[] args) {
        encrypt demo = new encrypt();
        // hookZz hook function
//        demo.hookEncryptWallEncode();
        // hookZz inline hook
//        demo.inlinehookEncryptWallEncode();

        // hook by unicorn origin api
        demo.hookByUnicorn();
        // 初始化函数
        demo.native_init();
        // 目标函数
        demo.encryptDemo();

    }

    public void native_init(){
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv()); // 第一个参数是env
        list.add(0); // 第二个参数，实例方法是jobject，静态方法是jclass，直接填0，一般用不到。
        DvmObject<?> context = vm.resolveClass("android/content/Context").newObject(null); // context
        list.add(vm.addLocalObject(context));
        module.callFunction(emulator, 0x9565, list.toArray());
    };


    public void encryptDemo(){
        List<Object> list = new ArrayList<>(10);
        // arg1 env
        list.add(vm.getJNIEnv());
        // arg2 jobject/jclass 一般用不到 可以直接填0
        list.add(0);
        String str = "http://app.weixin.sogou.com/api/searchapp";
        String str2 = "type=2&ie=utf8&page=1&query=%E5%A5%8B%E9%A3%9E%E5%AE%89%E5%85%A8&select_count=1&tsn=1&usip=";
        String str3 = "lilac";
        list.add(vm.addLocalObject(new StringObject(vm,str)));
        list.add(vm.addLocalObject(new StringObject(vm,str2)));
        list.add(vm.addLocalObject(new StringObject(vm,str3)));
        Number number = module.callFunction(emulator,0x9ca1,list.toArray())[0];
        String result = vm.getObject(number.intValue()).getValue().toString();
        System.out.println(result);
    }

    // HookZz hook Sc_EncryptWallEncode
    public void hookEncryptWallEncode(){
        // 获取HookZz对象
        IHookZz hookZz = HookZz.getInstance(emulator); // 加载HookZz，支持inline hook，文档看https://github.com/jmpews/HookZz
        // enable hook
        hookZz.enable_arm_arm64_b_branch(); // 测试enable_arm_arm64_b_branch
        hookZz.wrap(module.base + 0xA284 + 1, new WrapCallback<HookZzArm32RegisterContext>() {
            Pointer buffer;
            @Override
            // 方法执行前
            public void preCall(Emulator<?> emulator, HookZzArm32RegisterContext ctx, HookEntryInfo info) {
                System.out.println("HookZz hook EncryptWallEncode");
                Pointer input1 = ctx.getPointerArg(0);
                Pointer input2 = ctx.getPointerArg(1);
                Pointer input3 = ctx.getPointerArg(2);
                // getString的参数i代表index,即input[i:]
                System.out.println("参数1："+input1.getString(0));
                System.out.println("参数2："+input2.getString(0));
                System.out.println("参数3："+input3.getString(0));

                buffer = ctx.getPointerArg(3);
            };

            @Override
            // 方法执行后
            public void postCall(Emulator<?> emulator, HookZzArm32RegisterContext ctx, HookEntryInfo info) {
                // getByteArray参数1是起始index，参数2是长度，我们不知道结果多长，就先设置0x100吧
                byte[] outputhex = buffer.getByteArray(0, 0x100);
                Inspector.inspect(outputhex, "EncryptWallEncode output");
            }
        });
        hookZz.disable_arm_arm64_b_branch();

    }

    public void inlinehookEncryptWallEncode(){
        IHookZz hookZz = HookZz.getInstance(emulator);
        hookZz.enable_arm_arm64_b_branch();

        hookZz.instrument(module.base + 0x9d24 + 1, new InstrumentCallback<Arm32RegisterContext>() {
            @Override
            public void dbiCall(Emulator<?> emulator, Arm32RegisterContext ctx, HookEntryInfo info) {
                System.out.println("HookZz inline hook EncryptWallEncode");
                Pointer input1 = ctx.getPointerArg(0);
                Pointer input2 = ctx.getPointerArg(1);
                Pointer input3 = ctx.getPointerArg(2);
                // getString的参数i代表index,即input[i:]
                System.out.println("参数1："+input1.getString(0));
                System.out.println("参数2："+input2.getString(0));
                System.out.println("参数3："+input3.getString(0));

                buffer = ctx.getPointerArg(3);
            }
        });

        hookZz.instrument(module.base + 0x9d28 + 1, new InstrumentCallback<Arm32RegisterContext>() {
            @Override
            public void dbiCall(Emulator<?> emulator, Arm32RegisterContext ctx, HookEntryInfo info) {
                Inspector.inspect(buffer.getByteArray(0,0x100), "inline hook EncryptWallEncode");
            }
        });
    }

    public void hookByUnicorn(){
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void onAttach(Unicorn.UnHook unHook) {

            }

            @Override
            public void detach() {

            }

            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                if(address == (module.base+0x9d24)){
                    System.out.println("Hook By Unicorn");
                    RegisterContext ctx = emulator.getContext();
                    Pointer input1 = ctx.getPointerArg(0);
                    Pointer input2 = ctx.getPointerArg(1);
                    Pointer input3 = ctx.getPointerArg(2);
                    // getString的参数i代表index,即input[i:]
                    System.out.println("参数1："+input1.getString(0));
                    System.out.println("参数2："+input2.getString(0));
                    System.out.println("参数3："+input3.getString(0));

                    buffer = ctx.getPointerArg(3);
                }
                if(address == (module.base+0x9d28)){
                    Inspector.inspect(buffer.getByteArray(0,0x100), "Unicorn hook EncryptWallEncode");
                }


            }
        },module.base + 0x9d24, module.base + 0x9d28, null);
    }
}
```

需要注意的是，基于原生Unicorn API进行Hook时，不需要管thumb arm的地址转换，即不需要考虑+1。

可以发现，原生的办法进行Hook，代码量不小，但很多时候，我们会选择它，因为HookZz等工具有时候会遇到BUG，而且使用HookZz等hook框架时，样本可以较容易的检测到自身代码片段被Hook，而Unicorn原生的Hook不容易被检测，相当于是CPU自身在打印寄存器。我们日后会用样本阐述这一观点。

我们已经演示了两种Hook方式了，可以发现，不管是HookZz 还是 Unicorn自带API，Hook起来都不算简单，比较起来Frida是最优雅简洁的，那为什么要用Unidbg做算法欢迎呢？一般原因都是因为Console Debugger——简洁、快速、精准打击。

Console debugger 怎么用呢，来看一下吧

```java
public void HookByConsoleDebugger(){
    Debugger debugger = emulator.attach();
    debugger.addBreakPoint(module.base+0x9d24);
    debugger.addBreakPoint(module.base+0x9d28);
}
```

![image-20210801215020076](pic\9.png)

运行，需要注意的是，基于Unicorn的Console Debugger同样不用因为thumb模式+1，会自己做转换。

![image-20210801215121674](pic\10.png)

运行代码后首先断在了9d24上，0x40000000是Unidbg中第一个自定义加载SO的基地址。接下来开始调试

![image-20210801215638123](pic\11.png)

首先看一下Console Debugger支持的所有命令

> c: continue
> n: step over
> bt: back trace
>
> st hex: search stack
> shw hex: search writable heap
> shr hex: search readable heap
> shx hex: search executable heap
>
> nb: break at next block
> s|si: step into
> s[decimal]: execute specified amount instruction
> s(blx): execute util BLX mnemonic, low performance
>
> m(op) [size]: show memory, default size is 0x70, size may hex or decimal
> mr0-mr7, mfp, mip, msp [size]: show memory of specified register
> m(address) [size]: show memory of specified address, address must start with 0x
>
> wr0-wr7, wfp, wip, wsp <value>: write specified register
> wb(address), ws(address), wi(address) <value>: write (byte, short, integer) memory of specified address, address must start with 0x
> wx(address) <hex>: write bytes to memory at specified address, address must start with 0x
>
> b(address): add temporarily breakpoint, address must start with 0x, can be module offset
> b: add breakpoint of register PC
> r: remove breakpoint of register PC
> blr: add temporarily breakpoint of register LR
>
> p (assembly): patch assembly at PC address
> where: show java stack trace
>
> trace [begin end]: Set trace instructions
> traceRead [begin end]: Set trace memory read
> traceWrite [begin end]: Set trace memory write
> vm: view loaded modules
> vbs: view breakpoints
> d|dis: show disassemble
> d(0x): show disassemble at specify address
> stop: stop emulation
> run [arg]: run test
> cc size: convert asm from 0x40009d24 - 0x40009d24 + size bytes to c function

我们想要打印寄存器R0,R1,R2所指向的内存

![image-20210801220553077](pic\12.png)

![image-20210801220617801](pic\13.png)

我们顺带需要记一下此时R3的地址，r3=0x40203000，因为Unidbg中没设计ALSR（地址随机化），所以参数4总是会被分配到这块内存中去，运行一万次也不会变。（没ALSR也是Unidbg对抗中一个检测的点，大部分真实用户都不会关掉地址随机化）

![image-20210801220859529](pic\14.png)



c命令代表contine，进入了我们的下一个断点，查看此时的0x40203000

![image-20210801220937233](pic\15.png)

结果验证成功。

通过Console Debugger 进行Hook 验证，一小时可以稳定准确的验证上百个Hook点，敏捷且稳定，远不是Frida或者IDA所能比拟的。



##### 3.2 算法分析

我们确定Sc_EncryptWallEncode就是主函数，进去看看

![image-20210801221218829](pic\16.png)

顺嘴一提，这里的byte_3a0c0如果不为1，就直接不执行具体逻辑，这是啥意思？byte_3a0c0，按照IDA的命名约定，就是0x3a0c0位置处的一个字节。

![image-20210801221432933](pic\17.png)

跳转到这里看，发现是在bss节里，bss节里的数据都是未初始化的数据，在函数运行中才初始化。

我们通过traceWrite 监控对这个字节的读写

![image-20210801221807693](pic\18.png)

traceWrite 同样是对Unidbg内存读写监控API的封装，traceWrite & traceRead 是 Unidbg适合算法还原的另外一半原因，它可以很好的对付OLLVM后的样本。我们会在之后的样本中重度依赖和使用它。

运行代码，在日志中搜索3a0c0

![image-20210801221947580](pic\19.png)

赋值为1，发生在目标SO中0xa27a地址处，IDA中按G跳转到该地址

![image-20210801222054490](pic\20.png)

怎么验证它是在什么情况下发生的呢？或者朴素点说，调用它的是谁，我们可以像在Frida中一样查看调用栈吗？

```javascript
// Frida 查看native堆栈
console.log(Thread.backtrace(this.context, Backtracer.ACCURATE).map(DebugSymbol.fromAddress).join('\n'))
```

当然是可以的，我们使用Console Debugger Hook 这个位置

![image-20210801222532298](pic\21.png)

我们发现，在此处最先断下来了，说明这儿时机很早，**bt** 查看调用栈

![image-20210801222627451](pic\22.png)

可以发现，发生在init中，即初始化函数中，跳过去看看，0x9a61

![image-20210801222811600](pic\23.png)

这不就清晰了吗？init中做了签名校验，如果正确，那就将这个“开关”赋值1，如果签名校验失败，或者没用运行这个init函数，开关就是0，两种情况下都没法执行我们的目标函数。

小插曲结束，接下来分析算法

![image-20210801223039140](pic\24.png)

这三个函数都需要我们重视，我想，或许我们可以到最后的函数中瞧一瞧？因为结果大概率是在最后算出来，对吧。

得益于符号没去，我们可以清晰的看到密密麻麻的算法

![image-20210801223744026](pic\25.png)

它们是RSA、Base64，以及AES。

![image-20210801223839795](pic\26.png)

计算出来之后开始拼接成最终结果，k、v、u。在模拟执行的结果中，我们发现了其他的字段，在哪里呢？继续往下看

![image-20210801224011904](pic\27.png)

如果v45存在，就在a5后面拼接一些什么东西，如果v51存在，就继续在后面拼接东西，这是什么呢？

不要发呆，把这些数字转成char试试

![image-20210801224131294](pic\28.png)

转换之后

![image-20210801224210679](pic\29.png)

是不是就能把握这个拼接的感觉了呢？

接下来我只做四件事

- 分析EncryptHttpRequest3的入参
- 分析此处RSA
- 分析此处Base64
- 分析此处AES

首先第一个

EncryptHttpRequest3 有九个参数，根据ATPCS调用约定，后五个参数在栈中，我们该怎么在Console Debugger中看后面五个值是啥呢？来一起看一下！

```java
    public void HookByConsoleDebugger(){
        Debugger debugger = emulator.attach();
//        debugger.addBreakPoint(module.base+0x9d24);
//        debugger.addBreakPoint(module.base+0x9d28);

        // hook 0xa27a,此处修改了“开关”
//        debugger.addBreakPoint(module.base+0xa27a);

        // 0xb300 是EncryptHttpRequest3 的地址
        debugger.addBreakPoint(module.base+0xb300);
    }
```

![image-20210801224534872](pic\30.png)

R0-R3代表参数1234，R3后面那些可不是参数。

r3是5，r0-r2是指针，试着打印一下内存吧

![image-20210801224703434](pic\31.png)

好吧，就是我们的入参，r3即参数4值为5，也很容易让人想到是参数3字符串的长度，可以修改传入参数3的值验证这一点。

后面的参数怎么看？首先查看堆栈SP寄存器的内存

![image-20210801224915089](pic\32.png)

每四个字节就代表栈中的一个值，且为小端序

所以参数5就是0x40228000

参数6即0x40203000

参数7是0x40218040

参数8是0

参数9是0x40

我们再看看参数456的值，因为看着是指针

![image-20210801225108279](pic\33.png)

看起来参数5是buffer

![image-20210801225146248](pic\34.png)

参数6是什么？看不出来是什么，可能是CPU信息，也可能是分配的一块内存，但还没有做初始化，导致上面有垃圾数据

![image-20210801225358716](pic\35.png)

参数7也看不出啥。

我们这里不深究这些入参到底是啥，主要是学会如何通过堆栈查看第五个参数以及后面的参数。

![image-20210801225502374](pic\36.png)

看看这个RSA

![image-20210801225709223](pic\37.png)

10001是RSA常用的指数，有些地方也写成“AQAB”，那么此处另外一个大数应该就是模数了，有了模数和指数，即得到了公钥，然后用公钥完成加密，这里就不多说了。

再看看Base64

![image-20210801230050896](pic\38.png)

b372，就Hook这里吧，分析一下是否是标准Base64

![image-20210801230220373](pic\39.png)

参数2是0x80，一般而言是明文长度

![image-20210801230356841](pic\40.png)

![image-20210801230835624](pic\41.png)

将这个结果在cyberchef中解一下base64

![image-20210801230954652](pic\42.png)

结果正是base64函数的入参1



最后看一下AES

![image-20210801231045638](pic\43.png)

一个AES算法我们要关注哪些内容？（此部分要求对AES有了解，可以等待星球里八月份的密码学实战专题）

- 是AES -128 还是 AES -192 还是 AES -256
- 明文是什么
- 密钥是什么
- 什么模式，ECB还是CBC还是其他
- 如果是非ECB模式，那么IV是什么
- 明文填充模式呢，是PCKS#7吗？

SetEncKeySym像是密钥编排函数，Hook一下

![image-20210801231558419](pic\46.png)

Console Debugger Hook 114b2

![image-20210801231736377](pic\47.png)

参数1是buffer，参数2有内容，参数3是0x100。

这儿需要对AES算法的编程实现有所了解，可以进入这个函数具体看看

![image-20210801232102174](pic\48.png)

a2即外层的参数3，0x100即256，这儿指的是采用AES-256模式，而在AES 256中，密钥是32字节，所以参数1的32个字节就是密钥。

继续往下看，明文做了Padding，同样Hook验证，发现是标准的PKCS#7填充。

![image-20210801231135434](pic\44.png)

进入这个最终函数看看

![image-20210801231202939](pic\45.png)

符号表明是CBC模式，使用Console Debugger Hook一下这个函数，查看它的6个参数

![image-20210801232452115](C:\Users\pr0214\AppData\Roaming\Typora\typora-user-images\image-20210801232452115.png)

第一个是啥？是明文，因为第三行的后八个字节都是0x8，这是被PKCS#7填充后的标志鸭，所以它是明文

参数2是buffer，可能用来装结果

参数3是0x30，即填充后明文的长度

参数4是种子密钥，即密钥编排后的结果，更严谨的说，这里是查表法实现的AES其种子密钥

![image-20210801232740074](pic\49.png)

通过堆栈查看参数5和6

![image-20210801232916861](pic\50.png)

参数5是啥？暂且看不出

参数6不是地址，是值，为1

一般而言，加密中有个1呀0呀什么的，都表示模式，加密or解密。

回顾一下，AES所需要的那些参数，我们知道了吗？

- 是AES -128 还是 AES -192 还是 AES -256 √
- 明文是什么 √
- 密钥是什么 √
- 什么模式，ECB还是CBC还是其他 √
- 如果是非ECB模式，那么IV是什么 
- 明文填充模式呢，是PCKS#7吗？√

只有IV不知道了，对于CBC模式下的IV，在具体运算中，它会首先与密文发生异或

![image-20210801233213453](pic\51.png)

参数6即是IV，AES 的IV为十六个字节长度噢。

现在我们已经分析出了这个AES算法，如果你也真正熟悉AES算法，就可以半小时内搞清楚它，否则可能头撞南墙。

在写这篇文章的时候我深刻的意识到这一点，所以如果一部分朋友看的有点迷糊甚至着急，没关系，等星球里的密码学实战专栏吧，它是我们八月的主要目标之一。

对目标函数的分析还有许多未完成的工作，感兴趣的可以继续研究。

大家加油！



