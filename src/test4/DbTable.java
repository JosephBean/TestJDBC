package test4;

import java.time.LocalDate;
import java.util.Date;

public class DbTable {

	// 테이블 정의서를 보고 필드(변수)를 선언하시오.
	private String empno;
	private String name;
	private String email;
	private String deptname;
	private String position;
	private String phone;
	private int age;
	private Boolean isused;
	private String isused2;
	private String createdate;
	private String updatedate;
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
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
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Boolean isIsused() {
		return isused;
	}
	public void setIsused(Boolean isused) {
		this.isused = isused;
	}
	public String getIsused2() {
		return isused2;
	}
	public void setIsused2(String isused2) {
		this.isused2 = isused2;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	@Override
	public String toString() {
		return "DbTable [empno=" + empno + ", name=" + name + ", email=" + email + ", deptname=" + deptname
				+ ", position=" + position + ", phone=" + phone + ", age=" + age + ", isused=" + isused
				+ ", isused2=" + isused2
				+ ", createdate=" + createdate + ", updatedate=" + updatedate + "]";
	}
	
}
