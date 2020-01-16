/*�������ݿ⣨BookDB��*/
CREATE DATABASE BookDB;

/*ת����BookDB�����ݿ�*/
go
use BookDB;

/*�������ṹ*/
CREATE TABLE AdminUsers
(
	username char(8) primary key,
	password char(255) not null
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE Book
(
	bookNO char(10) primary key,
	bookName char(40) not null,
	authorName char(20) not null,
	publishingName char(30) not null,
	price decimal(7,2) not null,
	publishingDate date not null,
	shopNum int not null
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE Reader
(
	readerNO char(8) primary key,
	readerName char(8) not null,
	sex char(2) not null check(sex='��' or sex='Ů'),
	identitycard char(18) not null check(length(identitycard)=18),
	workUnit char(50) not null,
--	borrowCount tinyint,
	password char(255) not null
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE Borrow
(
	readerNO char(8),
	bookNO char(10),
	borrowDate date not null,
	shouldDate date not null,
	returnDate date,
	foreign key(bookNO) references Book(bookNO),
	foreign key(readerNO) references Reader(readerNO)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/* ΪJDBC���Ӵ����û� */
go
exec sp_addlogin 'BookDB','bookdb'
exec sp_adduser 'BookDB','BookDB'
exec sp_addrolemember 'db_owner', 'BookDB'

/* �޸Ļ�����Լ�� */
alter table Borrow add constraint CK_time check(borrowDate<=shouldDate);

/* ������ͼ */
--����δ����ͼ
go
create view View_Borrow_Not_Return
as
select * from Borrow where returnDate is null
go
select * from View_Borrow_Not_Return;

-- ���߽�����ͼ
go
create view View_Borrow
as
select Borrow.readerNO ���߱��,readerName ����,Borrow.bookNO ͼ����,bookName ͼ������,authorName ����,publishingName ������,borrowDate ����ʱ��,shouldDate Ӧ�黹����,returnDate �黹���� from Borrow,Book,Reader where Borrow.bookNO=Book.bookNO and Borrow.readerNO=Reader.readerNO
go
--select * from View_Borrow;

--����ͼ����ͼ
go
create view View_Book
as
select Book.bookNO ͼ����,bookName ͼ������,authorName ����,publishingName ������,publishingDate ��������,shopNum �������,shopNum-count(View_Borrow_Not_Return.bookNO) �ڿ����� from View_Borrow_Not_Return
right join Book on View_Borrow_Not_Return.bookNO=Book.bookNO 
left join Reader on View_Borrow_Not_Return.readerNO=Reader.readerNO
group by Book.bookNO,bookName,authorName,publishingName,publishingDate,shopNum;
go
--select * from View_Book;

--����Ա������ͼ
go
create view View_Reader 
as
select Reader.readerNO ���߱��,readerName ����,sex �Ա�,identitycard ����֤��,workUnit ������λ,count(Borrow.readerNO) �ܽ�������,count(Borrow.readerNO)-count(Borrow.returnDate) δ�黹���� from Borrow
right join Reader on Borrow.readerNO=Reader.readerNO
left join Book on Borrow.bookNO=Book.bookNO 
group by Reader.readerNO,Borrow.readerNO,readerName,sex,identitycard,workUnit;
go
--select * from View_Reader;

--����Աͼ����ͼ
go
create view View_Book_Admin
as
select Book.bookNO ͼ����,bookName ͼ������,authorName ����,publishingName ������,price �۸�,publishingDate ��������,shopNum �������,shopNum-count(View_Borrow_Not_Return.bookNO) �ڿ����� from View_Borrow_Not_Return
right join Book on View_Borrow_Not_Return.bookNO=Book.bookNO 
left join Reader on View_Borrow_Not_Return.readerNO=Reader.readerNO
group by Book.bookNO,bookName,authorName,publishingName,publishingDate,shopNum,price;
go
--select * from View_Book_Admin;

/*���ӹ���Ա�˻�*/
insert AdminUsers values('admin','');

/*���߱���Reader�����ݣ�*/
INSERT INTO Reader VALUES('R2005001','��С��','Ů','412723199001014321','ͳһ�ɷ����޹�˾','');
INSERT INTO Reader VALUES('R2006001','����','Ů','412723199003014321','���Ϲɷ����޹�˾','');
INSERT INTO Reader VALUES('R2007001','�ߴ���','��','412723199005014321','�鶼�ɷ����޹�˾','');
INSERT INTO Reader VALUES('R2008001','�»�','��','412723199111014321','�ϲ��е������ƹ�˾','');
INSERT INTO Reader VALUES('R2009001','����','Ů','412723199208014321','��ʿ���Ƽ�����','');
INSERT INTO Reader VALUES('R2005002','��¶','Ů','412723199002014321','��¡�ɷ����޹�˾','');
INSERT INTO Reader VALUES('R2006002','����ǿ','��','412723199004014321','���´�ɷ����޹�˾','');
INSERT INTO Reader VALUES('R2007002','����÷','Ů','412723199112014321','���缼��������˾','');
INSERT INTO Reader VALUES('R2008002','����','��','412723199110014321','�Ϻ������о���','');
INSERT INTO Reader VALUES('R2009002','����ƽ','��','412723199209014321','����Ԫ���޹�˾','');

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

/*���ı���Borrow�����ݣ�*/
INSERT INTO Borrow VALUES('R2005001','B200101001','20110901','20111001',null);
INSERT INTO Borrow VALUES('R2006001','B200101001','20110907','20111007','20111005');
INSERT INTO Borrow VALUES('R2007001','B200201001','20110913','20111013','20111010');
INSERT INTO Borrow VALUES('R2008001','B200301001','20110915','20111015','20111013');
INSERT INTO Borrow VALUES('R2009001','B200301001','20110918','20111018',null);
INSERT INTO Borrow VALUES('R2005001','B200101002','20110902','20111002',null);
INSERT INTO Borrow VALUES('R2005001','B200101003','20110903','20111003',null);
INSERT INTO Borrow VALUES('R2005002','B200101001','20110904','20111004','20111003');
INSERT INTO Borrow VALUES('R2005002','B200101002','20110905','20111005',null);
INSERT INTO Borrow VALUES('R2005002','B200101003','20110906','20111006','20111008');
INSERT INTO Borrow VALUES('R2006001','B200101002','20110908','20111008','20111006');
INSERT INTO Borrow VALUES('R2006001','B200101003','20110909','20111009',null);
INSERT INTO Borrow VALUES('R2009002','B200201001','20110910','20111010','20111008');
INSERT INTO Borrow VALUES('R2006002','B200201002','20110911','20111011','20111008');
INSERT INTO Borrow VALUES('R2006002','B200201003','20110912','20111012','20111008');
INSERT INTO Borrow VALUES('R2007001','B200201002','20110914','20111014',null);
INSERT INTO Borrow VALUES('R2007001','B200201003','20110915','20111015','20111010');
INSERT INTO Borrow VALUES('R2009002','B200201001','20110916','20111016','20111014');
INSERT INTO Borrow VALUES('R2007002','B200201002','20110917','20111017','20111014');
INSERT INTO Borrow VALUES('R2007002','B200201003','20110917','20111017','20111014');

/* ɾ��BookDB���ݿ⼰�û� */
go
use master
EXEC sp_dropuser'BookDB'
EXEC sp_droplogin 'BookDB'
drop database BookDB

/* JAVA�еĴ��ڲ���*/
/* ���߽��Ĵ��ڲ��� */
-- ������ʾ
select readerName from Reader where readerNO='R2005001'
-- ��ǰ������Ϣ��ʾ
select * from View_Borrow where ���߱��='R2005002' and �黹���� is null
-- ��ʷ������Ϣ��ʾ
select * from View_Borrow where ���߱��='R2005002' and �黹���� is not null
-- ͼ����Ϣ��ʾ
select * from View_Book where ͼ���� like '%2001%' and ͼ������ like '%����%' and ���� like '%��%' and ������ like '%�й�%' and �������� between '1990-01-01' and '2010-02-02' and �ڿ�����>0 and ͼ���� not in (select ͼ���� from View_Borrow where ���߱��='R2005002' and �黹���� is null)
-- ����
select * from Borrow where readerNO = 'R2009001' and bookNO='B200201003' and returnDate is null
if exists(select * from View_Book where ͼ����='B200201003' and �ڿ�����>0)
insert Borrow values('R2006002','B200201003',now() ,date_add(NOW(), interval 1 MONTH) ,null)
-- ����
update Borrow set returnDate=now() where readerNO='R2006001' and bookNO='B200101003' and returnDate is null;
-- ����
update Borrow set shouldDate=date_add(NOW(), interval 1 MONTH)
where readerNO = 'R2009001' and bookNO='B200201003' and returnDate is null

/* ����Ա������Ϣ���ڲ��� */
-- ��������
update Reader set password=''
where readerNo = 'R2009001'
-- ����
insert Reader values('R2010001','����','��','442000199001014321','����������ѧ�麣ѧԺ','')
-- ɾ��
select * from View_reader where ���߱��='R2005002' and δ�黹����=0
delete from Borrow where readerNO='R2005002' and returnDate is not null
delete from reader where readerNO='R2005002'
-- �޸�
update Reader set readerName='��÷÷',sex='Ů',identitycard='442000199501014321',workUnit='����������ѧ�麣ѧԺ' where readerNO='R2010001'
-- ��ѯ����
select * from View_Reader where ���߱�� like '%2006%' and ���� like '%��%' and �Ա� like '%%' and ����֤�� like '%1990%' and ������λ like '%��˾%'
-- ����
select * from View_Reader where ���߱�� like '%%' and ���� like '%%' and �Ա� like '%%' and ����֤�� like '%%' and ������λ like '%%' order by ���߱��

/* ����Աͼ��������ڲ��� */
-- ����
INSERT INTO Book VALUES('B200301101','Java�������','��׿��','������ͨ��ѧ������',41.00,'20110520',3);
-- ɾ��
select * from View_Book where ͼ����='B200201003' and �ڿ�����=�������
delete from Borrow where bookno='B200201003' and returnDate is not null
delete from Book where bookNO = 'B200201003'
-- �޸�
update Book set bookName='���ݿ�ϵͳԭ�������',authorName='��ѡ',publishingName='�廪��ѧ������',price=59.90,publishingDate='20090902',shopNum=6 where bookNO='B200301101'
-- ��ѯͼ��
select * from View_Book_Admin where ͼ���� like '%2001%' and ͼ������ like '%����%' and ���� like '%��%' and ������ like '%�й�%'

/* ��¼���� */
select readerNO from Reader where readerNo='R2005001' and password=''

/* �޸����봰�� */
select readerNO from Reader where readerNo='R2005001' and password=''
update Reader set password=encodeInp(pwd) where readerNo = ''

/* ������ */
delete from Borrow where bookNO='B200301002'
select bookNO from book where shopNum=1;
select * from AdminUsers
select * from Reader;
select * from Borrow;
select * from book;
select * from View_Book;
select * from View_Reader

/* MD5���� */
select substring(sys.fn_sqlvarbasetostr(HashBytes('MD5','123456')),3,32)
