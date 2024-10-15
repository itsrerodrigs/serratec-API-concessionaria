package org.serratec.veiculos.service;

import java.util.List;
import java.util.Optional;

import org.serratec.veiculos.model.Veiculos;
import org.serratec.veiculos.repository.VeiculosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {
	@Autowired
	private VeiculosRepository repositorio;
	
	
	public Veiculos salvarVeiculo(Veiculos veiculo) {
		return repositorio.save(veiculo);
	}
	
	public Optional<Veiculos> modificarVeiculo(Long id, Veiculos veiculo) {
		if(!repositorio.existsById(id)) {
			return Optional.empty();
		}
	veiculo.setId(id);
	return Optional.of(repositorio.save(veiculo));
	}
	
	public boolean apagarVeiculo(Long id) {
		if (!repositorio.existsById(id)) {
			return false;
		}
			repositorio.deleteById(id);
			return true;
	}
	
	public List<Veiculos> buscarTodos(){
		System.out.println("Veículos encontrados!");
		return repositorio.findAll();
		
	}
	
	
	public Optional<Veiculos> buscarPorId(Long id) {
		if (!repositorio.existsById(id)) {
		return Optional.empty();
		}
			return Optional.of(repositorio.findById(id).get());
		}
}
