/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BookBorrowingManagementSystem;

import ch09.Student;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;

/**
 *
 * @author 叶荣锋
 */
public class FrmReaderInformation extends javax.swing.JFrame {

    /**
     * Creates new form FrmReaderInformation
     */
    public FrmReaderInformation() {
        initComponents();
        //注册表头监听器
        jTableHeaderListen();
        //默认界面丑拒，换成Windows默认界面
        Util4Frm.setUI(this);
        this.setMinimumSize(new Dimension(890,560));
        RefreshReaderInformation("");
        
    }
    
    /**
     * 注册jTable表头监听器，用于排序
     */
    private void jTableHeaderListen(){
        final JTableHeader header1 = jTable1.getTableHeader();
        header1.addMouseListener (new MouseAdapter() {
            public void mouseReleased(MouseEvent e){
                if (e.getClickCount() == 1) {
                    int pick = header1.columnAtPoint(e.getPoint());
                    RefreshReaderInformation(Util4Frm.getappendsqlbyorder(jTable1, pick)); 
                    //System.out.println("表头被点击"+pick);
                }
            }
        });

    }
    
    /**
     * 编辑框是否为空
     * @return 是否为空
     */
    private boolean textFiledIsNull(){
        return InputReaderNo.getText().equals("") || InputReaderName.getText().equals("") || InputIdNum.getText().equals("") || InputWorkUnit.getText().equals("");
    }
    
    /**
     * 刷新读者信息
     * @param appendsql 追加的sql文本
     */
    private void RefreshReaderInformation(String appendsql) {
        Util4Frm.setFormdata("select * from View_Reader where 读者编号 like '%"+InputReaderNo.getText()+"%' and 姓名 like '%"+InputReaderName.getText()+"%' and 性别 like '%"+ChooseSex.getSelectedItem()+"%' and 身份证号 like '%"+InputIdNum.getText()+"%' and 工作单位 like '%"+InputWorkUnit.getText()+"%'"+appendsql,jTable1);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        IdNum = new javax.swing.JLabel();
        InputIdNum = new javax.swing.JTextField();
        WorkUnit = new javax.swing.JLabel();
        InputWorkUnit = new javax.swing.JTextField();
        ReaderNo = new javax.swing.JLabel();
        ReaderName = new javax.swing.JLabel();
        Sex = new javax.swing.JLabel();
        InputReaderNo = new javax.swing.JTextField();
        InputReaderName = new javax.swing.JTextField();
        ChooseSex = new javax.swing.JComboBox();
        Add = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        Alter = new javax.swing.JButton();
        Left = new javax.swing.JButton();
        Right = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Renovate = new javax.swing.JButton();
        ResetPassword = new javax.swing.JButton();
        Reset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("读者信息");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("筛选模式"));

        IdNum.setText("身份证号：");

        InputIdNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputIdNumActionPerformed(evt);
            }
        });

        WorkUnit.setText("工作单位：");

        ReaderNo.setText("读者编号：");

        ReaderName.setText("读者姓名：");

        Sex.setText("性别：");

        InputReaderName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputReaderNameActionPerformed(evt);
            }
        });

        ChooseSex.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "%", "男", "女" }));
        ChooseSex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChooseSexActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ReaderName)
                            .addComponent(Sex))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ChooseSex, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(InputReaderName, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(354, 354, 354)
                                .addComponent(WorkUnit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(InputWorkUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ReaderNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(InputReaderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(IdNum)
                        .addGap(18, 18, 18)
                        .addComponent(InputIdNum, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Sex)
                        .addGap(66, 66, 66))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ReaderNo)
                            .addComponent(InputReaderNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(InputIdNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IdNum))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ReaderName)
                            .addComponent(InputReaderName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(WorkUnit)
                            .addComponent(InputWorkUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(ChooseSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))))
        );

        ChooseSex.getAccessibleContext().setAccessibleName("");

        Add.setText("添加");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        Delete.setText("删除");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        Alter.setText("修改");
        Alter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlterActionPerformed(evt);
            }
        });

        Left.setText("<");
        Left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeftActionPerformed(evt);
            }
        });

        Right.setText(">");
        Right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RightActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "读者编号", "读者姓名", "性别", "身份证号", "工作单位", "总借书数量", "未归还数量"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        Renovate.setText("刷新");
        Renovate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RenovateActionPerformed(evt);
            }
        });

        ResetPassword.setText("重置读者密码");
        ResetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetPasswordActionPerformed(evt);
            }
        });

        Reset.setText("重置");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Renovate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Alter, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Reset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ResetPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Left, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Right, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Delete)
                        .addComponent(Renovate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Alter)
                        .addComponent(Reset)
                        .addComponent(ResetPassword))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Left)
                        .addComponent(Right)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ChooseSexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChooseSexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChooseSexActionPerformed

    private void InputIdNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputIdNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputIdNumActionPerformed

    private void InputReaderNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputReaderNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputReaderNameActionPerformed

    /**
     * 插入记录
     */
    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
         if (textFiledIsNull()){
             JOptionPane.showMessageDialog(null,"请填写欲添加读者的所有信息","系统提示",JOptionPane.INFORMATION_MESSAGE);
             return;
         } else if (BookDBCon.updateData("insert Reader values('"+InputReaderNo.getText()+"','"+InputReaderName.getText()+"','"+ChooseSex.getSelectedItem()+"','"+InputIdNum.getText()+"','"+InputWorkUnit.getText()+"','')")) {
                JOptionPane.showMessageDialog(null,"添加信息成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,"添加信息失败","系统提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
         resetTextfiled();
         RefreshReaderInformation("");
    }//GEN-LAST:event_AddActionPerformed

    
    private void RenovateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RenovateActionPerformed
        // TODO add your handling code here:
        RefreshReaderInformation("");
    }//GEN-LAST:event_RenovateActionPerformed

    private void LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeftActionPerformed
        // 按下左键按钮
        Util4Frm.moveFormRow(jTable1, -1);
    }//GEN-LAST:event_LeftActionPerformed

    private void RightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RightActionPerformed
        //按下右键按钮
        Util4Frm.moveFormRow(jTable1, 1);
    }//GEN-LAST:event_RightActionPerformed
    
    /**
     * 获取当前选中读者的ReaderNo
     * @return 返回ReaderNo
     */
    private String getreaderno()
    {
         if (jTable1.getSelectedRow()==-1) {
            JOptionPane.showMessageDialog(null,"请选择一位读者","系统提示",JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
        return (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
    }
    
    private void ResetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetPasswordActionPerformed
        // TODO add your handling code here:
        if (getreaderno()==null) return;
        String ReaderNO = getreaderno();
        if (BookDBCon.updateData("update Reader set password='' from Reader where readerNo = '"+ReaderNO+"'")) {
            JOptionPane.showMessageDialog(null,"重置密码成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,"重置密码失败","系统提示",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ResetPasswordActionPerformed

    /**
     * 读记录到编辑框
     */
    private void getdatatotextfiled(){
        InputReaderNo.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
        InputReaderName.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 1));
        if ((String) jTable1.getValueAt(jTable1.getSelectedRow(), 2)=="男") {
            ChooseSex.setSelectedIndex(1);
        } else ChooseSex.setSelectedIndex(2);
        InputIdNum.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 3));
        InputWorkUnit.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 4));
    }
    /**
     * 重置所有文本
     */
    private void resetTextfiled(){
        InputReaderNo.setText("");
        InputReaderName.setText("");
        ChooseSex.setSelectedIndex(0);
        InputIdNum.setText("");
        InputWorkUnit.setText("");
        ChooseSex.setSelectedIndex(0);
    }
    
    /**
     * 修改保存按钮被点击
     */
    private void AlterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlterActionPerformed
        // TODO add your handling code here:
        if (Alter.getText().equals("修改")){
            if (getreaderno()==null) return;
            String ReaderNO = getreaderno();
            getdatatotextfiled();
            Util4Frm.locktextfiled(InputReaderNo);
            jPanel1.setBorder(BorderFactory.createTitledBorder("编辑模式"));
            Alter.setText("保存");
            Reset.setEnabled(false);
        } else {
            if (BookDBCon.updateData("update Reader set readerName='"+InputReaderName.getText()+"',sex='"+ChooseSex.getSelectedItem()+"',identitycard='"+InputIdNum.getText()+"',workUnit='"+InputWorkUnit.getText()+"' where readerNO='"+InputReaderNo.getText()+"'")) {
                JOptionPane.showMessageDialog(null,"修改信息成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,"修改信息失败","系统提示",JOptionPane.ERROR_MESSAGE);
            }
            resetTextfiled();
            Util4Frm.unlocktextfiled(InputReaderNo);
            RefreshReaderInformation("");
            jPanel1.setBorder(BorderFactory.createTitledBorder("筛选模式"));
            Alter.setText("修改");
            Reset.setEnabled(true);
        }
    }//GEN-LAST:event_AlterActionPerformed
    /**
     * 删除记录
     */
    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        if (getreaderno()==null || !Util4Frm.confirmdelete()) return;
        String ReaderNO = getreaderno();
        if (BookDBCon.updateData("delete from reader where readerNO = '"+ReaderNO+"'")) {
                JOptionPane.showMessageDialog(null,"删除信息成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
        } else {
                JOptionPane.showMessageDialog(null,"删除信息失败","系统提示",JOptionPane.ERROR_MESSAGE);
        }
        RefreshReaderInformation("");
    }//GEN-LAST:event_DeleteActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        // TODO add your handling code here:
        resetTextfiled();
        RefreshReaderInformation("");
    }//GEN-LAST:event_ResetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmReaderInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmReaderInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmReaderInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmReaderInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmReaderInformation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton Alter;
    private javax.swing.JComboBox ChooseSex;
    private javax.swing.JButton Delete;
    private javax.swing.JLabel IdNum;
    private javax.swing.JTextField InputIdNum;
    private javax.swing.JTextField InputReaderName;
    private javax.swing.JTextField InputReaderNo;
    private javax.swing.JTextField InputWorkUnit;
    private javax.swing.JButton Left;
    private javax.swing.JLabel ReaderName;
    private javax.swing.JLabel ReaderNo;
    private javax.swing.JButton Renovate;
    private javax.swing.JButton Reset;
    private javax.swing.JButton ResetPassword;
    private javax.swing.JButton Right;
    private javax.swing.JLabel Sex;
    private javax.swing.JLabel WorkUnit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
