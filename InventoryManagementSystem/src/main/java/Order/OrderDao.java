package Order;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class OrderDao {
    private Order order = new Order();
    private Scanner sc = new Scanner(System.in);
    private Session session;

    public OrderDao(Session session) {
        this.session = session;
    }

    public int save() throws SQLException {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.print("Enter product name: ");
            order.setProductName(sc.nextLine());
            System.out.print("Enter quantity: ");
            order.setQuantity(sc.nextInt());
            sc.nextLine(); // Consume newline

            int orderId = (Integer) session.save(order);
            tx.commit();
            System.out.println("✅✅✅ Data inserted into Order table successfully! ✅✅✅");
            return orderId;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error saving order: " + e.getMessage());
        }
    }

    public Order getById() throws SQLException {
        try {
        	System.out.print("Enter id :");
            int id=sc.nextInt();
            System.out.println("🔍🎯 Data retrieved by ID successfully from Order table! 🎯🔍");
            return session.get(Order.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving order: " + e.getMessage());
        }
    }

    public List<Order> getAll() throws SQLException {
        try {
            Query<Order> query = session.createQuery("FROM Order", Order.class);
            List<Order> orders = query.getResultList();

            // Print header
            System.out.println("---------------------------------------------------------");
            System.out.printf("%-10s %-20s %-10s\n", "ID", "Product Name", "Quantity");
            System.out.println("---------------------------------------------------------");

            // Print each order
            for (Order order : orders) {
                System.out.printf("%-10s %-20s %-10s\n",
                        order.getId(),
                        order.getProductName(),
                        order.getQuantity());
            }

            System.out.println("📊📊📊 All data retrieved successfully from Order table! 📊📊📊");

            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving all orders: " + e.getMessage());
        }
    }

    public boolean update() throws SQLException {
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            System.out.print("Enter id :");
            int id=sc.nextInt();

            order = session.get(Order.class, id);

            System.out.print("Please enter updated product name: ");
            String productName = sc.nextLine();
            if (!productName.isEmpty()) {
                order.setProductName(productName);
            }

            System.out.print("Please enter updated quantity: ");
            int quantity = sc.nextInt();
            if (quantity > 0) {
                order.setQuantity(quantity);
            }

            session.update(order);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error updating order: " + e.getMessage());
        }
    }

    public boolean delete() throws SQLException {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.print("Enter id :");
            int id=sc.nextInt();
            Order order = session.get(Order.class, id);
            if (order != null) {
                session.delete(order);
            }
            tx.commit();
            System.out.println("🚮🔑 Order successfully deleted by ID from Order table! 🔑🚮");
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error deleting order: " + e.getMessage());
        }
    }
}
