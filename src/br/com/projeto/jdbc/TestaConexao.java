package br.com.projeto.jdbc;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class TestaConexao {
    
    public static void main(String[] args) {
        
        try {
            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "Operação com sucesso. ");
        } catch (Exception ex) {
            StringWriter strStackTrace = new StringWriter();
            ex.printStackTrace(new PrintWriter(strStackTrace));
            SwingUtilities.invokeLater(() -> TratacaoErros.showException(ex.getClass() + " " + ex.getMessage(),strStackTrace.toString()));
        }
    }
}
