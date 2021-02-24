package org.lxc.recruitment.entity;

import org.jetbrains.annotations.Nullable;

import javax.persistence.*;

@Entity
@Table(name ="letter")
public class LetterEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "l_id")
	public Long id;
	
	@Column(name = "u_send_id")
	public Long sender;
	
	@Transient
	public String senderName;
	
	@Column(name = "u_receive_id")
	public Long receiver;
	
	@Column(name = "l_title")
	public String title;
	
	@Column(name = "l_content")
	public String content;
	
	@Column(name = "l_type")
	public String type;
	
	@Transient
	public static final String DELIVERY = "delivery";
	@Transient
	public static final String INTERVIEW = "interview";
}
