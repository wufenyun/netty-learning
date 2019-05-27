package com.learning.netty.codec2;

/**
 * @description:
 * @author: wufenyun
 * @date: 2018-07-04 11
 **/
public class Request {
    private long id;
    //1.rpc请求；2.心跳请求；3.ping请求
    private byte messageType;
    //数据包请求体长度，不包含头部长度
    private int length;
    //请求体
    private Object data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte getMessageType() {
        return messageType;
    }

    public void setMessageType(byte messageType) {
        this.messageType = messageType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}