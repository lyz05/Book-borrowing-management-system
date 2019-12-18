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
	bookName char(40) not null,
	authorName char(20) not null,
	publishingName char(30) not null,
	price decimal(7,2) not null,
	publishingDate date not null,
	shopNum int not null
)

CREATE TABLE Reader
(
	readerNO char(8) primary key,
	readerName char(8) not null,
	sex char(2) not null check(sex='��' or sex='Ů'),
	identitycard char(18) not null check(len(identitycard)=18),
	workUnit char(50) not null,
--	borrowCount tinyint,
	password char(256) not null
)

CREATE TABLE Borrow
(
	readerNO char(8) foreign key(readerNO) references Reader(readerNO),
	bookNO char(10) foreign key(bookNO) references Book(bookNO),
	borrowDate date not null,
	shouldDate date not null,
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
insert Reader values('R2005001','��С��','Ů','412723199001014321','ͳһ�ɷ����޹�˾','')
insert Reader values('R2006001','����','Ů','412723199003014321','���Ϲɷ����޹�˾','')
insert Reader values('R2007001','�ߴ���','��','412723199005014321','�鶼�ɷ����޹�˾','')
insert Reader values('R2008001','�»�','��','412723199111014321','�ϲ��е������ƹ�˾','')
insert Reader values('R2009001','����','Ů','412723199208014321','��ʿ���Ƽ�����','')
insert Reader values('R2005002','��¶','Ů','412723199002014321','��¡�ɷ����޹�˾','')
insert Reader values('R2006002','����ǿ','��','412723199004014321','���´�ɷ����޹�˾','')
insert Reader values('R2007002','����÷','Ů','412723199112014321','���缼��������˾','')
insert Reader values('R2008002','����','��','412723199110014321','�Ϻ������о���','')
insert Reader values('R2009002','����ƽ','��','412723199209014321','����Ԫ���޹�˾','')

/*���ı�Borrow�����ݣ�*/
insert Borrow values('R2005001','B200101001','20110901','20111001',null)
insert Borrow values('R2006001','B200101001','20110907','20111007','20111005')
insert Borrow values('R2007001','B200201001','20110913','20111013','20111010')
insert Borrow values('R2008001','B200301001','20110915','20111015','20111013')
insert Borrow values('R2009001','B200301001','20110918','20111018',null)
insert Borrow values('R2005001','B200101002','20110902','20111002',null)
insert Borrow values('R2005001','B200101003','20110903','20111003',null)
insert Borrow values('R2005002','B200101001','20110904','20111004','20111003')
insert Borrow values('R2005002','B200101002','20110905','20111005',null)
insert Borrow values('R2005002','B200101003','20110906','20111006','20111008')
insert Borrow values('R2006001','B200101002','20110908','20111008','20111006')
insert Borrow values('R2006001','B200101003','20110909','20111009',null)
insert Borrow values('R2009002','B200201001','20110910','20111010','20111008')
insert Borrow values('R2006002','B200201002','20110911','20111011','20111008')
insert Borrow values('R2006002','B200201003','20110912','20111012','20111008')
insert Borrow values('R2007001','B200201002','20110914','20111014',null)
insert Borrow values('R2007001','B200201003','20110915','20111015','20111010')
insert Borrow values('R2009002','B200201001','20110916','20111016','20111014')
insert Borrow values('R2007002','B200201002','20110917','20111017','20111014')
insert Borrow values('R2007002','B200201003','20110917','20111017','20111014')

/* ������ͼ */
--����δ����ͼ
go
create view View_Borrow_Not_Return
as
select * from Borrow where returnDate is null
go
select * from View_Borrow_Not_Return;

--�����ѻ���ͼ
go
create view View_Borrow_Return
as
select * from Borrow where returnDate is not null
go
select * from View_Borrow_Return;

-- ������ͼ
go
create view View_Borrow
as
select Borrow.readerNO ���߱��,readerName ����,Borrow.bookNO ͼ����,bookName ͼ������,authorName ����,publishingName ������,borrowDate ����ʱ��,shouldDate Ӧ�黹����,returnDate �黹���� from Borrow,Book,Reader where Borrow.bookNO=book.bookNO and Borrow.readerNO=Reader.readerNO
go
select * from View_Borrow;

--������ͼ
go
create view View_Reader 
as
select Reader.readerNO ���߱��,readerName ����,sex �Ա�,identitycard ���֤��,workUnit ������λ,count(Borrow.readerNO) �ܽ�������,count(Borrow.readerNO)-count(Borrow.returnDate) δ�黹���� from Borrow
right join Reader on Borrow.readerNO=Reader.readerNO
left join Book on Borrow.bookNO=Book.bookNO 
group by Reader.readerNO,Borrow.readerNO,readerName,sex,identitycard,workUnit;
go
select * from View_Reader;

--ͼ����ͼ
go
create view View_Book
as
select Book.bookNO ͼ����,bookName ͼ������,authorName ����,publishingName ������,publishingDate ��������,shopNum �������,shopNum-count(View_Borrow_Not_Return.bookNO) �ڿ����� from View_Borrow_Not_Return
right join Book on View_Borrow_Not_Return.bookNO=Book.bookNO 
left join Reader on View_Borrow_Not_Return.readerNO=Reader.readerNO
group by Book.bookNO,bookName,authorName,publishingName,publishingDate,shopNum;
go
select * from View_Book;


/* ���Ĵ��ڲ��� */
-- ������ʾ
select readerName from Reader where readerNO='R2005001'
-- ��ǰ������Ϣ��ʾ
select * from View_Borrow where ���߱��='R2005002' and �黹���� is null
-- ��ʷ������Ϣ��ʾ
select * from View_Borrow where ���߱��='R2005002' and �黹���� is not null
-- ͼ����Ϣ��ʾ
select * from View_Book where ͼ���� like '%2001%' and ͼ������ like '%����%' and ���� like '%��%' and ������ like '%�й�%' and �������� between '1990-01-01' and '2010-02-02'

select ͼ������,����,������ from View_Borrow
-- ����
if exists(select * from View_Book where ͼ����='B200201003' and �ڿ�����>0)
insert Borrow values('R2006002','B200201003','20110917','20111017',null)
-- ����
update Borrow set returnDate=getdate() from borrow
where readerNO = 'R2009001'
-- �޸�����
update Reader set password=encodeInp(pwd) from Reader
where readerNo = 'R2009001'

/* ������Ϣ���ڲ��� */
-- ��������
update Reader set password='' from Reader
where readerNo = 'R2009001'
-- ���
insert Reader values('R2010001','����','��','442000199001014321','��������ѧ�麣ѧԺ','')
-- ɾ��
delete from reader where readerNO = 'R2010001'
-- �޸�
update Reader set readerName='��÷÷',sex='Ů',identitycard='442000199501014321',workUnit='��������ѧ�麣ѧԺ' where readerNO='R2010001'
-- ��ѯ����
select * from View_Reader where ���߱�� like '%2006%' and �������� like '%��%' and �Ա�='Ů' and ���֤�� like '%1990%' and ������λ like '%��˾%'

/* ͼ������ڲ��� */
-- ���
INSERT INTO Book VALUES('B200301101','Java�������','��׿��','������ͨ��ѧ������',41.00,'20110520',3);
-- ɾ��
delete from Book where bookNO = 'B200301101'
-- �޸�
update Book set bookName='���ݿ�ϵͳԭ�������',authorName='��ѡ',publishingName='�廪��ѧ������',price=59.90,publishingDate='20090902',shopNum=6 where bookNO='B200301101'
-- ��ѯͼ��
--select * from View_Book where ͼ���� like '%2001%' and ͼ������ like '%����%' and ���� like '%��%' and ������ like '%�й�%' and �������� between '1990-01-01' and '2010-02-02'

/* ��¼���� */
select readerNO from Reader where readerNo='R2005001' and password=''

select bookNO from book where shopNum=1;
select * from Reader;
select * from Borrow;
select * from View_Book;

/* MD5���� */
select substring(sys.fn_sqlvarbasetostr(HashBytes('MD5','123456')),3,32)

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