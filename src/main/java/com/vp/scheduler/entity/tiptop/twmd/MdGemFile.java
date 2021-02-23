package com.vp.scheduler.entity.tiptop.twmd;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "GEM_File", schema = "TWMD")

@Entity
@Data
public class MdGemFile implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column
	private String gem01;

	@Column
	private String gem02;
}
