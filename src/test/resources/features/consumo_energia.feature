  # language: pt
  Funcionalidade: Gestão de Consumo de Energia
  Como um usuário do sistema Energia Eficiente
  Eu quero registrar meu consumo diário
  Para monitorar minha eficiência energética

  Cenário: Registro de consumo com sucesso (Caminho Feliz)
  Dado que eu tenho os dados de consumo válidos
  Quando eu envio uma requisição POST para "/api/consumo"
  Então o status code deve ser 201
  E o corpo da resposta deve conter o ID do registro

  Cenário: Tentativa de registro com dados inválidos (Cenário Negativo)
  Dado que eu envio um consumo com valor negativo
  Quando eu envio uma requisição POST para "/api/consumo"
  Então o status code deve ser 400
  E a mensagem de erro deve ser "Consumo não pode ser negativo"

  Cenário: Login de usuário com credenciais inválidas (Cenário Negativo)
  Dado que eu informo um usuário inexistente
  Quando eu tento realizar o login na rota "/api/auth/login"
  Então o status code deve ser 401