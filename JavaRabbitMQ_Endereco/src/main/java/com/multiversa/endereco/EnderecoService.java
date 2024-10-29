package com.multiversa.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

  @Autowired
  private EnderecoRepository enderecoRepository;

  public Endereco salvarEndereco(Endereco endereco) {
    return enderecoRepository.save(endereco);
  }

  public List<Endereco> listarEnderecos() {
    return enderecoRepository.findAll();
  }

  public Endereco buscarEnderecoPorId(Long id) {
    return enderecoRepository.findById(id).orElse(null);
  }

  public void excluirEndereco(Long id) {
    enderecoRepository.deleteById(id);
  }

  // Método para atualizar um endereço
  public Endereco atualizarEndereco(Long id, Endereco enderecoAtualizado) {
    return enderecoRepository.findById(id).map(endereco -> {
      endereco.setNome(enderecoAtualizado.getNome());
      endereco.setRua(enderecoAtualizado.getRua());
      endereco.setNumero(enderecoAtualizado.getNumero());
      endereco.setBairro(enderecoAtualizado.getBairro());
      endereco.setCidade(enderecoAtualizado.getCidade());
      endereco.setEstado(enderecoAtualizado.getEstado());
      return enderecoRepository.save(endereco);
    }).orElse(null);
  }
}