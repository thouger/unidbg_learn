//package com.so_learn;
//
//private void printArgs(String signature, VaList vaList) {
//    log.info("------");
//    DvmObject<?> obj;
//    int hash;
//    try {
//        for (int i = 0; i < 10; i++) {
//            hash = vaList.getIntArg(i);
//            obj = vm.getObject(hash);
//            if (Objects.isNull(obj)) {
//                log.info("signature:{},参数:{},值:{}", signature, i, hash);
//            } else {
//                log.info("signature:{},参数:{},类型:{},值:{}", signature, i,
//                        obj.getObjectType().getClassName(), obj.getValue());
//            }
//        }
//    } catch (Exception ignored) {
//    }
//}
//
//@Override
//public DvmObject<?> callStaticObjectMethodV(BaseVM vm, DvmClass dvmClass, String signature, VaList vaList) {
//        printArgs(signature, vaList);
//        // 其他逻辑
//}
//
