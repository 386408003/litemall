package org.linlinjava.litemall.db.util;

public enum SystemType {
    LITEMALL(1),
    TIANYU(2);

    private Integer type;

    SystemType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }
}
