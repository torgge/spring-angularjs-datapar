Não é possível utilizar até a presente versão do Angular.js, operadores ternários dentro de expressões. No entanto, é possível com operadores lógicos efetuarmos a mesma lógica e obtermos o mesmo comportamento. Para isto, basta substituir a operação abaixo apresentada pela correspondente com operadores lógicos.

```js
//Operacao com operador ternário
a ? b : c

//Operação correspondente utilizando operadores lógicos
a && b || c
```

Por exemplo:

```js

Este recurso está {{ recurso.estaAtivado && "Ativado" || "Não ativado" }}.</p>

```

O motivo para este funcionamento é que a operação ‘a && b’ é tratada como ‘if(a) return b; return a;’ e ‘a || b’ é tratada como ‘if (!a) return b; return a;’.


source: http://devblog.drall.com.br/uso-de-operador-ternario-em-expressoes-no-view-do-angular-js/
