package com.hzxy.bean;

import java.util.Objects;

public class Province {
    private Integer id;
    private String name;
    private String jiancheng;
    private String shenghui;

    public Province() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Province)) return false;
        Province province = (Province) o;
        return Objects.equals(getId(), province.getId()) &&
                Objects.equals(getName(), province.getName()) &&
                Objects.equals(getJiancheng(), province.getJiancheng()) &&
                Objects.equals(getShenghui(), province.getShenghui());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getJiancheng(), getShenghui());
    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jiancheng='" + jiancheng + '\'' +
                ", shenghui='" + shenghui + '\'' +
                '}';
    }

    public Province(Integer id, String name, String jiancheng, String shenghui) {
        this.id = id;
        this.name = name;
        this.jiancheng = jiancheng;
        this.shenghui = shenghui;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJiancheng() {
        return jiancheng;
    }

    public void setJiancheng(String jiancheng) {
        this.jiancheng = jiancheng;
    }

    public String getShenghui() {
        return shenghui;
    }

    public void setShenghui(String shenghui) {
        this.shenghui = shenghui;
    }
}
