package org.lxc.recruitment.entity;

import javax.persistence.*;

/**
 * 管理员
 */
@Entity
@Table(name = "administrator_info")
public class AdministratorEntity {
	
	@Id
	@Column(name = "a_id")
	public Long id;
	@Column(name = "a_name")
	public String name;
	@Column(name = "a_sex")
	public String sex;
	@Column(name = "a_phone")
	public String phone;
	@Column(name = "a_number")
	public String number;
}
