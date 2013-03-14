package com.impetus.kundera.mongo.handler;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.impetus.kundera.mongo.entities.Address;
import com.impetus.kundera.mongo.entities.User;
import com.impetus.kundera.mongo.handler.OperationHandlerForUser;

public class OperationHandlerForUserTest
{

    private static OperationHandlerForUser handler = new OperationHandlerForUser();

    @Before
    public void setUp() throws Exception
    {
        if (handler == null)
        {
            handler = new OperationHandlerForUser();
        }
    }

    @After
    public void tearDown() throws Exception
    {
        handler.removeAll(User.class.getSimpleName());
    }

    public void persist()
    {
        Address address1 = new Address();
        address1.setAddressId("a1");
        address1.setStreet("AAAAAAA");

        Address address2 = new Address();
        address2.setAddressId("a2");
        address2.setStreet("BBBBBBB");

        User user1 = new User();
        user1.setPersonId(1);
        user1.setPersonName("Kuldeep");
        user1.setAge(24);
        user1.setAddress(address1);

        User user2 = new User();
        user2.setPersonId(2);
        user2.setPersonName("Amresh");
        user2.setAge(30);
        user2.setAddress(address2);

        handler.saveUser(user1);
        handler.saveUser(user2);
        handler.clear();

    }

    @Test
    public void testFindUserById()
    {
        persist();
        User user1 = handler.findUserById(User.class, 1);
        Assert.assertNotNull(user1);
        Assert.assertEquals("Kuldeep", user1.getPersonName());
        Assert.assertEquals(24, user1.getAge());
        Assert.assertNotNull(user1.getAddress());
        Assert.assertEquals("a1", user1.getAddress().getAddressId());
        Assert.assertEquals("AAAAAAA", user1.getAddress().getStreet());

        User user2 = handler.findUserById(User.class, 2);
        Assert.assertNotNull(user2);
        Assert.assertEquals("Amresh", user2.getPersonName());
        Assert.assertEquals(30, user2.getAge());
        Assert.assertNotNull(user2.getAddress());
        Assert.assertEquals("a2", user2.getAddress().getAddressId());
        Assert.assertEquals("BBBBBBB", user2.getAddress().getStreet());
    }

    @Test
    public void testFindUserByName()
    {
        persist();
        List<User> users = handler.findUserByName(User.class.getSimpleName(), "Kuldeep");
        Assert.assertNotNull(users);
        Assert.assertEquals(1, users.size());
        Assert.assertNotNull(users.get(0));
        User user1 = users.get(0);
        Assert.assertNotNull(user1);
        Assert.assertEquals("Kuldeep", user1.getPersonName());
        Assert.assertEquals(24, user1.getAge());
        Assert.assertNotNull(user1.getAddress());
        Assert.assertEquals("a1", user1.getAddress().getAddressId());
        Assert.assertEquals("AAAAAAA", user1.getAddress().getStreet());
    }

    @Test
    public void testFindUserByAge()
    {
        persist();
        List<User> users = handler.findUserByAge(User.class.getSimpleName(), 24);
        Assert.assertNotNull(users);
        Assert.assertEquals(1, users.size());
        Assert.assertNotNull(users.get(0));
        User user1 = users.get(0);
        Assert.assertNotNull(user1);
        Assert.assertEquals("Kuldeep", user1.getPersonName());
        Assert.assertEquals(24, user1.getAge());
        Assert.assertNotNull(user1.getAddress());
        Assert.assertEquals("a1", user1.getAddress().getAddressId());
        Assert.assertEquals("AAAAAAA", user1.getAddress().getStreet());
    }

    @Test
    public void testFindAllUser()
    {
        persist();
        List<User> users = handler.findAllUser(User.class.getSimpleName());
        Assert.assertNotNull(users);
        Assert.assertEquals(2, users.size());
        Assert.assertNotNull(users.get(0));
        User user1 = users.get(0);
        Assert.assertNotNull(user1);
        Assert.assertEquals("Kuldeep", user1.getPersonName());
        Assert.assertEquals(24, user1.getAge());
        Assert.assertNotNull(user1.getAddress());
        Assert.assertEquals("a1", user1.getAddress().getAddressId());
        Assert.assertEquals("AAAAAAA", user1.getAddress().getStreet());

        Assert.assertNotNull(users.get(1));
        User user2 = users.get(1);
        Assert.assertEquals("Amresh", user2.getPersonName());
        Assert.assertEquals(30, user2.getAge());
        Assert.assertNotNull(user2.getAddress());
        Assert.assertEquals("a2", user2.getAddress().getAddressId());
        Assert.assertEquals("BBBBBBB", user2.getAddress().getStreet());
    }
}
