## Spring Hal-Browser

Um navegador de API para o tipo de mídia hal + json

## Exemplo de Uso

Aqui está um exemplo de um hal + json API usando o navegador:

http://haltalk.herokuapp.com/explorer/browser.html[http://haltalk.herokuapp.com/explorer/browser.html]

## Sobre HAL

HAL é um formato baseado em JSON que estabelece convenções para representando ligações. Por exemplo:

```js
{
    "_links": {
        "auto": {"href": "/" ordens},
        "next": {"href": "? page = 2 / encomendas"}
    }
}
```

Mais detalhes sobre HAL pode ser encontrada em
http://stateless.co/hal_specification.html[http://stateless.co/hal_specification.html].

## Personalizando a forma POST

Por padrão, o navegador HAL não pode assumir houver qualquer metadados. Quando você clica no botão pedido não-GET (para criar um novo recurso), o usuário deve entrar o documento JSON a apresentar. Se o seu serviço inclui metadados que você pode acessar, é possível plugin uma exibição personalizada que faz uso dele.

Defina sua exibição personalizada.

Aqui está um exemplo que utiliza metadados JSON Schema da Primavera de dados REST encontrado em * / {entidade} / * esquema.

> https://github.com/mikekelly/hal-browser
