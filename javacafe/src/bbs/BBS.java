package bbs;

import java.util.Date;

public class BBS {

	private String bbsnum; // 게시글 번호
	private String title; // 제목
	private String contents; // 내용
	private String ref; // 댓글
	private String re_step; // 글 달린 순서
	private Date reg_date; // 작성일자
	private String readcount; // 조회수
	private String password_yn; // 비밀글 승인여부
	private String ref_lev; // 부모게시글에 대한 댓글 그룹번호
	private String user_no; // 회원정보
	private String prod_no; // 상품가격
	

	// 생성자 (필수입력 )
	public BBS(String bbsnum, String title, String contents, String ref, String re_step, Date reg_date,
			String readcount, String password_yn, String ref_lev, String user_no, String prod_no) {
		super();
		this.bbsnum = bbsnum;
		this.title = title;
		this.contents = contents;
		this.ref = ref;
		this.re_step = re_step;
		this.reg_date = reg_date;
		this.readcount = readcount;
		this.password_yn = password_yn;
		this.ref_lev = ref_lev;
		this.user_no = user_no;
		this.prod_no = prod_no;
	}

	public BBS() {
		// TODO Auto-generated constructor stub
	}

	public String getBbsnum() {
		return bbsnum;
	}

	public void setBbsnum(String bbsnum) {
		this.bbsnum = bbsnum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getRe_step() {
		return re_step;
	}

	public void setRe_step(String re_step) {
		this.re_step = re_step;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getReadcount() {
		return readcount;
	}

	public void setReadcount(String readcount) {
		this.readcount = readcount;
	}

	public String getPassword_yn() {
		return password_yn;
	}

	public void setPassword_yn(String password_yn) {
		this.password_yn = password_yn;
	}

	public String getRef_lev() {
		return ref_lev;
	}

	public void setRef_lev(String ref_lev) {
		this.ref_lev = ref_lev;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}	
	
	
	public String getProd_no() {
		return prod_no;
	}

	public void setProd_no(String prod_no) {
		this.prod_no = prod_no;
	}

	@Override
	public String toString() {
		return "BBS [bbsnum=" + bbsnum + ", title=" + title + ", contents=" + contents + ", ref=" + ref + ", re_step="
				+ re_step + ", reg_date=" + reg_date + ", readcount=" + readcount + ", password_yn=" + password_yn
				+ ", ref_lev=" + ref_lev + ", user_no=" + user_no + ", prod_no=" + prod_no + "]";
	}

}