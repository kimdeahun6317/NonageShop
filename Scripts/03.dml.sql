SELECT * FROM PRODUCT ;

INSERT INTO PRODUCT (NAME,KIND,PRICE,SALEPRICE,MARGIN,CONTENT,IMAGE)values(
'크로그다일부츠', '2', '40000', '50000', '10000', '오지니랄 크로그다일부츠 입니다.', 'w2.jpg');

insert into PRODUCT (NAME,KIND,PRICE,SALEPRICE,MARGIN,CONTENT,IMAGE,BEST_USEYN) values( 
'롱부츠', '2', '40000', '50000', '10000','따뜻한 롱부츠 입니다.', 'w-28.jpg', 'n');

insert into PRODUCT (NAME,KIND,PRICE,SALEPRICE,MARGIN,CONTENT,IMAGE,BEST_USEYN) values( 
 '힐', '1', '10000', '12000', '2000', '여성용전용 힐', 'w-26.jpg', 'n');

insert into PRODUCT (NAME,KIND,PRICE,SALEPRICE,MARGIN,CONTENT,IMAGE,BEST_USEYN) values(
 '슬리퍼', '4', '5000', '5500', '500', '편안한 슬리퍼입니다.', 'w-25.jpg', 'y');

insert into PRODUCT (NAME,KIND,PRICE,SALEPRICE,MARGIN,CONTENT,IMAGE,BEST_USEYN) values(
 '회색힐', '1', '10000', '12000', '2000', '여성용전용 힐', 'w9.jpg', 'n');

insert into PRODUCT (NAME,KIND,PRICE,SALEPRICE,MARGIN,CONTENT,IMAGE) values(
 '여성부츠', '2', '12000', '18000', '6000', '여성용 부츠', 'w4.jpg');

insert into PRODUCT (NAME,KIND,PRICE,SALEPRICE,MARGIN,CONTENT,IMAGE,BEST_USEYN) values(
  '핑크샌달', '3', '5000', '5500', '500', '사계절용 샌달입니다.', 'w-10.jpg', 'y');
 
insert into PRODUCT (NAME,KIND,PRICE,SALEPRICE,MARGIN,CONTENT,IMAGE,BEST_USEYN) values(
 '슬리퍼', '3', '5000', '5500', '500', '편안한 슬리퍼입니다.', 'w11.jpg', 'y');

insert into PRODUCT (NAME,KIND,PRICE,SALEPRICE,MARGIN,CONTENT,IMAGE) values(
  '스니커즈', '4', '15000', '20000', '5000', '활동성이 좋은 스니커즈입니다.', 'w1.jpg');
 
insert into PRODUCT (NAME,KIND,PRICE,SALEPRICE,MARGIN,CONTENT,IMAGE,BEST_USEYN) values(
  '샌달', '3', '5000', '5500', '500', '사계절용 샌달입니다.', 'w-09.jpg','n');
 
insert into PRODUCT (NAME,KIND,PRICE,SALEPRICE,MARGIN,CONTENT,IMAGE,BEST_USEYN) values(
  '스니커즈', '5', '15000', '20000', '5000', '활동성이 좋은 스니커즈입니다.', 'w-05.jpg','n');
 
 
 insert into member(id, pwd, name, zip_no, address, phone) values
('one', '1111', '김나리', '133-110', '서울시성동구성수동1가 1번지21호', '017-777-7777');

insert into member(id, pwd, name, zip_no, address, phone) values
('two', '2222', '이백합', '130-120', '서울시송파구잠실2동 리센츠 아파트 201동 505호', '011-123-4567');
  
 --------------------------------------------------VIEW
 SELECT * FROM PRODUCT ;
 -- 신상품
create or replace view new_pro_view
as
select no, name, saleprice, image 
from( select rownum, no, name, saleprice, image 
      from product  
      where DEL_USEYN='y' 
      order by REG_DATE desc)
where  rownum <=4;

SELECT NO,NAME,SALEPRICE,IMAGE FROM  new_pro_view;

-- 베스트 상품
create or replace view best_pro_view
as
select no, name, saleprice, image 
from( select rownum, no, name, saleprice, image 
      from product  
      where BEST_USEYN='y' 
      order by REG_DATE desc)
where  rownum <=4;

SELECT NO,NAME,SALEPRICE,IMAGE FROM best_pro_view;

SELECT NO,NAME,KIND,PRICE,SALEPRICE,MAGIN,CONTENT,IMAGE,DELYN,BESTYN,REGDATE FROM PRODUCT WHERE kind =1;
 

INSERT INTO address ( zip_no, sido, gugun, dong, bunji,
zip_code ) VALUES ( 
'135-512', '서울', '강남구', '역삼2동 진달래아파트', '(10∼17동)', '446'); 
INSERT INTO address ( zip_no, sido, gugun, dong, bunji,
zip_code ) VALUES ( 
'135-918', '서울', '강남구', '역삼2동', '706∼707', '447'); 

--order view
SELECT * FROM ORDER_DETAIL;
SELECT * FROM "MEMBER";

create or replace view order_view
as
select d.NO dno, o.NO ono, o.id mid, o.ORDER_DATE , d.PNO pno, d.quantity, m.name mname,
       m.zip_no, m.address, m.phone, p.name pname, p.SALEPRICE , d.RESULT_USEYN result   
  from orders o JOIN order_detail d ON o.NO = d.ONO JOIN member m ON o.ID =m.ID 
       JOIN product p ON d.PNO = p.NO;
      
SELECT * FROM order_view;
 
SELECT 대훈대지 꿀때지 퇴근할땐 4족보행 FROM 뷁;

 
 
----------------------order
SELECT * FROM ORDERS ;

INSERT INTO ORDERS( ID,ORDER_DATE) VALUES( 'ONE',SYSDATE);
insert into orders( id,) values( 'one');
insert into orders( id,ORDER_DATE) values( 'test',sysdate);

SELECT * FROM ORDER_DETAIL od ;

INSERT INTO ORDER_DETAIL(ONO, PNO, QUANTITY,result_useyn) VALUES( 5, 29, 3,'n');
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 1, 2, 5);
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 2,  4, 3);
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 3, 3, 1);
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 3, 2, 1);
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 3, 6, 2);
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 3, 1, 2);
 
 
 ---------------------------------qna
 insert into qna (subject, content, id) values('테스트', '질문내용1', 'test');
update qna SET rep='답변내용', rep_yn='2';

insert into qna (subject, content, id)values('테스트2', '질문내용2', 'test');
 
 