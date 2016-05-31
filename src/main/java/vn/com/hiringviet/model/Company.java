package vn.com.hiringviet.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company implements Serializable {

	private static final long serialVersionUID = 7621411313072097608L;

	private Integer id;

	private Account account;

	private String displayName;

	private Integer companySize;

	private Address address;

	private String description;

	private Integer foundedYear;

	private String avatar;

	private String coverImage;

	private String website;

	private String location;

	private ChangeLog changeLog;

	private List<CompanyPhoto> companyPhotoList;

	private List<Job> jobList;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer companyID) {
		this.id = companyID;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="account_id")
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Column(name = "display_name")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name = "company_size")
	public Integer getCompanySize() {
		return companySize;
	}

	public void setCompanySize(Integer companySize) {
		this.companySize = companySize;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "founded_year")
	public Integer getFoundedYear() {
		return foundedYear;
	}

	public void setFoundedYear(Integer foundedYear) {
		this.foundedYear = foundedYear;
	}

	@Column(name = "avatar")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Column(name = "cover_avatar")
	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	@Column(name = "website")
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "location")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "change_log_id")
	public ChangeLog getChangeLog() {
		return changeLog;
	}

	public void setChangeLog(ChangeLog changeLog) {
		this.changeLog = changeLog;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.ALL)
	public List<CompanyPhoto> getCompanyPhotoList() {
		return companyPhotoList;
	}

	public void setCompanyPhotoList(List<CompanyPhoto> companyPhotoList) {
		this.companyPhotoList = companyPhotoList;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	public List<Job> getJobList() {
		return jobList;
	}

	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}

}
