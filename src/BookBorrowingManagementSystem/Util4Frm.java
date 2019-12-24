/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BookBorrowingManagementSystem;

import java.awt.Component;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author congcong
 */
public class Util4Frm {
    private Util4Frm() {}       //禁止实例化
    /**
     * 当前登陆用户的ReaderNO，对所有的窗口均适用
     */
    public static String readerNO = "R2006001";
    /**
     * 排序的方向，用于对jTable中的列排序
     */
    private static boolean sort = false;
    
    /**
     * 设置窗口显示效果，原界面丑拒
     * @param comp 提供界面对应的Component
     */
    public static void setUI(Component comp) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(comp);
        } catch(Exception err) {
            System.out.println("捕获异常:"+err);
        }
    }
    
    /**
     * 将数据库中查询到的信息显示到jTable上
     * @param sql 查询的sql语句
     * @param jtable 显示到的jTable
     */
    public static void setFormdata(String sql,JTable jtable) {
        //data是表格中的数据，name是表头字段信息
        Vector data=new Vector(),name = new Vector();
        BookDBCon. queryVector2(sql,data,name);
        //用DefaultTableModel包装数据，以便JTable显示
        DefaultTableModel model = new DefaultTableModel(data, name) {
            //重写方法禁止编辑
            @Override
            public boolean isCellEditable(int row,int column) {
                return false;
            }
        };
        jtable.setModel(model);
    }
    
    /**
     * 移动jTable当前选中行的光标
     * @param jtable 要移动光标的jTable
     * @param dis 位置相较于原来的位移，+1表示向下，-1表示向上
     */
    public static void moveFormRow(JTable jtable,int dis)
    {
        int tmp =  jtable.getSelectedRow() + dis;
        if (tmp<0) {
            JOptionPane.showMessageDialog(null,"已经是第一条数据了","系统提示",JOptionPane.INFORMATION_MESSAGE);
        } else if (tmp>=jtable.getRowCount()) {
            JOptionPane.showMessageDialog(null,"已经是最后一条数据了","系统提示",JOptionPane.INFORMATION_MESSAGE);
        } else 
        jtable.setRowSelectionInterval(tmp, tmp);
    }

    /**
     * 移动jTable当前选中行光标到顶部或底部
     * @param jtable
     * @param status 
     */
    public static void moveFormRowToTop(JTable jtable,int status)
    {
        int tmp = 0;
        if (status==1) {
            tmp = jtable.getRowCount()-1;
        }
        jtable.setRowSelectionInterval(tmp, tmp);
    }

    public static void resetBackText(JTable jtable,JLabel jlabel) {
        int row = jtable.getSelectedRow(),tot = jtable.getRowCount();
        if (row >=0  && row < tot)
        {
            jlabel.setText("这是第"+(row+1)+"条记录，共查询到"+tot+"条记录");
        } else
            jlabel.setText("共查询到"+tot+"条记录");
    }

    /**
     * 获取对jTable列排序时需要追加的sql文本
     * @param jtable 对哪个jTable操作
     * @param col 对应的列
     * @return 返回排序需要追加的sql文本
     */
    public static String getappendsqlbyorder(JTable jtable,int col){
        String colName = jtable.getColumnName(col);
        String appendsql = " order by "+colName;
        if (sort) {
            appendsql += " desc";
        }
        sort = !sort;
        return appendsql;
    }
    
    /**
     * 锁定控件
     * @param jtextfield 待锁定控件
     */
    public static void locktextfiled(JTextField jtextfield){
        jtextfield.setEnabled(false);
    }
    
    /**
     * 解锁控件
     * @param jtextfield 待解锁空间 
     */
    public static void unlocktextfiled(JTextField jtextfield){
        jtextfield.setEnabled(true);
    }
    
    /**
     * 确认删除对话框
     * @return 是否按下确认按钮
     */
    public static boolean confirmdelete()
    {
         return JOptionPane.showConfirmDialog(null,"你确认删除该条数据吗？","确认删除",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION;
    }
    
    /**
     * 密码加密函数
     * @param input 输入的密码
     * @return 加密后的密码
     */
    public static String encodeInp(String input)
	{
		input += "\0\0\0";
		char keyStr[] = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=").toCharArray();
		String output = new String();
		int chr1,chr2,chr3;
		int enc1,enc2,enc3,enc4;
		int i=0;
		do{
			chr1 = input.charAt(i ++);
			chr2 = input.charAt(i ++);
			chr3 = input.charAt(i ++);
			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
			if(chr2==0)
			{
				enc3 = enc4 = 64;
			} else if (chr3==0)
			{
				enc4 = 64;
			}
			output += keyStr[enc1];
			output += keyStr[enc2];
			output += keyStr[enc3];
			output += keyStr[enc4];
			
			chr1 = chr2 = chr3 = 0;
			enc1 = enc2 = enc3 = enc4 = 0;
			
		}while (input.charAt(i) != '\0');
		return output;
	}

}
