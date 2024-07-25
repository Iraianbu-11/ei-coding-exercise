package com.iraianbu.singleton;

import java.util.HashMap;
import java.util.Map;
public enum EnumSingletonCache implements Cache<Object,Object>{
    INSTANCE;
    private final Map<Object,Object> map = new HashMap<>();
    @Override
    public void put(Object key, Object value) {
        map.put(key,value);
    }

    public void doSomething(){
        System.out.println("Enum Method Called");
    }

    @Override
    public Object get(Object key) {
        return map.get(key);
    }
}
