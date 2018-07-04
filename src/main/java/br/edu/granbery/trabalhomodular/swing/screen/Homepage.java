package br.edu.granbery.trabalhomodular.swing.screen;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.edu.granbery.trabalhomodular.controller.NotaFiscalController;
import br.edu.granbery.trabalhomodular.model.NotaFiscal;
import br.edu.granbery.trabalhomodular.swing.action.ToolbarAction;
import br.edu.granbery.trabalhomodular.swing.component.Menubar;
import br.edu.granbery.trabalhomodular.swing.component.Toolbar;

public class Homepage {

	private NotaFiscalController nfc = new NotaFiscalController();
	
	public void render() {
		JTable table = buildTable();
		ToolbarAction toolbarAction = new ToolbarAction();
		JScrollPane jScrollPane = buildScrollPane(table);		
		Toolbar toolbar = buildToolbar(table, toolbarAction);
		Menubar menubar = new Menubar();
		
		JFrame frame = new JFrame("Tela Inicial");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(toolbar.build(), BorderLayout.PAGE_START);
		frame.setJMenuBar(menubar.build());
		frame.add(jScrollPane);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}


	private Toolbar buildToolbar(JTable table, ToolbarAction toolbarAction) {
		Toolbar toolbar = new Toolbar();
		toolbar.setRemoveAction(toolbarAction.deleteRow(table));
		toolbar.setEditAction(toolbarAction.editRow(table));
		toolbar.setNewAction(toolbarAction.addRow(table));
		return toolbar;
	}


	private JScrollPane buildScrollPane(JTable table) {
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setViewportView(table);
		return jScrollPane;
	}
	
	
	private JTable buildTable() {
		DefaultTableModel model = createModel();
		List<NotaFiscal> notasFiscais = nfc.findAll();
		addRow(model, notasFiscais);
		return createTable(model);
	}


	private JTable createTable(DefaultTableModel model) {
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
		return table;
	}


	private DefaultTableModel createModel() {
		Object[] column = new String[]{"Núm. Nota", "Modelo", "Natureza", "Dt Emissão", "Destinatário", "Emitente"};
		Object[][] data = new Object[][]{};
		DefaultTableModel model = new DefaultTableModel(data, column);
		return model;
	}


	private void addRow(DefaultTableModel model, List<NotaFiscal> notasFiscais) {
		for(NotaFiscal notaFiscal : notasFiscais) {
			model.addRow(new Object[] {
					notaFiscal.getId(),
					notaFiscal.getModelo(),
					notaFiscal.getNatureza(),
					notaFiscal.getDataEmissao(),
					notaFiscal.getDestinatario().getNome() + " - " + notaFiscal.getDestinatario().getDocumento(),
					notaFiscal.getEmitente().getNome() + " - " + notaFiscal.getEmitente().getDocumento()
			});
		}
	}
	

}
