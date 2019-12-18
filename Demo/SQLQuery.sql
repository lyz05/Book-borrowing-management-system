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
	sex char(2) not null check(sex='男' or sex='女'),
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
insert Reader values('R2005001','张小娟','女','412723199001014321','统一股份有限公司','')
insert Reader values('R2006001','刘凤','女','412723199003014321','联合股份有限公司','')
insert Reader values('R2007001','高代鹏','男','412723199005014321','洪都股份有限公司','')
insert Reader values('R2008001','陈辉','男','412723199111014321','南昌市电脑研制公司','')
insert Reader values('R2009001','李虹冰','女','412723199208014321','富士康科技集团','')
insert Reader values('R2005002','张露','女','412723199002014321','兴隆股份有限公司','')
insert Reader values('R2006002','喻自强','男','412723199004014321','万事达股份有限公司','')
insert Reader values('R2007002','张晓梅','女','412723199112014321','世界技术开发公司','')
insert Reader values('R2008002','张良','男','412723199110014321','上海生物研究室','')
insert Reader values('R2009002','韩福平','男','412723199209014321','合生元有限公司','')

/*借阅表（Borrow）数据：*/
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

/* 创建视图 */
--借阅未还视图
go
create view View_Borrow_Not_Return
as
select * from Borrow where returnDate is null
go
select * from View_Borrow_Not_Return;

--借阅已还视图
go
create view View_Borrow_Return
as
select * from Borrow where returnDate is not null
go
select * from View_Borrow_Return;

-- 借阅视图
go
create view View_Borrow
as
select Borrow.readerNO 读者编号,readerName 姓名,Borrow.bookNO 图书编号,bookName 图书名称,authorName 作者,publishingName 出版社,borrowDate 借书时间,shouldDate 应归还日期,returnDate 归还日期 from Borrow,Book,Reader where Borrow.bookNO=book.bookNO and Borrow.readerNO=Reader.readerNO
go
select * from View_Borrow;

--读者视图
go
create view View_Reader 
as
select Reader.readerNO 读者编号,readerName 姓名,sex 性别,identitycard 身份证号,workUnit 工作单位,count(Borrow.readerNO) 总借书数量,count(Borrow.readerNO)-count(Borrow.returnDate) 未归还数量 from Borrow
right join Reader on Borrow.readerNO=Reader.readerNO
left join Book on Borrow.bookNO=Book.bookNO 
group by Reader.readerNO,Borrow.readerNO,readerName,sex,identitycard,workUnit;
go
select * from View_Reader;

--图书视图
go
create view View_Book
as
select Book.bookNO 图书编号,bookName 图书名称,authorName 作者,publishingName 出版社,publishingDate 出版日期,shopNum 入库数量,shopNum-count(View_Borrow_Not_Return.bookNO) 在库数量 from View_Borrow_Not_Return
right join Book on View_Borrow_Not_Return.bookNO=Book.bookNO 
left join Reader on View_Borrow_Not_Return.readerNO=Reader.readerNO
group by Book.bookNO,bookName,authorName,publishingName,publishingDate,shopNum;
go
select * from View_Book;


/* 借阅窗口操作 */
-- 名字显示
select readerName from Reader where readerNO='R2005001'
-- 当前借阅信息显示
select * from View_Borrow where 读者编号='R2005002' and 归还日期 is null
-- 历史借阅信息显示
select * from View_Borrow where 读者编号='R2005002' and 归还日期 is not null
-- 图书信息显示
select * from View_Book where 图书编号 like '%2001%' and 图书名称 like '%经济%' and 作者 like '%宋%' and 出版社 like '%中国%' and 出版日期 between '1990-01-01' and '2010-02-02'

select 图书名称,作者,出版社 from View_Borrow
-- 借书
if exists(select * from View_Book where 图书编号='B200201003' and 在库数量>0)
insert Borrow values('R2006002','B200201003','20110917','20111017',null)
-- 还书
update Borrow set returnDate=getdate() from borrow
where readerNO = 'R2009001'
-- 修改密码
update Reader set password=encodeInp(pwd) from Reader
where readerNo = 'R2009001'

/* 读者信息窗口操作 */
-- 重置密码
update Reader set password='' from Reader
where readerNo = 'R2009001'
-- 添加
insert Reader values('R2010001','李雷','男','442000199001014321','北京理工大学珠海学院','')
-- 删除
delete from reader where readerNO = 'R2010001'
-- 修改
update Reader set readerName='韩梅梅',sex='女',identitycard='442000199501014321',workUnit='北京理工大学珠海学院' where readerNO='R2010001'
-- 查询读者
select * from View_Reader where 读者编号 like '%2006%' and 读者姓名 like '%刘%' and 性别='女' and 身份证号 like '%1990%' and 工作单位 like '%公司%'

/* 图书管理窗口操作 */
-- 添加
INSERT INTO Book VALUES('B200301101','Java程序设计','赵卓君','北京交通大学出版社',41.00,'20110520',3);
-- 删除
delete from Book where bookNO = 'B200301101'
-- 修改
update Book set bookName='数据库系统原理与设计',authorName='万常选',publishingName='清华大学出版社',price=59.90,publishingDate='20090902',shopNum=6 where bookNO='B200301101'
-- 查询图书
--select * from View_Book where 图书编号 like '%2001%' and 图书名称 like '%经济%' and 作者 like '%宋%' and 出版社 like '%中国%' and 出版日期 between '1990-01-01' and '2010-02-02'

/* 登录窗口 */
select readerNO from Reader where readerNo='R2005001' and password=''

select bookNO from book where shopNum=1;
select * from Reader;
select * from Borrow;
select * from View_Book;

/* MD5加密 */
select substring(sys.fn_sqlvarbasetostr(HashBytes('MD5','123456')),3,32)

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