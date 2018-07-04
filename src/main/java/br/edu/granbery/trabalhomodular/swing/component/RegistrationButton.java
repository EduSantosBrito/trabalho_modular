package br.edu.granbery.trabalhomodular.swing.component;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RegistrationButton {

	private ActionListener saveAction;

	private ActionListener cancelAction;

	public void setSaveAction(ActionListener saveAction) {
		this.saveAction = saveAction;
	}

	public void setCancelAction(ActionListener cancelAction) {
		this.cancelAction = cancelAction;
	}
	
	public JPanel build() {
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton saveButton = new JButton("Salvar");
		JButton cancelButton = new JButton("Cancelar");
		panelButton.add(saveButton);
		panelButton.add(cancelButton);
		
		if(saveAction != null) {
			saveButton.addActionListener(saveAction);
		}
		if(cancelAction != null) {
			cancelButton.addActionListener(cancelAction);
		}
		
		return panelButton;
	}

}
