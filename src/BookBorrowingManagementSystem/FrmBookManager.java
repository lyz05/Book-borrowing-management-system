/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BookBorrowingManagementSystem;

import static BookBorrowingManagementSystem.BookDBCon.JdbcCon;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

/**
 *
 * @author 叶荣锋
 */
public class FrmBookManager extends javax.swing.JFrame {

    /**
     * Creates new form FrmBookManager
     */
    public FrmBookManager() {
        initComponents();
        jTableHeaderListen();
        jTableSelectionListener();
        //默认界面丑拒，换成Windows默认界面
        Util4Frm.setUI(this);
        //设置显示窗口的最小尺寸
        this.setMinimumSize(new Dimension(890,560));
        RefreshBookInformation("");
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
     * 注册jTable表头监听器
     */
    private void jTableHeaderListen(){
        final JTableHeader header1 = jTable1.getTableHeader();
        header1.addMouseListener (new MouseAdapter() {
            public void mouseReleased(MouseEvent e){
                if (e.getClickCount() == 1) {
                    int pick = header1.columnAtPoint(e.getPoint());
                    RefreshBookInformation(Util4Frm.getappendsqlbyorder(jTable1, pick)); 
                    //System.out.println("表头被点击"+pick);
                }
            }
        });
    }

    /**
     * 获取当前选中书籍的BookNo
     * @return 
     */
    private String getbookno()
    {
         if (jTable1.getSelectedRow()==-1) {
            JOptionPane.showMessageDialog(null,"请选择一本书","系统提示",JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
        return (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
    }

    /**
     * 判断文本框是否为空
     * @return 
     */
    private boolean textFiledIsNull(){
        return InputBookNum.getText().equals("") || InputBookName.getText().equals("") || InputAuthor.getText().equals("") || InputPress.getText().equals("") || InputPrice.getText().equals("") || InputPublishdate.getText().equals("") || InputShopNum.getText().equals("");
    }

    /**
     * 重置编辑框
     */
    private void resetTextfiled()
    {
        InputBookNum.setText("");
        InputBookName.setText("");
        InputAuthor.setText("");
        InputPress.setText("");
        InputPrice.setText("");
        InputPublishdate.setText("");
        InputShopNum.setText(""); 
    }
    
    /**
     * 读记录到文本框
     */
    private void getdatatotextfiled(){
        InputBookNum.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
        InputBookName.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 1));
        InputAuthor.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 2));
        InputPress.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 3));
        InputPrice.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 4));
        InputPublishdate.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 5));
        InputShopNum.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 6));
    }
    
    /**
     * 刷新图书信息
     * @param appendsql 追加的sql
     */

    private void RefreshBookInformation(String appendsql){
        Util4Frm.setFormdata("select * from View_Book_Admin where 图书编号 like '%"+InputBookNum.getText()+"%' and 图书名称 like '%"+InputBookName.getText()+"%' and 作者 like '%"+InputAuthor.getText() +"%' and 出版社 like '%"+InputPress.getText()+"%'"+appendsql,jTable1);
        //用resetBackText()封装    
//      int row = jTable1.getSelectedRow();
//      int total = jTable1.getRowCount();
//      if(row>=0 && row<total){
//         System.out.println("当前为第"+row+"行"+","+"共有"+total+"行");
//      }else{
//         System.out.println("共有"+total+"行");
//      }
        Util4Frm.resetBackText(jTable1, lblBack);//显示左下角的信息
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Delete = new javax.swing.JButton();
        Refresh = new javax.swing.JButton();
        Left = new javax.swing.JButton();
        Right = new javax.swing.JButton();
        Add = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        BookNo = new javax.swing.JLabel();
        BookName = new javax.swing.JLabel();
        Author = new javax.swing.JLabel();
        InputBookNum = new javax.swing.JTextField();
        InputBookName = new javax.swing.JTextField();
        InputAuthor = new javax.swing.JTextField();
        Price = new javax.swing.JLabel();
        PublishDate = new javax.swing.JLabel();
        StockInNum = new javax.swing.JLabel();
        InputPrice = new javax.swing.JTextField();
        InputPublishdate = new javax.swing.JTextField();
        InputShopNum = new javax.swing.JTextField();
        Press = new javax.swing.JLabel();
        InputPress = new javax.swing.JTextField();
        Alter = new javax.swing.JButton();
        Reset = new javax.swing.JButton();
        Front = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        lblBack = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("图书信息管理");

        Delete.setText("删除");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        Refresh.setText("刷新");
        Refresh.setToolTipText("");
        Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshActionPerformed(evt);
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

        Add.setText("添加");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        jScrollPane2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jScrollPane2PropertyChange(evt);
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
                "读者编号", "图书名称", "作者", "出版社", "价格", "出版日期", "入库数量", "在库数量"
            }
        ));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("筛选模式（左栏信息可筛选）"));

        BookNo.setText("图书编号：");

        BookName.setText("图书名称：");

        Author.setText("作者：");

        InputBookNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputBookNumActionPerformed(evt);
            }
        });

        InputBookName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputBookNameActionPerformed(evt);
            }
        });

        InputAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputAuthorActionPerformed(evt);
            }
        });

        Price.setText("价格：");

        PublishDate.setText("出版日期：");

        StockInNum.setText("入库数量：");

        InputPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputPriceActionPerformed(evt);
            }
        });

        Press.setText("出版社：");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Press)
                        .addGap(18, 18, 18)
                        .addComponent(InputPress, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BookNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InputBookNum))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BookName)
                            .addComponent(Author))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InputBookName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(InputAuthor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 382, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Price)
                    .addComponent(PublishDate)
                    .addComponent(StockInNum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(InputShopNum)
                    .addComponent(InputPublishdate)
                    .addComponent(InputPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Price)
                            .addComponent(InputPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PublishDate)
                            .addComponent(InputPublishdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(StockInNum)
                            .addComponent(InputShopNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BookNo)
                            .addComponent(InputBookNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BookName)
                            .addComponent(InputBookName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Author)
                            .addComponent(InputAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputPress, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Press))
                .addGap(20, 20, 20))
        );

        Alter.setText("修改");
        Alter.setToolTipText("");
        Alter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlterActionPerformed(evt);
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
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Refresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Alter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Reset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Front)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Left)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Right)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Back))
                    .addComponent(jScrollPane2))
                .addGap(10, 10, 10))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Left)
                        .addComponent(Right)
                        .addComponent(Front)
                        .addComponent(Back))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Add)
                        .addComponent(Delete)
                        .addComponent(Refresh)
                        .addComponent(Alter)
                        .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        if (getbookno()==null || !Util4Frm.confirmdelete()) 
            return;
        String BookNo = getbookno();
        if (BookDBCon.updateData("delete from Book where bookNO = '"+BookNo+"'")) {

        //判断是否选中图书与二次确认是否删除图书
        if (getbookno()==null || !Util4Frm.confirmdelete()) 
            return;
        BookNo = getbookno();
        String sql;
        sql = "select * from View_Book where 图书编号='"+BookNo+"' and 在库数量=入库数量";
        if (BookDBCon.queryResult(sql) == null)
        {
            JOptionPane.showMessageDialog(null,"还有读者未归还此本图书，因此无法删除此书","系统提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        //先删除借阅表中此书的历史记录再删除这本书
        sql = "delete from Borrow where bookno='"+BookNo+"' and returnDate is not null";
        BookDBCon.updateData(sql);
        sql = "delete from Book where bookNO = '"+BookNo+"'";
        if (BookDBCon.updateData(sql)) {
                JOptionPane.showMessageDialog(null,"删除信息成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
        } else {
                JOptionPane.showMessageDialog(null,"删除信息失败","系统提示",JOptionPane.ERROR_MESSAGE);
        }
        RefreshBookInformation("");
    }//GEN-LAST:event_DeleteActionPerformed

    private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshActionPerformed
        // TODO add your handling code here:
        RefreshBookInformation("");
    }//GEN-LAST:event_RefreshActionPerformed

    private void InputBookNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputBookNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputBookNumActionPerformed

    private void InputBookNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputBookNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputBookNameActionPerformed

    private void InputAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputAuthorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputAuthorActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        //当文本框不为空时，用函数textFileIsNull()封装该代码
        //InputBookNum.getText().equals("") || InputBookName.getText().equals("") || InputAuthor.getText().equals("") || InputPress.getText().equals("") || InputPrice.getText().equals("") || InputPublishdate.getText().equals("") || InputShopNum.getText().equals("");
       
        //封装updateData()
//        Connection conn=JdbcCon();
//        try{    
//            Statement stmt = conn.createStatement();
//            int rs = stmt.excuteUpdate(sql);
//            stmt.close();
//            conn.close();
//            if(rs>0)
//                return true;
//            else{
//                return false;
//             }
//         }catch(SQLException e){
//             e.printstack();
//             System.out.println("添加数据库失败");
//         }
        if (textFiledIsNull()){
             JOptionPane.showMessageDialog(null,"请填写欲添加书籍的所有信息","系统提示",JOptionPane.INFORMATION_MESSAGE);
             return;
         } else if (BookDBCon.updateData("INSERT INTO Book VALUES('"+InputBookNum.getText()+"','"+InputBookName.getText()+"','"+InputAuthor.getText()+"','"+InputPress.getText()+"',"+InputPrice.getText()+",'"+InputPublishdate.getText()+"',"+InputShopNum.getText()+")")) {
            JOptionPane.showMessageDialog(null,"添加信息成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
         } else {
             JOptionPane.showMessageDialog(null,"添加信息失败","系统提示",JOptionPane.ERROR_MESSAGE);
             return;
         }
         resetTextfiled();
         RefreshBookInformation("");
    }//GEN-LAST:event_AddActionPerformed

    private void LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeftActionPerformed
        // 用函数封装好了      
        int tem = jTable1.getSelectedRow()-1; //-1表示向上移动
        if(tem<0)
            JOptionPane.showMessageDialog(null,"已经是第一条数据了","系统提示",JOptionPane.INFORMATION_MESSAGE);
        jTable1.setRowSelectionInterval(tem, tem);
      //  Util4Frm.moveFormRow(jTable1, -1);
    }//GEN-LAST:event_LeftActionPerformed

    private void RightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RightActionPerformed
        // 继续用函数封装
        int tem = jTable1.getSelectedRow()+1;  //1表示向下移动
        if(tem>=jTable1.getRowCount())
            JOptionPane.showMessageDialog(null,"已经是最后一条数据了","系统提示",JOptionPane.INFORMATION_MESSAGE);
        jTable1.setRowSelectionInterval(tem, tem);
         //Util4Frm.moveFormRow(jTable1, 1); 
    }//GEN-LAST:event_RightActionPerformed

    /**
     * 修改保存按钮
     */
    private void AlterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlterActionPerformed
        //在修改书本信息的时候需要选择具体一本书，但有可能会存在没有选择书本的情况
        //判断没有选中书本的情况  用getbookno()封装
//        if(jTable1.getSelecetRow()==null){
//            JOptionPane.showMessageDialog(null,"请选择一本书","系统提示",JOptionPane.INFORMATION_MESSAGE);
//            return null;
//        }else{
//          String r = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
//        }
//        
        
        
        if (Alter.getText().equals("修改")){
            if (getbookno()==null)
                return;
            String ReaderNO = getbookno();
            getdatatotextfiled();//修改后的数据显示在表格中
            Util4Frm.locktextfiled(InputBookNum);//锁定书籍编号这一栏
            jPanel1.setBorder(BorderFactory.createTitledBorder("编辑模式"));
            Alter.setText("保存");
            Reset.setEnabled(false);
        } else {
            if (BookDBCon.updateData("update Book set bookName='"+InputBookName.getText()+"',authorName='"+InputAuthor.getText()+"',publishingName='"+InputPress.getText()+"',price="+InputPrice.getText()+",publishingDate='"+InputPublishdate.getText()+"',shopNum="+InputShopNum.getText()+" where bookNO='" + InputBookNum.getText()+"'")) {
                JOptionPane.showMessageDialog(null,"修改信息成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,"修改信息失败","系统提示",JOptionPane.ERROR_MESSAGE);
            }
            resetTextfiled();//刷新
            Util4Frm.unlocktextfiled(InputBookNum);
            RefreshBookInformation("");
           //加入筛选模式
            jPanel1.setBorder(BorderFactory.createTitledBorder("筛选模式(左栏信息可筛选)"));
            Alter.setText("修改");
            Reset.setEnabled(true);
        }
    }//GEN-LAST:event_AlterActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        // TODO add your handling code here:
        resetTextfiled();
        RefreshBookInformation("");
    }//GEN-LAST:event_ResetActionPerformed

    private void FrontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FrontActionPerformed

//        int tem = jTable1.getSelectedRow();
//        tem = 0;
//        jtable.setRowSelectionInterval(tmp, tmp);
        Util4Frm.moveFormRowToTop(jTable1, 0);
    }//GEN-LAST:event_FrontActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        //最终都调用封装的函数
//        int tem = jTable1.getSelectedRowCount();
//        jtable.setRowSelectionInterval(tmp, tmp);
        Util4Frm.moveFormRowToTop(jTable1, 1);
    }//GEN-LAST:event_BackActionPerformed

    private void jScrollPane2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jScrollPane2PropertyChange
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jScrollPane2PropertyChange

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void InputPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputPriceActionPerformed

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
            java.util.logging.Logger.getLogger(FrmBookManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBookManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBookManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBookManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBookManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton Alter;
    private javax.swing.JLabel Author;
    private javax.swing.JButton Back;
    private javax.swing.JLabel BookName;
    private javax.swing.JLabel BookNo;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Front;
    private javax.swing.JTextField InputAuthor;
    private javax.swing.JTextField InputBookName;
    private javax.swing.JTextField InputBookNum;
    private javax.swing.JTextField InputPress;
    private javax.swing.JTextField InputPrice;
    private javax.swing.JTextField InputPublishdate;
    private javax.swing.JTextField InputShopNum;
    private javax.swing.JButton Left;
    private javax.swing.JLabel Press;
    private javax.swing.JLabel Price;
    private javax.swing.JLabel PublishDate;
    private javax.swing.JButton Refresh;
    private javax.swing.JButton Reset;
    private javax.swing.JButton Right;
    private javax.swing.JLabel StockInNum;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblBack;
    // End of variables declaration//GEN-END:variables
}
