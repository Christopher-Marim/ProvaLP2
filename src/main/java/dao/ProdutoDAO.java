/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Produto;
import view.TelaPrincipalView;

/**
 *
 * @author chris
 */
public class ProdutoDAO {

    private static final ProdutoDAO instance = new ProdutoDAO();

    /**
     *
     * @return
     */
    public static ProdutoDAO getInstance() {
        return instance;
    }

    /**
     *
     * @param nome
     * @param preco
     */
    public void remove(int id) {

        try {
            Connection conexao = new Conexao().getConnection();

            String sql = "delete from produto where id=" + id + "";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.execute();

            conexao.close();

        } catch (SQLException ex) {

            System.out.printf("Erro ao remover %s\n%s\n\n", id, ex.getMessage());
            Logger.getLogger(TelaPrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insert(String nome, double preco) {

        try {
            Connection conexao = new Conexao().getConnection();

            String sql = "insert into produto(nomer,preco) values('" + nome + "', '" + preco + "')";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.execute();

            conexao.close();

        } catch (SQLException ex) {

            System.out.printf("Erro ao adicionar %s\n%s\n\n", nome, ex.getMessage());
            Logger.getLogger(TelaPrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public ArrayList<Produto> getProdutos() {
        ArrayList<Produto> produtos = new ArrayList<>();
        try {
            Connection conexao = new Conexao().getConnection();
            String sql = "select * from produto";

            PreparedStatement statement = conexao.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setNome(rs.getString(2));
                p.setPreco(rs.getDouble(3));
                produtos.add(p);
            }

            conexao.close();
            return produtos;

        } catch (SQLException ex) {

            Logger.getLogger(TelaPrincipalView.class.getName()).log(Level.SEVERE, null, ex);
            return produtos;
        }
    }

}
