package mvc.model;

public class ContactDTO {

	private int num;			// 문의함 순번
	private String user_id;		// 유저 아이디
	private String user_name;	// 유저 이름
	private String user_phone; 	// 유저 연락처
	private String subject;		// 게시글 제목
	private String content;		// 게시글 내용
	private String regist_day;	// 게시글 등록일자
	private String ip;			// 게시글 등록 ip
	
	// 기본 생성자
	public ContactDTO() {
		// TODO Auto-generated constructor stub
	}

	// Getter, Setter 메소드
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegist_day() {
		return regist_day;
	}

	public void setRegist_day(String regist_day) {
		this.regist_day = regist_day;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
