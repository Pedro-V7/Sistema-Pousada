/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TelasPousada;

import DalPousada.ConexaoPousada;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.mail.internet.InternetAddress;
 
public class Perfil extends javax.swing.JFrame {
    
    Connection conexao =  ConexaoPousada.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;
   
    public Perfil() {
        initComponents();
         addWindowListener(new WindowAdapter() {
        @Override
        public void windowOpened(WindowEvent e) {
            achar(); // Chame o método achar() quando a janela for aberta
        }
    });
    }
  
    private void achar(){
        String email = CadEmail.getText();
        System.out.println(email); 
        
    String sql = "SELECT * FROM tbl_cliente WHERE email=?";
        
    try{
    pst = conexao.prepareStatement(sql);
    pst.setString(1, email );
    rs = pst.executeQuery();
    if (rs.next()){
       CadNome.setText(rs.getString(2));
       CadCPF.setText(rs.getString(3));
       CadEndereco.setText(rs.getString(4));
       CadDDD.setText(rs.getString(5));
       CadTelefone.setText(rs.getString(6));
       CadCidade.setText(rs.getString(7));
       CadEstado.setText(rs.getString(8));
       CadEmail.setText(rs.getString(9));
       CadSenha.setText(rs.getString(10));
       
         
    } else{
        JOptionPane.showMessageDialog(null, "Erro ao achar o usuario");
        //As linhas abaixo limpam os campos do formulário
   
    } 
       }
   catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
            }
      
    }
    
  private void mudar(){

       String DDD = "";
       String DDDtabela = "";
          
     String email = CadEmail.getText();     
try{
          InternetAddress Email =  new InternetAddress(email);
          Email.validate();
      }
      catch(Exception e){
          JOptionPane.showMessageDialog(null,"Email Inválido");    
          return;        
      }
    
   // Validando CPF 
  int soma1 = 0;
  int soma2 = 0;
		
String cpf = CadCPF.getText();
if (cpf.length() !=11) {
	JOptionPane.showMessageDialog(null,"O CPF deve ter 11 números, digite também sem pontos e traços");
          return;
		}
else {
	
     try {
		
	      int num1 = Integer.parseInt(String.valueOf(cpf.charAt(0)));
	      int num2 = Integer.parseInt(String.valueOf(cpf.charAt(1)));
	      int num3 = Integer.parseInt(String.valueOf(cpf.charAt(2)));
	      int num4 = Integer.parseInt(String.valueOf(cpf.charAt(3)));
	      int num5 = Integer.parseInt(String.valueOf(cpf.charAt(4)));
	      int num6 = Integer.parseInt(String.valueOf(cpf.charAt(5)));
	      int num7 = Integer.parseInt(String.valueOf(cpf.charAt(6)));
	      int num8 = Integer.parseInt(String.valueOf(cpf.charAt(7)));
	      int num9 = Integer.parseInt(String.valueOf(cpf.charAt(8)));
	      int num10 = Integer.parseInt(String.valueOf(cpf.charAt(9)));
	      int num11 = Integer.parseInt(String.valueOf(cpf.charAt(10)));
	            
	          

	     soma1 = num1 * 10 + num2 * 9 + num3 * 8 + num4 * 7 + num5 * 6 + num6 * 5 + num7 * 4 + num8 * 3 + num9 * 2;
	     int veri1 = (soma1*10)%11;
             soma2 = num1 * 11 + num2 * 10 + num3 * 9 + num4 * 8 + num5 * 7 + num6 * 6 + num7 * 5 + num8 * 4 + num9 * 3 + num10 * 2;
		
	     int veri2 = (soma2*10)%11;
	         
		
	if (num1 == num2 && num2 ==num3 && num3==num4 && num4 ==num5 && num5 == num6 && num6 == num7 && num7 == num8 && num8 == num9 && num9 == num10 && num10 == num11 ) {
             JOptionPane.showMessageDialog(null,"O CPF "+cpf + " é inválido");
             return;
            }
	         
	else if (veri1 != num10 || (veri2 != num11) ) {
       	 JOptionPane.showMessageDialog(null,"O CPF "+cpf + " é inválido");
         return;
        }
		
	else{
      soma1 = 0;
      soma2 = 0;
        }
					
	}

	catch(Exception e ) {
	 JOptionPane.showMessageDialog(null,"Digite apenas números!");
         return;
		}
  }	
         String estado = CadEstado.getText().toUpperCase();
      
         if(estado.equals("SP")|| estado.equals("RO")|| estado.equals("AC") ||estado.equals("AM") ||estado.equals("RR") ||estado.equals("PA") ||estado.equals("AP") ||estado.equals("TO") ||estado.equals("MA") ||estado.equals("PI") ||estado.equals("CE") ||estado.equals("RN") ||estado.equals("PB") ||estado.equals("PE") ||estado.equals("AL") ||estado.equals("SE") ||estado.equals("BA") ||estado.equals("MG") ||estado.equals("ES") ||estado.equals("RJ") ||estado.equals("PR") ||estado.equals("SC") ||estado.equals("RS") ||estado.equals("MS") ||estado.equals("MT") ||estado.equals("GO") ||estado.equals("DF")){
        }
        else{
           JOptionPane.showMessageDialog(null,"Estado inexistente ou digitado de forma incorreta");
           return;
        }
      
       String senha = CadSenha.getText();
       String senhaC = CadConfirm.getText();
       
        if(!senha.equals(senhaC)){
             JOptionPane.showMessageDialog(null, "Senha não confirmada corretamente, por favor verifique sua senha novamente");
             return;
         }
          
       String sql = "UPDATE tbl_cliente SET nome=?,cpf=?,endereco=?,ddd=?,telefone=?,cidade=?,estado=?,email=?,senha=? WHERE email=?";
          
       try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, CadNome.getText());
            pst.setString(2, CadCPF.getText());
            pst.setString(3, CadEndereco.getText());
            DDD = CadDDD.getText();
            DDDtabela = DDD.substring(1, 3);
            pst.setString(4, DDDtabela);
            pst.setString(5, CadTelefone.getText());
            pst.setString(6, CadCidade.getText());
            pst.setString(7, CadEstado.getText());
            pst.setString(8, CadEmail.getText()); 
            pst.setString(9, CadSenha.getText());  
            pst.setString(10, CadEmail.getText()); 
            int adicionado = pst.executeUpdate();
             
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso!");
            }
            
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
      }
     
  
   private void excluir(){
           // a estrutura abaixo confirma a exclusão do usuario da tabela
           
           int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este usuario?","Atençao",JOptionPane.YES_NO_OPTION);
           if (confirma == JOptionPane.YES_OPTION){    
           String sql = "DELETE FROM tbl_cliente WHERE email=?";
           
           try{
               pst = conexao.prepareStatement(sql);
               pst.setString(1, CadEmail.getText());
               int apagado = pst.executeUpdate();
               if(apagado > 0){
               JOptionPane.showMessageDialog(null, "Usuário apagado");
               //limpar formulario
       
               TelaLogin login = new TelaLogin();
               login.setVisible(true);
               System.exit(0);
                     
               
           }
               
           }
           catch(Exception e ){
               System.out.println( e);
               
           }
                   
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
        CadNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        CadCPF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        CadEndereco = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        CadDDD = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        CadTelefone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        CadCidade = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        CadEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        CadSenha = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        CadConfirm = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        CadEstado = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nome");

        jLabel2.setText("CPF");

        jLabel3.setText("Endereço");

        jLabel4.setText("DDD");

        try {
            CadDDD.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel5.setText("Telefone");

        jLabel6.setText("Cidade");

        jLabel7.setText("Estado(UF)");

        jLabel8.setText("Email");

        jLabel9.setText("Senha");

        jLabel10.setText("Confirmar Senha:");

        jButton1.setText("Editar Perfil");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Excluir Perfil");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CadConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(CadSenha))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(CadEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jButton1)
                        .addGap(35, 35, 35)
                        .addComponent(jButton2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CadCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addComponent(CadEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(CadDDD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(CadTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(CadEndereco))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addGap(37, 37, 37)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(CadNome)
                                .addComponent(CadCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)))))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(CadNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CadCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CadEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CadDDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(CadTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(CadCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(CadEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(CadEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(CadSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(CadConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        mudar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        excluir();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Perfil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CadCPF;
    private javax.swing.JTextField CadCidade;
    private javax.swing.JPasswordField CadConfirm;
    private javax.swing.JFormattedTextField CadDDD;
    public static javax.swing.JTextField CadEmail;
    private javax.swing.JTextField CadEndereco;
    private javax.swing.JTextField CadEstado;
    private javax.swing.JTextField CadNome;
    private javax.swing.JPasswordField CadSenha;
    private javax.swing.JTextField CadTelefone;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
