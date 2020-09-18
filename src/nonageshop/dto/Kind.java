package nonageshop.dto;

public class Kind {
	private int no;
	private String name;

	public Kind() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Kind(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}

	public Kind(int no) {
		super();
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Kind [no=" + no + ", name=" + name + "]";
	}

}
