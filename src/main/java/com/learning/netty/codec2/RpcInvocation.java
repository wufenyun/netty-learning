package com.learning.netty.codec2;

import java.io.Serializable;

/**
 * @description:
 * @author: wufenyun
 * @date: 2018-07-04 16
 **/
public class RpcInvocation implements Serializable {

    private String api;
    private String args;


    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    @Override
    public String toString() {
        return "RpcInvocation{" +
                "api='" + api + '\'' +
                ", args='" + args + '\'' +
                '}';
    }

}