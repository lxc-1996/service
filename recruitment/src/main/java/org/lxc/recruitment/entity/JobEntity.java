package org.lxc.recruitment.entity;

import javax.persistence.*;

@Entity
@Table(name = "jobs")
public class JobEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "j_id")
	public Long id;
	@Column(name = "c_id")
	public Long companyId;
	@Column(name = "j_name")
	public String name;
	@Column(name = "j_professional")
	public String professional;
	@Column(name = "j_degree")
	public String degree;
	@Column(name = "j_duration")
	public String duration;
	@Column(name = "j_site")
	public String site;
	@Column(name = "j_treatment")
	public String treatment;
	@Column(name = "is_audit")
	public int isAudit;
}
