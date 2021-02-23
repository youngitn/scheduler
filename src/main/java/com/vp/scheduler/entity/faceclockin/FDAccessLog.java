package com.vp.scheduler.entity.faceclockin;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "FD_Access_Log")
@NoArgsConstructor
@Entity
@Data
public class FDAccessLog implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nIndex")
	String nIndex;

	@Column(name = "staff_index")
	String staffIndex;

	@Column(name = "data")
	String data;

	@Column(name = "created_at")
	String createdAt;
}
