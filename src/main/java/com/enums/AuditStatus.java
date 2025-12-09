package com.enums;

/**
 * 审核状态枚举
 */
public enum AuditStatus {
    AWAIT("0", "待审核"),
    REJECT("1", "审核驳回"),
    PASS("2", "审核通过"),
    ;

    private final String code;
    private final String name;

    AuditStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
    
}