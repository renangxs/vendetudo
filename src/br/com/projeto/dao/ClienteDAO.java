/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Cliente;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author renan
 */
public class ClienteDAO {
    private Connection conexao;
    
    public ClienteDAO(){
    this.conexao = new ConexaoBanco().pegarConexao();
    }
    
    //cadastrar clientes
    public void cadastrarClientes(Cliente obj){
        try {
            //l - criar a instrucao SQL
        String sql = "insert into tb_clientes (nome, sobrenome, senha)" + "values(?, ?, ?)";
        //2 - preparar o sql
        PreparedStatement stmt = conexao.prepareStatement (sql);
        stmt.setString(1,obj.getNome());
        stmt.setString(2,obj.getSobrenome());
        stmt.setString(3,obj.getSenha());
        
        //3 - executar
        stmt.execute();
        stmt.close();
        JOptionPane.showMessageDialog(null, "cliente cadastradro com sucesso!");
        
    }catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "erro!" +erro);
    }
}
    //listar clientes
    public List<Cliente> listarClientes(){
        try {
            List<Cliente> lista = new ArrayList<>();
            
            //1- instrução sql
            String sql = "selct * from tb_clientes";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente obj = new Cliente();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setSobrenome(rs.getString("sobrenome"));
                
                lista.add(obj);
                
            }
            return lista;        
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"erro "+ e);
        }
        return null;
        
        
        
        
        
    }

    private static class Clinete {

        public Clinete() {
        }
    }
}
