/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import javax.swing.JOptionPane;

/**
 *
 * @author GERAL
 */
public class TesteConexao {
    public static void main (String[] args){
        
        try{
            ModuloConexao.conectar();
        
        } catch (Exception erro){
             JOptionPane.showMessageDialog(null, "Opss, algo deu errado: " + erro);
      
        }
    }
    
}
