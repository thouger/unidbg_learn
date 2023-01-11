#include <jni.h>
#include <string>
#include <sys/resource.h>

extern "C" JNIEXPORT jint JNICALL
Java_com_roysue_getrusage_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {

    struct rusage usage;
    getrusage(RUSAGE_SELF, &usage);
    int sec = usage.ru_utime.tv_sec;
    return sec;
}