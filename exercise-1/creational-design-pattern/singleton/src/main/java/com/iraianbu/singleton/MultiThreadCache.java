package com.iraianbu.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/*
* This will be suitable for Multi threads application
*/
public class MultiThreadCache implements Cache<Object, Object> {

    // To store the cache values
    private final Map<Object,Object> map;

    // Static Instance
    private static MultiThreadCache instance = null;

    // Private Constructor to prevent instantiation
    private MultiThreadCache(){
        map = new ConcurrentHashMap<>();
    }

    public static MultiThreadCache getInstance() {
        if(instance == null){
            synchronized (MultiThreadCache.class){
                if(instance == null){
                    instance = new MultiThreadCache();
                }
            }
        }
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
