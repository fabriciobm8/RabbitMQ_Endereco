package com.multiversa.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

  @Autowired
  private EnderecoService enderecoService;

  @PostMapping
  public Endereco criarEndereco(@RequestBody Endereco endereco) {
    return enderecoService.salvarEndereco(endereco);
  }

  @GetMapping
  public List<Endereco> listarEnderecos() {
    return enderecoService.listarEnderecos();
  }

  @GetMapping("/{id}")
  public Endereco buscarEndereco(@PathVariable Long id) {
    return enderecoService.buscarEnderecoPorId(id);
  }

  @DeleteMapping("/{id}")
  public void excluirEndereco(@PathVariable Long id) {
    enderecoService.excluirEndereco(id);
  }

  @PutMapping("/{id}")
  public Endereco atualizarEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
    return enderecoService.atualizarEndereco(id, endereco);
  }
}