package domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@NamedQueries({
	@NamedQuery(name="product.all", query="SELECT p FROM Product p"),
	@NamedQuery(name="product.id", query="SELECT p FROM Product p where p.id=:productId"),
	@NamedQuery(name="product.byPrice", query="SELECT p FROM Product p where p.price >= :min and p.price <= :max"),
	@NamedQuery(name="product.byName", query="SELECT p FROM Product p where p.name=:name"),
	@NamedQuery(name="product.byCategory", query="SELECT p FROM Product p where p.category=:category")
	})

public class Product {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private BigDecimal price;
	private String name;
		
	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	private Category category;
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private List<Comment> comments = new ArrayList<Comment>();
	
	@XmlTransient
	@OneToMany(mappedBy="product",orphanRemoval = true)
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public enum Category{
		GRAPHIC_CARD,
		MAINBOARD,
		HARD_DISK,
		MEMORY
		}

//	@Enumerated(EnumType.STRING)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	

}
