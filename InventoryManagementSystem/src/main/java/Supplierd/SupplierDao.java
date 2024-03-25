package Supplierd;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SupplierDao {
    private Supplier supplier = new Supplier();
    private Scanner sc = new Scanner(System.in);
    private Session session;

    public SupplierDao(Session session) {
        this.session = session;
    }

    public int save() throws SQLException {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.print("Enter name: ");
            supplier.setName(sc.nextLine());
            System.out.print("Enter location: ");
            supplier.setLocation(sc.nextLine());

            int supplierId = (Integer) session.save(supplier);
            tx.commit();
            System.out.println("✅✅✅ Data inserted into Supplier table successfully! ✅✅✅");
            return supplierId;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error saving supplier: " + e.getMessage());
        }
    }

    public Supplier getById() throws SQLException {
        try {
        	System.out.print("Enter id :");
            int id=sc.nextInt();
            System.out.println("🔍🎯 Data retrieved by ID successfully from Supplier table! 🎯🔍");
            return session.get(Supplier.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving supplier: " + e.getMessage());
        }
    }

    public List<Supplier> getAll() throws SQLException {
        try {
            Query<Supplier> query = session.createQuery("FROM Supplier", Supplier.class);
            List<Supplier> suppliers = query.getResultList();

            // Print header
            System.out.println("----------------------------------------------------");
            System.out.printf("%-20s %-20s %-20s\n", "ID", "Name", "Location");
            System.out.println("----------------------------------------------------");

            // Print each supplier
            for (Supplier supplier : suppliers) {
                System.out.printf("%-20s %-20s %-20s\n",
                        supplier.getId(),
                        supplier.getName(),
                        supplier.getLocation());
            }

            System.out.println("📊📊📊 All data retrieved successfully from Supplier table! 📊📊📊");

            return suppliers;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving all suppliers: " + e.getMessage());
        }
    }

    public boolean update() throws SQLException {
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            System.out.print("Enter id :");
            int id=sc.nextInt();
            supplier = session.get(Supplier.class, id);

            System.out.print("Please enter updated name: ");
            String name = sc.nextLine();
            if (!name.isEmpty()) {
                supplier.setName(name);
            }

            System.out.print("Please enter updated location: ");
            String location = sc.nextLine();
            if (!location.isEmpty()) {
                supplier.setLocation(location);
            }

            session.update(supplier);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error updating supplier: " + e.getMessage());
        }
    }

    public boolean delete() throws SQLException {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.print("Enter id :");
            int id=sc.nextInt();
            Supplier supplier = session.get(Supplier.class, id);
            if (supplier != null) {
                session.delete(supplier);
            }
            tx.commit();
            System.out.println("🚮🔑 Supplier successfully deleted by ID from Supplier table! 🔑🚮");
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error deleting supplier: " + e.getMessage());
        }
    }
}
