package br.edu.granbery.trabalhomodular.swing.form;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.granbery.trabalhomodular.model.Pessoa;

public class PessoaForm {

	private JTextField documento = new JTextField(15);
	private JTextField nome = new JTextField(15);
	private JTextField inscricao = new JTextField(15);
	private JTextField estado = new JTextField(2);
	
	public JTextField getDocumento() {
		return documento;
	}
	public void setDocumento(JTextField documento) {
		this.documento = documento;
	}
	public JTextField getNome() {
		return nome;
	}
	public void setNome(JTextField nome) {
		this.nome = nome;
	}
	public JTextField getInscricao() {
		return inscricao;
	}
	public void setInscricao(JTextField inscricao) {
		this.inscricao = inscricao;
	}
	public JTextField getEstado() {
		return estado;
	}
	public void setEstado(JTextField estado) {
		this.estado = estado;
	}
	
	public JPanel build(String tipo) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(buildPanel(tipo));
		return panel;
	}
	private JPanel buildPanel(String tipo) {
		JPanel destinatarioPanel = new JPanel();
		destinatarioPanel.setBorder(BorderFactory.createTitledBorder(tipo));
		destinatarioPanel.setLayout(new BoxLayout(destinatarioPanel, BoxLayout.Y_AXIS));
		destinatarioPanel.add(new JLabel("CNPJ/CPF"));
		destinatarioPanel.add(documento);
		destinatarioPanel.add(new JLabel("Razão Social/Nome"));
		destinatarioPanel.add(nome);
		destinatarioPanel.add(new JLabel("Inscrição Estadual"));
		destinatarioPanel.add(inscricao);
		destinatarioPanel.add(new JLabel("Estado"));
		destinatarioPanel.add(estado);
		return destinatarioPanel;
	}
	public Pessoa toPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setDocumento(getDocumento().getText());
		pessoa.setNome(getNome().getText());
		pessoa.setInscricaoEstadual(getInscricao().getText());
		pessoa.setEstado(getEstado().getText());
		return pessoa;
	}
}
