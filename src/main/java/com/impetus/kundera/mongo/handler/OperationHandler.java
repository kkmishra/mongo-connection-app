package com.impetus.kundera.mongo.handler;

import java.util.List;

import com.impetus.kundera.mongo.entities.User;

public interface OperationHandler
{

    void saveUser(User user);

    User findUserById(Class clazz, Object id);

    List<User> findUserByName(String clazz, String name);

    List<User> findUserByAge(String clazz, int age);

    List<User> findAllUser(String clazz);
}
