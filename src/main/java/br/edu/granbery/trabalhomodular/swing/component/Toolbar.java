package br.edu.granbery.trabalhomodular.swing.component;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar {
	
	private ActionListener removeAction;

	private ActionListener newAction;

	private ActionListener editAction;

	public void setRemoveAction(ActionListener removeAction) {
		this.removeAction = removeAction;
	}

	public void setNewAction(ActionListener newAction) {
		this.newAction = newAction;
	}

	public void setEditAction(ActionListener editAction) {
		this.editAction = editAction;
	}
	
	public JToolBar build(){
		JButton btnNew = new JButton();
		btnNew.setIcon(new ImageIcon(btnNew.getClass().getResource("/images/novo.png")));		
		JButton btnEdit = new JButton();
		btnEdit.setIcon(new ImageIcon(btnEdit.getClass().getResource("/images/editar.png")));
		JButton btnDelete = new JButton();
		btnDelete.setIcon(new ImageIcon(btnDelete.getClass().getResource("/images/excluir.png")));
		
		if (removeAction != null){
			btnDelete.addActionListener(removeAction);
		}
		
		if (newAction != null){
			btnNew.addActionListener(newAction);
		}
		
		if(editAction != null){
			btnEdit.addActionListener(editAction);
		}
		
		JToolBar toolbar = new JToolBar("Barra de Ferramentas");
		toolbar.add(btnNew);
		toolbar.add(btnEdit);
		toolbar.add(btnDelete);
		
		return toolbar;
		
	}

}
