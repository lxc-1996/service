package org.lxc.recruitment.entity;

import javax.persistence.*;

/**
 * 收藏
 */
@Entity
@Table(name ="collection")
public class CollectionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "col_id")
	public Long id;
	@Column(name = "p_id")
	public Long userId;
	@Column(name = "j_id")
	public Long jobId;
	@Transient
	public String jobName;
}
