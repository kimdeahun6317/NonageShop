package nonageshop.dto;

import java.util.Date;

public class Cart {
	private int no;
	private Member member;
	private Product product;
	private int quantity;
	private String useyn;
	private Date regDate;

	public String getUseyn() {
		return useyn;
	}

	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}

	public Cart(int no, Member member, Product product, int quantity, String useyn, Date regDate) {
		super();
		this.no = no;
		this.member = member;
		this.product = product;
		this.quantity = quantity;
		this.useyn = useyn;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Cart [no=" + no + ", member=" + member + ", product=" + product + ", quantity=" + quantity
				+ ", regDate=" + regDate + "]";
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(Member member, Product product, int quantity, Date regDate) {
		super();
		this.member = member;
		this.product = product;
		this.quantity = quantity;
		this.regDate = regDate;
	}

}
