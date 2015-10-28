##O que é arquitetura baseada em micro serviços?


O termo micro serviço tem me ocupado nos últimos meses. Vejo a Pivotal dando ênfase a este estilo arquitetural no lançamento do Spring 4,  uma apresentação interessante de James Lewis a respeito e que mais tarde, junto com  Martin Fowler irá escrever um maravilhoso artigo a respeito e até mesmo textos tolos como este. Após ter lido tantas fontes há momentos em que me sinto como Santo Agostinho ao ser questionado sobre o tempo:

“O que é, por conseguinte, o tempo? Se ninguém me perguntar, eu sei; se o quiser explicar a quem me fizer a pergunta, já não sei.” (Livro XI das Confissões de Santo Agostinho)

É interessante como na maior parte das fontes que citei o texto se inicia com algo do tipo “ainda não há uma decisão precisa do termo” (Fowler). Como verão neste post minha dúvida primária ainda se mantém: é de fato algo novo ou apenas um daqueles momentos nos quais a indústria renomeia algo que já conhecíamos e usávamos há anos apenas para nos vender alguma coisa?

##Meu entendimento sobre a coisa

Resumindo: mais uma solução para o problema do alto acoplamento em aplicações corporativas. A diferença é que desta vez estamos atacando aplicações monolíticas. Bom: então primeiro é interessante pensarmos no que vêm a ser uma “aplicação monolítica”.

Uma aplicação monolítica é aquela na qual todos os componentes de negócio encontram-se dentro de uma mesma unidade de implantação. Se você é programador Java já sabe do que estou falando: os arquivos WAR ou EAR. Reparou que usei o termo “componentes de negócio”?

##O que é um componente?

componenteVou usar a definição de Fowler e Lewis de componente: “é aquela unidade de software que pode ser aprimorada e substituida de forma independente”. Vamos pensar num exemplo simples: a biblioteca que você usa para compactar dados. Esta possívelmente foi feita por um terceiro. Caso este terceiro descubra uma maneira de compactar dados de forma mais eficiente, basta que você troque a biblioteca, se esta mantiver a mesma assinatura, seu sistema inteiro se beneficiará destas melhorias de forma transparente. E você apenas substituiu um pedaço do seu sistema.

Bom: qual o problema então com aplicações monolíticas? O fato destas conterem em si todos os componentes usados e todos serem executados dentro do mesmo processo. Precisou trocar um componente? Você irá fazer o deploy da unidade inteira. Já houve algumas soluções interessantes para este problema, como por exemplo o EJB e OSGi (OSGi na minha opinião é como o Java SEMPRE deveria ter sido, falo mais sobre ele no futuro).

O ideal seria que você não precisasse parar seu sistema para substituir um componente. Como no caso do bundle OSGi ou da implementação remota do EJB, mas nem sempre isto ocorre ou mesmo é necessário. Ok: você sabe o que é um componente. Um componente de negócio seria portanto aquele que encapsula um requisito funcional do seu sistema. O que é então um micro serviço? Um serviço pequeno?

##O que é um serviço?

servicosSeguindo a definição de Fowler e Lewis, um serviço seria na realidade um tipo especial de componente. Aquele componente que descrevi alguns parágrafos acima é o que chamaríamos de biblioteca. É código contra o qual ligamos aquele que escrevemos, e que consequentemente nos gera um sistema monolítico. O serviço é o oposto da biblioteca, ou seja, nós não ligamos nosso código diretamente a este: apenas o executamos a partir de chamadas remotas.

Sendo assim, um serviço seria um componente executado em um processo diferente do cliente que o usa. Esta é uma idéia bastante interessante: caso um serviço apresente problemas, basta que, em tempo de execução, eu substitua apenas aquele pedaço do sistema, mantendo todo o resto em execução como se “quase nada” houvesse ocorrido. Preciso de maior escalabilidade? Poderia pensar em diversas instâncias do mesmo serviço. As possibilidades são práticamente infinitas quando pensamos desta forma.

E o que é o tal do micro serviço então?

##microservicos

O prefixo “micro” é enganador. Em um primeiro momento podemos pensar que se trata de um programa bem pequeno, e na apresentação de Lewis é defendido que devem ser pequenos o suficiente para que sejam compreendidos por uma úncia pessoa.

Um micro serviço compreende um requisito funcional do seu sistema. É um componente de negócio executado em um processo externo portanto. Como requisitos funcionais podem ser complexos, nada impede que seu “micro” componente não seja tão pequeno assim (mas isto vai depender mais da sua modelagem do que desta abordagem).  Sendo assim acredito que seja mais interessante pensar que o ideal seria serem micro serviços pequenos, porém o essencial é que determinem bem o que venha a ser os requisitos funcionais que implementam.

Um sistema que siga a arquitetura baseada em micro serviços portanto é aquela que é composta por micro serviços, cada um destes responsável por um conjunto finito de requisitos funcionais do projeto.

##Consequências do micro serviço

A saída do modo monolítico muda bastante coisa no nosso modo de pensar. Para começar podemos pensar em dividir melhor a equipe. Este é inclusive um ponto apresentado por Fowler e Lewis. Agora podemos ter equipes especializadas naquele conjunto de funcionalidades de negócio, que passarão a tratar o serviço não como um mero componente, mas sim um produto, com ciclo de vida independente. Isto trás implicações profundas do ponto de vista gerencial.

Outro ponto importante: como estes micro serviços se comunicam? Faz-se necessário adotar uma estratégia que facilite a integração de todas estas pontas. Em todos os artigos são expostas duas estratégias fundamentais: REST (alguns mencionam HATEOAS) e mensageria através de algum broker como ActiveMQ, RabbitMQ, HornetQ ou tantos outros. É obrigatória a presença de um protocolo aberto comum a todos os serviços: imagine ter de usar protocolos proprietários neste tipo de arquitetura, sua vida se tornaria um inferno.

Um ESB se aplica? No artigo de Fowler e Lewis ele é mencionado por alto, mas não se mostra como algo obrigatório. Sinceramente também acho que seja desnecessário, apesar de que um serviço similar ao UDDI quebre troncos nesta abordagem. UDDI é a grosso modo um diretório de web services baseados em SOAP. Algo que me responda à pergunta “qual o endereço das implementações do serviço x?” tornaria tudo muito mais fácil.

Se todos os processos são isolados, também preciso que sejam monitorados, ou seja, todo serviço deve apresentar métricas sobre sua qualidade de serviço. É fundamental que eu saiba quem está ou não online para que cada micro serviço possa implementar sua própria estratégia no caso de indisponibilidade de algum serviço do qual dependa.

E olha outro ponto importante: você também precisa de uma estratégia de versionamento que seja consistente. Se os micro serviços são por definição componentes, estes devem poder evoluir isoladamente também.

Se a comunicação entre micro serviços sempre requer a invocação de código externo é necessário pensar em estratégias visando minimizar perda de performance. Uma coisa é chamar código diretamente ligado ao meu (bibliotecas), outra código remoto.

Há também um outro ponto bastante delicado: como pensar a questão da componentização? Se quero que meus micro serviços sejam completamente independentes como organizo meu código fonte? Na apresentação de Lewis é proposto que cada equipe tenha seu próprio repositório a fim de evitar a criação de componentes compartilhados que causem dependências profundas entre um ou mais micro serviços. Faz muito sentido, especialmente se eu quiser que alguns componentes sejam escritos em linguagens distintas. Imagine dois micro serviços dependentes de uma biblioteca compartilhada: alterando-a mudo o comportamento de dois componentes que deveriam ser em teoria completamente independentes.

E estes são apenas alguns dos pontos gerais que observei nesta abordagem. Vocês com certeza me mostrarão mais alguns nos comentários deste post, certo? :)

Consequências do lado Java

Pensando como desenvolvedor Java as coisas mudam de figura no modo como pensamos o deploy de nossos projetos. Até então pensamos em arquivos WAR e EAR que iremos instalar em nossos servidores de aplicação. No entanto os serviços são executados isoladamente, certo?

Logo devemos minimizar o uso de containers. Imagine que eu sobrecarregue um container com inúmeros micro serviços. Se este cai, diversos vão junto. A solução é embarcar o container e distribuir nossa aplicação como um jar único. Já há projetos que nos permitem fazer isto. Destes o que acho mais interessante atualmente (e que podemos usar HOJE) é o Spring B oot. Grails terá algo similar em breve (afinal de contas, é Pivotal né?), Vertx também nos permite fazer algo similar.

E ei: se você olhar com atenção, verá que toda a história por trás do Node.js gira em torno deste conceito também. ;)

##Mas cadê a novidade?

Este é minha grande dúvida. Cadê a novidade? Decompor nossos projetos em micro serviços é algo que fazemos há muito tempo. Veja por exemplo EJB e seu modelo distribuído. Um único protocolo comum (RMI por baixo dos panos) e lógica de negócios encapsulada no bean.

Outro exemplo interessante: OSGi. Em que eu posso inclusive fazer o deploy de um serviço (representado como um bundle) no container em tempo de execução sem parar a aplicação. Neste caso não necessáriamente seria um micro serviço pois o bundle é executado na maior parte das vezes dentro de um mesmo processo, mas poderia ser externo também.

E tem também o SOA, que nos fornece exatamente isto com o uso de ESBs, certo? Isto sem mencionar as diversas vezes em que desenvolvemos sistemas usando esta mesma abordagem no passado sem fugir muito destes princípios que apresentei sem darmos este nome (aqui está um exemplo interessante no blog do Rafael Romão).

A novidade talvez esteja no fato de finalmente estarmos dando um nome a este boi. Gosto disto, quanto melhor o vocabulário, melhor.

##Concluindo

Não acredito que a pergunta “será uma arquitetura baseada em micro serviços o futuro?” será respondida somente em um futuro distante. Na realidade, ela já foi resolvida: ela é o presente. A partir do momento em que isolamos os objetivos de negócio do nosso sistema em processos isolados que possuam as características a seguir:

Cada serviço executado em um processo isolado.


Padrão de comunicação entre os serviços.

Pequenos o suficiente para que isolem bem requisitos funcionais do sistema.

Cada serviço apresente métricas sobre si mesmo.

Ou pelo menos a maior parte destes pontos, já é uma arquitetura baseada em micro serviços (ou próxima).

O que realmente gosto nesta história toda é a quantidade de discussão que imagino surgir a partir destes conceitos. Que venha e que com isto tenhamos melhores termos para estes problemas!

###PS: 31/3/2014 – 11:00 AM

Encontrei uma crítica bem interessante ao texto de Lewis e Fowler que você pode ler neste link. O autor tem um ponto bastante interessante: para ele “micro serviços” são na realidade um retrocesso pois ignoram os pontos aprendidos com SOA. Os comentários são particularmente interessantes. :)

###PS: 6/4/2014

Uma semana depois postei algumas conclusões a que cheguei sobre o assunto neste post.
