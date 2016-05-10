package vn.com.hiringviet.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY_PHOTO", catalog = "hiringviet")
public class CompanyPhoto implements Serializable {

	private static final long serialVersionUID = -6886420826151394119L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMPANY_PHOTO_ID", nullable = false, length = 11)
	private Integer companyPhotoID;

	@Column(name = "COMPANY_ID", nullable = false, length = 11)
	private Integer companyID;

	@Column(name = "PHOTO", nullable = false)
	private byte[] photo;

	@Column(name = "TITLE", nullable = true)
	private String title;

	@Column(name = "DESCRIPTION", nullable = true)
	private String description;

	@Column(name = "STATUS", nullable = false)
	private Integer status;

	@Column(name = "CREATED_AT", nullable = false)
	private Date createdAt;

	@Column(name = "UPDATED_AT", nullable = false)
	private Date updatedAt;

	@Column(name = "DELETED_AT")
	private Date deletedAt;

	public Integer getCompanyPhotoID() {
		return companyPhotoID;
	}

	public void setCompanyPhotoID(Integer companyPhotoID) {
		this.companyPhotoID = companyPhotoID;
	}

	public Integer getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

}