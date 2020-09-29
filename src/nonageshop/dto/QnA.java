package nonageshop.dto;

import java.util.Date;

public class QnA {
	private int no;
	private String subject;
	private String content;
	private String rep;
	private String id;
	private String repYN;
	private Date writeDate;

	public QnA() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QnA(int no, String subject, String content, String rep, String id, String repYN, Date writeDate) {
		super();
		this.no = no;
		this.subject = subject;
		this.content = content;
		this.rep = rep;
		this.id = id;
		this.repYN = repYN;
		this.writeDate = writeDate;
	}

	@Override
	public String toString() {
		return "QnA [no=" + no + ", subject=" + subject + ", content=" + content + ", rep=" + rep + ", id=" + id
				+ ", repYN=" + repYN + ", writeDate=" + writeDate + "]";
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public String getRep() {
		return rep;
	}

	public void setRep(String rep) {
		this.rep = rep;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRepYN() {
		return repYN;
	}

	public void setRepYN(String repYN) {
		this.repYN = repYN;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public QnA(String subject, String content, String id) {
		super();
		this.subject = subject;
		this.content = content;
		this.id = id;
	}

}
