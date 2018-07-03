package br.edu.granbery.trabalhomodular.model;

import java.util.List;

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

	@OneToMany(mappedBy = "emitente")
	private List<NotaFiscal> notasEmitidas;
	
	@OneToMany(mappedBy = "destinatario")
	private List<NotaFiscal> notasRecebidas;
	

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

	public void setNotasEmitidas(List<NotaFiscal> notasEmitidas) {
		this.notasEmitidas = notasEmitidas;
	}

	public List<NotaFiscal> getNotasRecebidas() {
		return notasRecebidas;
	}

	public void setNotasRecebidas(List<NotaFiscal> notasRecebidas) {
		this.notasRecebidas = notasRecebidas;
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
