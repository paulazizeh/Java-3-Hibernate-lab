package edu.cscc;

import edu.cscc.entities.OrderLineItems;
import edu.cscc.entities.Orders;
import edu.cscc.framework.ApplicationView;
import edu.cscc.framework.MVCContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OrdersIndex extends ApplicationView {
    private List<Orders> orderList;
    public OrdersIndex(MVCContext context, List<Orders> orderList) {
        super(context);
        this.orderList = orderList;
        }

    @Override
    public void show() {
        boolean isEmpty = true;
            for (Orders order : orderList) {
                isEmpty = false;
                System.out.println();
                System.out.println("Order:");
                System.out.println("************");
                System.out.println("Order ID: " + order.getId());
                System.out.println("Employee Name: " + order.getEmployee().getFirstName() + " " + order.getEmployee().getLastName());
                System.out.println("Customer Smart ID: " + order.getCustomer().getSmartId());
                System.out.println("Customer Name: " + order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName());
                System.out.println("Store Number: " + order.getStoreNumber());
                for (OrderLineItems orderLineItems : order.getOrderLineItems()) {
                    System.out.println("Rental Name: " + orderLineItems.getRental().getName());
                }
            }
            if(isEmpty) {
                System.out.println("No orders found");
            }

        System.out.println();
        System.out.println("Orders Management:");
        System.out.println("1. Create order");
        System.out.println("2. Delete all orders");
        System.out.println("3. Home");
        System.out.println("Choice:");
        Scanner scanner = new Scanner(System.in);
        try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    route("Orders", "create");
                    break;
                case 2:
                    route("Orders", "delete");
                    break;
                case 3:
                    route("Home", "index");
                    break;
            }
        } catch (InputMismatchException ex) {
            route("Orders", "index");

        }

    }

}
