package com.study.serializer;

import com.study.enums.SerializeType;
import com.study.serializer.json.FastJsonSerializer;
import com.study.serializer.json.JacksonJSONSerializer;
import com.study.serializer.xml.XStreamSerializer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SerializeEngine {

    public static final Map<SerializeType, ISerializer> serializeMap = new ConcurrentHashMap<>();

    // register serialize tool
    static {
        serializeMap.put(SerializeType.DefaultJavaSerializer, new DefaultJavaSerializer());
        serializeMap.put(SerializeType.HessianSerializer, new HessianSerializer());
        serializeMap.put(SerializeType.JacksonJSONSerializer, new JacksonJSONSerializer());
        serializeMap.put(SerializeType.FastJsonSerializer, new FastJsonSerializer());
        serializeMap.put(SerializeType.XStreamSerializer, new XStreamSerializer());
        serializeMap.put(SerializeType.MarshallingSerializer, new MarshallingSerializer());
    }

    /**
     * serialize
     */


    /**
     * deserialize
     */
}
