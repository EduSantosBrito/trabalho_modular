package br.edu.granbery.trabalhomodular.swing.screen;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import br.edu.granbery.trabalhomodular.swing.form.NotaFiscalForm;


public class Registration {
	
	private Integer selectedNotaFiscal;

	public void setSelectedNotaFiscal(Integer selectedNotaFiscal) {
		this.selectedNotaFiscal = selectedNotaFiscal;
	}
	
	public void render() {
		JPanel panelNF = buildJPanel();
		NotaFiscalForm formNF = new NotaFiscalForm();
		panelNF.add(formNF.build());
		
	}

	private JPanel buildJPanel() {
		JPanel panelNF = new JPanel();
		panelNF.setLayout(new BoxLayout(panelNF, BoxLayout.Y_AXIS));
		return panelNF;
	}

}
