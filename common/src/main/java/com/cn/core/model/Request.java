package com.cn.core.model;

/**
 * 请求对象
 * @author liyahui
 * @create 2019-06-08
 */
public class Request {
    /**模块号*/
    private short module;
    /**命令号*/
    private short cmd;
    /**数据*/
    private byte[] data;

    public short getModule() {
        return module;
    }

    public void setModule(short module) {
        this.module = module;
    }

    public short getCmd() {
        return cmd;
    }

    public void setCmd(short cmd) {
        this.cmd = cmd;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public static Request valueOf(short module, short cmd, byte[] data) {
        Request request = new Request();
        request.setModule(module);
        request.setCmd(cmd);
        request.setData(data);
        return request;
    }

}
