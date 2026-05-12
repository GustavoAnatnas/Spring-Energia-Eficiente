# language: pt
Funcionalidade: Monitoramento de Eficiência Energética (ESG)

  Contexto:
    Dado que eu registro um novo usuario com email "admin@teste.com" e senha "123456"
    E que eu estou autenticado no sistema

  Cenário: Listagem de consumos registrados (Caminho Feliz)
    Quando eu envio uma requisicao GET para "http://localhost:8080/consumo-energia"
    Então o status code deve ser 200
    E o corpo da resposta deve conter uma lista de consumos

  Cenário: Tentativa de acesso sem autenticação (Segurança)
    Quando eu envio uma requisicao GET para "http://localhost:8080/consumo-energia" sem token
    Então o status code deve ser 401

  Cenário: Cadastro de novo consumo via POST (Integridade)
    Dado que eu tenho os dados de consumo validos
    Quando eu envio uma requisicao POST para "http://localhost:8080/consumo-energia"
    Então o status code deve ser 200