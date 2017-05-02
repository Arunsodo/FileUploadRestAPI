package com.fileapi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * This contains all the meta-data, and will be saved in HSQLDB. 
 *
 */
@Entity
@Table(name = "file")
public class FileUploaded implements Serializable {

	private static final long serialVersionUID = 16467984321134867L;
	private long id;
	private String name;
	private long size;
	private Date timeUploaded;
	private String path;
	//private String user;

	public FileUploaded() {
		super();
	}

	public FileUploaded(long id, String name, long size, Date timeUploaded) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.timeUploaded = timeUploaded;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "file_name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "file_size")
	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@Column(name = "upload_time")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTimeUploaded() {
		return timeUploaded;
	}

	public void setTimeUploaded(Date timeUploaded) {
		this.timeUploaded = timeUploaded;
	}

	@Column(name = "file_path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "FileMetaData [id=" + id + ", name=" + name + ", size=" + size + ", timeUploaded=" + timeUploaded + "]";
	}

}
