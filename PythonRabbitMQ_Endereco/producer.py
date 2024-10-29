# producer.py
import pika
import json

# Conectar ao RabbitMQ
connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()

# Declarar a fila
channel.queue_declare(queue='endereco')

# Solicitar dados do usuário
nome = input("Digite o nome: ")
rua = input("Digite a rua: ")
numero = input("Digite o número: ")
bairro = input("Digite o bairro: ")
cidade = input("Digite a cidade: ")
estado = input("Digite o estado: ")

# Criar uma mensagem JSON com os dados do endereço
message = json.dumps({
    "nome": nome,
    "rua": rua,
    "numero": numero,
    "bairro": bairro,
    "cidade": cidade,
    "estado": estado
})

# Enviar a mensagem para a fila 'endereco'
channel.basic_publish(exchange='',
                      routing_key='endereco',
                      body=message)
print(f" [x] Sent '{message}'")

# Fechar a conexão
connection.close()
