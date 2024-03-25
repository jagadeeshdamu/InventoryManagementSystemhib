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

public class AllMethods {
	Scanner sc=new Scanner(System.in);
	SessionFactory factory = new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
	//session factory interface for openSession
	Session session  = factory.openSession();
	//instance of product tables
	Product product=new Product();
	//instance of productDao
	ProductDao pDao=new ProductDao(session);
    //instance of RegistrationDao
	RegistrationDao rDao=new RegistrationDao(session);
    //instance of AdminLoginDao
	AdminLoginDao aDao=new AdminLoginDao(session);
    //instance of LoginDao
	LoginDao lDao=new LoginDao(session);
	//instance of CustomerAddressDao
	CustomerAddressDao cDao=new CustomerAddressDao(session);
	//instance of SupplierDao
	SupplierDao sDao=new SupplierDao(session);
	//instance of OrderDao
	OrderDao oDao=new OrderDao(session);
	
	public void Home() {
		System.out.print("📝 Registration : ");
        
        System.out.print("🔑 Login : ");
                
        System.out.print("👨‍💼 AdminLogin : ");
        
        System.out.println("🚪 exit : ");
        
        System.out.print("🤔 Please choose one: ");
	}
	
	public void registration() throws SQLException {
		rDao.save();
    	System.out.println("🎉 Registration Successful! 🎉");
	}
	
	public void login() throws SQLException {

    	
    	if(lDao.checkCredentials()) {
    		for(int k=0;;k++) {
    		pDao.getAll();
    		System.out.print("🛒 Place Order : 1");
    		System.out.println("🚪 Logout : 0");
    		System.out.print("🤔 Please choose one: ");
    		String ll=sc.nextLine();
    		

    		
    		
            if(ll.equals("1")) {
            	pDao.placeOrder();
        		
        		cDao.save();
        		System.out.println("✅✅✅ Order placed successfully! ✅✅✅");
            	
            }
            else if(ll.equals("0")) {
            	break;
            }
    		
    		
    	}}
    
	}
	
	public void AdminLogin() throws SQLException {

    	
    	
    	if(aDao.checkCredentials()) {
    		
    		for(int hh=0;;hh++) {

    		System.out.print("📦 Product :1 ");
    		
    		System.out.print("📦 Order :2 ");
    		        		
    		System.out.print("🔑 Login :3 ");
    		        		
    		System.out.print("🛍️ Supplier :4");
    		        		
    		System.out.print("🏠 Customer Address :5");
    		        		
    		System.out.print("👨‍💼 AdminLogin :6");
    		
    		System.out.print("📝 registration :7 ");

    		
    		System.out.println("🚪 Logout : 0");

    		
    		System.out.print("🤔 Please choose one: ");
    		
    		String ss=sc.nextLine();
    		
    		if(ss.equals("1")) {
    			for(int i=0;;i++) {
    			System.out.print("💾 save : 1 ");
    			System.out.print("🔄 update : 2 ");
    			System.out.print("❌ delete : 3 ");
    			System.out.print("📋 getAll : 4 ");
    			System.out.print("🔍 getById : 5 ");
    			System.out.println("🚪 exit : 0 ");
    			System.out.print("🤔 Please choose one: ");
    			
    			String pp=sc.nextLine();
    			
    			if(pp.equals("1")) {
    				pDao.save();
    				
    			}
    			else if(pp.equals("2")) {
    				pDao.update();
    				
    			}
				else if(pp.equals("3")) {
					
					pDao.delete();
				        				
				 }
				else if(pp.equals("4")) {
					pDao.getAll();
				        				
				 }
				else if(pp.equals("5")) {
					pDao.getById();
					
				}
				else if(pp.equals("0")) {
					
					break;
				        				
				}
				        			
    			
    			

    			}	
    			
    		}
    		else if(ss.equals("2")) {
    			for(int i=0;;i++) {
    			
    			System.out.print("💾 save : 1 ");
    			System.out.print("🔄 update : 2 ");
    			System.out.print("❌ delete : 3 ");
    			System.out.print("📋 getAll : 4 ");
    			System.out.print("🔍 getById : 5 ");
    			System.out.println("🚪 exit : 0 ");
    			System.out.print("🤔 Please choose one: ");
    			
    			String pp=sc.nextLine();
    			

    			if(pp.equals("1")) {
    				oDao.save();
    				
    			}
    			else if(pp.equals("2")) {
    				oDao.update();
    				
    			}
				else if(pp.equals("3")) {
					
					oDao.delete();
				        				
				 }
				else if(pp.equals("4")) {
					oDao.getAll();
				        				
				 }
				else if(pp.equals("5")) {
					oDao.getById();
					
				}
				else if(pp.equals("0")) {
					
					break;
				        				
				}
    			}
    			
    		}
    		else if(ss.equals("3")) {
    			for(int i=0;;i++) {
    			System.out.print("💾 save : 1 ");
    			System.out.print("🔄 update : 2 ");
    			System.out.print("❌ delete : 3 ");
    			System.out.print("📋 getAll : 4 ");
    			System.out.print("🔍 getById : 5 ");
    			System.out.println("🚪 exit : 0 ");
    			System.out.print("🤔 Please choose one: ");
    			
				String pp=sc.nextLine();
    			

    			if(pp.equals("1")) {
    				lDao.save();
    				
    			}
    			else if(pp.equals("2")) {
    				lDao.update();
    				
    			}
				else if(pp.equals("3")) {
					
					lDao.delete();
				        				
				 }
				else if(pp.equals("4")) {
					lDao.getAll();
				        				
				 }
				else if(pp.equals("5")) {
					lDao.getById();
					
				}
				else if(pp.equals("0")) {
					
					break;
				        				
				}

    			}
    		}
    		
    		else if (ss.equals("4")) {
    			for(int i=0;;i++) {
    			System.out.print("💾 save : 1 ");
    			System.out.print("🔄 update : 2 ");
    			System.out.print("❌ delete : 3 ");
    			System.out.print("📋 getAll : 4 ");
    			System.out.print("🔍 getById : 5 ");
    			System.out.println("🚪 exit : 0 ");
    			System.out.print("🤔 Please choose one: ");
    			
				String pp=sc.nextLine();
    			

    			if(pp.equals("1")) {
    				sDao.save();
    				
    			}
    			else if(pp.equals("2")) {
    				sDao.update();
    				
    			}
				else if(pp.equals("3")) {
					
					sDao.delete();
				        				
				 }
				else if(pp.equals("4")) {
					sDao.getAll();
				        				
				 }
				else if(pp.equals("5")) {
					sDao.getById();
					
				}
				else if(pp.equals("0")) {
					
					break;
				        				
				}
    			}
    			
    		}
    		else if(ss.equals("5")) {
    			for(int i=0;;i++) {
    			System.out.print("💾 save : 1 ");
    			System.out.print("🔄 update : 2 ");
    			System.out.print("❌ delete : 3 ");
    			System.out.print("📋 getAll : 4 ");
    			System.out.print("🔍 getById : 5 ");
    			System.out.println("🚪 exit : 0 ");
    			System.out.print("🤔 Please choose one: ");
    			
				String pp=sc.nextLine();
    			

    			if(pp.equals("1")) {
    				cDao.save();
    				
    			}
    			else if(pp.equals("2")) {
    				cDao.update();
    				
    			}
				else if(pp.equals("3")) {
					
					cDao.delete();
				        				
				 }
				else if(pp.equals("4")) {
					cDao.getAll();
				        				
				 }
				else if(pp.equals("5")) {
					cDao.getById();
					
				}
				else if(pp.equals("0")) {
					
					break;
				        				
				}

    			}
    		}
    		else if(ss.equals("6")) {
    			for(int i=0;;i++) {
    			System.out.print("💾 save : 1 ");
    			System.out.print("🔄 update : 2 ");
    			System.out.print("❌ delete : 3 ");
    			System.out.print("📋 getAll : 4 ");
    			System.out.print("🔍 getById : 5 ");
    			System.out.println("🚪 exit : 0 ");
    			System.out.print("🤔 Please choose one: ");
    			
				String pp=sc.nextLine();
    			

    			if(pp.equals("1")) {
    				aDao.save();
    				
    			}
    			else if(pp.equals("2")) {
    				aDao.update();
    				
    			}
				else if(pp.equals("3")) {
					
					aDao.delete();
				        				
				 }
				else if(pp.equals("4")) {
					aDao.getAll();
				        				
				 }
				else if(pp.equals("5")) {
					aDao.getById();
					
				}
				else if(pp.equals("0")) {
					
					break;
				        				
				}
    			}
    			
    		}
    		else if(ss.equals("7")) {

    			for(int i=0;;i++) {
    			System.out.print("💾 save : 1 ");
    			System.out.print("🔄 update : 2 ");
    			System.out.print("❌ delete : 3 ");
    			System.out.print("📋 getAll : 4 ");
    			System.out.print("🔍 getById : 5 ");
    			System.out.println("🚪 exit : 0 ");
    			System.out.print("🤔 Please choose one: ");
    			
				String pp=sc.nextLine();
    			

    			if(pp.equals("1")) {
    				rDao.save();
    				
    			}
    			else if(pp.equals("2")) {
    				rDao.update();
    				
    			}
				else if(pp.equals("3")) {
					
					rDao.delete();
				        				
				 }
				else if(pp.equals("4")) {
					aDao.getAll();
				        				
				 }
				else if(pp.equals("5")) {
					rDao.getById();
					
				}
				else if(pp.equals("0")) {
					
					break;
				        				
				}
    			}	
    		}
    		else if(ss.equals("0")) {
    			break;
    		}
    		
    	}	
    }	
	}

}
