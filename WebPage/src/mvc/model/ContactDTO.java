package mvc.model;

public class ContactDTO {

	private int num;			// ������ ����
	private String user_id;		// ���� ���̵�
	private String user_name;	// ���� �̸�
	private String user_phone; 	// ���� ����ó
	private String subject;		// �Խñ� ����
	private String content;		// �Խñ� ����
	private String regist_day;	// �Խñ� �������
	private String ip;			// �Խñ� ��� ip
	
	// �⺻ ������
	public ContactDTO() {
		// TODO Auto-generated constructor stub
	}

	// Getter, Setter �޼ҵ�
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
