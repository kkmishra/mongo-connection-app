/**
 * 
 */
package com.impetus.kundera.mongo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.impetus.kundera.mongo.entities.Address;
import com.impetus.kundera.mongo.entities.User;
import com.impetus.kundera.mongo.handler.OperationHandlerForUser;

/**
 * @author impadmin
 * 
 */
public class StartUpListener implements ServletContextListener
{
    OperationHandlerForUser handler = null;

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
     * ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
//        handler.removeAll(User.class.getSimpleName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
     * .ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0)
    {
        handler = new OperationHandlerForUser();
        persist();
        find();
    }

    private void persist()
    {
        Long t1 = System.currentTimeMillis();
        Address address1 = new Address();
        address1.setAddressId(t1.toString());
        address1.setStreet("AAAAAAA");

        Address address2 = new Address();
        address2.setAddressId(t1.toString() + "1");
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

    private void find()
    {
        User user1 = handler.findUserById(User.class, 1);
        System.out.println(user1.getPersonName());
        System.out.println(user1.getAge());
        System.out.println(user1.getAddress().getAddressId());
        System.out.println(user1.getAddress().getStreet());

        User user2 = handler.findUserById(User.class, 2);
        System.out.println(user2.getPersonName());
        System.out.println(user2.getAge());
        System.out.println(user2.getAddress().getAddressId());
        System.out.println(user2.getAddress().getStreet());
    }
}
