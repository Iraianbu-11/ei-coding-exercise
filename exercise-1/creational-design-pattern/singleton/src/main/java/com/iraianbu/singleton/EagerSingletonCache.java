package com.iraianbu.singleton;

import java.util.HashMap;
import java.util.Map;

public class EagerSingletonCache implements Cache<Object,Object>{
    // To Store Cache Values
    private final Map<Object,Object> map;

    // Static Instance
    private static final EagerSingletonCache instance = new EagerSingletonCache();

    // Private Constructor to prevent instantiation
    private EagerSingletonCache(){
        map = new HashMap<>();
    }

    public static EagerSingletonCache getInstance() {
        return instance;
    }
    @Override
    public void put(Object key, Object value) {
        map.put(key,value);
    }

    @Override
    public Object get(Object key) {
        return map.get(key);
    }
}
