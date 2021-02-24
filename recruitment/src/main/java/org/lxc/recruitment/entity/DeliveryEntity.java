package org.lxc.recruitment.entity;

import javax.persistence.*;

@Entity
@Table(name ="delivery")
public class DeliveryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "d_id")
	public Long id;
	@Column(name = "r_id")
	public Long resumeId;
	@Column(name = "j_id")
	public Long jobId;
	@Transient
	public Long sender;
	@Transient
	public String senderName;
}
