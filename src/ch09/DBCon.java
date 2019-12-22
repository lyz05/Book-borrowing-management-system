package ch09;
//--1导入
import java.sql.*;
import java.util.*;

public class DBCon {           
    //连接数据库
    public static Connection JdbcCon(){
        try{
            //--2 加载数据库驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //--3 创建连接
            String url="jdbc:sqlserver://10.0.78.30:1433;databaseName=StudentDB";
            Connection conn=DriverManager.getConnection(url,"sa","qazQAZ123!");
            System.out.println("数据库连接成功");
            return conn ;//返回创建的数据库连接对象
        }catch(ClassNotFoundException ex){//捕获驱动程序找不到异常
            ex.printStackTrace();
            System.out.println("数据库驱动程序加载失败");
            return null;
        }catch(SQLException ex){//捕获数据库连接失败异常
            ex.printStackTrace();
            System.out.println("数据库连接失败");
            return null;
        }
    }
    
    //查询数据(返回Student对象的Vector集合)
    public static Vector<Student> queryStudents(String sql){
        Connection conn=JdbcCon();
        Statement stmt; //会话对象
        ResultSet rs; //结果集
        try{
            //创建会话对象
            stmt=conn.createStatement();
            //执行SQL语句
            rs=stmt.executeQuery(sql);
            Vector<Student> data=new Vector<Student>();
            while(rs.next()){
                String stuId=rs.getString(1);
                String stuName=rs.getString(2);
                String sex=rs.getString(3);
                String birth=rs.getString(4);
                Student s=new Student(stuId,stuName,sex,birth);
                data.add(s);
            }
            //关闭
            rs.close();
            stmt.close();
            conn.close();
            return data;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("查询数据失败");
            return null;
        }
    }

    //查询数据(返回二维Vector集合)
    public static Vector queryVectorStudents(String sql){
        Connection conn=JdbcCon();
        Statement stmt; //会话对象
        ResultSet rs; //结果集
        try{
            //创建会话对象
            stmt=conn.createStatement();
            //执行SQL语句
            rs=stmt.executeQuery(sql);
            Vector data=new Vector();
            while(rs.next()){
                Vector line=new Vector();
                line.add(rs.getString(1));
                line.add(rs.getString(2));
                line.add(rs.getString(3));
                line.add(rs.getString(4));
                data.add(line);
            }
            //关闭
            rs.close();
            stmt.close();
            conn.close();
            return data;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("查询数据失败");
            return null;
        }
    }
    
    //验证登录的方法
    public static boolean checkLogin(String sql){
        Connection conn=JdbcCon();
        Statement stmt; //会话对象
        ResultSet rs; //结果集
        boolean bln;
        try{
            //创建会话对象
            stmt=conn.createStatement();
            //执行SQL语句
            rs=stmt.executeQuery(sql); // 只针对于select的SQL语句
            Vector data=new Vector();
            if(rs.next()){ //至少有一条数据
                bln=true;
            }else{
                bln=false;
            }
            //关闭
            rs.close();
            stmt.close();
            conn.close();
            return bln;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("查询数据失败");
            return false;
        }
    }
    
}
