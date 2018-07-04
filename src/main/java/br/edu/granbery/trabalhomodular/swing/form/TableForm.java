package br.edu.granbery.trabalhomodular.swing.form;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.edu.granbery.trabalhomodular.model.Item;

public class TableForm {


	private DefaultTableModel model;

	public DefaultTableModel getModel() {
		return model;
	}

	public JScrollPane buildTabela() {
		Object[] column = new String[]{"Código", "Descrição", "Preço", "Qtd", "Total"};
		Object[][] data = new Object[][]{};
		
		this.model = new DefaultTableModel(data, column);
		JTable table = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {                
		        return false;               
		    };
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model);
		
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setViewportView(table);
		return jScrollPane;
	}
	
	public void addRow(Item i) {
		Integer codigo = i.getId();
		String descricao = i.getDescricao();
		Double preco = i.getPreco();
		Integer qtd = i.getQtd();
		Double total = preco * qtd;
		getModel().addRow(new Object[] { codigo, descricao, preco, qtd, total });
	}
	

}
