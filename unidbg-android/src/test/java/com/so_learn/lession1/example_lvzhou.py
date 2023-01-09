import posixpath
from androidemu.emulator import Emulator, logger
from androidemu.java.classes.string import String

# Initialize emulator
emulator = Emulator(
    vfp_inst_set=True,
    vfs_root=posixpath.join(posixpath.dirname(__file__), "vfs")
)

# 加载SO
lib_module = emulator.load_library("tests/bin/liboasiscore.so")

# find My module
for module in emulator.modules:
    if "liboasiscore" in module.filename:
        base_address = module.base
        logger.info("base_address=> 0x%08x - %s" % (module.base, module.filename))
        break

# run jni onload
emulator.call_symbol(lib_module, 'JNI_OnLoad', emulator.java_vm.address_ptr, 0x00)
# 准备参数
a1 = "aid=01A-khBWIm48A079Pz_DMW6PyZR8uyTumcCNm4e8awxyC2ANU.&cfrom=28B5295010&cuid=5999578300&noncestr=46274W9279Hr1X49A5X058z7ZVz024&platform=ANDROID&timestamp=1621437643609&ua=Xiaomi-MIX2S__oasis__3.5.8__Android__Android10&version=3.5.8&vid=1019013594003&wm=20004_90024"
# 通过地址直接调用
result = emulator.call_native(module.base + 0xC364 + 1, emulator.java_vm.jni_env.address_ptr, 0x00, String(a1).getBytes(emulator, String("utf-8")), 0)
# 打印结果
print("result:"+ result._String__str)
