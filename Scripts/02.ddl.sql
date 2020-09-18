-- 접속자 확인
SELECT USER FROM DUAL;

/* 상품 
 * no 시퀀스 생성
 * */
CREATE TABLE PRODUCT (
   no NUMBER(5) PRIMARY KEY, /* 상품번호 */
   name VARCHAR2(100), /* 상품이름 */
   kind CHAR(1), /* 상품종류 */
   price NUMBER, /* 원가 */
   saleprice NUMBER, /* 판매가 */
   margin NUMBER, /* 수익 */
   content VARCHAR2(1000), /* 상품내용 */
   image VARCHAR2(100) DEFAULT 'default.jpg', /* 상품이미지 */
   del_useyn CHAR(1) DEFAULT 'y', /* 상품삭제여부 */
   best_useyn CHAR(1) DEFAULT 'n', /* 베스트상품여부 */
   reg_date DATE DEFAULT SYSDATE /* 등록일 */
);

/* 회원 */
CREATE TABLE MEMBER (
   id VARCHAR2(20) PRIMARY KEY, /* 회원아이디 */
   pwd VARCHAR2(20), /* 회원암호 */
   name VARCHAR2(100), /* 회원이름 */
   email VARCHAR2(40), /* 회원이메일 */
   zip_no CHAR(7), /* 우편번호 */
   address VARCHAR2(100), /* 주소 */
   phone CHAR(13), /* 전화번호 */
   unsign_useyn CHAR(1) DEFAULT 'y', /* 탈퇴여부 */
   join_date DATE DEFAULT SYSDATE /* 가입일 */
);


/* 장바구니
 * no 시퀀스 생성
 *  */
CREATE TABLE CART (
   no NUMBER(5) PRIMARY KEY, /* 장바구니번호 */
   memberId VARCHAR2(20), /* 회원아이디 */
   pNo NUMBER(5), /* 상품번호 */
   quantity NUMBER(5), /* 수량 */
   result_useyn CHAR(1), /* 처리완료여부 */
   reg_date DATE /* 등록일 */
);

/* 주문 */
CREATE TABLE ORDERS (
   no NUMBER(5) PRIMARY KEY, /* 주문번호 */
   id VARCHAR2(20), /* 주문자아이디 */
   order_date DATE /* 주문일 */
);

/* 주문상세
 * no 시퀀스 생성
 *  */
CREATE TABLE ORDER_DETAIL (
   no NUMBER(5) PRIMARY KEY, /* 주문상세번호 */
   oNo NUMBER(5), /* 주문번호 */
   pNo NUMBER(5), /* 상품번호 */
   quantity NUMBER(5), /* 주문수량 */
   result_useyn CHAR(1) /* 처리완료여부 */
);

/* QNA게시판
 * no 시퀀스 생성
 *  */
CREATE TABLE QNA (
   no NUMBER(5) PRIMARY KEY, /* 번호 */
   subject VARCHAR2(100), /* 제목 */
   content VARCHAR2(1000), /* 내용 */
   reply VARCHAR2(1000), /* 답변 */
   id VARCHAR2(20), /* 작성자아이디 */
   reply_useyn CHAR(1) DEFAULT '1', /* 답변여부 */
   write_date DATE DEFAULT SYSDATE/* 작성일 */
);

/* 주소 */
CREATE TABLE ADDRESS (
   zip_no CHAR(7), /* 우편번호 */
   sido VARCHAR2(100), /* 시도 */
   gugun VARCHAR2(100), /* 구군 */
   dong VARCHAR2(100), /* 동 */
   zip_code VARCHAR2(100), /* 우편코드 */
   bunji VARCHAR2(100) /* 번지 */
);

/* 관리자 */
CREATE TABLE WORKER (
   id VARCHAR2(20) PRIMARY KEY, /* 아이디 */
   pwd VARCHAR2(20), /* 암호 */
   name VARCHAR2(100), /* 이름 */
   phone CHAR(13) /* 전화번호 */
);

---------------------------------------------
------------------ 외래키 ---------------------
---------------------------------------------

ALTER TABLE CART
   ADD
      CONSTRAINT FK_PRODUCT_TO_CART
      FOREIGN KEY (
         pNo
      )
      REFERENCES PRODUCT (
         no
      );

ALTER TABLE CART
   ADD
      CONSTRAINT FK_MEMBER_TO_CART
      FOREIGN KEY (
         memberId
      )
      REFERENCES MEMBER (
         id
      );

ALTER TABLE ORDERS
   ADD
      CONSTRAINT FK_MEMBER_TO_ORDER
      FOREIGN KEY (
         id
      )
      REFERENCES MEMBER (
         id
      );

ALTER TABLE ORDER_DETAIL
   ADD
      CONSTRAINT FK_PRODUCT_TO_ORDER_DETAIL
      FOREIGN KEY (
         pNo
      )
      REFERENCES PRODUCT (
         no
      );

ALTER TABLE ORDER_DETAIL
   ADD
      CONSTRAINT FK_ORDER_TO_ORDER_DETAIL
      FOREIGN KEY (
         oNo
      )
      REFERENCES ORDERS (
         no
      );
      
--------- product(no) 시퀀스 생성 ---------
CREATE SEQUENCE PRODUCT_NO_SEQ
   START WITH 1
   INCREMENT BY 1;

-- 트리거를 이용하여 자동으로 번호 입력되도록 --
CREATE OR REPLACE TRIGGER TRI_PRODUCT_NO_SEQ
BEFORE INSERT ON PRODUCT
FOR EACH ROW 
BEGIN 
   IF INSERTING AND :NEW.NO IS NULL THEN
      SELECT PRODUCT_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
   END IF;
END;

DROP TRIGGER TRI_PRODUCT_NO_SEQ;


--------- cart(no) 시퀀스 생성 ---------
CREATE SEQUENCE CART_NO_SEQ
   START WITH 1
   INCREMENT BY 1;

-- 트리거를 이용하여 자동으로 번호 입력되도록 --
CREATE OR REPLACE TRIGGER TRI_CART_NO_SEQ
BEFORE INSERT ON CART
FOR EACH ROW 
BEGIN 
   IF INSERTING AND :NEW.NO IS NULL THEN
      SELECT CART_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
   END IF;
END;

DROP TRIGGER TRI_CART_NO_SEQ;

--------- order_detail(no) 시퀀스 생성 ---------
CREATE SEQUENCE ORDER_DETAIL_NO_SEQ
   START WITH 1
   INCREMENT BY 1;

-- 트리거를 이용하여 자동으로 번호 입력되도록 --
CREATE OR REPLACE TRIGGER TRI_ORDER_DETAIL_NO_SEQ
BEFORE INSERT ON ORDER_DETAIL
FOR EACH ROW 
BEGIN 
   IF INSERTING AND :NEW.NO IS NULL THEN
      SELECT ORDER_DETAIL_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
   END IF;
END;

DROP TRIGGER TRI_ORDER_DETAIL_NO_SEQ;

--------- qna(no) 시퀀스 생성 ---------
CREATE SEQUENCE QNA_NO_SEQ
   START WITH 1
   INCREMENT BY 1;

-- 트리거를 이용하여 자동으로 번호 입력되도록 --
CREATE OR REPLACE TRIGGER TRI_QNA_NO_SEQ
BEFORE INSERT ON QNA
FOR EACH ROW 
BEGIN 
   IF INSERTING AND :NEW.NO IS NULL THEN
      SELECT QNA_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
   END IF;
END;

DROP TRIGGER TRI_QNA_NO_SEQ;