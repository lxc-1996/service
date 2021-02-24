package org.lxc.recruitment.entity;

import javax.persistence.*;

/**
 * 审核
 */
@Entity
@Table(name ="audit_info")
public class AuditEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ai_id")
	public Long id;
	
	@Column(name = "a_id")
	public Long adminId;
	
	@Column(name = "j_id")
	public Long jobId;
}
