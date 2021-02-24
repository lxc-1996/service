package org.lxc.recruitment.entity;

import org.apache.catalina.User;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "u_id")
	public Long id;
	
	@Transient
	public String name;
	
	@Column(name = "u_name")
	public String account;
	
	@Column(name = "u_pass")
	public String password;
	
	@Column(name = "u_problem")
	public String problem;
	
	@Column(name = "u_answer")
	public String answer;
	
	/**
	 * 1：管理员
	 * 2：企业
	 * 3：个人
	 */
	@Column(name = "u_type")
	public String type;
	
	public static String ADMIN = "1";
	
	public static String COMPANY = "2";
	
	public static String PERSONAL = "3";
}
