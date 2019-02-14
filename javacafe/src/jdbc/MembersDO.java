package jdbc;

public class MembersDO {
	String user_no;
	String password;
	String name;
	String email;
	String phone;
	String address1;
	String address2;
	String birth;
	String reg_date;
	String out_date;
	String grade;

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getOut_date() {
		return out_date;
	}

	public void setOut_date(String out_date) {
		this.out_date = out_date;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "MembersDO [user_no=" + user_no + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", phone=" + phone + ", address1=" + address1 + ", address2=" + address2 + ", birth=" + birth
				+ ", reg_date=" + reg_date + ", out_date=" + out_date + ", grade=" + grade + "]";
	}

}
