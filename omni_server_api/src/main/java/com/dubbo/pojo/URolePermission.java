package com.dubbo.pojo;

import java.io.Serializable;

public class URolePermission implements Serializable {
    private Long rid;

    private Long pid;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}