
package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
	@NamedQuery(name="actor.all", query="SELECT a FROM Actor a"),
	@NamedQuery(name="actor.id", query="SELECT a FROM Actor a where a.id=:actorId")
	})
public class Actor {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String name;
	private String surname;

	private List<Film> films = new ArrayList<>();
//	@XmlTransient
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
////	@JoinTable(name = "film_actor",  joinColumns = { 
////			@JoinColumn(name = "film_id", nullable = false, updatable = false) }, 
////			inverseJoinColumns = { @JoinColumn(name = "actor_id", 
////					nullable = false, updatable = false) })
//	public List<Film> getFilms() {
//		return films;
//	}
//
//	public void setFilms(List<Film> films) {
//		this.films = films;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
