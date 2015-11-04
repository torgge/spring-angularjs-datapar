# app-Biblioteca
Aplicação exemplo do treinamento de Spring/AngularJS na Datapar SA em ciudad del Este. Nov/2015.


## Spring HAL-Browser

Um navegador de API para o tipo de mídia hal + json (Hypertext Application Language)

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

> Source: https://github.com/mikekelly/hal-browser

## angular-HY-res 

Cliente hipermídia / biblioteca para angularjs. HAL, Siren, e cabeçalho link extensões são incluídos por padrão, mas o suporte para outros tipos de mídia podem ser adicionados. angular-HY-res é uma camada em torno do núcleo da biblioteca HY-res.

https://github.com/petejohanson/angular-hy-res

## Adicione a dependência HAL-Browser

```java
       
<dependency> 
	<groupId>org.springframework.data</groupId> 
	<artifactId>spring-data-rest-hal-browser</artifactId> 
</dependency> 

```
## localhost:8080
Acessando API do **MasChief6b**

<p align="center">
<img src="https://github.com/lyndontavares/spring-angularjs-datapar/blob/master/app-MasterChico/MasterChicoSB6b/src/main/resources/static/image/raw.png" width="660">
</p>

<p align="center">
<img src="https://github.com/lyndontavares/spring-angularjs-datapar/blob/master/app-MasterChico/MasterChicoSB6b/src/main/resources/static/image/raw2.png" width="660">
</p>

<p align="center">
<img src="https://github.com/lyndontavares/spring-angularjs-datapar/blob/master/app-MasterChico/MasterChicoSB6b/src/main/resources/static/image/raw3.png" width="660">
</p>

<p align="center">
<img src="https://github.com/lyndontavares/spring-angularjs-datapar/blob/master/app-MasterChico/MasterChicoSB6b/src/main/resources/static/image/raw4.png" width="660">
</p>
