package nonageshop.dto;

import java.util.Date;

public class Member {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String zipNo;
	private String address;
	private String phone;
	private String useyn;
	private Date joindate;

	public Member(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(String id) {
		super();
		this.id = id;
	}

	public Member(String id, String pwd, String name, String email, String zipNo, String address, String phone,
			String useyn, Date joindate) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.zipNo = zipNo;
		this.address = address;
		this.phone = phone;
		this.useyn = useyn;
		this.joindate = joindate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipNo() {
		return zipNo;
	}

	public void setZipNo(String zipNo) {
		this.zipNo = zipNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUseyn() {
		return useyn;
	}

	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", zipNo=" + zipNo
				+ ", address=" + address + ", phone=" + phone + ", useyn=" + useyn + ", joindate=" + joindate + "]";
	}

}
