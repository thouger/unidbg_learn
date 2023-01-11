


Java.perform(function() {

    console.log(123);
    var StatisticsReportUtil = Java.use('cn.thecover.lib.common.utils.LogShutDown');
    // StatisticsReportUtil.getSign.implementation = function() {
    //     var res = this.getSign();
    //     console.log("res->sign", res)
    //     return res;
    // }
    var res = StatisticsReportUtil.getAppSign();
    console.log("getSign=", res)




    var signKeyV1Cls = Java.use('com.sichuanol.cbgc.util.SignManager');
    signKeyV1Cls.getSign.implementation = function(str1, str2, str3) {
        var retval = this.getSign(str1, str2, str3);
        console.log("str1", str1)
        console.log("str2", str2)
        console.log("str3", str3)
        console.log("res", retval)
        return retval;
    }

});