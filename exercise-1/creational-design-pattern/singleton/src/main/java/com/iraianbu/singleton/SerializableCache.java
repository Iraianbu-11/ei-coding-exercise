package com.iraianbu.singleton;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *  During deserialization, it will create the new object every time.
 *  This will violate the Singleton design pattern
 */

public class SerializableCache implements Serializable,Cache<Object,Object>  {


    // To Store Cache Values
    private final Map<Object,Object> map;

    // Static Instance
    private static SerializableCache instance = null;

    // Private Constructor to prevent instantiation
    private SerializableCache(){
        map = new HashMap<>();
    }

    public static SerializableCache getInstance() {
        if(instance == null){
            instance = new SerializableCache();
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

    /**
     * This method will help us to return the already created object
     * rather than creating new object
     */

    protected Object readResolve(){
        return instance;
    }
}
