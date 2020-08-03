package com.leyou.item.bo;

import com.leyou.item.pojo.Spu;

/**
 * @author kpwang
 * @create 2020-08-03 15:13
 */
public class SpuBo extends Spu {
    private String cname;
    private String bname;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    @Override
    public String toString() {
        return "SpuBo{" +
                "cname='" + cname + '\'' +
                ", bname='" + bname + '\'' +
                '}';
    }
}
