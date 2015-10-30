## (I) Configurando banner de log no Spring Boot

adicionar um arquivo banner.txt no path da aplicação.

<p align="center">
<img src="https://github.com/lyndontavares/spring-angularjs-datapar/blob/master/app-MasterChico/MasterChicoSB6/src/main/resources/static/image/banner.png" width="660">
</p>

## (II) Mostrando parâmetros no log

No *aplicattion.properties* definas os parâmetros, incluse os customizados.

```

developer.nome=lyndon tavares
developer.email=integraldominio@gmail.com

```

No banner.txt adicione *${parametro}*. Exemplo: 

```
Desenvolvedor: ${developer.nome}  ${spring-boot.version}

```

contato: integraldominio@gmail.com

