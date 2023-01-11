var p_pthread_create = Module.findExportByName("libc.so", "pthread_create");
var pthread_create = new NativeFunction( p_pthread_create, "int", ["pointer", "pointer", "pointer", "pointer"]);
Interceptor.replace( p_pthread_create, new NativeCallback(function (ptr0, ptr1, ptr2, ptr3) {
    console.log("pthread_create() overloaded");
    var ret = ptr(0);
    var base_addr;
    try {
        base_addr = Module.getBaseAddress('libvdog.so');
    } catch(error) {
    }

    var funcAddress = ptr(0);
    if(base_addr != null){
        funcAddress = ptr2.sub(base_addr);
    }
    if(funcAddress.equals(0x449d5) || funcAddress.equals(0x43221)){
        return -1;
    } else {
        ret = pthread_create(ptr0,ptr1,ptr2,ptr3);
        return ret;
    }

}, "int", ["pointer", "pointer", "pointer", "pointer"]));

