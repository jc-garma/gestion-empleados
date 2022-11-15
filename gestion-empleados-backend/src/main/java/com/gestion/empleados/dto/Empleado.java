package com.gestion.empleados.dto;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empleado")
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 25, nullable = false)
	private String name;

	@Column(name = "lastname", length = 25, nullable = false)
	private String lastname;

	@Column(name = "email", length = 25, nullable = false, unique = true)
	private String email;

	public Empleado() {

	}

	public Empleado(Long id, String name, String lastname, String email) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", name=" + name + ", lastname=" + lastname + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, lastname, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(lastname, other.lastname) && Objects.equals(name, other.name);
	}
	
	

}
