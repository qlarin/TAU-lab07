package lab07.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {

	private Long id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public enum Category{
		GRAPHIC_CARD("Graphic card"),
		MAINBOARD("Mainboard"),
		HARD_DISK("Hard disk"),
		MEMORY("Memory");
		private String name;
		private Category(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	@Enumerated(EnumType.STRING)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
