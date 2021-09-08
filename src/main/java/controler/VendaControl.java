/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Venda;

/**
 *
 * @author chris
 */
public class VendaControl {

    private ArrayList<Venda> lista;

    public VendaControl(ArrayList<Venda> vendas) {
        this.lista = vendas;
    }

    public VendaControl() {

    }

    public ArrayList<Venda> getVendas() {

        return this.lista;
    }

    public void addVenda(int id, String nome, double preco, int qtd) {
        Venda v = new Venda(id, nome, preco, qtd);
        this.lista.add(v);

    }

    public void removeVenda(int id) {
            for (int i = 0; i < this.lista.size(); i++) {
                if (this.lista.get(i).getId() == id) {
                    this.lista.remove(i);                  
                }              
            }
    }

    public void removeAll() {

        this.lista.removeAll(lista);
    }

}
