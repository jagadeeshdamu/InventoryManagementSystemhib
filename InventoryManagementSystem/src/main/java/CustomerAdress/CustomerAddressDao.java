package CustomerAdress;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerAddressDao {
    CustomerAddress customerAddress = new CustomerAddress();
    Scanner sc = new Scanner(System.in);

    private Session session;

    public CustomerAddressDao(Session session) {
        this.session = session;
    }

    public int save() throws SQLException {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.print("Enter name: ");
            customerAddress.setName(sc.nextLine());
            System.out.print("Enter location: ");
            customerAddress.setLocation(sc.nextLine());
            System.out.print("Enter pincode: ");
            customerAddress.setPincode(sc.nextLine());

            int addressId = (Integer) session.save(customerAddress);
            tx.commit();
            System.out.println("✅✅✅ Data inserted into CustomerAddress table successfully! ✅✅✅");
            return addressId;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error saving customer address: " + e.getMessage());
        }
    }

    public CustomerAddress getById() throws SQLException {
        try {
        	System.out.print("Enter id :");
            int id=sc.nextInt();
            System.out.println("🔍🎯 Data retrieved by ID successfully from CustomerAddress table! 🎯🔍");
            return session.get(CustomerAddress.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving customer address: " + e.getMessage());
        }
    }

    public List<CustomerAddress> getAll() throws SQLException {
        try {
            Query<CustomerAddress> query = session.createQuery("FROM CustomerAddress", CustomerAddress.class);
            List<CustomerAddress> addresses = query.getResultList();

            // Print header
            System.out.println("-----------------------------------------------------------------------------");
            System.out.printf("%-20s %-20s %-10s\n", "Name", "Location", "Pincode");
            System.out.println("-----------------------------------------------------------------------------");

            // Print each address
            for (CustomerAddress address : addresses) {
                String formattedAddress = String.format("%-20s %-20s %-10s",
                        address.getName(),
                        address.getLocation(),
                        address.getPincode());

                System.out.println(formattedAddress);
            }

            System.out.println("📊📊📊 All data retrieved successfully from CustomerAddress table! 📊📊📊");

            return addresses;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving all customer addresses: " + e.getMessage());
        }
    }

    public boolean update() throws SQLException {
        Transaction tx = null;

        try {
        	System.out.print("Enter id :");
            int id=sc.nextInt();
            tx = session.beginTransaction();

            customerAddress = session.get(CustomerAddress.class, id);

            System.out.print("Please enter updated name: ");

            String name = sc.nextLine();

            if (!name.isEmpty()) {
                customerAddress.setName(name);
            }

            System.out.print("Please enter updated location: ");

            String location = sc.nextLine();

            if (!location.isEmpty()) {
                customerAddress.setLocation(location);
            }

            System.out.print("Please enter updated pincode: ");

            String pincode = sc.nextLine();

            if (!pincode.isEmpty()) {
                customerAddress.setPincode(pincode);
            }

            session.update(customerAddress);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error updating customer address: " + e.getMessage());
        }
    }

    public boolean delete() throws SQLException {
        Transaction tx = null;
        try {
        	System.out.print("Enter id :");
            int id=sc.nextInt();
            tx = session.beginTransaction();
            CustomerAddress address = session.get(CustomerAddress.class, id);
            if (address != null) {
                session.delete(address);
            }
            tx.commit();
            System.out.println("🚮🔑 Customer address successfully deleted by ID from CustomerAddress table! 🔑🚮");
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error deleting customer address: " + e.getMessage());
        }
    }
}
