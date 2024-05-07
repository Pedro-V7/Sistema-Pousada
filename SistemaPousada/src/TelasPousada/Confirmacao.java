/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TelasPousada;

import DalPousada.ConexaoPousada;
import static TelasPousada.Perfil.CadEmail;
import static TelasPousada.Quartos.cadNum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
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
         CadEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CadEntradaKeyReleased(evt);
            }
        });

        CadSaida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CadSaidaKeyReleased(evt);
            }
        });
        Preco.setVisible(false);
    }
       
      
     private void Alugar(){
      String situacao = "INDISPONÍVEL";   
       String datas = "";
       String dataTabela ="";
        
    String sql = "UPDATE tbl_quarto SET situacao=? dataEntrada=?,dataSaida=? WHERE numero=?";

    
   try{
            pst = conexao.prepareStatement(sql);
            pst.setString(2,situacao);
            datas = CadEntrada.getText();
            dia = datas.substring(0, 2);
            mes = datas.substring(3, 5);
            ano = datas.substring(6, 10);
            dataTabela = ano+mes+dia;
            pst.setString(2, dataTabela);
            datas = CadSaida.getText();
            dia2 = datas.substring(0, 2);
            mes2 = datas.substring(3, 5);
            ano2 = datas.substring(6, 10);
            dataTabela= ano2+mes2+dia2;
            pst.setString(3, dataTabela);
            pst.setString(4, NumCad.getText());
            
            int adicionado = pst.executeUpdate();
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso!");
            }
            
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
       }
     
   private void CadEntradaKeyReleased(java.awt.event.KeyEvent evt) {
        if(CadEntrada.getText().length()==10 && CadSaida.getText().length()==10){
            // Atualiza as variáveis ano, mes e dia antes de criar o objeto Period
            ano = CadEntrada.getText().substring(6, 10);
            mes = CadEntrada.getText().substring(3, 5);
            dia = CadEntrada.getText().substring(0, 2);
            
            LocalDate dataEntrada = LocalDate.of(Integer.parseInt(ano),Integer.parseInt(mes),Integer.parseInt(dia));
            LocalDate dataSaida = LocalDate.of(Integer.parseInt(ano2),Integer.parseInt(mes2),Integer.parseInt(dia2));
            
            long Dias = ChronoUnit.DAYS.between(dataEntrada, dataSaida);
            String DiasAlugado = String.valueOf(Dias);
            cadDias.setText(DiasAlugado);
            int Valor = Integer.parseInt(Preco.getText());
            int TotalD = Integer.parseInt(DiasAlugado);
            int Calculo = TotalD * Valor;
           String CalculoStr = String.valueOf(Calculo);
           cadPrecoT.setText(CalculoStr);
           System.out.print(CalculoStr);
        }
    }

    // Método para tratar o evento de liberação de tecla para CadSaida
    private void CadSaidaKeyReleased(java.awt.event.KeyEvent evt) {
        if(CadEntrada.getText().length()==10 && CadSaida.getText().length()==10){
            // Atualiza as variáveis ano2, mes2 e dia2 antes de criar o objeto Period
            ano2 = CadSaida.getText().substring(6, 10);
            mes2 = CadSaida.getText().substring(3, 5);
            dia2 = CadSaida.getText().substring(0, 2);
            
            LocalDate dataEntrada = LocalDate.of(Integer.parseInt(ano),Integer.parseInt(mes),Integer.parseInt(dia));
            LocalDate dataSaida = LocalDate.of(Integer.parseInt(ano2),Integer.parseInt(mes2),Integer.parseInt(dia2));
            
            long Dias = ChronoUnit.DAYS.between(dataEntrada, dataSaida);
            String DiasAlugado = String.valueOf(Dias);
            cadDias.setText(DiasAlugado);
            int Valor = Integer.parseInt(Preco.getText());
            int TotalD = Integer.parseInt(DiasAlugado);
            int Calculo = TotalD * Valor;
            String CalculoStr = String.valueOf(Calculo);
            cadPrecoT.setText(CalculoStr);
            System.out.print(CalculoStr);
           
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Preço Total:");

        cadPrecoT.setEditable(false);

        jLabel2.setText("Dia de entrada:");

        jLabel3.setText("Dia de saída:");

        jLabel4.setText("Total de dias:");

        cadDias.setEditable(false);

        cadConfirm.setText("Confirmar");

        jLabel5.setText("Nº do quarto");

        NumCad.setEditable(false);

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

        Preco.setEditable(false);
        Preco.setRequestFocusEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(31, 31, 31)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CadEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(CadSaida))
                        .addGap(200, 200, 200))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(NumCad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(82, 82, 82)
                                    .addComponent(cadConfirm)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cadPrecoT, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cadDias, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Preco, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(NumCad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Preco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CadEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CadSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cadDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cadPrecoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(cadConfirm)
                .addContainerGap())
        );

        Preco.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JButton cadConfirm;
    private javax.swing.JTextField cadDias;
    private javax.swing.JTextField cadPrecoT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
