package com.example.evara;

public class UpdateAccountResponse {
    private boolean status;
    private String errNum , msg;

    public boolean isStatus() {
        return status;
    }

    public String getErrNum() {
        return errNum;
    }

    public String getMsg() {
        return msg;
    }
}
