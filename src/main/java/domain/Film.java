package domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
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
	@NamedQuery(name="film.all", query="SELECT f FROM Film f"),
	@NamedQuery(name="film.id", query="SELECT f FROM Film f where f.id=:filmId")
	})

public class Film {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String title;
	private String description;
	private List<Comment> comments = new ArrayList<Comment>();
	private List<Value> values = new ArrayList<>();
	private BigDecimal ocena = BigDecimal.ZERO;
	
	@XmlTransient
	@OneToMany(mappedBy="film")
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setOcena(BigDecimal ocena) {
	}

	public BigDecimal getOcena() {
		BigDecimal suma = BigDecimal.ZERO;
		boolean isValuesEmpty = (values == null || values.isEmpty());
		if (!isValuesEmpty) {
			for (Value val : values) {
				suma = suma.add(val.getValue());
			}
		}
		return isValuesEmpty ? BigDecimal.ZERO
				: ((BigDecimal) suma.divide(new BigDecimal(Integer.toString(values.size()))));
	}
	@OneToMany(mappedBy="film")
	public List<Value> getValues() {
		return values;
	}

	public void setValues(List<Value> values) {
		this.values = values;
	}
	
	

}
