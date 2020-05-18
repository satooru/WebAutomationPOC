# encoding: utf-8
# language: pt
@correios

Funcionalidade: Pesquisar Endereço

  Contexto:
    Dado que abra a página dos correios

  @cep
  Esquema do Cenário: Pesquisar um CEP e dar output das informações retornadas
    Dado que digite "<cep>" no textbox de pesquisa de endereço
    Então deve mostrar o output "<encontrado>" das informações retornadas

    Exemplos:
      | cep       | encontrado |
      | 01302-000 | true       |
      | 04104-902 | true       |
      | 99999-999 | false      |

  @logradouro
  Esquema do Cenário: Pesquisar um logradouro que retorne mais de um resultado e dar output de todos os dados da tabela retornada
    Dado que digite "<logradouro>" no textbox de pesquisa de endereço
    Então deve mostrar o output "<encontrado>" das informações retornadas

    Exemplos:
      | logradouro        | encontrado |
      | Rua da Conceição  | true       |
      | Avenida Paulista  | true       |
      | Avenidaa Palitas  | false      |