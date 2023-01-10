package com.vivo.push.ups;

public class TokenResult extends CodeResult {
    String token;

    public TokenResult(int i, String str) {
        super(i);
        this.token = str;
    }

    public String getToken() {
        return this.token;
    }
}
