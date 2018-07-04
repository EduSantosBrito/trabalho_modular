package br.edu.granbery.trabalhomodular.swing.screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import br.edu.granbery.trabalhomodular.controller.ItemController;
import br.edu.granbery.trabalhomodular.controller.NotaFiscalController;
import br.edu.granbery.trabalhomodular.controller.PessoaController;
import br.edu.granbery.trabalhomodular.model.Item;
import br.edu.granbery.trabalhomodular.model.NotaFiscal;
import br.edu.granbery.trabalhomodular.model.Pessoa;
import br.edu.granbery.trabalhomodular.swing.component.Menubar;
import br.edu.granbery.trabalhomodular.swing.component.RegistrationButton;
import br.edu.granbery.trabalhomodular.swing.form.ItemForm;
import br.edu.granbery.trabalhomodular.swing.form.NotaFiscalForm;
import br.edu.granbery.trabalhomodular.swing.form.PessoaForm;
import br.edu.granbery.trabalhomodular.swing.form.TableForm;

public class Registration {

	private Integer selectedNotaFiscal;
	private NotaFiscalController nfc = new NotaFiscalController();
	private ItemController ic = new ItemController();
	private PessoaController pc = new PessoaController();

	public void setSelectedNotaFiscal(Integer selectedNotaFiscal) {
		this.selectedNotaFiscal = selectedNotaFiscal;
	}

	public void render() {
		JPanel panelNF = buildJPanel();
		NotaFiscalForm notaFiscalForm = new NotaFiscalForm();
		panelNF.add(notaFiscalForm.build());
		PessoaForm emitenteForm = new PessoaForm();
		panelNF.add(emitenteForm.build("EMITENTE"));
		PessoaForm destinatarioForm = new PessoaForm();
		panelNF.add(destinatarioForm.build("DESTINATARIO"));
		ItemForm itemForm = new ItemForm();
		TableForm tableForm = new TableForm();
		ActionListener addItemAction = buildAddItemAction(itemForm, tableForm);
		itemForm.setNewItem(addItemAction);
		JScrollPane jScrollPane = tableForm.buildTabela();
		ActionListener deleteItemAction = buildDeleteItemAction(jScrollPane, tableForm);
		itemForm.setDeleteItem(deleteItemAction);
		panelNF.add(itemForm.build());
		if (selectedNotaFiscal != null) {
			NotaFiscal notaFiscal = nfc.find(selectedNotaFiscal);
			carregarDados(notaFiscalForm, emitenteForm, destinatarioForm, notaFiscal, tableForm);
		}
		panelNF.add(jScrollPane);
		ActionListener cancelAction = buildCancelAction();
		ActionListener saveAction = buildSaveAction(notaFiscalForm, emitenteForm, destinatarioForm, tableForm);
		RegistrationButton registrationButton = buildButton(cancelAction, saveAction);
		JScrollPane jScrollPaneForm = buildJScrollPane(panelNF);
		Menubar menubar = new Menubar();
		JPanel background = buildJPanelBackground(registrationButton, jScrollPaneForm);
		JFrame janela = new JFrame();
		janela.setTitle("Cadastro");
		janela.setSize(800, 600);
		janela.add(background);
		janela.setJMenuBar(menubar.build());
		janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janela.setVisible(true);
	}

	private JPanel buildJPanelBackground(RegistrationButton registrationButton, JScrollPane jScrollPaneForm) {
		JPanel background = new JPanel();
		background.add(jScrollPaneForm);
		background.add(registrationButton.build());
		background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
		return background;
	}

	private JScrollPane buildJScrollPane(JPanel panelNF) {
		JScrollPane jScrollPaneForm = new JScrollPane();
		jScrollPaneForm.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPaneForm.setViewportView(panelNF);
		return jScrollPaneForm;
	}

	private RegistrationButton buildButton(ActionListener cancelAction, ActionListener saveAction) {
		RegistrationButton registrationButton = new RegistrationButton();
		registrationButton.setCancelAction(cancelAction);
		registrationButton.setSaveAction(saveAction);
		return registrationButton;
	}

	private ActionListener buildSaveAction(final NotaFiscalForm notaFiscalForm, final PessoaForm emitenteForm,
			final PessoaForm destinatarioForm, final TableForm tableForm) {
		ActionListener save = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NotaFiscal nf = notaFiscalForm.toNotaFiscal();
				Pessoa emitente = emitenteForm.toPessoa();
				nf.setEmitente(emitente);
				Pessoa destinatario = destinatarioForm.toPessoa();
				nf.setDestinatario(destinatario);

				/* Navegando nas linhas */
				for (int i = 0; i < tableForm.getModel().getRowCount(); i++) {
					Item itemLinha = new Item();
					/* Navegando nas colunas */
					for (int j = 0; j < 4; j++) {
						Object valorColuna = tableForm.getModel().getValueAt(i, j);

						if (j == 0 && valorColuna != null) {
							itemLinha.setId((Integer) valorColuna);
						} else if (j == 1) {
							itemLinha.setDescricao((String) valorColuna);
						} else if (j == 2) {
							itemLinha.setPreco((Double) valorColuna);
						} else if (j == 3) {
							itemLinha.setQtd((Integer) valorColuna);
						}
					}
					nf.addItem(itemLinha);
				}
				tratarDados(nf);
				if (nf.getId() == null) {
					nfc.create(nf);
				} else {
					selectedNotaFiscal = null;
					nfc.update(nf);
				}
				Homepage homepage = new Homepage();
				homepage.render();
			}
		};
		return save;
	}

	private void tratarDados(NotaFiscal nf) {
			Pessoa emitente = pc.findByDocumento(nf.getEmitente().getDocumento());
			if(emitente != null) {
				nf.setEmitente(emitente);
			}
			Pessoa destinatario = pc.findByDocumento(nf.getDestinatario().getDocumento());
			if(destinatario != null) {
				nf.setDestinatario(destinatario);
			}
	}

	private ActionListener buildCancelAction() {
		ActionListener cancel = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja cancelar?");
				if (dialogResult == JOptionPane.YES_OPTION) {
					Homepage homepage = new Homepage();
					homepage.render();
				}
			}
		};
		return cancel;
	}

	private ActionListener buildDeleteItemAction(final JScrollPane jScrollPaneForm, final TableForm tableForm) {
		ActionListener deleteItem = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja excluir esse item?");
					if(dialogResult == JOptionPane.YES_OPTION) {
						JTable table = (JTable) jScrollPaneForm.getViewport().getView();
						int row = table.getSelectedRow();
						Integer id = (Integer) tableForm.getModel().getValueAt(row, 0);
						Item item = ic.find(id);
						item.setNotaFiscal(null);
						ic.delete(item);
						tableForm.getModel().removeRow(row);
					}
			}
			
		};
		return deleteItem;
	}

	private ActionListener buildAddItemAction(final ItemForm itemForm, final TableForm tableForm) {
		ActionListener newItem = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Integer codigo = null;
					String descricao = itemForm.getDescricao().getText();
					Double preco = Double.parseDouble(itemForm.getPreco().getText());
					Integer qtd = Integer.parseInt(itemForm.getQtd().getText());
					Double total = preco * qtd;
					tableForm.getModel().addRow(new Object[] { codigo, descricao, preco, qtd, total });
				} catch (Exception e) {
					System.out.println(e);
				}
				itemForm.getDescricao().setText("");
				itemForm.getPreco().setText("");
				itemForm.getQtd().setText("");
			}
		};
		return newItem;
	}

	private void carregarDados(NotaFiscalForm notaFiscalForm, PessoaForm emitenteForm, PessoaForm destinatarioForm,
			NotaFiscal notaFiscal, TableForm tableForm) {
		notaFiscalForm.getId().setText(String.valueOf(notaFiscal.getId()));
		notaFiscalForm.getDataOperacao().setText(notaFiscal.getDataOperacao().toString());
		notaFiscalForm.getDataEmissao().setText(notaFiscal.getDataEmissao().toString());
		notaFiscalForm.getInformacoes().setText(notaFiscal.getInformacoes());
		notaFiscalForm.getModelo().setText(notaFiscal.getModelo());
		notaFiscalForm.getNatureza().setText(notaFiscal.getNatureza());

		emitenteForm.getDocumento().setText(notaFiscal.getEmitente().getDocumento());
		emitenteForm.getEstado().setText(notaFiscal.getEmitente().getEstado());
		emitenteForm.getInscricao().setText(notaFiscal.getEmitente().getInscricaoEstadual());
		emitenteForm.getNome().setText(notaFiscal.getEmitente().getNome());

		destinatarioForm.getDocumento().setText(notaFiscal.getDestinatario().getDocumento());
		destinatarioForm.getEstado().setText(notaFiscal.getDestinatario().getEstado());
		destinatarioForm.getInscricao().setText(notaFiscal.getDestinatario().getInscricaoEstadual());
		destinatarioForm.getNome().setText(notaFiscal.getDestinatario().getNome());

		notaFiscal.getItens().forEach(item -> {
			tableForm.addRow(item);
		});

	}

	private JPanel buildJPanel() {
		JPanel panelNF = new JPanel();
		panelNF.setLayout(new BoxLayout(panelNF, BoxLayout.Y_AXIS));
		return panelNF;
	}

}
