package com.enums;

/**
 * 审核类型枚举
 */
public enum AuditType {
    MP4_IMPORT("01", "视频上传")
    ;
    
    private final String code;
    private final String name;

    AuditType(String code, String name) {
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