##Criando micro serviços com o Spring Boot

Postado por Dan Woods , traduzido por Wellington Pinheiro em 05 Mai 2015 | Dê sua opinião CompartilharShare |  Share on facebookShare on diggShare on dzoneShare on twitterShare on redditShare on deliciousShare on emailMarcar como favoritoFavoritos
O conceito de arquitetura de micro serviços vem gradualmente encontrando o seu espaço no desenvolvimento de software. Como um sucessor da Arquitetura Baseada em Serviços (SOA - Service Oriented Architecture), os micro serviços podem ser categorizados como sistemas distribuídos e usam muitos conceitos e práticas do SOA. Eles se diferem, entretanto, no escopo da responsabilidade dada para cada serviço individualmente. No SOA, um serviço pode ser responsável por tratar diversas funcionalidades e domínios enquanto que uma regra geral para um micro serviço é que ele seja responsável por gerenciar um único domínio e as funcionalidades que manipulam esse domínio. A abordagem dos sistemas distribuídos consistem em decompor a infraestrutura monolítica de um serviço em subsistemas escaláveis, organizados através de um corte vertical envolvendo cada uma das camadas empilhadas do sistema e interconectadas por uma camada de transporte comum.

Em uma infraestrutura monolítica os serviços que compõem um sistema são organizados logicamente no mesmo código fonte e unidade de instalação. Isso permite a dependência entre os serviços que serão gerenciados dentro do mesmo ambiente de execução e também significa que modelos e recursos comuns podem ser compartilhados entre os componentes do sistema. A conectividade entre subsistemas de uma infraestrutura monolítica significa que a lógica de negócio e os dados do domínio podem ter alta reusabilidade em abstrações e funções utilitárias, frequentemente muito acopladas, mas com uma capacidade potencial de verificar como uma única mudança pode afetar o sistema inteiro. Essa vantagem vem com um preço de escalabilidade dos componentes individuais da infraestrutura e significa que o crescimento de um sistema se restringe aos componentes menos escaláveis.

Um sistema distribuído decompõe os componentes de um sistema monolítico em unidades individuais de distribuição, capazes de evoluir com as suas próprias exigências de escalabilidade e independente dos outros subsistemas. Isso significa que o impacto de um recurso no sistema como um todo pode ser gerenciado de forma mais eficiente e a conexão entre componentes pode compartilhar um contrato menos rígido, pois a dependência não é mais gerenciada através de um ambiente de execução. No SOA tradicional os serviços podem encapsular um conjunto de funcionalidades para uma determinada parte do negócio, ao redor de vários domínios. Uma arquitetura de micro serviços une o conceito de distribuição de sistemas com a promessa do gerenciamento de uma única regra para um único modelo de negócio, facilitando muito a compreensão do que um subsistema faz. Isso também significa que o escopo da documentação e do teste podem ser gerenciados de forma mais fáceis, ajudando a obter uma cobertura maior nos dois casos.

## Conteúdo relacionado de patrocinadores

Guia de REST e design de APIs 5 fundamentos do OAuth para controle de acesso a APIs Proteja suas APIs de ataques e hijacks Cinco pilares da gerência de APIs 5 estratégias para segurança de APIs
Assim como no SOA, uma arquitetura de micro serviços deve estar conectada através de uma camada de transporte comum, e nos últimos anos o HTTP tem se mostrado uma ótima opção para isso. Mas existem outras opções como protocolos binários de transporte ou os serviços de mensageria, e a arquitetura de micro serviços não favorece e nem restringe nenhum mecanismo em especial, exceto pela maturidade, acessibilidade e as bibliotecas que facilitam a comunicação entre servidores. Sendo o HTTP um protocolo de transporte maduro, com bibliotecas cliente para várias linguagens e frameworks, isso o torna uma excelente escolha para fazer a comunicação entre os serviços. Um ponto que a arquitetura de micro serviços opina é sobre o estado entre as interações de serviços. Independente da camada de transporte, a comunicação entre micro serviços deveria ser feito sem manter informação de estado e que sigam o paradigma RESTful. Isso significa que a requisição e a resposta de um micro serviço deve ter toda informação necessária para garantir o estado. Simplificando, o serviço não deve esperar que sejam enviadas informações baseadas em interações anteriores. Implementando corretamente os serviços REST garante que o micro serviço seja escalável e que as atualizações tenham um mínimo de indisponibilidade.

Entender os pontos que quebram um serviço monolítico em micro serviços pode ser difícil, especialmente em código legado, com alto acoplamento e objetos de domínio que ultrapassam as fronteiras de serviços. Como regra, uma partição vertical na infraestrutura pode ser feita nas fronteiras de uma regra de negócio em particular. Muitos micro serviços podem trabalhar cooperativamente dentro do contexto dessa partição vertical para completar uma regra de negócio. Considere, por exemplo, a funcionalidade de um site de comércio eletrônico que define o negócio baseado em fluxo no qual o cliente acessa uma página de entrada, interage com os produtos e finalmente faz a compra do produto. Esse processo pode ser dividido na vertical para visualizar os detalhes dos produtos, outra para fazer a inclusão de um produto no carrinho de compras e mais uma para fechar o pedido para um ou mais produtos. No contexto de negócio de um cliente olhando um produto, muitos micro serviços podem ser envolvidos para coletar os dados usados para mostrar os detalhes de um produto em particular. Na página de entrada do site, por exemplo, os títulos, figuras e preços podem ser mostrados para vários produtos. A página pode coletar esses detalhes através de dois micro serviços de backend: um para fornecer detalhes do produto e outro para o preço do produto. Quando um cliente escolhe um produto em particular, o site pode adicionalmente, chamar dois outros micro serviços responsáveis por obter a classificação do produto e os comentários de clientes. Assim, para acomodar a partição vertical da arquitetura responsável pelos detalhes de visualização de produtos, essa partição deve fazer uso de quatro micro serviços de backend.

Cada micro serviço da vertical de produto é planejado para ajudar a criar diferentes visões do domínio do produto, sendo cada uma escalável e disponível conforme os requisitos do sistema. Pode-se assumir, por exemplo, que os serviços responsáveis por gerar os dados de uma página principal do site de comércio eletrônico precisa atender uma quantidade maior de requisições do que uma página que mostra os detalhes de um produto individualmente. Esses micro serviços também pode ser construídos com base em diversas decisões técnicas como estratégias de cache, que não tem aplicação válida nos serviços que mostram avaliações de produtos e avaliações feitas pelos clientes. Permitir que cada micro serviço possa ter suas características técnicas atendidas conforme a sua função permite que a utilização dos recursos sejam feitas de forma mais eficiente. Em uma arquitetura monolítica os serviços de classificação do produto e avaliação dos clientes fica preso aos requisitos de escalabilidade e disponibilidade dos serviços de detalhes do produto e precificação.

A complexidade de um micro serviço não reflete na quantidade de linhas da sua implementação. Um equívoco comum é que a quantidade de código de um micro serviço também seja micro, mas isso não faz muito sentido quando considerada a meta que uma arquitetura de micro serviços tem como objetivo atender. A meta endereça a decomposição de serviços em um sistema distribuído e a complexidade da sua implementação pode ser feita com o código do tamanho que for necessário. A nomenclatura "micro" expressa o padrão de responsabilidade através dos diferentes subsistemas, não o código fonte. Dado que a responsabilidade de um micro serviço é limitado uma única fatia vertical de um sistema, o seu código é frequentemente conciso, de fácil compreensão e possibilita a sua instalação com pequenas publicações. Um padrão positivo dos micro serviços é que eles são instalados com todos os recursos necessários para que sejam executados. Isso significa eles possuem todo o ambiente de execução embarcado e podem ser acessados de forma autônoma, simplificando de forma drástica o custo associado com a sua distribuição e instalação.

Históricamente, a publicação das aplicações Web construídas em Java é um conto de volume, servidores de aplicação pré-configurados que recebem um arquivo WAR específico que é extraído no ambiente prescrito e frequentemente com informação de estado. Esses servidores de aplicações podem levar dezenas de minutos para extrair o conteúdo do arquivo Web até começar a servir o novo conteúdo da aplicação, tornando difícil de iterar nas mudanças e pouco atraente para ter múltiplas aplicações de um sistema. Com o passar do tempo os frameworks evoluíram para facilitar o desenvolvimento de micro serviços, assim como o processo de empacotamento desses artefatos para publicação. Atualmente, as aplicações Web de micro serviços Java são capazes de facilmente embarcar todo o seu ambiente de execução em um arquivo executável. Ambientes de execução embarcados, como Tomcat e Jetty, são versões leves de seus predecessores servidores de aplicação e geralmente capazes de iniciar em segundos. Qualquer sistema com Java instalado é então capaz de fazer a publicação, simplificando o processo de distribuição de novas funcionalidades.

## Spring Boot

Um framework que tem evoluído consideravelmente para o desenvolvimento de micro serviços Java é o Spring Boot. O Spring Boot é construído em cima do Spring Framework, e com isso ele obtém os benefícios de sua maturidade, escondendo a sua complexidade com instalações opinativas que auxiliam no desenvolvimento de micro serviços. Muito do Spring Boot foi construído pensado na produtividade do desenvolvedor, tornando conceitos como RESTful HTTP e ambientes de execução de aplicações Web embarcados fáceis de conectar e usar. Em muitos aspectos ele também atua como um "micro-framework", permitindo aos desenvolvedores escolherem quais partes do framework que precisam, sem a necessidade de sobrecarregar o ambiente de execução com dependências. Isso também permite que aplicações feitas com o Spring Boot sejam empacotadas em unidades menores para publicação, além de ser capaz de usar sistemas de montagem (build) para gerar instaláveis como arquivos Java executáveis (JAR).

A equipe do Spring Boot criou um site conveniente para iniciar a construção de aplicações, conhecido como Spring Initializr. O propósito desse site é gerar a configuração inicial para uma aplicação Web baseada no Spring Boot e permitir que desenvolvedores escolham quais bibliotecas eles precisam em seus projetos. Passando algumas informações sobre o projeto e as dependências, o site é capaz de gerar um arquivo ZIP do projeto baseado no Spring Boot tanto para Maven quanto para Gradle. Isso fornece uma estrutura básica para começar e um excelente ponto de partida para iniciantes do framework.

Como framework, o Spring Boot é construído como um agregado de módulos conhecidos como "starters". Esses starters são composições de versões interoperáveis de bibliotecas que podem ser usadas para fornecer alguma funcionalidade para a aplicação. Eles também formam a estrutura que permitem ao Spring Boot fazer a configuração da aplicação seguindo o modelo de convenção sobre configuração, utilizado para acomodar uma arquitetura de micro serviços e expondo funcionalidades chaves para os desenvolvedores de aplicações. Um micro serviço RESTful HTTP pode ser feito no Spring Boot incluindo os módulos atuadores e de starters web. O starter web fornece o ambiente de execução embarcado e as funcionalidade que permitem construir a API do micro serviço em cima de controladores RESTful HTTP. O módulo atuador serve para operacionalizar os micro serviços fornecendo a estrutura e os endpoints RESTful HTTP, expondo métricas, parâmetros de configuração e mapeamento de componentes internos, que são úteis para depuração.

Além disso tudo que o Spring Boot fornece como framework de micro serviço ele também fornece um ferramental para projetos baseados no Maven e no Gradle. É necessário muito pouca configuração dos plugins dessas ferramentas para empacotar o projeto em um arquivo leve e executável. O código apresentado na Listagem 1 mostra o script de montagem Gradle, que pode ser usado como um ponto de partida para o micro serviço com o Spring Boot. A versão mais verbosa utilizando o Maven POM pode ser escolhido no site do Spring Initializr e revela a necessidade de informar ao plugin a localização da classe de inicialização da aplicação. Essa configuração não é necessária com o Gradle, pois o plugin descobrirá a localização dessa classe.

```java

buildscript {
  repositories {
    jcenter()
  }
  dependencies { 
   classpath 'org.springframework.boot:spring-boot-gradle-plugin:1.2.0.RELEASE'
  }
}
apply plugin: 'spring-boot'
repositories { 
  jcenter()
}
dependencies {
  compile "org.springframework.boot:spring-boot-starter-actuator"
  compile "org.springframework.boot:spring-boot-starter-web"
}
Listagem 1 - Script de montagem do Gradle.

Com o Spring Initializr é possível gerar um projeto e verificar a sua estrutura geral, que segue basicamente a estrutura convencional de layout baseado no Maven. O código fonte deve ficar no diretório src/main/java para ser compilado corretamente. O projeto deve fornecer um ponto de entrada da aplicação. No projeto gerado pelo Spring Initializr há o arquivo DemoApplication.java que funciona como esse ponto de entrada. O nome dessa classe é indiferente e chamando-a de Main é suficiente. O exemplo na Listagem 2.2 mostra o mínimo de código necessário para iniciar o desenvolvimento de um micro serviço.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
@EnableAutoConfiguration
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
```

Listagem 1.1 - Ponto de entrada da aplicação Spring Boot.

Na Listagem 1.1 notamos o uso da anotação @EnableAutoConfiguration que instrui o Spring Boot a inicializar, configurar e executar a aplicação. Para inicializar a aplicação ele utiliza o modelo de convenção sobre configuração, procurando na classes do classpath tudo que os micro serviços precisam ter. No exemplo anterior os micro serviços incluíram os módulos atuador e web, então o framework determinará que o projeto é um micro serviço e iniciará um contêiner Tomcat embarcado e começa a servir os endpoints pré-configurados. O código no exemplo anterior não faz muita coisa, mas ao executá-lo serão revelados os endpoints operacionais expostos pelo atuador. Importando o projeto em qualquer IDE permitirá que o micro serviço seja inicializado executando a classe "Main". Alternativamente, a aplicação pode ser executada a partir da linha de comando, executando o comando "gradle bootRun" do Gradle ou "mvn spring-boot:run" do Maven, conforme a configuração escolhida para o projeto.

## Trabalhando com Dados

Com base na fatia vertical de produto citado anteriormente, considere o serviço de detalhe de produto que, junto do serviço de precificação, fornece os detalhes que serão mostrados nessa página. Em termos das responsabilidades dos micro serviços, seu domínio será um subconjunto de atributos do produto, mais especificamente seu nome, descrição curta, descrição completa e um id. Esse detalhes pode ser modelados como o JavaBean apresentado na Listagem 1.2.

```java 

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class ProductDetail {
    @Id
    private String productId;
    private String productName;
    private String shortDescription;
    private String longDescription;
    private String inventoryId;
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getShortDescription() {
        return shortDescription;
    }
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public String getLongDescription() {
        return longDescription;
    }
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
    public String getInventoryId() {
        return inventoryId;
    }
    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }
}

```

> Listagem 1.2 - POJO de produto.

É importante perceber que o JavaBean ProductDetail usa as anotações do JPA para indicar que ele é uma entidade. O Spring Boot fornece um starter para trabalhar com entidades JPA e fontes de dados para bancos de dados relacionais. Considerando o script da Listagem 1, podemos adicionar na seção "dependencies" os módulos Spring Boot Starter para trabalhar com Datasets persistentes, como mostrado na Listagem 1.3.

```java

dependencies {
  compile "org.springframework.boot:spring-boot-starter-actuator"
  compile "org.springframework.boot:spring-boot-starter-web"
  compile "org.springframework.boot:spring-boot-starter-data-jpa"
  compile 'com.h2database:h2:1.4.184'
}

```

> Listagem 1.3 - Dependências do Spring Boot no script de montagem.

Com essas dependências o projeto agora também inclui o banco de dados H2 embarcado. O mecanismo de autoconfiguração do Spring Boot identificará que o H2 está no classpath e irá gerar a estrutura de tabela necessária para a entidade ProductDetail. Nos bastidores o Spring Boot está orientando o Spring Data a usar o seu Mapeamento Objeto Relacional da mesma forma que podemos influenciar as suas convenções e mecanismos para trabalhar com bancos de dados. Uma abstração conveniente do Spring Data é o conceito de "repositório", que é essencialmente um Data Access Object (DAO) configurado e disponível para ser acessado via injeção. Para obter uma funcionalidade de CRUD para a entidade ProductDetail é necessário somente criar uma interface e estendê-la de CrudRepository do Spring Data, como mostrado na Listagem 1.4.

```java

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductDetailRepository extends CrudRepository {
}

```

> Listagem 1.4 - Data Access Object (Spring Data Repository) de Product Detail.

A anotação @Repository na interface informa ao Spring que deve ser respeitado nessa classe o seu papel especializado de DAO. A anotação também serve como um mecanismo pelo qual podemos informar ao framework para automaticamente configurá-lo no micro serviço permitindo que ele seja acessado pela injeção de dependência. Para que essa funcionalidade esteja disponível no Spring é necessário usar a anotação @ComponentScan na classe principal da Listagem 1.1. Quando o micro serviço é inicializado, o Spring buscará no classpath da aplicação pelos componentes e os deixará disponíveis para a injeção de dependência dentro da aplicação.

Para demonstrar as novas capacidades do micro serviço, considere o código da Listagem 1.5 que utiliza o ApplicationContext do Spring dentro do método main.

```java

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan
@EnableAutoConfiguration
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class);
        ProductDetail detail = new ProductDetail();
        detail.setProductId("ABCD1234");
        detail.setProductName("O livro de Dan sobre a escrita");
        detail.setShortDescription("Um livro sobre como escrever livros.");
        detail.setLongDescription("Neste livro Dan apresenta ao leitor técnicas sobre como escrever livros.");
        detail.setInventoryId("009178461");
        ProductDetailRepository repository = ctx.getBean(ProductDetailRepository.class);
        repository.save(detail);
        for (ProductDetail productDetail : repository.findAll()) {
            System.out.println(productDetail.getProductId());
        }
    }
}

```
> Listagem 1.5 - Demonstração de acesso a dados.

Nesse exemplo um objeto ProductDetail é preenchido com alguns dados, o ProductDetailRepository é usado para salvar os detalhes e em seguida ele é usado novamente para consultar os detalhes no banco de dados. Além disso, nenhuma configuração adicional é necessária para fazer o micro serviço funcionar com persistência. Podemos usar o código de protótipo na Listagem 1.5 como base para definir o contrato da API RESTful HTTP através do mecanismo @RestController do Spring.

Definindo a API

Para o micro serviço de "product detail" é suficiente expor algumas capacidades simples de CRUD, mas ele também poderá fornecer algumas funcionalidades estendidas, como paginação e filtros. A API de produtos pode ser iniciada com um simples controlador que o Spring utilizará para mapear a rota HTTP. A Listagem 1.6 mostra um código que serve como ponto de partida e expõe os métodos create e findAll e demonstra a funcionalidade que foi prototipada no exemplo anterior.

```java 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/products")
public class ProductDetailController {
    private final ProductDetailRepository repository;
    @Autowired
    public ProductDetailController(ProductDetailRepository repository) {
        this.repository = repository;
    }
    @RequestMapping(method = RequestMethod.GET)
    public Iterable findAll() {
        return repository.findAll();
    }
    @RequestMapping(method = RequestMethod.POST)
    public ProductDetail create(@RequestBody ProductDetail detail) {
        return repository.save(detail);
    }
}

```
> Listagem 1.6 - Controlador de produtos.

A anotação @RestController do Spring informa que ele deve fazer a serialização e a vinculação (binding) de parâmetros. Adicionalmente, para os serviços de criação é necessário anotar os parâmetros com @RequestBody, como no método create, para que o Spring os preencha automaticamente. O objeto ProductDetail pode então ser salvo com o ProductDetailRepository injetado. O Spring Boot decora essas funcionalidades com alguns conversores de dados que usam o Jackson para converter os objetos de ProductDetail em JSON para os consumidores da API do microserviço. Baseado no exemplo no Controlador da Listagem 1.6, se o serviço receber um conteúdo JSON, como mostrado na Listagem 1.7, no endpoint /products, o detalhe do produto será criado.

```java

{
    "productId": "DEF0000",
    "productName": "MakerBot",
    "shortDescription": "Um produto que faz outros produtos",
    "longDescription": "Esta é uma descrição completa para um MarkerBot, que é basicamente um produto que faz outros produtos.",
    "inventoryId": "00854321"
}

```

> Listagem 1.7 - JSON representando a estrutura do Produto.

Fazendo uma requisição GET no controlador de produtos será mostrado o produto recém criado.

Vincular os dados aos parâmetros e persistir uma entidade posteriormente pode ser o único caso de uso para um funcionalidade e criação de um micro serviço. É mais provável, contudo, que o serviço precise executar alguma regra de negócio para garantir que os dados fornecidos são consistentes. Isso pode ser feito com o framework de validação interna do Spring que garante que os detalhes do produto em relação à lógica de negócios do micro serviço são consistentes durante a vinculação. O código da Listagem 1.8 mostra a implementação do validador de ProductDetail, que usa outro micro serviço para determinar a validade do ID fornecido.

```java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;
@Component
public class ProductDetailValidator implements Validator {
    private final InventoryService inventoryService;
    @Autowired
    public ProductDetailValidator(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    @Override
    public boolean supports(Classclazz) {
        return ProductDetail.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        ProductDetail detail = (ProductDetail)target;
        if (!inventoryService.isValidInventory(detail.getInventoryId())) {
            errors.rejectValue("inventoryId", "inventory.id.invalid", "ID de Estoque inválido");
        }
    }
}

```

> Listagem 1.8 - Validador do produto.

O InventoryService nesse exemplo está bem superficial, mas ele pode ser visto como um mecanismo para fazer validação de dados que é flexível e capaz de consultar outros micro serviços que possuem conhecimento a respeito de algum subconjunto do domínio de dados.

Para usar o ProductDetailValidator em tempo de vinculação é necessário registrá-lo com o DataBinder do Spring, no contexto do controlador. O código do controlador alterado é mostrado na Listagem 1.9, com o validador sendo injetado e então registrado na sequência com o DataBinder através do método initBinder. A anotação @InitBinder nesse método informa ao Spring que queremos personalizar o DataBinder padrão para essa classe. Adicionalmente, perceba a anotação @Valid que agora é aplicada ao objeto ProductDetail no método create. Essa anotação informa o DataBinder que desejamos fazer a validação do conteúdo da requisição durante a vinculação dos dados. O validador padrão do Spring também fornecerá validação em atributos segundo as JSR-303 e JSR 349 (Bean Validation).

```java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
@RestController
@RequestMapping("/products")
public class ProductDetailController {
    private final ProductDetailRepository repository;
    private final ProductDetailValidator validator;
    @Autowired
    public ProductDetailController(ProductDetailRepository repository, ProductDetailValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }
    @RequestMapping(method = RequestMethod.GET)
    public Iterable findAll() {
        return repository.findAll();
    }
    @RequestMapping(method = RequestMethod.POST)
    public ProductDetail create(@RequestBody @Valid ProductDetail detail) {
        return repository.save(detail);
    }
}

``` 

> Listagem 1.9 - Controlador de produtos atualizado com o validador.

Se um consumidor da API mandar um POST contendo um JSON com um ID de inventário inválido, o Spring identificará a falha de validação e devolverá ao consumidor um código HTTP 400 (Bad Request). Uma vez que o controlador está anotado com @RestController, o Spring poderia também serializar apropriadamente a falha de validação em uma estrutura que o consumidor estivesse esperando. Como desenvolveres de micro serviços, não precisamos fazer nenhuma configuração adicional para termos acesso à essa funcionalidade.

No exemplo do site de comércio eletrônico, o micro serviço de detalhes de produtos com uma API REST de CRUD simples não é de grande valor. O serviço também precisará fornecer a habilidade de paginação e ordenação da lista de produtos, bem como fornecer alguma capacidade de busca. Para disponibilizar essas funcionalidades, o método findAll no controlador ProductDetailController pode ser modificado para aceitar parâmetros na requisição que limitam a consulta. Para essa tarefa pode ser usado o PagingAndSortingRepository do Spring Data que fornecerá essas habilidades de paginação e ordenação durante a chamada do findAll no repositório. O ProductDetailRepository precisa ser modificado para herdar seu novo tipo, como mostrado na Listagem 1.10.

```java

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ProductDetailRepository extends PagingAndSortingRepository {
}
Listagem 1.10 - ProductDetailRepository atualizado com suporte a paginação e ordenação.

O código na Listagem 1.11 mostra a ação findAll do controlador usando as novas habilidades de paginação e ordenação do repositório. Uma requisição feita no endpoint /products da API, fornecendo os parâmetros page=0 e count=20 devolverá os primeiros 20 produtos do banco de dados. Nesse exemplo, o código utiliza um padrão do Spring que permite especificar valores padrões para parâmetros de consulta, tornando a maioria deles opcional.

@RequestMapping(method = RequestMethod.GET)
public Iterable findAll(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
    @RequestParam(value = "count", defaultValue = "10", required = false) int count,
    @RequestParam(value = "order", defaultValue = "ASC", required = false) Sort.Direction direction,
    @RequestParam(value = "sort", defaultValue = "productName", required = false) String sortProperty) {
    Page result = repository.findAll(new PageRequest(page, count, new Sort(direction, sortProperty)));
    return result.getContent();
}

```

> Listagem 1.11 - findAll atualizado na ProductDetailController, agora com paginação e ordenação.

Quando um usuário do site de comércio eletrônico chega em uma página de entrada, ela pode trazer 10 ou 20 produtos e posteriormente carregar mais 50 após a rolagem da página ou após algum tempo. Disponibilizando a funcionalidade de paginação permite ao consumidor controlar a quantidade de dados devolvida durante uma determinada requisição. A versão completa de ProductDetailController é apresentada na Listagem 1.12.

```java

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
@RestController
@RequestMapping("/products")
public class ProductDetailController {
    private final ProductDetailRepository repository;
    private final ProductDetailValidator validator;
    private final ObjectMapper objectMapper;
    @Autowired
    public ProductDetailController(ProductDetailRepository repository, ProductDetailValidator validator, ObjectMapper objectMapper) {
        this.repository = repository;
        this.validator = validator;
        this.objectMapper = objectMapper;
    }
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }
    @RequestMapping(method = RequestMethod.GET)
    public Iterable findAll(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
    @RequestParam(value = "count", defaultValue = "10", required = false) int count,
    @RequestParam(value = "order", defaultValue = "ASC", required = false) Sort.Direction direction,
    @RequestParam(value = "sort", defaultValue = "productName", required = false) String sortProperty) {
        Page result = repository.findAll(new PageRequest(page, count, new Sort(direction, sortProperty)));
        return result.getContent();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProductDetail find(@PathVariable String id) {
        ProductDetail detail = repository.findOne(id);
        if (detail == null) {
            throw new ProductNotFoundException();
        } else {
            return detail;
        }
    }
    @RequestMapping(method = RequestMethod.POST)
    public ProductDetail create(@RequestBody @Valid ProductDetail detail) {
        return repository.save(detail);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public HttpEntity update(@PathVariable String id, HttpServletRequest request) throws IOException {
        ProductDetail existing = find(id);
        ProductDetail updated = objectMapper.readerForUpdating(existing).readValue(request.getReader());
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("productId", updated.getProductId());
        propertyValues.add("productName", updated.getProductName());
        propertyValues.add("shortDescription", updated.getShortDescription());
        propertyValues.add("longDescription", updated.getLongDescription());
        propertyValues.add("inventoryId", updated.getInventoryId());
        DataBinder binder = new DataBinder(updated);
        binder.addValidators(validator);
        binder.bind(propertyValues);
        binder.validate();
        if (binder.getBindingResult().hasErrors()) {
            return new ResponseEntity<>(binder.getBindingResult().getAllErrors(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
        }
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HttpEntity delete(@PathVariable String id) {
        ProductDetail detail = find(id);
        repository.delete(detail);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    static class ProductNotFoundException extends RuntimeException {
    }
}
Listagem 1.12 - Versão completa de ProductDetailsController.

Além de paginação e ordenação, o site de comércio eletrônico precisará expor alguma funcionalidade parecida com um motor de busca. Como cada micro serviço fatiado verticalmente mantém seu próprio subconjunto de dados do domínio, faz sentido que eles gerenciem as próprias buscas. Ele também permite aos consumidores que as buscas sejam feitas assincronamente por várias propriedades do domínio de dados.

O Spring Data permite que consultas personalizadas sejam associadas às assinaturas de métodos anexadas à interface do repositório. Isso significa que o repositório pode ter uma consulta JPA que busca um subconjunto de propriedades de cada detalhe de produto salvo no banco, permitindo ao micro serviço executar algumas buscas primitivas. O ProductDetailRepository é modificado na Listagem 1.13 para incorporar um método search, que recebe um termo de busca e tenta combiná-lo com o nome do produto ou a descrição longa. Uma lista de resultados é devolvida ao consumidor.

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ProductDetailRepository extends PagingAndSortingRepository {
    @Query("select p from ProductDetail p where UPPER(p.productName) like UPPER(?1) or " +
            "UPPER(p.longDescription) like UPPER(?1)")
    List search(String term);
}
Listagem 1.13 - Consulta personalizada no ProductDetailRepository.

Para disponibilizar essa funcionalidade de busca, podemos construir outro RestController e mapeá-lo ao endpoint /search, como mostrado na Listagem 1.14.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/search")
public class ProductDetailSearchController {
    private final ProductDetailRepository repository;
    @Autowired
    public ProductDetailSearchController(ProductDetailRepository repository) {
        this.repository = repository;
    }
    @RequestMapping(method = RequestMethod.GET)
    public List search(@RequestParam("q") String queryTerm) {
        List productDetails = repository.search("%"+queryTerm+"%");
        return productDetails == null ? new ArrayList<>() : productDetails;
    }
}

```

> Listagem 1.14 - Controlador de busca para ProductDetails.

Uma evolução futura no ProductDetailSearchController pode ser a implementação de paginação e ordenação da mesma forma que ProductDetailController faz.

## Configuração

As configurações do Spring Boot permitem aos micro serviços ir muito longe e em alguns casos sem a necessidade de sobrescrever absolutamente nada. Quando um serviço tiver que ir para produção, certas propriedade, como a porta que o contêiner embarcado usará, pode precisar ser derivada no ambiente de execução ou baseada em alguma influência externa. O Spring Boot fornece aos desenvolvedores de micro serviços várias maneiras de sobrescrever suas configurações padrões, e o framework entende que a configuração pode vir de muitas fontes.

Relacionado à configuração dos micro serviços é importante considerar o ambiente de execução. Se ele for instalado em uma infraestrutura estática, então é correto pré-definir certas configurações. Considere o exemplo anterior que o Datasource do micro serviço era uma instância do H2. Em um ambiente de produção o micro serviço pode apontar para uma fonte de dados persistente, como um banco de dados MySQL ou Oracle, de forma que aplicação deverá ser configurada com a URL de conexão apropriada, usuário, senha e com o driver JDBC apropriado. Com uma infraestrutura estática esses valores podem ser pré-definidos e empacotados com a aplicação. O Spring Boot pode carregar essas configuracões de arquivos de propriedade, arquivos XML de configuração ou YAML, e procurará por arquivos de configuração na raiz do classpath, em arquivos com o nome application.properties, application.xml ou application.yml (application.yaml), respectivamente. Para uma configuração pré-definida, o arquivo de propriedades na Listagem 1.15 mostra a configuração que sobrescreverá a configuracão padrão.

```java

spring.datasource.url=jdbc:mysql://prod-mysql/product
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
Listagem 1.15 - Arquivo de configuração para uma fonte de dados.

``` 

Uma habilidade importante no mecanismo de configuração do Spring Boot é que as configurações podem ser sobrescritas pelas propriedades de sistema do Java, fornecidas na inicialização. Qualquer configuração fornecida na inicialização da JVM irá sobrepor a que foi definida no arquivos application.properties encontrado no classpath. Isso significa que o ambiente de execução pode definir a configuração baseada em aspectos desconhecidos durante o empacotamento do micro serviço. Por exemplo, se o micro serviço está sendo executado em um ambiente mais dinâmico, como na nuvem, a máquina de banco de dados pode ser definida conforme a localização da VM ou do contêiner. Isso pode ficar acessível à aplicação através de variáveis de ambiente. Essas variáveis de ambiente podem ser consumidas e expostas facilmente através de parâmetros de inicialização da JVM ou diretamente junto da configuração. Nesse último caso, a notação do Spring para acessar propriedades pode ser usada para obter essas configurações. O arquivo de configuração mostrado na Listagem 1.16 é uma versão modificada da Listagem 1.15, mas desta vez utilizando a notação do Spring com valor padrão.

```java

spring.datasource.url=${JDBC_URL:jdbc:mysql://prod-mysql/product}
spring.datasource.username=${JDBC_USER:root}
spring.datasource.password=${JDBC_PASS:}
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

```

> Listagem 1.16 - Arquivo de configuração atualizado para utilizar variáveis de ambiente com valores padrões.

O Spring Boot procura no sistema de arquivos um diretório chamado "config", relativo ao diretório de inicialização. Nesse diretório ele procura pela mesma sequência de arquivos de configuração e se encontrar ele carregará essas configurações primeiro, antes de aplicar qualquer configuração encontrada no classpath. A propriedade Java de sistema spring.config.location também pode ser usada para informar ao Spring o arquivo de configuração. Por exemplo, se as configurações do micro serviço estiverem em /etc/spring/boot.yml, especificando -Dspring.config.location=/etc/spring/boot.yml fará com que o Spring carregue as configurações desse arquivo. Recursos no classpath podem ser carregador pelo mesmo mecanismo, somente adicionando o prefixo "classpath:" no valor da propriedade.

A porta do servidor embarcado pode também ser configurado pelo mesmo mecanismo, através da propriedade server.port. Essa possibilidade é de extrema importância quando executa em um ambiente PaaS, como Heroku, que mapeia faixas de portas e as expõem através de variáveis de ambiente. Diretivas de configuração como essas mostradas na Listagem 1.16 podem ser usadas para mapear a variável de ambiente PORT. Listagem 1.17 mostra essa configuração.

```java

server.port=${PORT:8080}
Listagem 1.17 - Configuração usada para mapear a porta conforme a variável de ambiente.

```

## Empacotamento

Uma vez que o micro serviço está pronto para ser instalado, o ferramental do Spring Boot ajuda a gerar um artefato leve e executável. Como citado anteriormente, o Spring Boot fornece plugins para ambos Gradle e Maven, que permitem criar um arquivo JAR executável para distribuição. Usando o mesmo script de construção do Gradle mostrado nas listagens anteriores, o arquivo JAR pode ser construído executando o seguinte comando: gradle build. O Spring Boot intercepta a tarefa jar e reempacota o artefato gerado como um novo arquivo contendo todas as dependências, chamado de "gordo" ou "uber" JAR. Com o Maven, a meta "goal" será interceptada pelo plugin do Spring Boot e executará a mesma operação.

O plugin do Gradle para o Spring Boot tem um benefício adicional que é a integração com o plugin da aplicação, que gera um arquivo tar com todas as dependência empacotadas e com scripts de inicialização para variantes Unix a Windows. Esse método de empacotamento é ideal para fazer a instalação porque todos os scripts de inicialização já estarão escritos para o micro serviço. O arquivo tar pode ser extraído no servidor destino e o micro serviço iniciado através do script com o nome do projeto no diretório bin.

Embora a instalação de forma autônoma é a forma preferida e geralmente a melhor unidade instalável aceita para um micro serviço, nada especifica estritamente que eles devem ser executados de forma autônoma. As aplicações do Spring Boot também podem ser empacotadas com arquivos WAR e instalados em um contêiner. O script de construção do Gradle precisará ser modificado para usar o plugin 'war', como mostrado na Listagem 1.18. Similar ao anterior, a tarefa 'build' produzirá o artefato web.

```java

buildscript {
  repositories {
    jcenter()
  }
  dependencies {
    classpath 'org.springframework.boot:spring-boot-gradle-plugin:1.2.0.RELEASE'
  }
}
apply plugin: 'spring-boot'
apply plugin: 'war'
repositories {
  jcenter()
}
dependencies {
  compile "org.springframework.boot:spring-boot-starter-actuator"
  compile "org.springframework.boot:spring-boot-starter-web"
  compile "org.springframework.boot:spring-boot-starter-data-jpa"
  compile 'mysql:mysql-connector-java:5.1.34'
}

```

> Listagem 1.18 - Script de montagem do Gradle com os plugins do Spring Boot e o War usados.

No projeto Maven, o empacotamento pode ser feito mudando a configuração de empacotamento do pom.xml do projeto. O trecho de código na Listagem 1.19 mostra a configuração modificada.

```java

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.infoq</groupId>
    <artifactId>sb-microservices</artifactId>
    <version>0.1.0</version>
    <packaging>war</packaging>
    <!-- ...o restante foi omitido para manter a brevidade ... -->
</project>

```

> Listagem 1.19 - Inicio do pom.xml do Maven configurado para empacotar o projeto como war.

## API de Gateway

Os passos para construção do micro serviço de detalhes do produto que foi explorado em detalhes nas seções anteriores podem ser aplicado de forma similar a outros serviços em cada uma das fatias verticais do site de comércio eletrônico. Uma vez que os componentes foram decompostos em coleções de micro serviços, o sistema é então visto como sendo completamente distribuído com uma infraestrutura de micro serviços. Contudo, isso apresenta algumas complicações para os consumidores externos, tal como a página de entrada do site que precisa consumir dados vindos de diversos micro serviços diferentes. Sem nenhum mecanismos para recompor os serviços em uma API aparentemente monolítica, o ônus de acessar os dados separadamente e recompô-los em estruturas usáveis estaria em cada consumidor da API. Isso pode ser muito custoso aos consumidores que podem precisar estabelecer potencialmente dezenas de conexões HTTP para agregar alguns conjuntos de dados. Isso também significa que se algum dos serviços estão indisponíveis e cabe a cada consumidor fazer um tratamento específico para esse caso.

Um padrão emergente para a infraestrutura de micro serviços traz a ideia de um serviço de API de Gateway que opera na frente dos diversos serviços de backend para fornecer uma API compreensiva e facilmente consumível. Seguindo pelo exemplo do site de comércio eletrônico, quando um visitante do site decide visualizar os detalhes de um produto há quatro serviços envolvidos para se obter todos os dados da visualização. Ao invés do serviço responsável por gerar a página chamar cada um desses serviços, ele poderia acessar um endpoint da API de Gateway e ela, por sua vez, faria as chamadas e combinaria os resultados para a página. Da perspectiva da pagina Web, somente uma chamada é feita e tudo que for necessário para obter os dados fica fora do seu escopo.

Um ganho adicional nessa estratégia é que o dado trafegado entre o consumidor e o serviço de backend se torna mais apropriado. Por exemplo, o serviço de Gateway pode ter alguma lógica em sua camada de serviço para reconhecer quando um grande volume de requisições está sendo feita para os detalhes de um produto específico e, ao invés de chamar o micro serviço de detalhes do produto em cada requisição, ele pode decidir servir o dado a partir de um cache por algum período de tempo pré-definido. Esse efeito pode melhorar dramaticamente o desempenho e reduzir a carga de rede.

Outro ponto igualmente importante é a abstração da disponibilidade de serviços de backend. O Gateway de serviços pode ser capaz de tomar decisões inteligentes sobre qual dado servir na situação de um serviço backend não estar acessível. Há diversas opções para fazer isso, mas talvez o mecanismo mais interessante para garantir a durabilidade de sistemas distribuídos no Gateway de serviços é uma biblioteca do Netflix chamada Hystrix. Há muitas funcionalidades no Hystrix que garantem a tolerância a falhas e fornecem otimizações para altos volumes de requisições, mas talvez a sua funcionalidade mais interessante seja o padrão de "circuit breaker". Especificamente, o Hystrix verifica quando um link para um serviço de backend está inacessível e, ao invés de bombardear o serviço indisponível com um tráfego de rede e esperar por timeouts, ele abre o circuito do serviço, delegando chamadas subsequentes a um método de contingência. Em detalhes, o Hystrix periodicamente verifica a conexão para saber se o serviço de backend voltou a um estado operacional e caso isso tenha acontecido reestabelece a conexão.

Quando o circuito está aberto, o Gateway de serviço pode devolver qualquer resposta que ele escolha como apropriada para os consumidores. Isso pode incluir o último dado consistente, talvez uma resposta vazia com algum cabeçalho indicando ao consumidores que o circuito do backend está aberto ou talvez alguma combinação dos dois. A resiliência que o Hystrix fornece é um componente crítico em qualquer sistema distribuído não trivial. Para entender um pouco melhor o Hystrix, considere novamente o fatiamento vertical de produto do site de comércio eletrônico, com seus quatro serviços que devem ser chamados para obter os detalhes de um produto em uma página. A Listagem 1.20 mostra uma possível implementação do ProductService dentro da API de serviços do Gateway.

```java

import com.netflix.hystrix.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.*;
@Service
public class ProductService {
    private static final String GROUP = "products";
    private static final int TIMEOUT = 60000;
    private final ProductDetailService productDetailService;
    private final ProductPricingService productPricingService;
    private final ProductRatingService productRatingService;
    private final ProductReviewService productReviewService;
    @Autowired
    public ProductService(ProductDetailService productDetailService, ProductPricingService productPricingService,
                          ProductRatingService productRatingService, ProductReviewService productReviewService) {
        this.productDetailService = productDetailService;
        this.productPricingService = productPricingService;
        this.productRatingService = productRatingService;
        this.productReviewService = productReviewService;
    }
    public Map> getProductSummary(String productId) {
        List> callables = new ArrayList<>();
        callables.add(new BackendServiceCallable("details", getProductDetails(productId)));
        callables.add(new BackendServiceCallable("pricing", getProductPricing(productId)));
        return doBackendAsyncServiceCall(callables);
    }
    public Map> getProduct(String productId) {
        List> callables = new ArrayList<>();
        callables.add(new BackendServiceCallable("details", getProductDetails(productId)));
        callables.add(new BackendServiceCallable("pricing", getProductPricing(productId)));
        callables.add(new BackendServiceCallable("ratings", getProductRatings(productId)));
        callables.add(new BackendServiceCallable("reviews", getProductReviews(productId)));
        return doBackendAsyncServiceCall(callables);
    }
    private static Map> doBackendAsyncServiceCall(List> callables) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        try {
            List> futures = executorService.invokeAll(callables);
            executorService.shutdown();
            executorService.awaitTermination(TIMEOUT, TimeUnit.MILLISECONDS);
            Map> result = new HashMap<>();
            for (Future future : futures) {
                AsyncResponse response = future.get();
                result.put(response.serviceKey, response.response);
            }
            return result;
        } catch (InterruptedException|ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    @Cacheable
    private HystrixCommand> getProductDetails(String productId) {
        return new HystrixCommand>(
                HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(GROUP))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("getProductDetails"))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withExecutionIsolationThreadTimeoutInMilliseconds(TIMEOUT)
                        )
        ) {
            @Override
            protected Map run() throws Exception {
                return productDetailService.getDetails(productId);
            }
            @Override
            protected Map getFallback() {
                return new HashMap<>();
            }
        };
    }
    private HystrixCommand> getProductPricing(String productId) {
        // ... veja getProductDetails() ...
    }
    private HystrixCommand> getProductRatings(String productId) {
        // ... veja getProductDetails() ...
    }
    private HystrixCommand> getProductReviews(String productId) {
        // ... veja getProductDetails() ...
    }
    private static class AsyncResponse {
        private final String serviceKey;
        private final Map response;
        AsyncResponse(String serviceKey, Map response) {
            this.serviceKey = serviceKey;
            this.response = response;
        }
    }
    private static class BackendServiceCallable implements Callable {
        private final String serviceKey;
        private final HystrixCommand> hystrixCommand;
        public BackendServiceCallable(String serviceKey, HystrixCommand> hystrixCommand) {
            this.serviceKey = serviceKey;
            this.hystrixCommand = hystrixCommand;
        }
        @Override
        public AsyncResponse call() throws Exception {
            return new AsyncResponse(serviceKey, hystrixCommand.execute());
        }
    }
}

```

> Listing 1.20 - Exemplo do serviço da API de Gateway assíncrona e usando Hystrix.

Os serviços apresentados nos exemplos devem ser considerados clientes RESTful HTTP, possivelmente construídos em cima do RestTemplate do Spring ou qualquer outro cliente HTTP, como o Retrofit. O getProductSummary() faz uma chamada assíncrona aos serviços de backend necessários para obter os detalhes do produto na página de entrada do site. Similarmente, o getProduct() chama assíncronamente todos os serviços do backend para obter os detalhes de um produto e devolve essa informação para os consumidores da API. Nesse exemplo, os detalhes sobre um produto raramente serão alterados, e por essa razão faz sentido para o serviço de Gateway reduzir o volume de chamadas aos serviços de backend quando possível, então o método getProductDetails() usa a anotação @Cacheable do Spring para garantir que o resultado dessas chamadas serão armazenadas no cache por um período de tempo apropriado. O serviço de Gateway devolve os dados através de um RestController que mapeia para /products. Endpoints similares poderiam ser construídos para todas as fatias verticais da arquitetura de micro serviços e os consumidores da API do sistema poderiam ser capazes de acessá-la da mesma forma que eles faziam em uma aplicação monolítica tradicional.

## Conclusão

O Spring Boot reconhece desde o início os benefícios de decompor serviços monolíticos em micro serviços distribuídos. Ele foi projetado para tornar o desenvolvimento e a construção de micro serviços centrado em recursos e no desenvolvedor. Fornecendo módulos pré-definidos que possibilitam a autoconfiguração dentro do framework, permite que as aplicações se liguem a um poderoso subconjunto de funcionalidades que de outra forma precisariam de configurações explícitas e feita programaticamente. Esses módulos autoconfigurados podem servir como base para uma infra-estrutura compreensiva de micro serviços, incluindo uma API de Gateway de serviços.

## Sobre o autor

 > Daniel Woods é especialista e entusiasta de tecnologicas JEE, Groovy e Grails. Ele trabalha com desenvolvimento de software há aproximadamente uma década, construindo software para a JVM e compartilha a sua experiência em projetos de código aberto como Grails e Ratpack. Dan foi palestrante nas conferências Gr8conf e SpringOne 2GX, nas quais ele mostrou a sua experiência em arquiteturas de aplicações corporativas para JVM
 
 
