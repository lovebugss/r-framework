package com.itrjp.cloud.demo.common.pojo.vo;


public class DemoVo {
    private String value;

    public DemoVo(String number) {
        this.value = number;
    }

    public DemoVo() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DemoVo{" +
                "value='" + value + '\'' +
                '}';
    }
}
