package com.visfull.bz.domain;

// Generated 2013-1-22 13:45:08 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

/**
 * BzDataTree generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "bz_data_tree")
public class BzDataTree implements java.io.Serializable {
	
	@Expose
	private Long id;
	@Expose
	private DataType dataType;
	@Expose
	private String dataName;
	@Expose
	private NodeType nodeType;
	@Expose
	private Long pid;
	@Expose
	private Date createDate;

	public BzDataTree() {
	}

	public BzDataTree(DataType dataType, String dataName, NodeType nodeType,
			Long pid, Date createDate) {
		this.dataType = dataType;
		this.dataName = dataName;
		this.nodeType = nodeType;
		this.pid = pid;
		this.createDate = createDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "data_type", length = 32)
	public DataType getDataType() {
		return this.dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	@Column(name = "data_name", length = 64)
	public String getDataName() {
		return this.dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "node_type", length = 32)
	public NodeType getNodeType() {
		return this.nodeType;
	}

	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}

	@Column(name = "pid")
	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public enum DataType implements IEnumDisplay{
		CATALOG("分类"),OTHER("其他");
		private String msg ;
		private DataType(String msg){
			this.msg = msg;
		}
		public String getDisplayName() {
			return msg;
		}
	}
	
	public enum NodeType implements IEnumDisplay{
		ROOT("根节点"),NODE("节点"),LEAF("叶子");
		private String msg ;
		private NodeType(String msg){
			this.msg = msg;
		}
		public String getDisplayName() {
			return msg;
		}
	}
}
