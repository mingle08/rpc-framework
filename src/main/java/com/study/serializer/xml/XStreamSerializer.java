package com.study.serializer.xml;

import com.study.serializer.ISerializer;
import com.thoughtworks.xstream.XStream;

public class XStreamSerializer implements ISerializer {
    // init XStream Object
    private static final XStream xStream = new XStream();

    @Override
    public <T> byte[] serialize(T obj) {
        return xStream.toXML(obj).getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        String xml = new String(data);
        return (T) xStream.fromXML(xml);
    }
}
