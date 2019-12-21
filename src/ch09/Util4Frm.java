/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch09;

import java.awt.Component;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author congcong
 */
public class Util4Frm {
    private Util4Frm() {}       //禁止实例化
    public static void setUI(Component comp) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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
}
