## Dicas de Java Script

```html
<!DOCTYPE html>
<html>
<body>

<p> Operador typeof </p>

<p id="demo"></p>

<script>
  document.getElementById("demo").innerHTML = 
      typeof "john" + "<br>" + 
      typeof 3.14 + "<br>" +
      typeof false + "<br>" +
      typeof [1,2,3,4] + "<br>" +
      typeof {name:'john', age:34} + "<br>" +
      typeof function(){}  + "<br>" +
      typeof somarValores;
</script>

</body>
</html>
```
