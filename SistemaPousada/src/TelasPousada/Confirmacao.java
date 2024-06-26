/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TelasPousada;

import DalPousada.ConexaoPousada;
import static TelasPousada.Perfil.CadEmail;
import static TelasPousada.Quartos.cadNum;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;


/**
 *
 * @author CT Desenvolvimento
 */
public class Confirmacao extends javax.swing.JFrame {
    
       String ano = "";
       String mes = "";
       String dia = "";
       String ano2 = "";
       String mes2 = "";
       String dia2 = "";
   Connection conexao =  ConexaoPousada.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;
    public Confirmacao() {
        initComponents();
    }
       
      
     private void Alugar(){
      if(verificação() == false){
          dispose();
          return;
          
      }
      String situacao = "INDISPONÍVEL";   
       String datas = "";
       String dataTabela ="";
        
    String sql = "UPDATE tbl_quartos SET situacao=?,dataReserva=?,dataSaida=?,total=? WHERE numero=?";

    
   try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1,situacao);
            datas = CadEntrada.getText();
            dia = datas.substring(0, 2);
            mes = datas.substring(3, 5);
            ano = datas.substring(6, 10);
            dataTabela = ano+mes+dia;
            String validar = dia + "/" + mes + "/"+ ano;
            if(! isValid(validar)){
                return;
            }
            pst.setString(2, dataTabela);
            datas = CadSaida.getText();
            dia2 = datas.substring(0, 2);
            mes2 = datas.substring(3, 5);
            ano2 = datas.substring(6, 10);
            dataTabela= ano2+mes2+dia2;
            validar = dia2 + "/" + mes2 + "/"+ ano2;
            if(! isValid(validar)){
                return;
            }
            pst.setString(3, dataTabela);
            pst.setString(4, cadPrecoT.getText());
            pst.setString(5, NumCad.getText());
            
            int adicionado = pst.executeUpdate();
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "Quarto Reservado!");
                reservarProUser();
            }
            
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
       }
     
     
     
 private void reservarProUser(){
    String User = UserC.getText();
    String sql = "UPDATE tbl_cliente SET reserva=? WHERE email=?";
    try {
        pst = conexao.prepareStatement(sql);
        pst.setString(1, NumCad.getText());
        pst.setString(2, User);
        
        int rowsAffected = pst.executeUpdate(); // Executa a atualização e retorna o número de linhas afetadas
        
        if (rowsAffected > 0) {
            System.out.println("Atualização bem-sucedida."); // Ou qualquer outra ação que você queira tomar
            int HIDE_ON_CLOSE1 = Quartos.HIDE_ON_CLOSE;
            dispose();
            
        } else {
            System.out.println("Nenhuma atualização realizada."); // Ou qualquer outra ação que você queira tomar
        }
    } catch(Exception e) {
        System.out.println(e);
    }
}

    // Método para tratar o evento de liberação de tecla para CadSaida
    private void Totais(){

        try{
        if(CadEntrada.getText().length()==10 && CadSaida.getText().length()==10){
            ano = CadEntrada.getText().substring(6, 10);
            mes = CadEntrada.getText().substring(3, 5);
            dia = CadEntrada.getText().substring(0, 2);
            ano2 = CadSaida.getText().substring(6, 10);
            mes2 = CadSaida.getText().substring(3, 5);
            dia2 = CadSaida.getText().substring(0, 2);
            
            LocalDate dataEntrada = LocalDate.of(Integer.parseInt(ano),Integer.parseInt(mes),Integer.parseInt(dia));
            LocalDate dataSaida = LocalDate.of(Integer.parseInt(ano2),Integer.parseInt(mes2),Integer.parseInt(dia2));
            long Dias = ChronoUnit.DAYS.between(dataEntrada, dataSaida);
            String DiasAlugado = String.valueOf(Dias);
            if(Integer.parseInt(DiasAlugado)<=0){
            JOptionPane.showMessageDialog(null, "Formato de data inválida");
            return ;     
            }
            cadDias.setText(DiasAlugado);
            String valorStr = Preco.getText();
            System.out.print(valorStr);
            Double Valor = Double.parseDouble(valorStr);
            int TotalD = Integer.parseInt(DiasAlugado);
            Double Calculo = TotalD * Valor;
            String CalculoStr = String.valueOf(Calculo);
            cadPrecoT.setText(CalculoStr);
        }
        else{
          JOptionPane.showMessageDialog(null, "Digite as datas que deseja ficar no quarto");
 
        }
    }
        catch(Exception e){
            System.out.print(e);
        }
    }
    


   public boolean isValid(String date) {
      try {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         LocalDate d = LocalDate.parse(date, formatter);    
         return true;
      } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "Formato de data inválida");
        return false;
      }   
   }
   
   
  public boolean verificação(){
       
       String sql = "SELECT reserva FROM tbl_cliente WHERE email=?";
       
        try{
    pst = conexao.prepareStatement(sql);
    pst.setString(1, UserC.getText() );
    rs = pst.executeQuery();
    if (rs.next()){
       String reservaFeita = rs.getString(1);
       if(reservaFeita != ""){
        JOptionPane.showMessageDialog(null, "Não é possível fazer duas reservas com o mesmo usuario!");   
        return false;
       }
       else{
           
       }
    }
        }
        catch(Exception e){
            System.out.println(e);
            
        }
         return true;  
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
        cadPrecoT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cadDias = new javax.swing.JTextField();
        cadConfirm = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        NumCad = new javax.swing.JTextField();
        CadEntrada = new javax.swing.JFormattedTextField();
        CadSaida = new javax.swing.JFormattedTextField();
        Preco = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        UserC = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 436));

        jLabel1.setText("Preço Total:");

        cadPrecoT.setEditable(false);

        jLabel2.setText("Dia de entrada:");

        jLabel3.setText("Dia de saída:");

        jLabel4.setText("Total de dias:");

        cadDias.setEditable(false);

        cadConfirm.setText("Confirmar");
        cadConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadConfirmActionPerformed(evt);
            }
        });

        jLabel5.setText("Nº do quarto");

        NumCad.setEditable(false);
        NumCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumCadActionPerformed(evt);
            }
        });

        try {
            CadEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            CadSaida.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        CadSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadSaidaActionPerformed(evt);
            }
        });
        CadSaida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CadSaidaKeyPressed(evt);
            }
        });

        Preco.setEditable(false);
        Preco.setRequestFocusEnabled(false);

        jLabel6.setText("Preco por Noite");

        UserC.setText("User");

        jLabel7.setText("Escolha a Bandeira do Cartão de Crédito:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Visa", "MasterCard" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Número do Cartão:");

        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("################")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel9.setText("CVV:");

        try {
            jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(31, 31, 31)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(41, 41, 41))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel4))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(63, 63, 63)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(NumCad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cadDias, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 5, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cadPrecoT, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(CadSaida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                                .addComponent(Preco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(CadEntrada, javax.swing.GroupLayout.Alignment.LEADING)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(71, 71, 71)
                        .addComponent(UserC)
                        .addGap(3607, 3607, 3607))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cadConfirm))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(NumCad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(UserC)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Preco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CadEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CadSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cadDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cadPrecoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(cadConfirm)
                .addGap(21, 21, 21))
        );

        Preco.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NumCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumCadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NumCadActionPerformed

    private void CadSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadSaidaActionPerformed

          
    }//GEN-LAST:event_CadSaidaActionPerformed

    private void CadSaidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CadSaidaKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            Totais();
        };
    }//GEN-LAST:event_CadSaidaKeyPressed

    private void cadConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadConfirmActionPerformed
      Alugar();
    }//GEN-LAST:event_cadConfirmActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(Confirmacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Confirmacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Confirmacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Confirmacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Confirmacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField CadEntrada;
    private javax.swing.JFormattedTextField CadSaida;
    public static javax.swing.JTextField NumCad;
    public static javax.swing.JTextField Preco;
    public static javax.swing.JLabel UserC;
    private javax.swing.JButton cadConfirm;
    private javax.swing.JTextField cadDias;
    private javax.swing.JTextField cadPrecoT;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
