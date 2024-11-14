package com.hooniegit.Xtream.Modules.Data;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.util.EnumMap;

public class KryoData {
    private final EnumMap<Type, byte[]> map = new EnumMap<>(Type.class);
    private final Kryo kryo = new Kryo();

    public KryoData() {
        kryo.setRegistrationRequired(false);
    }

    // 객체를 저장할 때 Kryo로 직렬화하여 저장
    public <T> void set(Type type, T value) {
        Output output = new Output(1024, -1); // 초기 버퍼 크기를 지정하고, 필요에 따라 확장
        kryo.writeClassAndObject(output, value);
        map.put(type, output.toBytes());
        output.close();
    }

    // 객체를 가져올 때 Kryo로 역직렬화하여 타입 캐스팅 없이 반환
    public <T> T get(Type type) {
        byte[] bytes = map.get(type);
        if (bytes != null) {
            Input input = new Input(bytes);
            @SuppressWarnings("unchecked")
            T value = (T) kryo.readClassAndObject(input);
            input.close();
            return value;
        }
        return null;
    }
}

