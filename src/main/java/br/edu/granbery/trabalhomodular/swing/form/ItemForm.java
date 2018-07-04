package br.edu.granbery.trabalhomodular.swing.form;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ItemForm {

	private JTextField codigo = new JTextField(15);
	private JTextField descricao = new JTextField(15);
	private JTextField preco = new JTextField(15);
	private JTextField qtd = new JTextField(15);
	private ActionListener newItem;
	private ActionListener deleteItem;
	
	public JTextField getCodigo() {
		return codigo;
	}
	public void setCodigo(JTextField codigo) {
		this.codigo = codigo;
	}
	public JTextField getDescricao() {
		return descricao;
	}
	public void setDescricao(JTextField descricao) {
		this.descricao = descricao;
	}
	public JTextField getPreco() {
		return preco;
	}
	public void setPreco(JTextField preco) {
		this.preco = preco;
	}
	public JTextField getQtd() {
		return qtd;
	}
	public void setQtd(JTextField qtd) {
		this.qtd = qtd;
	}
	public void setNewItem(ActionListener newItem) {
		this.newItem = newItem;
	}
	public void setDeleteItem(ActionListener deleteItem) {
		this.deleteItem = deleteItem;
	}
	public JPanel build() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("ITEM"));
		panel.add(new JLabel("Descrição"));
		panel.add(descricao);
		panel.add(new JLabel("Preço"));
		panel.add(preco);
		panel.add(new JLabel("Quantidade"));
		panel.add(qtd);
		JButton salvarItem = new JButton("Inserir");
		panel.add(salvarItem);
		JButton deletarItem = new JButton("Excluir");
		panel.add(deletarItem);
		
		if(newItem != null) {
			salvarItem.addActionListener(newItem);
		}
		if(deletarItem != null) {
			deletarItem.addActionListener(deleteItem);
		}
		
		return panel;
				
	}
	
}
