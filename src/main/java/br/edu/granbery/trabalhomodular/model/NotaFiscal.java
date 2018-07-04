package br.edu.granbery.trabalhomodular.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_NOTAFISCAL")
public class NotaFiscal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NUM_NOTA", nullable = false)
	private Integer id;

	@Column(name = "MODELO", length = 255)
	private String modelo;

	@Column(name = "NATUREZA", length = 255)
	private String natureza;

	@Column(name = "DATA_OPERACAO", nullable = false)
	private LocalDate dataOperacao;

	@Column(name = "DATA_EMISSAO", nullable = false)
	private LocalDate dataEmissao;

	@Column(name = "INFORMACOES", length = 2048)
	private String informacoes;

	@OneToMany(mappedBy = "notaFiscal", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Item> itens;

	@ManyToOne(cascade=CascadeType.ALL)
	private Pessoa emitente;

	@ManyToOne(cascade=CascadeType.ALL)
	private Pessoa destinatario;

	public NotaFiscal() {
		modelo = "Modelo 1-A";
		natureza = "Venda";
		itens = new LinkedList<Item>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

	public LocalDate getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(LocalDate dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Integer getQtdItens() {
		Integer qtd = 0;
		for(Item item : itens) {
			qtd += item.getQtd();
		}
		return qtd;
	}

	public Double getValor() {
		Double valor = 0d;
		for(Item item : itens) {
			valor += item.getTotal();
		}
		return valor;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens.clear();
		for (Item item : itens) {
			addItem(item);
		}
	}

	public Pessoa getEmitente() {
		return emitente;
	}

	public void setEmitente(Pessoa emitente) {
		this.emitente = emitente;
	}

	public Pessoa getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Pessoa destinatario) {
		this.destinatario = destinatario;
	}

	public void addItem(Item item) {
		item.setNotaFiscal(this);
		this.itens.add(item);
	}
	
	public void removeItem(Item item) {
		for(Item i : itens) {
			if(item.equals(i)) {
				i.setNotaFiscal(null);
				itens.remove(i);
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotaFiscal other = (NotaFiscal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NotaFiscal [id=" + id + ", modelo=" + modelo + ", natureza=" + natureza + ", dataOperacao="
				+ dataOperacao + ", dataEmissao=" + dataEmissao + ", informacoes=" + informacoes + ", itens=" + itens
				+ ", emitente=" + emitente + ", destinatario=" + destinatario + "]";
	}
	
	

}
