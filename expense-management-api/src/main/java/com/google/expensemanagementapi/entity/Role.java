package com.google.expensemanagementapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
	        generator ="role-id-generator"
	        )
	@SequenceGenerator(name = "role-id-generator", initialValue = 100)
	private long id;
	@Column(name = "role_name")
	private String roleName;
}
