/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TelasPousada;

import DalPousada.ConexaoPousada;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author CT Desenvolvimento
 */
public class Reservas extends javax.swing.JFrame {

   
    Connection conexao =  ConexaoPousada.conector();
    PreparedStatement pst = null;
    ResultSet rs = null; 
    public Reservas() {
        initComponents();
        initComponents();
         addWindowListener(new WindowAdapter() {
        @Override
        public void windowOpened(WindowEvent e) {
            NumeroQuarto(); // Chame o método achar() quando a janela for aberta
        }
    });
    }

    private void NumeroQuarto(){
      String Opa = UserR.getText();
      String sql = "SELECT reserva FROM tbl_cliente WHERE email=?";
                  
    try{
    pst = conexao.prepareStatement(sql);
    pst.setString(1,Opa);
        System.out.println(Opa);
        System.out.println(Opa);
    rs = pst.executeQuery();
    if (rs.next()){
        NumQuarto.setText(rs.getString(1));
        Achar();
    } else{
        JOptionPane.showMessageDialog(null, "Nenhuma reserva feita");
        //As linhas abaixo limpam os campos do formulário
        dispose();
    } 
       }
   catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
            }

    }
    
     private void Achar(){
     String datas = "";
     String ano = "";
     String mes = "";
     String dia = "";
     String dataTabela = "";
   
    String sql = "SELECT * FROM tbl_quartos WHERE numero=?";
    
    try{
    pst = conexao.prepareStatement(sql);
    pst.setString(1, NumQuarto.getText());
    rs = pst.executeQuery();
    if (rs.next()){
        datas = (rs.getString(5));
        ano = datas.substring(0, 4);
        mes = datas.substring(5, 7);
        dia = datas.substring(8, 10);
        dataTabela = dia+mes+ano;
        Chekin.setText(dataTabela);
        datas = (rs.getString(6));
        ano = datas.substring(0, 4);
        mes = datas.substring(5, 7);
        dia = datas.substring(8, 10);
        dataTabela = dia+mes+ano;
        Checkout.setText(dataTabela);
        PrecoPorNoite.setText(rs.getString(7));
        Pago.setText(rs.getString(8));
        
    } else{
        JOptionPane.showMessageDialog(null, "Nenhuma reserva encontrada!");
        //As linhas abaixo limpam os campos do formulário
       dispose();
    } 
       }
   catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
            }

    }
    
    private void Cancelar(){
       String ano = "";
       String mes = "";
       String dia = "";
       String status = "CANCELADA";   
       String datas = "";
       String dataTabela ="";
       
       String sql = "INSERT INTO tbl_veri (NumQuarto, entrada, saida, status) VALUES (?, ?, ?, ?)";
                try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, NumQuarto.getText());
            datas = Chekin.getText();
            dia = datas.substring(0, 2);
            mes = datas.substring(3, 5);
            ano = datas.substring(6, 10);
            dataTabela = ano+mes+dia;
            System.out.println(dataTabela);        
            pst.setString(2, dataTabela);
            datas = Checkout.getText();
            dia = datas.substring(0, 2);
            mes = datas.substring(3, 5);
            ano = datas.substring(6, 10);
            dataTabela = ano+mes+dia;
            pst.setString(3, dataTabela);
            pst.setString(4, status);
            
              int adicionado = pst.executeUpdate();
              
                if(adicionado > 0){
                    System.out.println("Foi");
                    Atualizar();
                }
                else{
                    System.out.println("Não foi");
                }
                }
                catch(Exception e){
                    System.out.println(e);
                }
    }
    
    private void Atualizar(){
        String situacao ="DISPONÍVEL";
            String sql = "UPDATE tbl_quartos SET situacao=?,dataReserva=?,dataSaida=?,total=? WHERE numero=?";
 try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1,situacao);
            pst.setString(2,null);
            pst.setString(3,null);
            pst.setString(4,null);
            pst.setString(5,NumQuarto.getText());
             int adicionado = pst.executeUpdate();
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "Reserva Cancelada");
                AtualizarUser();
            }
    }
 catch(Exception e ){
       System.out.println(e);
 }
    }
     private void AtualizarUser(){
        String sql = "UPDATE tbl_cliente SET reserva=? WHERE email=?";
try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1,null);
            pst.setString(2,UserR.getText());

            int adicionado = pst.executeUpdate();
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "Reserva Cancelada");
                dispose();
            }
    }
 catch(Exception e ){
      System.out.println(e);
 
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

        jLabel1 = new javax.swing.JLabel();
        NumQuarto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        PrecoPorNoite = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Pago = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        UserR = new javax.swing.JLabel();
        Chekin = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Checkout = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Número do Quarto:");

        NumQuarto.setEditable(false);

        jLabel3.setText("Preco:");

        PrecoPorNoite.setEditable(false);
        PrecoPorNoite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecoPorNoiteActionPerformed(evt);
            }
        });

        jLabel4.setText("Total Pago:");

        Pago.setEditable(false);
        Pago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PagoActionPerformed(evt);
            }
        });

        jButton1.setText("Cancelar Reserva");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        UserR.setText("User");

        try {
            Chekin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel5.setText("Checkin");

        jLabel6.setText("Checkout");

        try {
            Checkout.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(159, 159, 159))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(UserR)
                        .addGap(73, 73, 73))))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(28, 28, 28)
                        .addComponent(NumQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PrecoPorNoite, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(Pago, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Checkout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Chekin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(UserR)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(NumQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(Chekin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Checkout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(PrecoPorNoite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PagoActionPerformed

    private void PrecoPorNoiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrecoPorNoiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrecoPorNoiteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    Cancelar() ;       // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Reservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reservas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField Checkout;
    private javax.swing.JFormattedTextField Chekin;
    private javax.swing.JTextField NumQuarto;
    private javax.swing.JTextField Pago;
    private javax.swing.JTextField PrecoPorNoite;
    public static javax.swing.JLabel UserR;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
