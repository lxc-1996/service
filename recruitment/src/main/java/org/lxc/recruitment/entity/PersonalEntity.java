package org.lxc.recruitment.entity;

import javax.persistence.*;

@Entity
@Table(name ="personal_info")
public class PersonalEntity {
	
	@Id
	@Column(name = "p_id")
	public Long id;
	@Column(name = "p_name")
	public String name;
	@Column(name = "p_sex")
	public String sex;
	@Column(name = "p_docType")
	public String docType;
	@Column(name = "p_idcard")
	public String idcard;
	@Column(name = "p_nationality")
	public String nationality;
	@Column(name = "p_age")
	public String age;
	@Column(name = "p_national")
	public String national;
	@Column(name = "p_native")
	public String nativeAddress;
	@Column(name = "p_address")
	public String address;
	@Column(name = "p_political")
	public String political;
	@Column(name = "p_degree")
	public String degree;
	@Column(name = "p_status")
	public String status;
	@Column(name = "p_phone")
	public String phone;
	@Column(name = "p_mail")
	public String mail;
}
