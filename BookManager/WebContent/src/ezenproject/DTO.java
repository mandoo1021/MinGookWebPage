package ezenproject;

import java.util.Date;

public class DTO {

	String code;
	String bookname;
	String author;
	String editorial;
	Date publish_day;
	String condition;
	String kind;
	int remain;
	int category;
	//String pattern = "[A-C]{1}[a-zA-Z]{2}[0-9]{3}";

	public String getCode() {

		return code;
	}

	public void setCode(String code) {

		this.code = code;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public Date getPublish_day() {
		return publish_day;
	}

	public void setPublish_day(Date publish_day) {
		this.publish_day = publish_day;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getRemain() {
		return remain;
	}

	public void setRemain(int remain) {
		this.remain = remain;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public void CheckString(String checkString) {

	}

	public void CheckInt(int checknum) {

	}

}