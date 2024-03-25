package AdminLogin;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AdminLoginDao {
    private AdminLogin adminLogin = new AdminLogin();
    private Scanner sc = new Scanner(System.in);
    private Session session;

    public AdminLoginDao(Session session) {
        this.session = session;
    }

    public int save() throws SQLException {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.print("Enter username: ");
            adminLogin.setUsername(sc.nextLine());
            System.out.print("Enter password: ");
            adminLogin.setPassword(sc.nextLine());

            int adminId = (Integer) session.save(adminLogin);
            tx.commit();
            System.out.println("✅✅✅ Data inserted into AdminLogin table successfully! ✅✅✅");
            return adminId;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error saving admin login: " + e.getMessage());
        }
    }

    public AdminLogin getById() throws SQLException {
        try {
        	System.out.print("Enter id :");
            int id=sc.nextInt();
            System.out.println("🔍🎯 Data retrieved by ID successfully from AdminLogin table! 🎯🔍");
            return session.get(AdminLogin.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving admin login: " + e.getMessage());
        }
    }

    public List<AdminLogin> getAll() throws SQLException {
        try {
            Query<AdminLogin> query = session.createQuery("FROM AdminLogin", AdminLogin.class);
            List<AdminLogin> adminLogins = query.getResultList();

            // Print header
            System.out.println("-------------------------------------------------------");
            System.out.printf("%-10s %-20s\n", "ID", "Username");
            System.out.println("-------------------------------------------------------");

            // Print each admin login
            for (AdminLogin adminLogin : adminLogins) {
                System.out.printf("%-10s %-20s\n",
                        adminLogin.getId(),
                        adminLogin.getUsername());
            }

            System.out.println("📊📊📊 All data retrieved successfully from AdminLogin table! 📊📊📊");

            return adminLogins;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving all admin logins: " + e.getMessage());
        }
    }

    public boolean update() throws SQLException {
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            System.out.print("Enter id :");
            int id=sc.nextInt();
            adminLogin = session.get(AdminLogin.class, id);

            System.out.print("Please enter updated username: ");
            String username = sc.nextLine();
            if (!username.isEmpty()) {
                adminLogin.setUsername(username);
            }

            System.out.print("Please enter updated password: ");
            String password = sc.nextLine();
            if (!password.isEmpty()) {
                adminLogin.setPassword(password);
            }

            session.update(adminLogin);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error updating admin login: " + e.getMessage());
        }
    }

    public boolean delete() throws SQLException {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.print("Enter id :");
            int id=sc.nextInt();
            AdminLogin adminLogin = session.get(AdminLogin.class, id);
            if (adminLogin != null) {
                session.delete(adminLogin);
            }
            tx.commit();
            System.out.println("🚮🔑 Admin login successfully deleted by ID from AdminLogin table! 🔑🚮");
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error deleting admin login: " + e.getMessage());
        }
    }
    
    
    
    public boolean checkCredentials() {
		
    	System.out.print("Please Enter UserName : ");
    	
    	String username=sc.nextLine();
    	
    	System.out.print("Please Enter Password : ");
    	
    	String password=sc.nextLine();
    	
    	
    	boolean isAdmin=false;
    	
    	String queryString = "SELECT COUNT(*) FROM AdminLogin WHERE username = :username AND password = :password";
    	
    	Query<Long> query = session.createQuery(queryString, Long.class);
    	
    	
		query.setParameter("username", username);
		
		query.setParameter("password", password);
		
		Long count = query.uniqueResult();
		
		
		isAdmin=count>0;
		
		if (isAdmin) {
            System.out.println("\uD83D\uDC4D Admin login successfully \uD83D\uDC4D");
        }
		else {
			System.out.println("\uD83D\uDC4E Admin login failed \uD83D\uDC4E");
		}
		
		return isAdmin;
		
		
    	
    	
    	
    	
    }
}
