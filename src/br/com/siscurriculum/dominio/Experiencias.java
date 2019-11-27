package br.com.siscurriculum.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "experiencias")
public class Experiencias implements Serializable {
	private static final long serialVersionUID = -258794535381188269L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nomeEmpresa;

	private String cargo;

	private String descricaoAtribuicoes;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtEntrada;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtSaida;

	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;

	public Experiencias() {

	}

	public Experiencias(int id, String nomeEmpresa, String cargo, String descricaoAtribuicoes, Date dtEntrada,
			Date dtSaida, Pessoa pessoa) {
		super();
		this.id = id;
		this.nomeEmpresa = nomeEmpresa;
		this.cargo = cargo;
		this.descricaoAtribuicoes = descricaoAtribuicoes;
		this.dtEntrada = dtEntrada;
		this.dtSaida = dtSaida;
		this.pessoa = pessoa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDescricaoAtribuicoes() {
		return descricaoAtribuicoes;
	}

	public void setDescricaoAtribuicoes(String descricaoAtribuicoes) {
		this.descricaoAtribuicoes = descricaoAtribuicoes;
	}

	public Date getDtEntrada() {
		return dtEntrada;
	}

	public void setDtEntrada(Date dtEntrada) {
		this.dtEntrada = dtEntrada;
	}

	public Date getDtSaida() {
		return dtSaida;
	}

	public void setDtSaida(Date dtSaida) {
		this.dtSaida = dtSaida;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Experiencias other = (Experiencias) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
