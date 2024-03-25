package Registration;



import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Login.Login;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RegistrationDao {
    private Registration registration = new Registration();
    private Scanner sc = new Scanner(System.in);
    private Session session;

    public RegistrationDao(Session session) {
        this.session = session;
    }

    public int save() throws SQLException {
        Transaction tx = null;
        try {
        	Login login=new Login();
            tx = session.beginTransaction();
            System.out.print("Enter name: ");
            registration.setName(sc.nextLine());
            System.out.print("Enter email: ");
            registration.setEmail(sc.nextLine());
            System.out.print("Enter password: ");
            registration.setPassword(sc.nextLine());

            int registrationId = (Integer) session.save(registration);
            login.setEmail(registration.getEmail());
            
            login.setPassword(registration.getPassword());
            session.save(login);
            tx.commit();
            
            return registrationId;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error saving registration: " + e.getMessage());
        }
    }

    public Registration getById() throws SQLException {
        try {
        	System.out.print("Enter id :");
            int id=sc.nextInt();
            System.out.println("🔍🎯 Data retrieved by ID successfully from Registration table! 🎯🔍");
            return session.get(Registration.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving registration: " + e.getMessage());
        }
    }

    public List<Registration> getAll() throws SQLException {
        try {
            Query<Registration> query = session.createQuery("FROM Registration", Registration.class);
            List<Registration> registrations = query.getResultList();

            // Print header
            System.out.println("-----------------------------------------------------------------------------");
            System.out.printf("%-20s %-20s %-20s\n", "ID", "Name", "Email");
            System.out.println("-----------------------------------------------------------------------------");

            // Print each registration
            for (Registration registration : registrations) {
                System.out.printf("%-20s %-20s %-20s\n",
                        registration.getId(),
                        registration.getName(),
                        registration.getEmail());
            }

            System.out.println("📊📊📊 All data retrieved successfully from Registration table! 📊📊📊");

            return registrations;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving all registrations: " + e.getMessage());
        }
    }

    public boolean update() throws SQLException {
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            System.out.print("Enter id :");
            int id=sc.nextInt();

            registration = session.get(Registration.class, id);

            System.out.print("Please enter updated name: ");
            String name = sc.nextLine();
            if (!name.isEmpty()) {
                registration.setName(name);
            }

            System.out.print("Please enter updated email: ");
            String email = sc.nextLine();
            if (!email.isEmpty()) {
                registration.setEmail(email);
            }

            System.out.print("Please enter updated password: ");
            String password = sc.nextLine();
            if (!password.isEmpty()) {
                registration.setPassword(password);
            }

            session.update(registration);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error updating registration: " + e.getMessage());
        }
    }

    public boolean delete() throws SQLException {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.print("Enter id :");
            int id=sc.nextInt();
            Registration registration = session.get(Registration.class, id);
            if (registration != null) {
                session.delete(registration);
            }
            tx.commit();
            System.out.println("🚮🔑 Registration successfully deleted by ID from Registration table! 🔑🚮");
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error deleting registration: " + e.getMessage());
        }
    }
}
