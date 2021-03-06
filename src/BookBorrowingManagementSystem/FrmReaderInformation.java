/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BookBorrowingManagementSystem;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

/**
 *
 * @author 马立健
 */
public class FrmReaderInformation extends javax.swing.JFrame {

    /**
     * Creates new form FrmReaderInformation
     */
    public FrmReaderInformation() {
        initComponents();
        ChooseSex.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "","男", "女" }));
        //注册表头监听器
        jTableHeaderListen();
        jTableSelectionListener();
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
     * 注册jTable选择行监听器
     */
    private void jTableSelectionListener(){
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                Util4Frm.resetBackText(jTable1, lblBack);
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
    //刷新、查询读者信息
    private void RefreshReaderInformation(String appendsql) {
        // 将数据库中查询到的信息显示到jTable上
        Util4Frm.setFormdata("select * from View_Reader where 读者编号 like '%"+InputReaderNo.getText()+"%' and 姓名 like '%"+InputReaderName.getText()+"%' and 性别 like '%"+ChooseSex.getSelectedItem()+"%' and 身份证号 like '%"+InputIdNum.getText()+"%' and 工作单位 like '%"+InputWorkUnit.getText()+"%'"+appendsql,jTable1);
        //刷新底部状态栏的标签显示
        Util4Frm.resetBackText(jTable1, lblBack);
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
        Front = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        lblBack = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("读者信息管理");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("筛选模式"));

        IdNum.setText("身份证号：");

        InputIdNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputIdNumActionPerformed(evt);
            }
        });

        WorkUnit.setText("工作单位：");

        ReaderNo.setText("读者编号：");

        ReaderName.setText("姓名：");

        Sex.setText("性别：");

        InputReaderNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputReaderNoActionPerformed(evt);
            }
        });

        InputReaderName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputReaderNameActionPerformed(evt);
            }
        });

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
                        .addComponent(ReaderNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(InputReaderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
                        .addComponent(IdNum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(InputIdNum, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ReaderName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Sex, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ChooseSex, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(InputReaderName, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(362, 362, 362)
                                .addComponent(WorkUnit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(InputWorkUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Sex)
                            .addComponent(ChooseSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ReaderNo)
                            .addComponent(InputReaderNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(InputIdNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IdNum))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(WorkUnit)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ReaderName)
                                .addComponent(InputReaderName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(InputWorkUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(103, 103, 103))))
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
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jTable1);

        Renovate.setText("查询");
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

        Front.setText("<<");
        Front.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FrontActionPerformed(evt);
            }
        });

        Back.setText(">>");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        jToolBar1.setRollover(true);

        lblBack.setText("准备就绪");
        jToolBar1.add(lblBack);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Renovate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Alter, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Reset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ResetPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                        .addComponent(Front)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Left)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Right)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Back)
                        .addGap(4, 4, 4))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(Right)
                        .addComponent(Front)
                        .addComponent(Back)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
     * 添加记录
     */
    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        //若编辑框是否为空，系统提示
        if (textFiledIsNull()){
            JOptionPane.showMessageDialog(null,"请填写欲添加读者的所有信息","系统提示",JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (ChooseSex.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(null,"请填写读者性别","系统提示",JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (InputIdNum.getText().length()!=18) {
            JOptionPane.showMessageDialog(null,"身份证位数不正确","系统提示",JOptionPane.ERROR_MESSAGE);
            return;
            
        } else if (BookDBCon.preparedupdateData("insert Reader values(?,?,?,?,?,'')",InputReaderNo.getText(),InputReaderName.getText(),String.valueOf(ChooseSex.getSelectedItem()),InputIdNum.getText(),InputWorkUnit.getText())) {
            JOptionPane.showMessageDialog(null,"添加信息成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,"添加信息失败","系统提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        //重置所有文本框
         resetTextfiled();
         //刷新、查询读者信息
         RefreshReaderInformation("");
    }//GEN-LAST:event_AddActionPerformed

    
    private void RenovateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RenovateActionPerformed
        // TODO add your handling code here:
        //刷新、查询读者信息
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
    
    /**
     * 重置密码按钮被按下
     */
    private void ResetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetPasswordActionPerformed
        // TODO add your handling code here:
        if (getreaderno()==null || !Util4Frm.confirmresetpwd()) return;
        String ReaderNO = getreaderno();
        
        if (BookDBCon.preparedupdateData("update Reader set password='' from Reader where readerNo=?",ReaderNO)) {
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
        if (String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2)).equals("男")) {
            ChooseSex.setSelectedIndex(1);
        } else ChooseSex.setSelectedIndex(2);
        InputIdNum.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 3));
        InputWorkUnit.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 4));
    }
    /**
     * 重置所有文本框
     */
    private void resetTextfiled(){
        InputReaderNo.setText("");
        InputReaderName.setText("");
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
            //读记录到编辑框
            getdatatotextfiled();
            //锁主键（读者号的文本框）
            Util4Frm.locktextfiled(InputReaderNo);
            jPanel1.setBorder(BorderFactory.createTitledBorder("编辑模式"));
            //把修改按钮改成保存按钮
            Alter.setText("保存");
            //锁定部分按钮
            Reset.setEnabled(false);
            Delete.setEnabled(false);
            Add.setEnabled(false);
        } else {
            if (BookDBCon.preparedupdateData("update Reader set readerName=?,sex=?,identitycard=?,workUnit=? where readerNO=?",InputReaderName.getText(),String.valueOf(ChooseSex.getSelectedItem()),InputIdNum.getText(),InputWorkUnit.getText(),InputReaderNo.getText())) {
                JOptionPane.showMessageDialog(null,"修改信息成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,"修改信息失败","系统提示",JOptionPane.ERROR_MESSAGE);
            }
            //重置所有文本框
            resetTextfiled();
            //解锁主键（读者号的文本框）
            Util4Frm.unlocktextfiled(InputReaderNo);
            //刷新读者信息
            RefreshReaderInformation("");
            jPanel1.setBorder(BorderFactory.createTitledBorder("筛选模式"));
            //把保存按钮改回修改按钮
            Alter.setText("修改");
            //解锁按钮
            Reset.setEnabled(true);
            Delete.setEnabled(true);
            Add.setEnabled(true);
        }
    }//GEN-LAST:event_AlterActionPerformed
    /**
     * 删除记录
     */
    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        //获取当前选中读者的ReaderNo，确认删除对话框
        if (getreaderno()==null || !Util4Frm.confirmdelete()) return;
        String ReaderNO = getreaderno(),sql;
        sql = "select * from View_reader where 读者编号=? and 未归还数量=0";
        //判断读者是否还有未归还的图书
        if (BookDBCon.preparedqueryResult(sql,ReaderNO) == null)
        {
            JOptionPane.showMessageDialog(null,"该读者还有未归还的图书，因此无法删除该读者","系统提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        //有历史借阅记录，二次确认是否删除
        sql = "select * from Borrow where readerNO=?";
        if (BookDBCon.preparedqueryResult(sql, ReaderNO) != null)
        {
            int ret = JOptionPane.showConfirmDialog(null,"警告：借阅表中存在该读者的历史借阅记录，删除该读者将连带删除历史借阅记录\n是否继续操作？","系统提示",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if (ret != JOptionPane.YES_OPTION)
                return;
        }
        //先删除借阅表中该读者的历史记录再删除该读者
        sql = "delete from Borrow where readerNO=? and returnDate is not null";
        BookDBCon.preparedupdateData(sql,ReaderNO);
        sql = "delete from reader where readerNO=?";
        if (BookDBCon.preparedupdateData(sql,ReaderNO)) {
                JOptionPane.showMessageDialog(null,"删除信息成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
        } else {
                JOptionPane.showMessageDialog(null,"删除信息失败","系统提示",JOptionPane.ERROR_MESSAGE);
        }
        //刷新、查询读者信息
        RefreshReaderInformation("");
    }//GEN-LAST:event_DeleteActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        // TODO add your handling code here:
       //重置所有文本框
        resetTextfiled();
        //刷新、查询读者信息
        RefreshReaderInformation("");
    }//GEN-LAST:event_ResetActionPerformed

    private void FrontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FrontActionPerformed
        // TODO add your handling code here:
        Util4Frm.moveFormRowToTop(jTable1, 0);
    }//GEN-LAST:event_FrontActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
        Util4Frm.moveFormRowToTop(jTable1, 1);
    }//GEN-LAST:event_BackActionPerformed

    private void InputReaderNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputReaderNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputReaderNoActionPerformed

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
    private javax.swing.JButton Back;
    private javax.swing.JComboBox ChooseSex;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Front;
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
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblBack;
    // End of variables declaration//GEN-END:variables
}
