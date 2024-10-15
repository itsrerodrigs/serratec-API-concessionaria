package org.serratec.veiculos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")

public class Veiculos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private Marca marca;
	private String modelo;
	@Enumerated(EnumType.STRING)
	private Cor cor;
	private int anoFabricacao;
	@Enumerated(EnumType.STRING)
	private TipoCombustivel tipoCombustivel;
	@OneToOne(cascade = CascadeType.ALL)
	private Proprietario proprietario;
	
	@OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Acessorio> acessorios;
	
	// iniciar a relação do veículo com a concessionaria
	@JsonManagedReference
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "concessionaria_veiculo",
		joinColumns = @JoinColumn(name = "veiculo_id"),
		inverseJoinColumns = @JoinColumn(name = "concessionaria_id")
			)
	private List<Concessionaria> concessionarias;
	
	public List<Concessionaria> getConcessionarias() {
		return concessionarias;
	}

	public void setConcessionarias(List<Concessionaria> concessionarias) {
		this.concessionarias = concessionarias;
	}

	public List<Acessorio> getAcessorio(){
		return acessorios;
	}
	
	public void setAcessorios(List<Acessorio> acessorios) {
		acessorios.forEach(a -> a.setVeiculo(this)); // essa linha faz acesso a uma chave estrangeira do pgadmin4, especificamente 
		this.acessorios = acessorios;				// na tabela veiculo_id e relaciona a info de descrição e características
	}												// com o id do carro específico. 

	public Proprietario getProprietario() {
		return proprietario;
	}
	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Marca getMarca() {
		return marca;
	}
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public TipoCombustivel getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
}
