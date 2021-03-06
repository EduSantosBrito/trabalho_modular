package br.edu.granbery.trabalhomodular.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PESSOA")
public class Pessoa {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "DOCUMENTO", length = 14, nullable = false, unique=true)
	private String documento;

	@Column(name = "NOME", length = 200, nullable = false)
	private String nome;

	@Column(name = "INSCRICAO_ESTADUAL", length = 20, nullable = false)
	private String inscricaoEstadual;

	@Column(name = "ESTADO", length = 2, nullable = false)
	private String estado;

	@OneToMany(mappedBy = "emitente", cascade=CascadeType.ALL)
	private List<NotaFiscal> notasEmitidas = new LinkedList<NotaFiscal>();
	
	@OneToMany(mappedBy = "destinatario", cascade=CascadeType.ALL)
	private List<NotaFiscal> notasRecebidas = new LinkedList<NotaFiscal>();;
	

	public Integer getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<NotaFiscal> getNotasEmitidas() {
		return notasEmitidas;
	}

	public void addNotaEmitida(NotaFiscal notaFiscal) {
		this.notasEmitidas.add(notaFiscal);
	}

	public List<NotaFiscal> getNotasRecebidas() {
		return notasRecebidas;
	}

	public void addNotaRecebida(NotaFiscal notaFiscal) {
		this.notasRecebidas.add(notaFiscal);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", documento=" + documento + ", nome=" + nome + ", inscricaoEstadual="
				+ inscricaoEstadual + ", estado=" + estado + "]";
	}
	
	
}
