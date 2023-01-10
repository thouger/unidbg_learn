package com.sina.weibo.sdk.api.share;

public interface IWeiboHandler {

    public interface Request {
        void onRequest(BaseRequest baseRequest);
    }

    public interface Response {
        void onResponse(BaseResponse baseResponse);
    }
}
