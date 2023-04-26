package com.bayua.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="M_POSITION")
@Data
public class M_POSITION {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="IS_DELETE")
	private Integer isDelete;

}
