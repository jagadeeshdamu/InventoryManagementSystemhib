package Product;


import DaoInterface.CrudOperations;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDao implements CrudOperations<Product> {
	Product product =new Product();
	
	Scanner sc=new Scanner(System.in);

    private Session session;

    public ProductDao(Session session) {
        this.session = session;
    }

    @Override
    public int save() throws SQLException {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.print("Enter name : ");
            product.setName(sc.nextLine());
            System.out.print("Enter Description : ");
            product.setDescription(sc.nextLine());
            System.out.print("Enter CategoryType : ");
            product.setCategory(sc.nextLine());
            System.out.print("Enter price : ");
            product.setPrice(sc.nextDouble());
            sc.nextLine(); // Consume newline
            System.out.print("Enter quantity : ");
            product.setQuantity(sc.nextInt());
            sc.nextLine(); // Consume newline

            
            int productId = (Integer) session.save(product);
            tx.commit();
            System.out.println("✅✅✅ Data inserted into table successfully! ✅✅✅");
            return productId;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error saving product: " + e.getMessage());
        }
    }

    @Override
    public Product getById() throws SQLException {
        try {
        	System.out.print("Enter id :");
            int id=sc.nextInt();
        	System.out.println("🔍🎯 Data retrieved by ID successfully! 🎯🔍");
            return session.get(Product.class, id);  

        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving product: " + e.getMessage());
        }
    }

    @Override
    public List<Product> getAll() throws SQLException {
        try {
            Query<Product> query = session.createQuery("FROM Product", Product.class);
            List<Product> products = query.getResultList();
            
            // Print header
            System.out.println("-----------------------------------------------------------------------------");
            System.out.printf("%-20s %-20s %-10s %-10s %-50s\n", "Name", "Category", "Price", "Quantity", "Description");
            System.out.println("-----------------------------------------------------------------------------");
            
            // Print each product
            for (Product product : products) {
                String formattedProduct = String.format("%-20s %-20s %-10.2f %-10d %-50s",
                        product.getName(),
                        product.getCategory(),
                        product.getPrice(),
                        product.getQuantity(),
                        product.getDescription());

                System.out.println(formattedProduct);
            }
            
            System.out.println("📊📊📊 All data retrieved successfully! 📊📊📊");


            return products;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving all products: " + e.getMessage());
        }
    }

    @Override
    public boolean update() throws SQLException {
        Transaction tx = null;
        
        try {
        	
            tx = session.beginTransaction();
            System.out.print("Enter id :");
            int id=sc.nextInt();
            product =session.get(Product.class, id);
            
            System.out.print("Please enter updated name : ");
            
            String name=sc.nextLine();
            
            if(!name.isEmpty()) {
            	product.setName(name);
            }
            
           System.out.print("Please enter updated Category : ");
            
           String Category=sc.nextLine();
            
            if(!Category.isEmpty()) {
            	product.setCategory(Category);
            }
            
            System.out.print("Please enter updated Price : ");
            
            String price=String.valueOf(sc.nextLine())   ;
             
             if(!price.isEmpty()) {
            	 int p=Integer.valueOf(price);
             	 product.setPrice(p);
             }
             
             System.out.print("Please enter updated Qauntity : ");
             
             String Quantity=sc.nextLine();
              
              if(!Quantity.isEmpty()) {
            	  int q=Integer.valueOf(Quantity);
              	  product.setQuantity(q);
              }
              
              System.out.print("Please enter updated Description : ");
              
              String Description=sc.nextLine();
               
               if(!Description.isEmpty()) {
               	product.setDescription(Description);
               }
           
            
            session.update(product);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error updating product: " + e.getMessage());
        }
    }

    @Override
    public boolean delete() throws SQLException {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.print("Enter id :");
            int id=sc.nextInt();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.delete(product);
            }
            tx.commit();
            System.out.println("🚮🔑 Data successfully deleted by ID! 🔑🚮");
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error deleting product: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteAll() throws SQLException {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Product");
            query.executeUpdate();
            tx.commit();
            System.out.println("🧹🧹🧹 All data successfully cleared! 🧹🧹🧹");

            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error deleting all products: " + e.getMessage());
        }
    }
    
    
    public boolean placeOrder( ) throws SQLException {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            System.out.print("Please Enter Product Name : ");
            String productName=sc.nextLine();
            
            System.out.print("Please Enter Quantity : ");
            int quantityToOrder=sc.nextInt();

            Query<Product> query = session.createQuery("FROM Product WHERE name = :productName", Product.class);
            query.setParameter("productName", productName);
            Product product = query.uniqueResult();

            if (product != null && product.reduceQuantity(quantityToOrder)) {
                session.update(product);
                tx.commit();
                return true;
            } else {
                System.out.println("❌❌❌ Insufficient quantity available for the order! ❌❌❌");
                return false;
            }
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Error placing order: " + e.getMessage());
        }
    }

}
