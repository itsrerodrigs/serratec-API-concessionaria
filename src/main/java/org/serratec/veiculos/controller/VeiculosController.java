package org.serratec.veiculos.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.veiculos.model.Veiculos;
import org.serratec.veiculos.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculo")
	public class VeiculosController {
	@Autowired
	private VeiculoService servico;
	
	@GetMapping("/veiculo")
	public List<Veiculos> obterTodos(){
		System.out.println("Teste obter todos os dados efetuado com sucesso!");
		return servico.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Veiculos> obterPorId(@PathVariable Long id){
		Optional<Veiculos> veiculo = servico.buscarPorId(id);
		if (!veiculo.isPresent()) {
			System.out.println("Falha ao obter por Id");
			return ResponseEntity.notFound().build();
		}		
		System.out.println("Inclusão por Id concluída");
		return ResponseEntity.ok(veiculo.get());
	}
	
	@PostMapping("/veiculo")
	@ResponseStatus (HttpStatus.CREATED)
	public Veiculos cadastrarVeiculo(@RequestBody @Valid Veiculos veiculo) {
		return servico.salvarVeiculo(veiculo);
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id) {
		if(!servico.apagarVeiculo(id)) {
			return ResponseEntity.notFound().build();
		}	
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Veiculos> alterarVeiculo(@PathVariable Long id, 
			@RequestBody @Valid Veiculos veiculo) {
		Optional<Veiculos> veiculoAlterado = servico.modificarVeiculo(id, veiculo);
		
		if(!veiculoAlterado.isPresent()) {
			return ResponseEntity.notFound().build();
		}	
		return ResponseEntity.ok(veiculoAlterado.get());
	}
	
}