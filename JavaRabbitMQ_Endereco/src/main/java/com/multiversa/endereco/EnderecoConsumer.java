package com.multiversa.endereco;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EnderecoConsumer {

  @Autowired
  private EnderecoService enderecoService;

  private final ObjectMapper objectMapper = new ObjectMapper();
  @RabbitListener(queues = JavaRabbitMQConfig.QUEUE_NAME)
  public void receiveMessage(String message) {
    try {
      JsonNode jsonNode = objectMapper.readTree(message);
      Endereco endereco = new Endereco();
      endereco.setNome(jsonNode.get("nome").asText());
      endereco.setRua(jsonNode.get("rua").asText());
      endereco.setNumero(jsonNode.get("numero").asText());
      endereco.setBairro(jsonNode.get("bairro").asText());
      endereco.setCidade(jsonNode.get("cidade").asText());
      endereco.setEstado(jsonNode.get("estado").asText());

      // Salvar no banco de dados
      enderecoService.salvarEndereco(endereco);
      System.out.println(" [x] Endereço salvo: " + endereco);
    } catch (IOException e) {
      System.err.println("Erro ao processar mensagem: " + e.getMessage());
    }
  }
}
