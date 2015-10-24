# WebSocket

A especificação Java API for WebSocket foi submetida no início do ano pela Oracle e será incluída no futuro Java EE 7. A JSR que trata da especificação está prevista para finalização no início de 2013. Neste artigo, são apresentados as APIs Java e exemplos para programação com WebSocket. (As especificações WebSocket e Java EE 7, ainda estão em andamento, portanto os códigos e definições mostrados aqui estão sujeitos a alterações.)

O WebSocket é um protocolo e também uma API. A especificação da API é desenvolvida pelo W3C e o protocolo de comunicação é padronizado pelo IETF. (Ainda há divergências entre os próprios criadores se o termo correto é WebSocket ou WebSockets - aqui usamos o singular.) As especificações tornam possível abrir uma conexão bidirecional permanente entre um cliente e um servidor, por canais full-duplex, que operam através de um único socket TCP. Isso significa que ambos os lados poderão se comunicar ao mesmo tempo, sem problemas de compartilhar portas com conteúdo HTTP existente, ou de conviver com proxy/firewall.

O WebSocket foi criado para implementação em navegadores e servidores web, mas pode ser usado por qualquer cliente, servidor de aplicações, ou até em dispositivos móveis, desde que atenda aos requisitos para ser cliente ou para ser servidor, presentes na especificação do protocolo. A tecnologia pode também ser utilizada para estender aplicações distribuídas e de alto desempenho, e para trocar mensagens em volumes maiores, com menos exigência de infraestrutura, em cenários como SOA, ESB e cloud computing.

O intuito da criação do WebSocket é permitir, de maneira facilitada, o desenvolvimento de aplicações mais performáticas, que dependam de atualizações em tempo real. Exemplos são aplicações de bate-papo, jogos multiplayer online, mapas interativos, cotações de ações ao vivo, ferramentas de colaboração online, notificações, dashboards etc. Aplicações web como estas já eram possíveis antes, mas o WebSocket oferece uma saída para os problemas de escalabilidade e desempenho presentes no HTTP, e sem a complexidade de soluções anteriores.

Para conhecer outros detalhes da tecnologia, vale a pena conferir este vídeo da apresentação sobre WebSocket, de Brad Drysdale, no InfoQ.com (em inglês).

## Exemplos na web

Postado por André Campanini em 19 Nov 2012 | 2  Dê sua opinião CompartilharShare |  Share on facebookShare on diggShare on dzoneShare on twitterShare on redditShare on deliciousShare on emailMarcar como favoritoFavoritos
A especificação Java API for WebSocket foi submetida no início do ano pela Oracle e será incluída no futuro Java EE 7. A JSR que trata da especificação está prevista para finalização no início de 2013. Neste artigo, são apresentados as APIs Java e exemplos para programação com WebSocket. (As especificações WebSocket e Java EE 7, ainda estão em andamento, portanto os códigos e definições mostrados aqui estão sujeitos a alterações.)

O WebSocket é um protocolo e também uma API. A especificação da API é desenvolvida pelo W3C e o protocolo de comunicação é padronizado pelo IETF. (Ainda há divergências entre os próprios criadores se o termo correto é WebSocket ou WebSockets - aqui usamos o singular.) As especificações tornam possível abrir uma conexão bidirecional permanente entre um cliente e um servidor, por canais full-duplex, que operam através de um único socket TCP. Isso significa que ambos os lados poderão se comunicar ao mesmo tempo, sem problemas de compartilhar portas com conteúdo HTTP existente, ou de conviver com proxy/firewall.

O WebSocket foi criado para implementação em navegadores e servidores web, mas pode ser usado por qualquer cliente, servidor de aplicações, ou até em dispositivos móveis, desde que atenda aos requisitos para ser cliente ou para ser servidor, presentes na especificação do protocolo. A tecnologia pode também ser utilizada para estender aplicações distribuídas e de alto desempenho, e para trocar mensagens em volumes maiores, com menos exigência de infraestrutura, em cenários como SOA, ESB e cloud computing.

Conteúdo relacionado de patrocinadores

5 estratégias para segurança de APIs 5 fundamentos do OAuth para controle de acesso a APIs Cinco pilares da gerência de APIs Guia de REST e design de APIs Proteja suas APIs de ataques e hijacks
O intuito da criação do WebSocket é permitir, de maneira facilitada, o desenvolvimento de aplicações mais performáticas, que dependam de atualizações em tempo real. Exemplos são aplicações de bate-papo, jogos multiplayer online, mapas interativos, cotações de ações ao vivo, ferramentas de colaboração online, notificações, dashboards etc. Aplicações web como estas já eram possíveis antes, mas o WebSocket oferece uma saída para os problemas de escalabilidade e desempenho presentes no HTTP, e sem a complexidade de soluções anteriores.

Para conhecer outros detalhes da tecnologia, vale a pena conferir este vídeo da apresentação sobre WebSocket, de Brad Drysdale, no InfoQ.com (em inglês).

Exemplos na web

Um contexto onde já é possível observar projetos reais fazendo uso de WebSocket é o de desenvolvimento de jogos online, apenas usando HTML5 e JavaScript e rodando 100% no browser. É possível jogar online o clássico jogo Frogger da Atari na sua implementação com WebSocket. Outros jogos usando a tecnologia são um sudoku multiplayer e o clássico Asteroids. O site kaazing.me tem uma demonstração do uso de WebSocket: exibe atualizações em tempo real de indicadores e cotações da bolsa, as últimas notícias publicadas, um monitor de uso de seu DataCenter, entre outros.

Outro exemplo, publicado no blog do GlassFish, mostra como criar uma aplicação com HTML5 e WebSocket para controlar um vídeo em uso em outro cliente remoto. Esse exemplo utiliza uma implementação já possível no atual GlassFish 3.1, com uma API do framework Grizzly contida nesse servidor.

Há também outros demos conhecidos na web reunidos no site websocket.org.

## Detalhes do protocolo

Para estabelecer uma conexão WebSocket, o cliente envia uma requisição com o cabeçalho "upgrade", para solicitar a alteração do modo HTTP, e o servidor responde à solicitação. Essa comunicação inicial é chamada de "opening handshake". Veremos a seguir alguns exemplos de cabeçalhos de handshake.

Começamos com um exemplo da comunicação do cliente com o servidor:

```
GET /demo HTTP/1.1
Host: example.com
Connection: Upgrade
Sec-WebSocket-Key2: 12998 5 Y3 1 .P00
Sec-WebSocket-Protocol: sample
Upgrade: WebSocket
Sec-WebSocket-Key1: 4@1 46546xW%0l 1 5
Origin: http://example.com
[8-byte security key]
```

Abaixo está um exemplo de cabeçalho retornado pelo servidor:

```
HTTP/1.1 101 WebSocket Protocol Handshake
Upgrade: WebSocket
Connection: Upgrade
WebSocket-Origin: http://example.com
WebSocket-Location: ws://example.com/demo
WebSocket-Protocol: sample
```

Nos exemplos, os prefixos ws:// e wss:// se referem respectivamente a conexões WebSocket e WebSocket segura.

O protocolo WebSocket define duas partes: o handshake, já mostrado, e a transferência de dados em si. Cada mensagem de dados (chamada também de frame) começa com um byte "0x00" e termina com um byte "0xFF". Entre esses bytes seguem os dados em formato UTF-8, sem limite de tamanho. Os dados podem ser enviados em modo texto ou binário. Veja um exemplo mínimo de mensagem em formato texto:

```
\x00Hello, WebSocket\0xff
```
## Usando a API WebSocket no desenvolvimento client-side

Juntamente com a definição do protocolo, a especificação do W3C define a interface do objeto WebSocket, para uso em aplicações JavaScript. É através dessa interface que deve ser criada uma instância de WebSocket para se conectar a um host, informando-se como argumento a URL de destino. A interface é descrita a seguir:

```
[Constructor(in DOMString url, in optional DOMString protocol)]
    interface WebSocket {
        readonly attribute DOMString URL;
        const unsigned short CONNECTING = 0;
        const unsigned short OPEN = 1;
        const unsigned short CLOSED = 2;
        readonly attribute unsigned short readyState;
        readonly attribute unsigned long bufferedAmount;
        attribute Function onopen;
        attribute Function onmessage;
        attribute Function onclose;
        void close();
        boolean send(in DOMString data);
    };
    WebSocket implements EventTarget;
```

É importante verificar se a versão do browser em uso já é compatível com WebSocket. Há sites que informam o suporte de cada browser e versão, tais como o Caniuse, que apresenta uma tabela de compatibilidade para o suporte de WebSocket em todos os principais navegadores desktop e mobile. Veja um exemplo simples de verificação:

``` 
// Checando o suporte pelo browser em uso
if (window.WebSocket) {
   document.getElementById("support").innerHTML =
   "HTML5 WebSocket é suportado neste browser.";
} else {
   document.getElementById("support").innerHTML =
   "HTML5 WebSocket não é suportado neste browser.";
}
```
O código da API segue um modelo de programação assíncrona. Uma vez que um socket é aberto, basta esperar por eventos através de funções de retorno de chamada (callbacks), que são adicionadas para o objeto WebSocket. Veja um exemplo de como fazer uma conexão:

```js
<script language="javascript" type="text/javascript">
    // Cria um novo WebSocket
    var mySocket = new WebSocket("ws://echo.websocket.org/");
    // Associa os “listeners”
    mySocket.onopen = function(evt) {
        alert("Abre conexão…");
        // Enviando dados
        mySocket.send("WebSocket Rocks!");
        // Fecha o WebSocket
        mySocket.close();
    };
    mySocket.onmessage = function(evt) {
        alert("Mensagem recebida: " + evt.data);
    };
    mySocket.onclose = function(evt) {
        alert("Conexão fechada…");
    };
    mySocket.onerror = function(evt) {
        alert("Erro…");
    };
</script> 
```

Neste exemplo, o envio da mensagem ocorre na própria abertura da conexão, mas em um cenário real o envio poderia ocorrer após uma ação, como o clique de um botão. Um exemplo relacionado mostra uma página cliente se conectando ao mesmo servidor do código acima, porém pronta para realizar os testes de WebSocket (veja a página na imagem a seguir):

Agora que já foi mostrado um exemplo de desenvolvimento client-side para fazer a conexão e comunicação via WebSocket, veremos a implementação em WebSocket do lado servidor, através de Java EE.

## Implementação com Java EE

Como mencionado, o protocolo WebSocket é um protocolo de dois sentidos. Uma vez que a conexão foi estabelecida, o protocolo é simétrico entre as duas partes na conversa. A diferença entre um "cliente" WebSocket e um "servidor" WebSocket é somente o meio pelo qual as duas partes são conectadas.

Aplicações Java WebSocket consistem dos chamados endpoints, os finais de uma conexão WebSocket entre dois pontos, e são representados por um objeto Java. A especificação define algumas anotações que permitem tirar proveito de POJOs e transformá-los em endpoints.

A proposta da JSR 356 (a especificação de WebSocket para Java) é oferecer suporte, na plataforma Java, para a criação de componentes para lidar com a comunicação bidirecional WebSocket através de APIs do lado do cliente para Java SE ou do lado servidor, para Java EE, além de funcionalidades relacionadas, como:

Iniciar e interceptar eventos;
Criar e consumir mensagens em formato texto e binário;
Definir protocolos WebSocket e modelos de conteúdo para uma aplicação;
Configurar e gerenciar sessões WebSocket como timeouts, novas tentativas, cookies, pools de conexões etc.;
Integração com containers web Java EE;
Especificar como uma aplicação WebSocket irá trabalhar dentro do modelo de segurança do Java EE.
No lado do servidor, um POJO é convertido em um endpoint de WebSocket usando anotações ou interfaces. Veja um exemplo:

import java.net.websocket.annotations.*;
import java.net.websocket.*;

```java
@WebSocketEndpoint(path="/hello")
public class EchoPojo {
 @WebSocketMessage
 public void echoMessage(String message, Session session) {
   try {
     session.getRemote().sendString("Hello back to you, " + session + " !");
   } catch (Exception e) {
     // ...
   }
 }
}
```
No caso do cliente, apenas com Java SE é possível implementar um endpoint que converse com o outro lado, não sendo obrigatório o Java EE, em que uma classe Java SE pura pode ser cliente e servidora (como um chat), como já existe com as classes da API de "socket" do Java. A API WebSocket possui a classe ContainerProvider, que usa o mecanismo de carregar serviços do Java SE para carregar provedores em tempo de execução.

Enquanto a especificação evolui, pode-se acompanhar o repositório do projeto "websocket-spec" no Java.net, que reúne um conjunto de exemplos e o código-fonte da API em elaboração, bem como acompanhar a Reference Implementation (RI) denominada "projeto Tyrus". A equipe da JSR irá trabalhar em colaboração com o Expert Group da especificação Servlet 3.1, que está explorando a integração do handshake inicial.

A API em Java está até o momento especificada em classes do pacote java.net.websocket, mas estuda-se adotar javax.net.websocket em seu lugar. Pode-se conhecer mais sobre a JSR de WebSocket nesta apresentação (em inglês) de Arun Gupta sobre Java EE 7 e a JSR 356, no InfoQ.com.

## Projeto de exemplos com uso da API do Java EE

Além da JSR de WebSocket, outro grande esforço dentro do Java EE, relacionado ao HTML5 é o projeto WebSocket SDK, que deve contribuir bastante para a JSR 356, pois já possui exemplos mais completos e funcionais. O WebSocket SDK é um novo subprojeto dentro do Java EE, criado para fornecer código de alto nível, APIs e ferramentas para desenvolvedores Java criarem aplicativos WebSocket facilmente. O projeto é coordenado pelo próprio líder da JSR, Danny Coward. Para testar os exemplos com segurança, recomenda-se a leitura das páginas Introduction e Get Started do projeto (em inglês).

Implementações WebSocket existentes pré-Java EE

Antes mesmo de existir uma JSR para uso de API padrão de WebSocket para Java, já havia alguns servidores web com implementação de WebSocket disponíveis, por exemplo o GlassFish 3.1. Veja algumas outras implementações existentes:

Apache Tomcat 7 (Java) - nova API;
Jetty - container web;
Framework Netty da JBoss - framework Java para redes;
Java-WebSocket - implementação cliente e servidor escrita 100% em Java;
Kaazing WebSocket Gateway (produz um Gateway baseado em Java);
Socket.io (implementação servidor para Java Servlets que usa node.js).
Mais implementações podem ser consultadas no Wiki da JSR 356 no Java.net.

Outro exemplo de implementação existente, neste caso no servidor Jetty, tem abordagem diferente daquela proposta pela JSR para o Java EE. Veja um exemplo de código:

´´´java
public class TesteJettyWebSocketServlet extends WebSocketServlet implements Servlet{
   public void doGet(HttpServletRequest req, HttpServletResponse res){}
   public void doPost(HttpServletRequest req, HttpServletResponse res){}

   //disparado pelo request para atualizar para conexão WebSocket
   @Override
   protected WebSocket doWebSocketConnect(HttpServletRequest req, String arg) {
       // ...
   }
}
```
## Conclusões

Através deste artigo foram mostrados alguns detalhes sobre a importante tecnologia de WebSocket e sua integração com Java e JavaScript, incluindo código do lado do cliente para realizar uma conexão com um servidor preparado para atender a requisições WebSocket. É possível acompanhar o processo da JSR 356 no site da especificação, além de visualizar o draft do documento.

## Sobre o autor

André Campanini (@andrecampanini) atua há 15 anos na área de TI desenvolvendo projetos, sistemas e definições de arquitetura utilizando principalmente a tecnologia Java. Atualmente é Analista de Sistemas Sênior na ECT-Empresa Brasileira de Correios e Telégrafos, especialista em desenvolvimento de softwares corporativos com a tecnologia Java EE. Já atuou como instrutor de Java EE em parceria com a Universidade Correios, e é instrutor de Java na Globalcode em São Paulo, além de editor do site InfoQ Brasil.

Source: http://www.infoq.com/br/articles/websocket-java-javaee















    







[16-byte hash response]





