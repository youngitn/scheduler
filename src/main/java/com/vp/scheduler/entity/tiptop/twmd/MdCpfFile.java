package com.vp.scheduler.entity.tiptop.twmd;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.vp.scheduler.entity.tiptop.twvp.GemFile;

import lombok.Data;

//@NamedEntityGraphs(value = {
//	    @NamedEntityGraph(name = "emp.all", attributeNodes = {
//	        @NamedAttributeNode("depinfo")
//	    })
//	})
@NamedEntityGraph(name = "MdCpfFile.depinfo", attributeNodes = { @NamedAttributeNode("depinfo") })
@Table(name = "CPF_File", schema = "TWMD")
@Entity
@Data
public class MdCpfFile implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column
	private String cpf01;

	@Column
	private String cpf02;

	@Column(insertable = false, updatable = false)
	private String cpf29;

	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cpf29", referencedColumnName = "gem01")
	private GemFile depinfo;

}
