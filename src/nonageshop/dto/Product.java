package nonageshop.dto;

import java.util.Date;

public class Product {
	private int no; // 상품번호
	private String name; // 상풍명
	private String kind; // 종류
	private int price; // 원가
	private int salePrice; // 판매가
	private int magin; // 수익
	private String content; // 상품내용
	private String image; // 상품이미지
	private String delYn; // 상품삭제여부 'y'사용 , 'n'삭제
	private String bestYn; // 베스트상품여부
	private Date regDate; // 등록일

	public Product(int no, String name, int salePrice, String image) {
		super();
		this.no = no;
		this.name = name;
		this.salePrice = salePrice;
		this.image = image;
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

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public int getMagin() {
		return magin;
	}

	public void setMagin(int magin) {
		this.magin = magin;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getBestYn() {
		return bestYn;
	}

	public void setBestYn(String bestYn) {
		this.bestYn = bestYn;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Product [no=" + no + ", name=" + name + ", kind=" + kind + ", price=" + price + ", salePrice="
				+ salePrice + ", magin=" + magin + ", content=" + content + ", image=" + image + ", delYn=" + delYn
				+ ", bestYn=" + bestYn + ", regDate=" + regDate + "]";
	}

	public Product(int no, String name, String kind, int price, int salePrice, int magin, String content, String image,
			String delYn, String bestYn, Date regDate) {
		super();
		this.no = no;
		this.name = name;
		this.kind = kind;
		this.price = price;
		this.salePrice = salePrice;
		this.magin = magin;
		this.content = content;
		this.image = image;
		this.delYn = delYn;
		this.bestYn = bestYn;
		this.regDate = regDate;
	}

}
