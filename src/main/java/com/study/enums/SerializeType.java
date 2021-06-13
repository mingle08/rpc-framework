package com.study.enums;

import org.apache.commons.lang3.StringUtils;

public enum SerializeType {
    DefaultJavaSerializer("DefaultJavaSerializer"),
    JacksonJSONSerializer("JacksonJSONSerializer"),
    FastJsonSerializer("FastJsonSerializer"),
    HessianSerializer("HessianSerializer"),
    XStreamSerializer("XStreamSerializer"),
    MarshallingSerializer("MarshallingSerializer");

    private String serializeType;

    SerializeType(String serializerType) {
        this.serializeType = serializerType;
    }

    public String getSerializeType() {
        return serializeType;
    }

    public static SerializeType queryByType(String serializerType) {
        if (StringUtils.isBlank(serializerType)) {
            return null;
        }

        for (SerializeType serializeType : SerializeType.values()) {
            if (StringUtils.equals(serializerType, serializeType.getSerializeType())) {
                return serializeType;
            }
        }
        return null;
    }
}
