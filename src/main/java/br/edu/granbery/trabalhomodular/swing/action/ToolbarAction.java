package br.edu.granbery.trabalhomodular.swing.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.edu.granbery.trabalhomodular.controller.NotaFiscalController;
import br.edu.granbery.trabalhomodular.model.NotaFiscal;
import br.edu.granbery.trabalhomodular.swing.screen.Registration;

public class ToolbarAction {

	private NotaFiscalController nfc = new NotaFiscalController();
	
	public ActionListener deleteRow(final JTable table) {
		ActionListener delete = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer row = table.getSelectedRow();
				if(row != -1){
					Integer id = (Integer) table.getModel().getValueAt(row, 0); 
					NotaFiscal nf = nfc.find(id);
					nfc.delete(nf);
					((DefaultTableModel) table.getModel()).removeRow(row);
				}
			}
		};
		return delete;
	}
	
	public ActionListener editRow(final JTable table) {
		ActionListener edit = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer row = table.getSelectedRow();
				if(row != -1) {
					Integer id = (Integer) table.getModel().getValueAt(row, 0);
					Registration registrationPage = new Registration();
					registrationPage.setSelectedNotaFiscal(id);
					registrationPage.render();
				}
			}
		};
		return edit;
	}
	
	public ActionListener addRow(JTable table) {
		ActionListener edit = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registration registrationPage = new Registration();
				registrationPage.render();
			}
		};
		return edit;
	}
	
	

}
