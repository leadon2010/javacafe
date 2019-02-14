/**
 * 파일명 : Board.java
 * 작성자 : 김유미
 * 파일설명 :
 */
package board;

/**
 * @author user
 *
 */
public class Board {
	String no; // 번호
	String title; // 제목
	String contents; // 내용
	String wdate; // 작성일자
	String userid; // 작성자
	String originalFileName; // 원래파일명
	String uploadedFileName; // 업로드된 파일명

	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}

	/**
	 * @param no
	 *            the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * @param contents
	 *            the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * @return the wdate
	 */
	public String getWdate() {
		return wdate;
	}

	/**
	 * @param wdate
	 *            the wdate to set
	 */
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the originalFileName
	 */
	public String getOriginalFileName() {
		return originalFileName;
	}

	/**
	 * @param originalFileName
	 *            the originalFileName to set
	 */
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	/**
	 * @return the uploadedFileName
	 */
	public String getUploadedFileName() {
		return uploadedFileName;
	}

	/**
	 * @param uploadedFileName
	 *            the uploadedFileName to set
	 */
	public void setUploadedFileName(String uploadedFileName) {
		this.uploadedFileName = uploadedFileName;
	}
}
