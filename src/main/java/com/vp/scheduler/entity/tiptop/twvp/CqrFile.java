package com.vp.scheduler.entity.tiptop.twvp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.data.domain.Persistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "CQR_File", schema = "TWVP")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@IdClass(CqrPk.class)
public class CqrFile  implements Serializable  {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	private String cqr01;

	@Id
	@Column
	private Date cqr02;

	@Id
	@Column
	private String cqr03;
	
	@Column
	private Date cqr06;
	
	@Column
	private String cqr07;
	
	@Column
	private String cqracti;
	
	@Column
	private String cqruser;

	@Column
	private String cqrgrup;
	
	@Column
	private Date cqrdate;
	@Column
	private String cqrno1;

	@Column
	private String cqrno2;

	
}
