/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TelasPousada;

import java.sql.*;
import DalPousada.ConexaoPousada;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;
import javax.swing.JOptionPane;
public class TelaCadastro extends javax.swing.JFrame {

    Connection conexao =  ConexaoPousada.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaCadastro() {
        initComponents();
    }

private void criar(){
    
       String DDD = "";
       String DDDtabela = "";
          
    
         
     String email = cadEmail.getText();     
try{
          InternetAddress Email =  new InternetAddress(email);
          Email.validate();
      }
      catch(Exception e){
          JOptionPane.showMessageDialog(null,"Email Inválido");    
          return;        
      }
        
int soma1 = 0;;
int soma2 = 0;
		
    String cpf = cadCPF.getText();

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
	
      String estado = cadEstado.getText().toUpperCase();
      
         if(estado.equals("SP")|| estado.equals("RO")|| estado.equals("AC") ||estado.equals("AM") ||estado.equals("RR") ||estado.equals("PA") ||estado.equals("AP") ||estado.equals("TO") ||estado.equals("MA") ||estado.equals("PI") ||estado.equals("CE") ||estado.equals("RN") ||estado.equals("PB") ||estado.equals("PE") ||estado.equals("AL") ||estado.equals("SE") ||estado.equals("BA") ||estado.equals("MG") ||estado.equals("ES") ||estado.equals("RJ") ||estado.equals("PR") ||estado.equals("SC") ||estado.equals("RS") ||estado.equals("MS") ||estado.equals("MT") ||estado.equals("GO") ||estado.equals("DF")){
        }
        else{
           JOptionPane.showMessageDialog(null,"Estado inexistente ou digitado de forma incorreta");
           return;
        }
         
         cadEstado.setText(estado);
         
       String senha = cadSenha.getText();
       String senhaC = cadConfirm.getText();
         
        System.out.println(senha);
        System.out.println(senhaC);
         if(!senha.equals(senhaC)){
             JOptionPane.showMessageDialog(null, "Senha não confirmada corretamente, por favor verifique sua senha novamente");
             return;
         }
         
        String sql = "INSERT INTO tbl_cliente (nome,cpf,endereco,ddd,telefone,cidade,estado,email,senha) VALUES(?,?,?,?,?,?,?,?,?)";
        //Pega os valores e inseri dentro da tabela do banco de dados. Usando o ? pra indicar que será inserido um valor
        
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cadNome.getText());
            pst.setString(2, cadCPF.getText());
            pst.setString(3, cadEndereco.getText());
            DDD = cadDDD.getText();
            DDDtabela = DDD.substring(1, 3);
            pst.setString(4, DDDtabela);
            pst.setString(5, cadTelefone.getText());
            pst.setString(6, cadCidade.getText());
            pst.setString(7, cadEstado.getText());
            pst.setString(8, cadEmail.getText());
            pst.setString(9, cadSenha.getText());  
            
            
           
            int adicionado = pst.executeUpdate();
            //retorna 1 se estiver ok
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "Usuário cadastrado!");
                //As linhas abaixo irá limpar o formulario 
                
        cadNome.setText(null);
        cadCPF.setText(null);
        cadEndereco.setText(null);
        cadDDD.setText(null);
        cadTelefone.setText(null); 
        cadCidade.setText(null);
        cadEstado.setText(null); 
        
  
            }
         
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
             System.out.println(e);
             return;
        }
        
     }

criarSenha();
    }

 private void criarSenha(){
        //String sql = "INSERT INTO tbl_agenda (email) SELECT email FROM tbl_user ";
        String sql = "INSERT INTO tbl_login (email,senha) VALUES(?,?)";
        //Pega os valores e inseri dentro da tabela do banco de dados. Usando o ? pra indicar que será inserido um valor
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1,cadEmail.getText());
            pst.setString(2, cadSenha.getText());
           
            
            int adicionado = pst.executeUpdate();
            //retorna 1 se estiver ok
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "Senha e email cadastrado!");
                //As linhas abaixo irá limpar o formulario 
                cadSenha.setText(null);
                cadEmail.setText(null);
                cadConfirm.setText(null);
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cadNome = new javax.swing.JTextField();
        textCPF = new javax.swing.JLabel();
        cadCPF = new javax.swing.JTextField();
        textDDD = new javax.swing.JLabel();
        cadDDD = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        cadTelefone = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cadEndereco = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cadCidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cadEstado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cadEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cadSenha = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        cadConfirm = new javax.swing.JPasswordField();
        btnCadastrado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Nome");

        textCPF.setText("CPF");

        textDDD.setText("DDD");

        try {
            cadDDD.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel2.setText("Telefone");

        jLabel3.setText("Endereço");

        jLabel4.setText("Cidade");

        jLabel5.setText("Estado(UF)");

        jLabel6.setText("Email");

        jLabel7.setText("Senha");

        jLabel8.setText("Confirme a Senha");

        btnCadastrado.setText("Cadastrar");
        btnCadastrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastradoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cadConfirm))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(cadEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cadEndereco))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textDDD)
                                .addGap(18, 18, 18)
                                .addComponent(cadDDD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cadTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(textCPF))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cadNome)
                                    .addComponent(cadCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cadSenha))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cadEmail))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(cadCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(btnCadastrado)))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cadNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textCPF)
                    .addComponent(cadCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textDDD)
                    .addComponent(cadDDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cadTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cadEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cadCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cadEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cadEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cadSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cadConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnCadastrado)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastradoActionPerformed
    criar();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCadastradoActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrado;
    private javax.swing.JTextField cadCPF;
    private javax.swing.JTextField cadCidade;
    private javax.swing.JPasswordField cadConfirm;
    private javax.swing.JFormattedTextField cadDDD;
    private javax.swing.JTextField cadEmail;
    private javax.swing.JTextField cadEndereco;
    private javax.swing.JTextField cadEstado;
    private javax.swing.JTextField cadNome;
    private javax.swing.JPasswordField cadSenha;
    private javax.swing.JTextField cadTelefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel textCPF;
    private javax.swing.JLabel textDDD;
    // End of variables declaration//GEN-END:variables
}
