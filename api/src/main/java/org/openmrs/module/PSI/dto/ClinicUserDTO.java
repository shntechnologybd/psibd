package org.openmrs.module.PSI.dto;

public class ClinicUserDTO {
	
	private int cuid;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String mobile;
	
	private String email;
	
	private String password;
	
	private String gender;
	
	private String roles;
	
	private int clinicId;
	
	public int getCuid() {
		return cuid;
	}
	
	public void setCuid(int cuid) {
		this.cuid = cuid;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getRoles() {
		return roles;
	}
	
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	public int getClinicId() {
		return clinicId;
	}
	
	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}
	
}
