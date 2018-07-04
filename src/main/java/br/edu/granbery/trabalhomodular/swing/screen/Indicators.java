package br.edu.granbery.trabalhomodular.swing.screen;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import br.edu.granbery.trabalhomodular.controller.NotaFiscalController;
import br.edu.granbery.trabalhomodular.swing.component.Menubar;

public class Indicators {

	private NotaFiscalController nfc = new NotaFiscalController();
	public void render() {
		Object[] colunas = new String[] { "Indicador", "Valor" };
		Object[][] dados = new Object[][] { { "Total de Notas Fiscais cadastradas" }, { "Média de valor das notas" },
				{ "Média de valor dos itens das notas" }, { "Maior valor de nota" },
				{ "Estado com maior número de notas emitidas" },
				{ "Estado com maior número de notas como destinatário" },
				{ "CNPJ -Nome’ da empresa que é a maior compradora em volume de vendas" },
				{ "CNPJ -Nome’ da empresa que é a maior vendedora em volume de vendas" },
				{ "Total de notas com valor superior a 10 mil" }, { "Total de notas com mais de 10 itens" }
		};

		DefaultTableModel model = new DefaultTableModel(dados, colunas);

		JTable tabela = new JTable();
		tabela.setModel(model);
		tabela.setValueAt(nfc.countAll(), 0, 1);
		tabela.setValueAt(nfc.getTotalAverage(), 1, 1);
		tabela.setValueAt(nfc.getAverageItem(), 2, 1);
		tabela.setValueAt(nfc.getMaxNota(), 3, 1);
		tabela.setValueAt(nfc.getMaxEstadoEmitente(), 4, 1);
		tabela.setValueAt(nfc.getMaxEstadoDestinatario(), 5, 1);
		tabela.setValueAt(nfc.getMaxDocumentoDestinatario().toString(), 6, 1);
		tabela.setValueAt(nfc.getMaxDocumentoEmitente().toString(), 7, 1);
		tabela.setValueAt(nfc.getValueBiggerThanTenThousand(), 8, 1);
		tabela.setValueAt(nfc.getQtdBiggerThanTen(), 9, 1);
		JScrollPane painelTabela = new JScrollPane();
		painelTabela.setViewportView(tabela);

		JPanel painel2 = new JPanel();
		painel2.setLayout(new BoxLayout(painel2, BoxLayout.Y_AXIS));
		painel2.add(painelTabela);

		Menubar menubar = new Menubar();
		/* Janela */
		JFrame app = new JFrame("Indicadores");
		app.add(painel2);
		app.setSize(1000, 400);
		app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		app.setJMenuBar(menubar.build());
		app.setVisible(true);

		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel("Label");
		label.setPreferredSize(new Dimension(1000, 1000));

		/* Rolagem */
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setViewportView(label);

	}
}
