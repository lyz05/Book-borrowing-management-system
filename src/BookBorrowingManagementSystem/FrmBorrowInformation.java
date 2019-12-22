/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BookBorrowingManagementSystem;

import ch09.DBCon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author 叶荣锋
 */
public class FrmBorrowInformation extends javax.swing.JFrame {
    private JTable nowJTable;
    /**
     * Creates new form FrmBorrowInformation
     */
    //构造方法
    public FrmBorrowInformation() {
        initComponents();  
        jTableHeaderListen();
        //默认界面丑拒，换成Windows默认界面
        Util4Frm.setUI(this);

        Hello.setText("您好，"+BookDBCon.sqlQueryResult("select readerName from Reader where readerNO='"+Util4Frm.readerNO + "'"));
        refreshBorrowTable("");
    }
    
    private void jTableHeaderListen(){
        final JTableHeader header1 = jTable1.getTableHeader();
        header1.addMouseListener (new MouseAdapter() {
            public void mouseReleased(MouseEvent e){
                if (e.getClickCount() == 1) {
                    int pick = header1.columnAtPoint(e.getPoint());
                    refreshBorrowTable(Util4Frm.getappendsqlbyorder(jTable1, pick)); 
                    //System.out.println("表头被点击"+pick);
                }
            }
        });
        final JTableHeader header2 = jTable2.getTableHeader();
        header2.addMouseListener (new MouseAdapter() {
            public void mouseReleased(MouseEvent e){
                if (e.getClickCount() == 1) {
                    int pick = header2.columnAtPoint(e.getPoint());
                    refreshBorrowHistoryTable(Util4Frm.getappendsqlbyorder(jTable2, pick)); 
                    //System.out.println("表头被点击"+pick);
                }
            }
        });
        final JTableHeader header3 = jTable3.getTableHeader();
        header3.addMouseListener (new MouseAdapter() {
            public void mouseReleased(MouseEvent e){
                if (e.getClickCount() == 1) {
                    int pick = header3.columnAtPoint(e.getPoint());
                    refreshBookTable(Util4Frm.getappendsqlbyorder(jTable3, pick)); 
                    //System.out.println("表头被点击"+pick);
                }
            }
        });
    }
    
    //获取当前选中书籍的BookNo
    private String getbookno()
    {
         if (nowJTable.getSelectedRow()==-1) {
            JOptionPane.showMessageDialog(null,"请选择一本书","系统提示",JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
        return (String) nowJTable.getValueAt(nowJTable.getSelectedRow(), 0);
    }
    
    //借书还书操作
    private void borrowandreturn()
    {
        if (getbookno()==null) return;
        String BookNO = getbookno(),sql;
        if (btnBorrowReturn.getText().equals("还书")) {
            sql = "update Borrow set returnDate=getdate() from borrow where readerNO = '"+Util4Frm.readerNO+"' and bookNO='"+BookNO+"' and returnDate is null";
        } else {
            sql = "if exists(select * from View_Book where 图书编号='"+BookNO+"' and 在库数量>0) insert Borrow values('"+Util4Frm.readerNO+"','"+BookNO+"',getdate(),dateadd(mm,1,getdate()),null)";
            if (Integer.parseInt(BookDBCon.sqlQueryResult("select 在库数量 from View_Book where 图书编号='"+BookNO+"'")) <= 0) {
                JOptionPane.showMessageDialog(null,"这本书已经被借光了","系统提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (BookDBCon.sqlQueryResult("select readerNO from Borrow where readerNO = '"+Util4Frm.readerNO+"' and bookNO='"+BookNO+"' and returnDate is null") != null){
                JOptionPane.showMessageDialog(null,"这本书你已经借过了，归还后可再次借阅","系统提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if( BookDBCon.updateData(sql))
            JOptionPane.showMessageDialog(null,btnBorrowReturn.getText()+"成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null,btnBorrowReturn.getText()+"失败","系统提示",JOptionPane.ERROR_MESSAGE);
        if (btnBorrowReturn.getText().equals("还书")) 
             refreshBorrowTable("");
        else 
            refreshBookTable("");
       
    }
    
    //续借操作
    private void renew()
    {
        if (nowJTable.getSelectedRow()==-1) {
            JOptionPane.showMessageDialog(null,"请选择一本书","系统提示",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String BookNo = (String) nowJTable.getValueAt(nowJTable.getSelectedRow(), 0);
        if (BookDBCon.updateData("update Borrow set shouldDate=dateadd(mm,1,getdate()) from Borrow where readerNO = '"+Util4Frm.readerNO+"' and bookNO='"+BookNo+"' and returnDate is null")) {
            JOptionPane.showMessageDialog(null,"续借成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,"续借失败","系统提示",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void refreshBorrowTable(String appendsql){
         //刷新当前借阅表
        Util4Frm.setFormdata("select 图书编号,图书名称,作者,出版社,借书时间,应归还日期,归还日期 from View_Borrow where 读者编号='"+ Util4Frm.readerNO + "' and 归还日期 is null"+appendsql, jTable1);
    }
    private void refreshBorrowHistoryTable(String appendsql){ 
        //刷新历史借阅表
        Util4Frm.setFormdata("select 图书编号,图书名称,作者,出版社,借书时间,应归还日期,归还日期 from View_Borrow where 读者编号='"+ Util4Frm.readerNO + "' and 归还日期 is not null"+appendsql, jTable2);
    }
    private void refreshBookTable(String appendsql){ 
        //刷新图书信息表
        String sql = "select * from View_Book where 图书编号 like '%"+ InputBookNo.getText() + "%' and 图书名称 like '%" + InputBookName.getText() + "%' and 作者 like '%" + InputAuthor.getText() + "%' and 出版社 like '%" + InputPublishName.getText() + "%'";
        String PublishDate1 = InputPublishDate_1.getText(),PublishDate2 = InputPublishDate_2.getText();
        if (!PublishDate1.equals("")) 
            sql += " and 出版日期>='" + PublishDate1+"'";
        if (!PublishDate2.equals("")) 
            sql += " and 出版日期<='" + PublishDate2+"'";
        if (jCheckBox1.isSelected())
            sql += " and 在库数量>0";
        Util4Frm.setFormdata(sql+appendsql,jTable3);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        Hello = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        BookNo = new javax.swing.JLabel();
        BookName = new javax.swing.JLabel();
        Author = new javax.swing.JLabel();
        InputBookNo = new javax.swing.JTextField();
        InputBookName = new javax.swing.JTextField();
        InputAuthor = new javax.swing.JTextField();
        PublishDate = new javax.swing.JLabel();
        InputPublishDate_1 = new javax.swing.JTextField();
        Press = new javax.swing.JLabel();
        InputPublishName = new javax.swing.JTextField();
        To = new javax.swing.JLabel();
        InputPublishDate_2 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        Select = new javax.swing.JButton();
        btnBorrowReturn = new javax.swing.JButton();
        btnRenew = new javax.swing.JButton();
        AlterPassword = new javax.swing.JButton();
        jTabbedPane = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        Right = new javax.swing.JButton();
        Left = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("借阅信息");

        Hello.setText("你好，");

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "图书信息 过滤器"));
        jLayeredPane1.setToolTipText("");
        jLayeredPane1.setAlignmentX(10.0F);
        jLayeredPane1.setName(""); // NOI18N
        jLayeredPane1.setPreferredSize(new java.awt.Dimension(623, 186));

        BookNo.setText("图书编号：");

        BookName.setText("图书名称：");

        Author.setText("作者：");

        InputBookNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputBookNoActionPerformed(evt);
            }
        });

        PublishDate.setText("出版日期：");

        InputPublishDate_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputPublishDate_1ActionPerformed(evt);
            }
        });

        Press.setText("出版社：");

        InputPublishName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputPublishNameActionPerformed(evt);
            }
        });

        To.setText("至");

        jCheckBox1.setText("仅显示可借阅书籍");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(BookName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InputBookName)
                            .addComponent(InputAuthor)))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(BookNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(InputBookNo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Author))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(Press)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(InputPublishName))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addGap(41, 41, 41))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(PublishDate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(InputPublishDate_1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(To, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputPublishDate_2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BookNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InputBookNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Press)
                    .addComponent(InputPublishName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BookName)
                    .addComponent(InputBookName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PublishDate)
                    .addComponent(To)
                    .addComponent(InputPublishDate_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InputPublishDate_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Author)
                    .addComponent(jCheckBox1))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jLayeredPane1.setLayer(BookNo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(BookName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Author, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(InputBookNo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(InputBookName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(InputAuthor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(PublishDate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(InputPublishDate_1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(Press, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(InputPublishName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(To, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(InputPublishDate_2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jCheckBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Select.setText("查询");
        Select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectActionPerformed(evt);
            }
        });

        btnBorrowReturn.setText("借书");
        btnBorrowReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrowReturnActionPerformed(evt);
            }
        });

        btnRenew.setText("续借");
        btnRenew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenewActionPerformed(evt);
            }
        });

        AlterPassword.setText("修改密码");
        AlterPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlterPasswordActionPerformed(evt);
            }
        });

        jTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneStateChanged(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "图书编号", "图书名称", "作者", "出版社", "价格", "出版日期", "入库数量", "在库数量"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTabbedPane.addTab("当前借阅", jScrollPane1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "图书编号", "图书名称", "作者", "出版社", "价格", "出版日期", "入库数量", "在库数量"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jTabbedPane.addTab("历史借阅", jScrollPane2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "图书编号", "图书名称", "作者", "出版社", "价格", "出版日期", "入库数量", "在库数量"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jTabbedPane.addTab("图书信息", jScrollPane3);

        Right.setText(">");
        Right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RightActionPerformed(evt);
            }
        });

        Left.setText("<");
        Left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeftActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Hello, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Select)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrowReturn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRenew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AlterPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Left)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Right)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(Hello, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Left)
                    .addComponent(Right)
                    .addComponent(Select)
                    .addComponent(btnBorrowReturn)
                    .addComponent(btnRenew)
                    .addComponent(AlterPassword))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.getAccessibleContext().setAccessibleName("过滤器1");
        btnRenew.getAccessibleContext().setAccessibleName("");
        btnRenew.getAccessibleContext().setAccessibleDescription("");
        jTabbedPane.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void InputBookNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputBookNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputBookNoActionPerformed

    private void InputPublishDate_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputPublishDate_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputPublishDate_1ActionPerformed

    private void InputPublishNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputPublishNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputPublishNameActionPerformed

    private void SelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectActionPerformed
        // TODO add your handling code here:
        refreshBookTable("");
    }//GEN-LAST:event_SelectActionPerformed

    private void RightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RightActionPerformed
        //按下右键按钮
        Util4Frm.moveFormRow(nowJTable, 1);
    }//GEN-LAST:event_RightActionPerformed

    private void btnRenewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenewActionPerformed
        // TODO add your handling code here:
        renew();
        refreshBorrowTable("");
    }//GEN-LAST:event_btnRenewActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeftActionPerformed
        // 按下左键按钮
        Util4Frm.moveFormRow(nowJTable, -1);
    }//GEN-LAST:event_LeftActionPerformed

    private void jTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneStateChanged
        // TODO add your handling code here:
        if (jTabbedPane.getSelectedIndex() == 0) {
            btnBorrowReturn.setText("还书");
            btnBorrowReturn.setEnabled(true);
            btnRenew.setEnabled(true);
            nowJTable = jTable1;
            refreshBookTable("");
        }else if (jTabbedPane.getSelectedIndex()==1){
            btnBorrowReturn.setEnabled(false);
            btnRenew.setEnabled(false);
            nowJTable =jTable2;
            refreshBorrowHistoryTable("");
        } else if (jTabbedPane.getSelectedIndex()==2) {
            btnBorrowReturn.setText("借书");
            btnBorrowReturn.setEnabled(true);
            btnRenew.setEnabled(false);
            nowJTable = jTable3;
            refreshBookTable("");
        }
        
    }//GEN-LAST:event_jTabbedPaneStateChanged

    private void AlterPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlterPasswordActionPerformed
          FrmAlterPassword frame=new FrmAlterPassword();
          frame.setVisible(true);
          frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//关闭时，仅销毁窗口
    }//GEN-LAST:event_AlterPasswordActionPerformed

    private void btnBorrowReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrowReturnActionPerformed
        // TODO add your handling code here:
        borrowandreturn();
    }//GEN-LAST:event_btnBorrowReturnActionPerformed

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
            java.util.logging.Logger.getLogger(FrmBorrowInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBorrowInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBorrowInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBorrowInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBorrowInformation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AlterPassword;
    private javax.swing.JLabel Author;
    private javax.swing.JLabel BookName;
    private javax.swing.JLabel BookNo;
    private javax.swing.JLabel Hello;
    private javax.swing.JTextField InputAuthor;
    private javax.swing.JTextField InputBookName;
    private javax.swing.JTextField InputBookNo;
    private javax.swing.JTextField InputPublishDate_1;
    private javax.swing.JTextField InputPublishDate_2;
    private javax.swing.JTextField InputPublishName;
    private javax.swing.JButton Left;
    private javax.swing.JLabel Press;
    private javax.swing.JLabel PublishDate;
    private javax.swing.JButton Right;
    private javax.swing.JButton Select;
    private javax.swing.JLabel To;
    private javax.swing.JButton btnBorrowReturn;
    private javax.swing.JButton btnRenew;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
