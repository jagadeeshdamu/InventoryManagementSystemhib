package Main;

import java.sql.SQLException;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import AdminLogin.AdminLoginDao;
import CustomerAdress.CustomerAddressDao;
import Login.LoginDao;
import Order.OrderDao;
import Product.Product;
import Product.ProductDao;
import Registration.RegistrationDao;
import Supplierd.SupplierDao;

public class InventoryMain {

	public static void main(String[] args) throws SQLException {
		
         Scanner sc=new Scanner(System.in);
         
		AllMethods method=new AllMethods();
		
		System.out.println("                       ╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("                         💼                     Inventory Management                     💼   ");
        System.out.println("                       ╚════════════════════════════════════════════════════════════════════╝");
        
        for(int j = 0;;j++) {
        	
        	method.Home();

        //taking input from user
        String name=sc.nextLine();
        //registration condition
        if(name.equals("Registration")) {
        	
        	method.registration();
        	
        }
        //login condition
        else if(name.equals("Login")) {
        	method.login();
        }
        //AdminLogin
        else if(name.equals("AdminLogin")){
        	method.AdminLogin();
        }
        else if(name.equals("exit")) {
        	break;
        	
        }
	}

}
}
