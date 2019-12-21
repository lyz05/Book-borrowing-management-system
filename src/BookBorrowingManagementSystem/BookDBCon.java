/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BookBorrowingManagementSystem;

import static BookBorrowingManagementSystem.DBCon.JdbcCon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author congcong
 */
public class BookDBCon {
    private BookDBCon() {
        //禁止实例化
    }
    //连接数据库
    public static Connection JdbcCon(){
        try{
            //--2 加载数据库驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //--3 创建连接
            String url="jdbc:sqlserver://10.0.78.30:1433;databaseName=BookDB";
            Connection conn=DriverManager.getConnection(url,"sa","qazQAZ123!");
            //System.out.println("数据库连接成功");
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
    
    //查询数据库第一行第一个数据
    public static String sqlQueryResult(String sql){
        System.out.println(sql);
        Connection conn=JdbcCon();
        Statement stmt; //会话对象
        ResultSet rs; //结果集
        try{
            String ret = new String();
            //创建会话对象
            stmt=conn.createStatement();
            //执行SQL语句
            rs=stmt.executeQuery(sql);
            //跳转到第一行
            rs.next(); 
            //获取第一列数据
            ret = rs.getString(1);
            //System.out.println(ret);
            //关闭
            rs.close();
            stmt.close();
            conn.close();
            return ret;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("查询数据失败");
            return null;
        }
    }
    
    //返回一位Vector数据库字段信息
     public static Vector queryVectorName(String sql){
        System.out.println(sql);
        Connection conn=JdbcCon();
        Statement stmt; //会话对象
        ResultSet rs; //结果集
        ResultSetMetaData metaData;  //列集
        try{
           stmt=conn.createStatement();            //创建会话对象
            rs=stmt.executeQuery(sql);                  //执行SQL语句
            metaData = rs.getMetaData();            //获取列集
            int columnCount = metaData.getColumnCount(); //获取列的数量
            Vector data=new Vector();
            //循环遍历
            for (int i=1;i<=columnCount;i ++) {
                data.add(metaData.getColumnName(i));
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
    //返回二维Vector表格和一维字段信息
    public static void queryVector2(String sql,Vector data,Vector name){
        System.out.println(sql);
        Connection conn=JdbcCon();
        Statement stmt; //会话对象
        ResultSet rs; //结果集
        ResultSetMetaData metaData;  //列集
        try{
           stmt=conn.createStatement();            //创建会话对象
            rs=stmt.executeQuery(sql);                  //执行SQL语句
            metaData = rs.getMetaData();            //获取列集
            int columnCount = metaData.getColumnCount(); //获取列的数量
            for (int i=1;i<=columnCount;i ++) 
                name.add(metaData.getColumnName(i));        //获取字段名
            Vector ret=new Vector();
            //循环遍历
            while(rs.next()){
                Vector line=new Vector();
                for (int i=1;i<=columnCount;i ++)  {
                    String item = String.valueOf(rs.getString(i)).trim();
                    line.add(item);                       //添加一条数据并过滤首尾空格
                }
                data.add(line);   
            }
            //关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("查询数据失败");
            data = null;
            name = null;
        }
    }
}
