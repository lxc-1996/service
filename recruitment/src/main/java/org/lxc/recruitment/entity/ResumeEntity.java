package org.lxc.recruitment.entity;

import javax.persistence.*;

@Entity
@Table(name ="resume")
public class ResumeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "r_id")
	public Long id;
	@Column(name = "p_id")
	public Long personalId;
	@Column(name = "r_name")
	public String name;
	@Column(name = "r_professional")
	public String professional;
	@Column(name = "r_description")
	public String description;
	@Column(name = "r_experience")
	public String experience;
	@Column(name = "r_duration")
	public String duration;
	@Column(name = "r_treatment")
	public String treatment;
	@Column(name = "r_degree")
	public String degree;
}
