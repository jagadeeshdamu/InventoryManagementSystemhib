package Login;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LoginDao {
    private Login login = new Login();
    private Scanner sc = new Scanner(System.in);
    private Session session;

    public LoginDao(Session session) {
        this.session = session;
    }

    public int save() throws SQLException {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.print("Enter email: ");
            login.setEmail(sc.nextLine());
            System.out.print("Enter password: ");
            login.setPassword(sc.nextLine());

            int loginId = (Integer) session.save(login);
            tx.commit();
            System.out.println("✅✅✅ Data inserted into Login table successfully! ✅✅✅");
            return loginId;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error saving login: " + e.getMessage());
        }
    }

    public Login getById() throws SQLException {
        try {
        	System.out.print("Enter id :");
            int id=sc.nextInt();
            System.out.println("🔍🎯 Data retrieved by ID successfully from Login table! 🎯🔍");
            return session.get(Login.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving login: " + e.getMessage());
        }
    }

    public List<Login> getAll() throws SQLException {
        try {
            Query<Login> query = session.createQuery("FROM Login", Login.class);
            List<Login> logins = query.getResultList();

            // Print header
            System.out.println("----------------------------------------------------");
            System.out.printf("%-20s %-20s\n", "ID", "Email");
            System.out.println("----------------------------------------------------");

            // Print each login
            for (Login login : logins) {
                System.out.printf("%-20s %-20s\n",
                        login.getId(),
                        login.getEmail());
            }

            System.out.println("📊📊📊 All data retrieved successfully from Login table! 📊📊📊");

            return logins;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving all logins: " + e.getMessage());
        }
    }

    public boolean update() throws SQLException {
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            System.out.print("Enter id :");
            int id=sc.nextInt();

            login = session.get(Login.class, id);

            System.out.print("Please enter updated email: ");
            String email = sc.nextLine();
            if (!email.isEmpty()) {
                login.setEmail(email);
            }

            System.out.print("Please enter updated password: ");
            String password = sc.nextLine();
            if (!password.isEmpty()) {
                login.setPassword(password);
            }

            session.update(login);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error updating login: " + e.getMessage());
        }
    }

    public boolean delete() throws SQLException {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.print("Enter id :");
            int id=sc.nextInt();
            Login login = session.get(Login.class, id);
            if (login != null) {
                session.delete(login);
            }
            tx.commit();
            System.out.println("🚮🔑 Login successfully deleted by ID from Login table! 🔑🚮");
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error deleting login: " + e.getMessage());
        }
    }
    
    
    
public boolean checkCredentials() {
		
    	System.out.print("Please Enter Email : ");
    	
    	String Email=sc.nextLine();
    	
    	System.out.print("Please Enter Password : ");
    	
    	String password=sc.nextLine();
    	
    	
    	boolean isAdmin=false;
    	
    	String queryString = "SELECT COUNT(*) FROM Login WHERE Email = :Email AND password = :password";
    	
    	Query<Long> query = session.createQuery(queryString, Long.class);
    	
    	
		query.setParameter("Email", Email);
		
		query.setParameter("password", password);
		
		Long count = query.uniqueResult();
		
		
		isAdmin=count>0;
		
		if (isAdmin) {
            System.out.println("\uD83D\uDC4D login successfully \uD83D\uDC4D");
        }
		else {
			System.out.println("\uD83D\uDC4E login failed \uD83D\uDC4E");
		}
		
		return isAdmin;	
    	
    }
}
