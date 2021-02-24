package org.lxc.recruitment.entity;

import javax.persistence.*;

@Entity
@Table(name ="user_delete")
public class UserDeleteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ud_id")
	public Long id;
	@Column(name = "a_id")
	public Long adminId;
	@Column(name = "ud_deleted_id")
	public Long deletedId;
	@Column(name = "ud_reason")
	public String reason;
}
