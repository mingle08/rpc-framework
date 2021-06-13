package com.study.serializer;

import org.jboss.marshalling.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class MarshallingSerializer implements ISerializer {

    final static MarshallingConfiguration config = new MarshallingConfiguration();
    // get serialize factory
    final static MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");

    static {
        config.setVersion(5);
    }

    @Override
    public <T> byte[] serialize(T obj) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            final Marshaller marshaller = marshallerFactory.createMarshaller(config);
            marshaller.start(Marshalling.createByteOutput(byteArrayOutputStream));
            marshaller.writeObject(obj);
            marshaller.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {

        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            final Unmarshaller unmarshaller = marshallerFactory.createUnmarshaller(config);
            unmarshaller.start(Marshalling.createByteInput(byteArrayInputStream));
            Object obj = unmarshaller.readObject();
            unmarshaller.finish();
            return (T) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
