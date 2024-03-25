package Product;



import javax.persistence.*;
//product table
@Entity
@Table(name = "Product", 
indexes = {@Index(columnList = "name, category, price, quantity, description")})
public class Product {
    //columns of table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    
	@Column(name = "name", length = 100)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "category", length = 50)
    private String category;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    @Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", quantity=" + quantity + ", category=" + category + "]";
	}
    
    public boolean reduceQuantity(int quantityToReduce) {
        if (quantity >= quantityToReduce) {
            quantity -= quantityToReduce;
            return true; // Quantity reduced successfully
        } else {
            return false; // Insufficient quantity available
        }
    }
}
