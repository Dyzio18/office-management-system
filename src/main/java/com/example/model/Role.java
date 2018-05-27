package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
//	@ManyToMany(cascade = CascadeType.REMOVE)
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//	@ManyToMany(mappedBy="role_id",cascade = CascadeType.REMOVE)
	@Column(name="role_id")
	private int id;
	@Column(name="role")
	private String role;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
