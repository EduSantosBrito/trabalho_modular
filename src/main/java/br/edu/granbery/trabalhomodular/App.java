package br.edu.granbery.trabalhomodular;

import java.time.LocalDate;

import br.edu.granbery.trabalhomodular.controller.NotaFiscalController;
import br.edu.granbery.trabalhomodular.model.Item;
import br.edu.granbery.trabalhomodular.model.NotaFiscal;
import br.edu.granbery.trabalhomodular.model.Pessoa;
import br.edu.granbery.trabalhomodular.swing.screen.Homepage;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		NotaFiscalController nfc = new NotaFiscalController();
		Pessoa emitente = new Pessoa();
		emitente.setNome("Emitente");
		emitente.setDocumento("12345");
		emitente.setEstado("MG");
		emitente.setInscricaoEstadual("1234");

		Pessoa destinatario = new Pessoa();
		destinatario.setNome("Destinatario");
		destinatario.setDocumento("1235");
		destinatario.setEstado("MG");
		destinatario.setInscricaoEstadual("1234");

		Pessoa pessoa3 = new Pessoa();
		pessoa3.setNome("Testando");
		pessoa3.setDocumento("123456");
		pessoa3.setEstado("SP");
		pessoa3.setInscricaoEstadual("4567");

		NotaFiscal nf = new NotaFiscal();
		nf.setDataEmissao(LocalDate.now());
		nf.setDataOperacao(LocalDate.now());
		nf.setDestinatario(destinatario);
		nf.setEmitente(emitente);

		Item item = new Item();
		item.setDescricao("Teste");
		item.setPreco(12.4d);
		item.setQtd(8);

		Item item2 = new Item();
		item2.setDescricao("Teste2");
		item2.setPreco(13.4d);
		item2.setQtd(9);

		nf.addItem(item);
		nf.addItem(item2);
		nfc.create(nf);
		Homepage homepage = new Homepage();
		homepage.render();
	}
}
