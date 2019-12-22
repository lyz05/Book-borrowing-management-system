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

/**
 *
 * @author congcong
 */
public class Util4Frm {
    private Util4Frm() {}       //禁止实例化
    public static String readerNO;//当前登陆用户的ReaderNO
    public static void setUI(Component comp) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(comp);
        } catch(Exception err) {
            System.out.println("捕获异常:"+err);
        }
    }
    
    public static void setFormdata(String sql,JTable jtable) {
        Vector data=new Vector(),name = new Vector();
        BookDBCon. queryVector2(sql,data,name);
        DefaultTableModel model = new DefaultTableModel(data, name);
        jtable.setModel(model);
    }
    
    //移动超级列表框当前选中行
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
    //获取排序追加sql文本
    static boolean sort = false;
    public static String getappendsqlbyorder(JTable jtable,int col){
        String colName = jtable.getColumnName(col);
        String appendsql = " order by "+colName;
        if (sort) {
            appendsql += " desc";
        }
        sort = !sort;
        return appendsql;
    }
    
    //锁定控件
    public static void locktextfiled(JTextField jtextfield){
        jtextfield.setEnabled(false);
    }
    
    //解锁控件
    public static void unlocktextfiled(JTextField jtextfield){
        jtextfield.setEnabled(true);
    }
}
