package com.iraianbu.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Client {
    public static void main(String[] args) {
        try {
            singletonExample();
            //serializationExample();
            //reflectionExample();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void singletonExample() {
        LazySingletonCache lazySingletonCache = LazySingletonCache.getInstance();
        lazySingletonCache.put("A", 65);
        lazySingletonCache.put("B", 66);
        // Verify that only one instance is created
        LazySingletonCache lazySingletonCache1 = LazySingletonCache.getInstance();
        lazySingletonCache1.put("C", 67);
        System.out.println(
                lazySingletonCache == lazySingletonCache1 ?
                        "Both References to the Same Instances" :
                        "Different Instances Exist! Violates Singleton Pattern"
        );

    }

    private static void reflectionExample() throws InstantiationException,IllegalAccessException, InvocationTargetException {
        Constructor[] constructors = LazySingletonCache.class.getDeclaredConstructors();
        //Knowing only one constructor taking it using index
        Constructor constructor = constructors[0];
        constructor.setAccessible(true);
        LazySingletonCache lazySingleton = (LazySingletonCache) constructor.newInstance();
        LazySingletonCache instance = LazySingletonCache.getInstance();
        System.out.println(lazySingleton.hashCode() == instance.hashCode() ?
                "Both References to the Same Instances" :
                "Different Instances Exist! Violates Singleton Pattern"
        );

        // To resolve the violation
        EnumSingletonCache.INSTANCE.doSomething();
    }

    private static void serializationExample() throws IOException,ClassNotFoundException {
        LazySingletonCache lazySingletonCache = LazySingletonCache.getInstance();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("object-1.txt")
        );
        objectOutputStream.writeObject(lazySingletonCache);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream("object-1.txt")
        );
        LazySingletonCache deserializedObject = (LazySingletonCache) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println( lazySingletonCache.hashCode() == deserializedObject.hashCode() ?
                "Both have same hashcode" : "Different Hashcode! Violates Singleton Pattern"
        );

        // To resolve the violation
        SerializableCache serializableCache = SerializableCache.getInstance();
        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(
                new FileOutputStream("object-2.obj")
        );
        objectOutputStream1.writeObject(serializableCache);
        objectOutputStream1.close();

        ObjectInputStream objectInputStream1 = new ObjectInputStream(
                new FileInputStream("object-2.obj")
        );
        SerializableCache deserializedObject1 = (SerializableCache) objectInputStream1.readObject();
        objectInputStream1.close();

        System.out.println( serializableCache.hashCode() == deserializedObject1.hashCode() ?
                "After resolving the deserialization issue\nBoth have same hashcode" : "Different Hashcode! Violates Singleton Pattern"
        );
    }
}