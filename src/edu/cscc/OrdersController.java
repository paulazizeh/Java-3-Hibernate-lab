package edu.cscc;

import com.mysql.cj.jdbc.MysqlDataSource;
import edu.cscc.entities.*;
import edu.cscc.framework.ApplicationController;
import edu.cscc.framework.MVCContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class OrdersController extends ApplicationController {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LacklusterVideo");
    private static final String MYSQL_DB_URL = "jdbc:mysql://localhost:3306/lackluster_video";
    private static final String MYSQL_DB_USERNAME = "root";
    private static final String MYSQL_DB_PASSWORD = "password";


    public OrdersController(MVCContext context) {
        super(context);
    }

    public void index() throws SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String orderQuery = "select ord from Orders ord order by ord.id desc";
        List<Orders> ordersList = (List<Orders>) entityManager.createQuery(orderQuery).getResultList();
                render(new OrdersIndex(context, ordersList));

       }

    private static void buildProperties(Properties properties) {
        properties.setProperty(MYSQL_DB_URL, "jdbc:mysql://localhost:3306/lackluster_video");
        properties.setProperty(MYSQL_DB_USERNAME, "root");
        properties.setProperty(MYSQL_DB_PASSWORD, "password");
    }

    public void delete() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String orderQuery = "select ord from Orders ord order by ord.id desc";
        List<Orders> ordersList = (List<Orders>) entityManager.createQuery(orderQuery).getResultList();
        render(new OrdersIndex(context, ordersList));
    }

    public void create() {
        render(new OrdersCreate(context));
    }

    public void complete() {
        // Todo save order to database
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Orders orders = new Orders();
        Employees employees = entityManager.find(Employees.class, Integer.parseInt(context.getRequest().getParams().get("employeeId").toString()));
        Customers customers = entityManager.getReference(Customers.class, Integer.parseInt(context.getRequest().getParams().get("customerid").toString()));
        orders.setEmployee(employees);
        orders.setCustomer(customers);
        orders.setStoreNumber(employees.getActiveStoreNumber());
        List<String> rentalIds = (List <String>) context.getRequest().getParams().get("rentalIds");
        for (String rentalId : rentalIds) {
            OrderLineItems orderlineitem = new OrderLineItems();
            orderlineitem.setRental(entityManager.getReference(Rentals.class, Integer.parseInt(rentalId)));
            orders.getOrderLineItems().add(orderlineitem);
        }
        entityManager.getTransaction().begin();
        entityManager.persist(orders);
        entityManager.getTransaction().commit();
        String orderQuery = "select ord from Orders ord order by ord.id desc";
        List<Orders> ordersList = (List<Orders>) entityManager.createQuery(orderQuery).getResultList();
        render(new OrdersIndex(context, ordersList));
    }



    private static DataSource getDataSource(Properties properties) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(properties.getProperty(MYSQL_DB_URL));
        dataSource.setUser(properties.getProperty(MYSQL_DB_USERNAME));
        dataSource.setPassword(properties.getProperty(MYSQL_DB_PASSWORD));

        return dataSource;
    }

}


