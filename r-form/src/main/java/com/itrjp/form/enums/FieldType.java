package com.itrjp.form.enums;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2023/8/1 10:48
 */
public enum FieldType {
    RADIO(1, "单选", "radio"),
    SELECT(2, "多选", "select"),


    INPUT(3, "填空", "input"),
    SCORE(4, "打分", "score"),
    IMAGE(5, "图片", "image"),
    DATE(6, "日期", "date");

    private final int id;
    private final String name;
    private final String code;

    FieldType(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
