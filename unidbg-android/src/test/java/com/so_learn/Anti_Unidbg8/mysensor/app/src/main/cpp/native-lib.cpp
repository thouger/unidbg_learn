#include <jni.h>
#include <string>
#include <android/sensor.h>
#include <android/asset_manager.h>
#include <android/log.h> // 日志

#define TAG "lilac"
// 定义info信息
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_mysensor_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {

    ASensorList list;

    // 初始化传感器管理器
    ASensorManager * mpSensorManager = ASensorManager_getInstance();

    int count = ASensorManager_getSensorList(mpSensorManager, &list);
    for(int i=0;i<count;i++){
        int type = ASensor_getType(list[i]);
        const ASensor* sensor = list[i];
        // Returns this sensor's name (non localized)
        const char* name = ASensor_getName(sensor);
        // Returns this sensor's vendor's(供应商) name (non localized)
        const char* vendor = ASensor_getVendor(sensor);
        // Returns this sensors's resolution.(分辨率)
        float resolution = ASensor_getResolution(sensor);
        LOGI("%s (%s) %d %f", name, vendor, type, resolution);
    }


    return env->NewStringUTF("");
}