package com.vp.scheduler.entity.tiptop.twmd;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "CQR_File", schema = "TWMD")
@NoArgsConstructor
@Entity
@Data
public class MdCqrFile implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column
	private String cqr01;

	@Column
	private Date cqr02;

	@Column
	private String cqr03;

	@Column
	private String cqrno1;

	@Column
	private String cqrno2;
}
