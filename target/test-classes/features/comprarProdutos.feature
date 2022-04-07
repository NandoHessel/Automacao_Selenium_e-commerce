# language: pt

Funcionalidade: Visualizar produtos
  Como um usuario não logado
  Eu quero visualizar produtos disponíveis
  Para poder escolher qual vou comprar

  @validacaoinicial
  Cenario: Deve mostrar uma lista de oito produtos na pagina inicial
    Dado que estou na pagina inicial
    Quando nao estou logado
    Entao visualizo 8 produtos disponiveis
    E carrinho zerado

  @fluxopadrao
  Cenario: Deve mostrar produto escolhido confirmado
    Dado que estou na pagina inicial
    Quando estou logado
    E seleciono um produto na posição 0
    E nome do produto na tela principal eh "Hummingbird printed t-shirt"
    E o preco do produto eh "$19.12"
    E adiciono o produto no carrinho com tamanho "M", com cor "Black" e quantidade 2
    Entao o produto aparece na confirmacao com nome "Hummingbird printed t-shirt", com preco "$19.12", com tamanho "M", com cor "Black" e com quantidade 2

  @fluxopadraoComEsquema
  Esquema do Cenario: Deve mostrar produto escolhido confirmado
    Dado que estou na pagina inicial
    Quando estou logado
    E seleciono um produto na posição <posicao>
    E nome do produto na tela principal eh <nomeProduto>
    E o preco do produto eh <precoProduto>
    E adiciono o produto no carrinho com tamanho <tamanhoProduto>, com cor <corProduto> e quantidade <quantidadeProduto>
    Entao o produto aparece na confirmacao com nome <nomeProduto>, com preco <precoProduto>, com tamanho <tamanhoProduto>, com cor <corProduto> e com quantidade <quantidadeProduto>
    Exemplos:
      | posicao | nomeProduto                   | precoProduto | tamanhoProduto | corProduto | quantidadeProduto |
      | 0       | "Hummingbird printed t-shirt" | "$19.12"     | "M"            | "Black"    | 2                 |
      | 1       | "Hummingbird printed Swater"  | "$28.72      | "XL"           | "N/A"      | 3                 |