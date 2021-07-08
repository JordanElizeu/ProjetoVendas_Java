package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class ClientesDAO {

    private Connection connection;

    public ClientesDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void cadastrarClientes(Clientes cli) {
        try {
            String sql = "INSERT INTO tb_clientes (nome,rg,cpf,email,telefone,celular,cep,endereco,numero" + ",complemento,bairro,cidade,estado)" + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, cli.getNome());
            stmt.setString(2, cli.getRg());
            stmt.setString(3, cli.getCpf());
            stmt.setString(4, cli.getEmail());
            stmt.setString(5, cli.getTelefone());
            stmt.setString(6, cli.getCelular());
            stmt.setString(7, cli.getCep());
            stmt.setString(8, cli.getEndereco());
            stmt.setInt(9, cli.getNumero());
            stmt.setString(10, cli.getComplemento());
            stmt.setString(11, cli.getBairro());
            stmt.setString(12, cli.getCidade());
            stmt.setString(13, cli.getUf());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Algo deu errado!" + ex);
        }
    }
    public List<Clientes> listarClientes() {
        try {

            //1 passo criar a lista
            List<Clientes> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "SELECT * FROM tb_clientes";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Clientes cli = new Clientes();

                cli.setId(rs.getInt("id"));
                cli.setNome(rs.getString("nome"));
                cli.setRg(rs.getString("rg"));
                cli.setCpf(rs.getString("cpf"));
                cli.setEmail(rs.getString("email"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setCelular(rs.getString("celular"));
                cli.setCep(rs.getString("cep"));
                cli.setEndereco(rs.getString("endereco"));
                cli.setNumero(rs.getInt("numero"));
                cli.setComplemento(rs.getString("complemento"));
                cli.setBairro(rs.getString("bairro"));
                cli.setCidade(rs.getString("cidade"));
                cli.setUf(rs.getString("estado"));

                lista.add(cli);
            }

         
            return lista;
           
            

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
    
    public void excluirCliente(Clientes cli){
        try {
            String sql = "DELETE FROM tb_clientes WHERE ID = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cli.getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso .");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Algo deu errado!" + ex);
        }
    }
}
