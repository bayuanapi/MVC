package com.bayua.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="M_EMPLOYEE")
@Data
public class M_EMPLOYEE {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="ID_NUMBER")
	private Integer idNumber;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="BIRTH_DATE")
	private Date birth_date;
	
	@Column(name="POSITION_ID")
	private Integer positionId;
	
	@Column(name="GENDER")
	private Integer gender;
	
	@Column(name="IS_DELETE")
	private Integer isDelete;

	
}
