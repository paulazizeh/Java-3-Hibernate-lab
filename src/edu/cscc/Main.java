package edu.cscc;

import edu.cscc.framework.MVCContext;
import edu.cscc.framework.Request;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LacklusterVideo");
        MVCContext context = new MVCContext();

        try {
            context.processRequest(new Request("Home", "index"));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
