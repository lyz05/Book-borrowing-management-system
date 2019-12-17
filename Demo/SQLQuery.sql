/*�������ݿ⣨BookDB��*/
CREATE DATABASE [BookDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = 'BookDB', FILENAME = 'D:\SQ\BookDB.mdf' , SIZE = 8192KB , MAXSIZE = 30720KB , FILEGROWTH = 1024KB )
 LOG ON 
( NAME = 'BookLog', FILENAME = 'D:\SQ\BookLog.ldf' , SIZE = 5120KB , MAXSIZE = 20480KB , FILEGROWTH = 1024KB )

/*ת����BookDB�����ݿ�*/
use BookDB;
/*�������ݿ��޷���ʾ���ĵ�����*/
ALTER DATABASE [BookDB] COLLATE Chinese_PRC_CI_AS;

/*������ṹ*/
CREATE TABLE Book
(
	bookNO char(10) primary key,
	bookName char(40),
	authorName char(8),
	publishingName char(20),
	price decimal(7,2),
	publishingDate date,
	shopNum int
)

CREATE TABLE Reader
(
	readerNO char(8) primary key,
	readerName char(8),
	sex char(2),
	identitycard char(18),
	workUnit char(50),
--	borrowCount tinyint,
	password char(256)
)

CREATE TABLE Borrow
(
	readerNO char(8) foreign key(readerNO) references Reader(readerNO),
	bookNO char(10) foreign key(bookNO) references Book(bookNO),
	borrowDate date,
	shouldDate date,
	returnDate date
)


/*ͼ���(Book)���ݣ�*/
INSERT INTO Book VALUES('B200101001','���ξ���ѧ','����','�й������ѧ������',31.80,'19910101',5);
INSERT INTO Book VALUES('B200201001','��ѧӢ��','֣����','�����ѧ���о�������',35.20,'19920101',3);
INSERT INTO Book VALUES('B200301001','���ݿ�ϵͳԭ��','�⾩��','�廪��ѧ������',58.20,'19930101',3);
INSERT INTO Book VALUES('B200101002','΢�۾���ѧ','����','�ߵȽ���������',41.80,'19910102',2);
INSERT INTO Book VALUES('B200101003','��۾���ѧ','Ԭ��ʥ','�й��ƾ����ó�����',51.80,'19910103',2);
INSERT INTO Book VALUES('B200201002','����Ӣ��','������','�Ϻ�����ѧԺ������',45.20,'19920102',5);
INSERT INTO Book VALUES('B200201003','����Ӣ��2','�����','������ͨ��ѧ������',55.20,'19920103',3);
INSERT INTO Book VALUES('B200301002','��������','����','�㽭��ѧ������',38.20,'19930102',1);
INSERT INTO Book VALUES('B200301003','�˹�����','������','���ӹ�ҵ������',43.20,'19930103',5);
INSERT INTO Book VALUES('B200301004','�㷨��������','�»���','��ѧ������',58.20,'19930104',10);


/*���߱�Reader�����ݣ�*/
insert Reader values('R2005001','��С��','F','412723199001014321','ͳһ�ɷ����޹�˾','')
insert Reader values('R2006001','����','F','412723199003014321','���Ϲɷ����޹�˾','')
insert Reader values('R2007001','�ߴ���','M','412723199005014321','�鶼�ɷ����޹�˾','')
insert Reader values('R2008001','�»�','M','412723199111014321','�ϲ��е������ƹ�˾','')
insert Reader values('R2009001','����','F','412723199208014321','��ʿ���Ƽ�����','')
insert Reader values('R2005002','��¶','F','412723199002014321','��¡�ɷ����޹�˾','')
insert Reader values('R2006002','����ǿ','M','412723199004014321','���´�ɷ����޹�˾','')
insert Reader values('R2007002','����÷','F','412723199112014321','���缼��������˾','')
insert Reader values('R2008002','����','M','412723199110014321','�Ϻ������о���','')
insert Reader values('R2009002','����ƽ','M','412723199209014321','����Ԫ���޹�˾','')

/*���ı�Borrow�����ݣ�*/
insert Borrow values('R2005001','B200101001','20110901','20111001',null)
insert Borrow values('R2006001','B200101001','20110907','20111007','20111005')
insert Borrow values('R2007001','B200201001','20110913','20111013','20111010')
insert Borrow values('R2008001','B200301001','20110915','20111015','20111013')
insert Borrow values('R2009001','B200301001','20110918','20111018',null)
insert Borrow values('R2005001','B200101002','20110902','20111002','20111001')
insert Borrow values('R2005001','B200101003','20110903','20111003',null)
insert Borrow values('R2005002','B200101001','20110904','20111004','20111003')
insert Borrow values('R2005002','B200101002','20110905','20111005',null)
insert Borrow values('R2005002','B200101003','20110906','20111006','20111008')
insert Borrow values('R2006001','B200101002','20110908','20111008','20111006')
insert Borrow values('R2006001','B200101003','20110909','20111009','20111012')
insert Borrow values('R2009002','B200201001','20110910','20111010','20111008')
insert Borrow values('R2006002','B200201002','20110911','20111011','20111008')
insert Borrow values('R2006002','B200201003','20110912','20111012','20111008')
insert Borrow values('R2007001','B200201002','20110914','20111014',null)
insert Borrow values('R2007001','B200201003','20110915','20111015','20111010')
insert Borrow values('R2009002','B200201001','20110916','20111016','20111014')
insert Borrow values('R2007002','B200201002','20110917','20111017','20111014')
insert Borrow values('R2007002','B200201003','20110917','20111017','20111014')

/* ������ͼ */
--������ͼ
go
create view View_Reader 
as
select Reader.readerNO ���߱��,readerName ��������,sex �Ա�,identitycard ���֤��,workUnit ������λ,count(Borrow.readerNO) �������� from Borrow
right join Reader on Borrow.readerNO=Reader.readerNO
left join Book on Borrow.bookNO=Book.bookNO 
group by Reader.readerNO,Borrow.readerNO,readerName,sex,identitycard,workUnit;
select * from View_Reader;

--�鱾��ͼ
go
create view View_Book
as
select Book.bookNO ͼ����,bookName ͼ������,authorName ����,publishingName ������,publishingDate ��������,shopNum �������,shopNum-count(Borrow.bookNO) �ڿ����� from Borrow
right join Book on Borrow.bookNO=Book.bookNO 
left join Reader on Borrow.readerNO=Reader.readerNO
group by Book.bookNO,bookName,authorName,publishingName,publishingDate,shopNum;
select * from View_Book;

/* ���Ĳ��� */
-- ����
insert Borrow values('R2007002','B200201003','20110917','20111017','20111014')
-- ����
update borrow set returnDate=getdate() from borrow
where readerNO = 'R2009001'

select * from Borrow;

/* ��ҵ������ע�͵�
/*��ѯ����*/
--select * from Book;
/*����1:��ѯ1991������Ķ���������������λ�����֤��*/
select readerName ��������,workUnit ������λ,identitycard ���֤�� from Reader where identitycard like '______1991________';
/*����2:��ѯ�ڸ�ʿ���Ƽ����Ź����Ķ��߱�š��������Ա�*/
select readerNO ���߱��,readerName ����,sex �Ա� from Reader where workUnit = '��ʿ���Ƽ�����';
--select * from Book;
/*����3:��ѯͼ�����к���"���ݿ�"��ͼ�����ϸ��Ϣ*/
select * from Book where bookName like '%���ݿ�%';
/*����4:��ѯԬ��ʥ��ʦ��д�ĵ��۲�����40Ԫ��ÿ��ͼ���ͼ���š��������*/
select bookNO ͼ����,shopNum ������� from Book where authorName = 'Ԭ��ʥ' and price>=40;
/*����5:��ѯ��1995-1996��֮������ͼ���š�����ʱ�䡢���ʱ���ͼ�����ƣ��������ʱ���������*/
select bookNO ͼ����,publishingDate ����ʱ��,shopDate ���ʱ��,bookName ͼ������ from Book where year(shopDate) between 1995 and 1996 order by shopDate;
*/