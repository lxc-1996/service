package org.lxc.recruitment.entity;

import javax.persistence.*;

@Entity
@Table(name = "company_info")
public class CompanyEntity {
	
	@Id
	@Column(name = "c_id")
	public Long id;
	@Column(name = "c_name")
	public String name;
	@Column(name = "c_sex")
	public String sex;
	@Column(name = "c_phone")
	public String phone;
	@Column(name = "c_mail")
	public String mail;
	@Column(name = "c_company_name")
	public String companyName;
	@Column(name = "c_license")
	public String license;
	@Column(name = "c_properties")
	public String properties;
	@Column(name = "c_sector")
	public String sector;
	@Column(name = "c_size")
	public String size;
	@Column(name = "c_located")
	public String located;
	@Column(name = "c_web")
	public String web;
	@Column(name = "c_introduction")
	public String introduction;
}
