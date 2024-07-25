package com.iraianbu.singleton;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LazySingletonCache implements Cache<Object,Object>, Serializable {
    // To Store Cache Values
    private final Map<Object,Object> map;

    // Static Instance
    private static LazySingletonCache instance = null;

    // Private Constructor to prevent instantiation
    private LazySingletonCache(){
        map = new HashMap<>();
    }

    public static LazySingletonCache getInstance(){
        if(instance == null){
            instance = new LazySingletonCache();
        }
    return instance;
    }

    @Override
    public void put(Object key, Object value) {
        map.put(key, value);
    }

    @Override
    public Object get(Object key) {
        return map.get(key);
    }
}
