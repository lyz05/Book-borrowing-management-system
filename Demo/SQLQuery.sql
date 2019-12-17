/*创建数据库（BookDB）*/
CREATE DATABASE [BookDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = 'BookDB', FILENAME = 'D:\SQ\BookDB.mdf' , SIZE = 8192KB , MAXSIZE = 30720KB , FILEGROWTH = 1024KB )
 LOG ON 
( NAME = 'BookLog', FILENAME = 'D:\SQ\BookLog.ldf' , SIZE = 5120KB , MAXSIZE = 20480KB , FILEGROWTH = 1024KB )

/*转到（BookDB）数据库*/
use BookDB;
/*修正数据库无法显示中文的问题*/
ALTER DATABASE [BookDB] COLLATE Chinese_PRC_CI_AS;

/*创建表结构*/
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


/*图书表(Book)数据：*/
INSERT INTO Book VALUES('B200101001','政治经济学','宋涛','中国人民大学出版社',31.80,'19910101',5);
INSERT INTO Book VALUES('B200201001','大学英语','郑树棠','外语教学与研究出版社',35.20,'19920101',3);
INSERT INTO Book VALUES('B200301001','数据库系统原理','吴京慧','清华大学出版社',58.20,'19930101',3);
INSERT INTO Book VALUES('B200101002','微观经济学','张蕊','高等教育出版社',41.80,'19910102',2);
INSERT INTO Book VALUES('B200101003','宏观经济学','袁明圣','中国财经经济出版社',51.80,'19910103',2);
INSERT INTO Book VALUES('B200201002','商务英语','马升烨','上海外语学院出版社',45.20,'19920102',5);
INSERT INTO Book VALUES('B200201003','商务英语2','江宇佳','西安交通大学出版社',55.20,'19920103',3);
INSERT INTO Book VALUES('B200301002','组网技术','万征','浙江大学出版社',38.20,'19930102',1);
INSERT INTO Book VALUES('B200301003','人工智能','费翔林','电子工业出版社',43.20,'19930103',5);
INSERT INTO Book VALUES('B200301004','算法设计与分析','陈慧南','科学出版社',58.20,'19930104',10);


/*读者表（Reader）数据：*/
insert Reader values('R2005001','张小娟','F','412723199001014321','统一股份有限公司','')
insert Reader values('R2006001','刘凤','F','412723199003014321','联合股份有限公司','')
insert Reader values('R2007001','高代鹏','M','412723199005014321','洪都股份有限公司','')
insert Reader values('R2008001','陈辉','M','412723199111014321','南昌市电脑研制公司','')
insert Reader values('R2009001','李虹冰','F','412723199208014321','富士康科技集团','')
insert Reader values('R2005002','张露','F','412723199002014321','兴隆股份有限公司','')
insert Reader values('R2006002','喻自强','M','412723199004014321','万事达股份有限公司','')
insert Reader values('R2007002','张晓梅','F','412723199112014321','世界技术开发公司','')
insert Reader values('R2008002','张良','M','412723199110014321','上海生物研究室','')
insert Reader values('R2009002','韩福平','M','412723199209014321','合生元有限公司','')

/*借阅表（Borrow）数据：*/
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

/* 创建视图 */
--读者视图
go
create view View_Reader 
as
select Reader.readerNO 读者编号,readerName 读者姓名,sex 性别,identitycard 身份证号,workUnit 工作单位,count(Borrow.readerNO) 借书数量 from Borrow
right join Reader on Borrow.readerNO=Reader.readerNO
left join Book on Borrow.bookNO=Book.bookNO 
group by Reader.readerNO,Borrow.readerNO,readerName,sex,identitycard,workUnit;
select * from View_Reader;

--书本视图
go
create view View_Book
as
select Book.bookNO 图书编号,bookName 图书名称,authorName 作者,publishingName 出版社,publishingDate 出版日期,shopNum 入库数量,shopNum-count(Borrow.bookNO) 在库数量 from Borrow
right join Book on Borrow.bookNO=Book.bookNO 
left join Reader on Borrow.readerNO=Reader.readerNO
group by Book.bookNO,bookName,authorName,publishingName,publishingDate,shopNum;
select * from View_Book;

/* 借阅操作 */
-- 借书
insert Borrow values('R2007002','B200201003','20110917','20111017','20111014')
-- 还书
update borrow set returnDate=getdate() from borrow
where readerNO = 'R2009001'

select * from Borrow;

/* 作业内容先注释掉
/*查询操作*/
--select * from Book;
/*操作1:查询1991年出生的读者姓名、工作单位和身份证号*/
select readerName 读者姓名,workUnit 工作单位,identitycard 身份证号 from Reader where identitycard like '______1991________';
/*操作2:查询在富士康科技集团工作的读者编号、姓名和性别*/
select readerNO 读者编号,readerName 姓名,sex 性别 from Reader where workUnit = '富士康科技集团';
--select * from Book;
/*操作3:查询图书名中含有"数据库"的图书的详细信息*/
select * from Book where bookName like '%数据库%';
/*操作4:查询袁明圣老师编写的单价不低于40元的每种图书的图书编号、入库数量*/
select bookNO 图书编号,shopNum 入库数量 from Book where authorName = '袁明圣' and price>=40;
/*操作5:查询在1995-1996年之间入库的图书编号、出版时间、入库时间和图书名称，并按入库时间排序输出*/
select bookNO 图书编号,publishingDate 出版时间,shopDate 入库时间,bookName 图书名称 from Book where year(shopDate) between 1995 and 1996 order by shopDate;
*/