package com.google.expensemanagementapi.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
	@SequenceGenerator(name = "user_id_generator", initialValue = 1000)
	private long id;
	@NotBlank(message = "Name Should not blank")
	private String name;
	@NotBlank(message = "email Should not blank")
	private String email;
	@NotBlank(message = "password Should not blank")
	private String password;
	@CreationTimestamp
	@Column(updatable = false, name = "Created_At")
	private Timestamp createdAt;
	@UpdateTimestamp
	@Column(name = "Last_updated_at")
	private Timestamp updatedAt;
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Role> roles = new HashSet<>();
}
