package org.lxc.recruitment.entity;

import javax.persistence.*;

@Entity
@Table(name = "news")
public class NewsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_id")
	public Long id;
	@Column(name = "a_id")
	public Long adminId;
	@Column(name = "n_title")
	public String title;
	@Column(name = "n_content")
	public String content;
}
