# Use Case: Singleton Design Pattern for Caching

## Overview
This use case demonstrates the Singleton design pattern by implementing a simple caching mechanism.
## Problem Statement
In many applications, caching frequently accessed data can significantly improve performance by reducing the number of expensive 
operations such as database queries. To ensure that the cache is consistent and does not create multiple instances, a Singleton design pattern is used to manage a single cache instance.
## Design Pattern

### Pattern Name
Singleton 
### Pattern Description
The central idea behind this design pattern is to ensure that only a single instance of a class
is created in the entire application scope
## Solution
[LazySingleton.java](exercise-1/creational-design-pattern/singleton/src/main/java/com/iraianbu/singleton/LazySingletonCache.java)

[EagerSingleton.java](exercise-1/creational-design-pattern/singleton/src/main/java/com/iraianbu/singleton/EagerSingletonCache.java)

[MultiThreadCache.java](exercise-1/creational-design-pattern/singleton/src/main/java/com/iraianbu/singleton/MultiThreadCache.java)

[SerializableCache.java](exercise-1/creational-design-pattern/singleton/src/main/java/com/iraianbu/singleton/SerializableCache.java)

[EnumSingletonCache.java](exercise-1/creational-design-pattern/singleton/src/main/java/com/iraianbu/singleton/EnumSingletonCache.java.java)
## Conclusion



