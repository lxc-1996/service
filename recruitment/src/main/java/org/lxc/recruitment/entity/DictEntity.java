package org.lxc.recruitment.entity;

import javax.persistence.*;

/**
 * 字典
 */
@Entity
@Table(name ="dict")
public class DictEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long id;
	
	@Column(name = "name")
	public String value;
	
	@Column(name = "root")
	public Long rootId;
}
