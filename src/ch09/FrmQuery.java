/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch09;

import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author abc
 */
public class FrmQuery extends javax.swing.JFrame {
    //定义默认的表格模式
    DefaultTableModel dtm=new DefaultTableModel();
    //定义JTable
    JTable tbl=new JTable(dtm);
    //定义带滚动条的面板
    JScrollPane scp=new JScrollPane();
    Vector data; //用于在表格中显式数据
    Vector title=new Vector(); //表头
    /**
     * Creates new form FrmQuery
     */
    public FrmQuery() {
        initComponents();
        //显式表格
        scp.setViewportView(tbl);
        scp.setBounds(20,150,370,220);
        //加入到窗口的内容面板里
        this.getContentPane().add(scp);
        //准备表头
        title.add("学号");
        title.add("姓名");
        title.add("性别");
        title.add("生日");
        //准备数据
        data=DBCon.queryVectorStudents("Select * from Student");
        //在表格显式数据
        showTableData();
    }
    //在表格显式数据
    void showTableData(){
       if(data!=null && data.size()>0){//有数据才显示
            //在表格显式数据
            dtm.setDataVector(data, title);
       }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngrpSex = new javax.swing.ButtonGroup();
        txtBirthday = new javax.swing.JTextField();
        radNull = new javax.swing.JRadioButton();
        lblStuID = new javax.swing.JLabel();
        txtStuID = new javax.swing.JTextField();
        lblStuName = new javax.swing.JLabel();
        txtStuName = new javax.swing.JTextField();
        lblSex = new javax.swing.JLabel();
        radMan = new javax.swing.JRadioButton();
        radWoman = new javax.swing.JRadioButton();
        lblBirthday = new javax.swing.JLabel();
        txtBirthday2 = new javax.swing.JTextField();
        lblZhi = new javax.swing.JLabel();
        btnQuery = new javax.swing.JButton();
        btnQueryAll = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("学生信息查询");

        txtBirthday.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N

        btngrpSex.add(radNull);
        radNull.setText("不限");

        lblStuID.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        lblStuID.setText("学号");

        txtStuID.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N

        lblStuName.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        lblStuName.setText("姓名");

        txtStuName.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N

        lblSex.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        lblSex.setText("性别");

        btngrpSex.add(radMan);
        radMan.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        radMan.setText("男");

        btngrpSex.add(radWoman);
        radWoman.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        radWoman.setText("女");

        lblBirthday.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        lblBirthday.setText("生日");

        lblZhi.setText("至");

        btnQuery.setText("查询");
        btnQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQueryActionPerformed(evt);
            }
        });

        btnQueryAll.setText("显示所有数据");
        btnQueryAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQueryAllActionPerformed(evt);
            }
        });

        btnReturn.setText("返回");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblStuID)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtStuID))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lblBirthday)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(34, 34, 34)
                                .addComponent(lblZhi))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblStuName)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblSex)
                                    .addGap(18, 18, 18)
                                    .addComponent(radMan)
                                    .addGap(18, 18, 18)
                                    .addComponent(radWoman)
                                    .addGap(22, 22, 22)
                                    .addComponent(radNull))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStuName, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBirthday2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(31, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnReturn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnQueryAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnQuery)
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStuID)
                    .addComponent(txtStuID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStuName)
                    .addComponent(txtStuName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radMan)
                        .addComponent(radWoman)
                        .addComponent(lblSex))
                    .addComponent(radNull))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBirthday2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblZhi))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblBirthday)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnQuery)
                        .addComponent(btnQueryAll))
                    .addComponent(btnReturn))
                .addContainerGap(253, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQueryActionPerformed
        //--1 获得所有的输入的查询条件
        String stuId=txtStuID.getText();
        String stuName=txtStuName.getText();
        String birth1=txtBirthday.getText();
        String birth2=txtBirthday2.getText();
        String sex="";
        if(radMan.isSelected()){
            sex="男";
        }else if(radWoman.isSelected()){
            sex="女";
        }
        //设置查询的SQL语句
        String sql="Select * from Student";
        String query="";
        //学号
        if(stuId!=null && stuId.length()>0){
            query="stuId like '%"+stuId+"%'";
        }
        //姓名
        if(stuName!=null && stuName.length()>0){
            if(query.length()>0){
                query=query + " and stuName like '%"+stuName+"%'";
            }
            else{
                query ="stuName like '%"+stuName+"%'";
            }
        }
        //性别
        if(sex.length()>0){
            if(query.length()>0){
                query=query + " and sex='"+sex+"'";
            }
            else{
                query="sex='"+sex+"'";
            }
        }
        
        //生日查询条件
        String birth="";
        if(birth1!=null && birth1.length()>0 && birth2!=null && birth2.length()>0 ){
            birth="birth >='"+birth1+"' and birth<='"+birth2+"'";
        }else if((birth1==null || birth1.length()==0) && birth2!=null && birth2.length()>0 ){
            birth="birth<='"+birth2+"'";
        }else if((birth1!=null || birth1.length()>0) && (birth2==null && birth2.length()==0) ){
            birth="birth>='"+birth1+"'";
        }
        //形成完整的查询SQL语句
        if(query.length()>0){
            sql=sql+" where "+query;
        }
        if(birth.length()>0){
            if(query.length()>0){
                sql=sql+" and "+birth;
            }else{
                sql=sql+" where "+birth;
            }
        }
        
        //调用DBCon的方法完成查询
        System.out.println(sql); //打印
        data=DBCon.queryVectorStudents(sql);
        showTableData(); //在表格中显示查询的结果
    }//GEN-LAST:event_btnQueryActionPerformed

    private void btnQueryAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQueryAllActionPerformed
        String sql="select * from Student";
        data=DBCon.queryVectorStudents(sql);
        showTableData(); //在表格中显示查询的结果
    }//GEN-LAST:event_btnQueryAllActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:
        FrmStudent frame=new FrmStudent();
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnReturnActionPerformed

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
            java.util.logging.Logger.getLogger(FrmQuery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmQuery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmQuery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmQuery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmQuery().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuery;
    private javax.swing.JButton btnQueryAll;
    private javax.swing.JButton btnReturn;
    private javax.swing.ButtonGroup btngrpSex;
    private javax.swing.JLabel lblBirthday;
    private javax.swing.JLabel lblSex;
    private javax.swing.JLabel lblStuID;
    private javax.swing.JLabel lblStuName;
    private javax.swing.JLabel lblZhi;
    private javax.swing.JRadioButton radMan;
    private javax.swing.JRadioButton radNull;
    private javax.swing.JRadioButton radWoman;
    private javax.swing.JTextField txtBirthday;
    private javax.swing.JTextField txtBirthday2;
    private javax.swing.JTextField txtStuID;
    private javax.swing.JTextField txtStuName;
    // End of variables declaration//GEN-END:variables
}
