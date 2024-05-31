package test3;

public class DbTable {

	// 테이블 정의서를 보고 필드(변수)를 선언하시오.
	private int no;
	private String email;
	private String password;
	private boolean delYn;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public boolean isDelYn() {
		return delYn;
	}
	public void setDelYn(boolean delYn) {
		this.delYn = delYn;
	}
	@Override
	public String toString() {
		return "DbTable [no=" + no + ", email=" + email + ", password=" + password + ", delYn=" + delYn + "]";
	}
}
