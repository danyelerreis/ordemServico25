/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jdbc.ModuloConexao;
import model.Cliente;
import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;




/**
 *
 * @author GERAL
 */
public class ClienteDAO { 
    Connection conexao;
    
    public ClienteDAO(){
        this.conexao= ModuloConexao.conectar();
    }
    
      public List<Cliente> listarCliente() {
        try {

            //1 passo criar a lista
            List<Cliente> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from tbclientes";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente obj = new Cliente();

                obj.setId(rs.getInt("idcli"));
                obj.setNome(rs.getString("nomecli"));
                obj.setEndereco(rs.getString("endcli"));
                obj.setFone(rs.getString("fonecli"));
                obj.setEmail(rs.getString("emailcli"));
                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
  
     public void adicionarCliente(Cliente obj) {

        try {
            //1 passo - criar o sql
            String sql = "insert into tbclientes(nome, email, endereco, fone) values(?,?,?,?,?)";
            //2 passo o conectar o banco de dados e organizar o comando sql
            conexao= ModuloConexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getEndereco());
            stmt.setString(5, obj.getFone());

            //3 passo - executar o comando sql
            stmt.execute();
            //  System.out.println(stmt);
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!!");

        } catch (SQLIntegrityConstraintViolationException e1) {
            JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login.");

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    /**
     * método responsável por alterar usuário no banco
     *
     * @param obj
     */
    public void alterarCliente(Cliente obj){

        try {
            //1 passo - criar o sql
            String sql = "update tbclientes set  nome=?, email=?, endereco=?, fone=?, where idcli=?";
            //2 passo o conectar o banco de dados e organizar o comando sql
            conexao = ModuloConexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getEmail());
            stmt.setString(3, obj.getEndereco());
            stmt.setString(4, obj.getFone());
            stmt.setInt(5, obj.getId());

            //3 passo - executar o comando sql
            stmt.execute();
            //  System.out.println(stmt);
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!!");

        } catch (SQLIntegrityConstraintViolationException e1) {
            JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login.");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    /**
     * Método que busca o usuáro pelo ID
     *
     * @param idCli do tipo inteiro
     * @return Objeto Usuario(model)
     */
    public Cliente buscarCliente(int idCli) {
        try {
            //1 passo - criar o sql
            String sql = "select * from tbclientes WHERE idcli = ?;";

            //2 passo o conectar o banco de dados e organizar o comando sql
            conexao = ModuloConexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idCli);

            //3 passo - executar o comando sql
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("idcli"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setFone(rs.getString("fone"));
                
                return cliente;

            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!!");
            }
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
     
    }
    }
 
