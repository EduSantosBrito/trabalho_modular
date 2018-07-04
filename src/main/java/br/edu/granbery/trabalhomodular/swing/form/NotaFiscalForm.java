package br.edu.granbery.trabalhomodular.swing.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.granbery.trabalhomodular.model.NotaFiscal;

public class NotaFiscalForm {

	private JTextField id = new JTextField(10);
	private JTextField modelo = new JTextField(10);
	private JTextField natureza = new JTextField(10);
	private JTextField dataOperacao = new JTextField(10);
	private JTextField dataEmissao = new JTextField(10);
	private JTextField informacoes = new JTextField(20);

	public JTextField getId() {
		return id;
	}

	public void setId(JTextField id) {
		this.id = id;
	}

	public JTextField getModelo() {
		return modelo;
	}

	public void setModelo(JTextField modelo) {
		this.modelo = modelo;
	}

	public JTextField getNatureza() {
		return natureza;
	}

	public void setNatureza(JTextField natureza) {
		this.natureza = natureza;
	}

	public JTextField getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(JTextField dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public JTextField getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(JTextField dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public JTextField getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(JTextField informacoes) {
		this.informacoes = informacoes;
	}
	
	
	public JPanel build() {
		JPanel fields = new JPanel();
		fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));
		fields.add(new JLabel("N Nota"));
		fields.add(id);
		id.setEditable(false);
		fields.add(new JLabel("Modelo"));
		modelo.setText("Modelo 1-A");
		modelo.setEditable(false);
		fields.add(modelo);
		fields.add(new JLabel("Natureza"));
		natureza.setText("Venda");
		natureza.setEditable(false);
		fields.add(natureza);
		fields.add(new JLabel("Data da Operação"));
		fields.add(dataOperacao);
		fields.add(new JLabel("Data da Emissão"));
		fields.add(dataEmissao);
		dataEmissao.setEditable(false);
		fields.add(new JLabel("Informações Complementares"));
		fields.add(informacoes);
		
		return fields;
	}
	
	public NotaFiscal toNotaFiscal() {
		NotaFiscal nf = new NotaFiscal();
		nf.setId(getId().getText().isEmpty() ? null : Integer.parseInt(getId().getText()));
		nf.setNatureza("Venda");
		nf.setModelo("Modelo 1-A");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		dtf.withLocale(new Locale("pt", "BR"));
		nf.setDataOperacao(LocalDate.parse(getDataOperacao().getText(), dtf));
		nf.setDataEmissao(LocalDate.now());
		nf.setInformacoes(getInformacoes().getText());
		return nf;
	}

}
