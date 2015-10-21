## Dicas de Java Script

### (I) Resumo operadores

```html
<table>
 <tr>
  <th style="width:10%">Value</th>
  <th style="width:15%">Operator</th>
  <th>Description</th>
  <th>Example</th>
 </tr>
 <tr>
  <td>19</td>
  <td>( )</td>
  <td>Expression grouping</td>
  <td>(3 + 4)</td>
 </tr>
 <tr>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
 </tr>
 <tr>
  <td>18</td>
  <td>.</td>
  <td>Member</td>
  <td>person.name</td>
 </tr>
 <tr>
  <td>18</td>
  <td>[]</td>
  <td>Member</td>
  <td>person[&quot;name&quot;]</td>
 </tr>
 <tr>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
 </tr>
 <tr>
  <td>17</td>
  <td>()</td>
  <td>Function call</td>
  <td>myFunction()</td>
 </tr>
 <tr>
  <td>17</td>
  <td>new</td>
  <td>Create</td>
  <td>new Date()</td>
 </tr>
 <tr>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
 </tr>
 <tr>
  <td>16</td>
  <td>++</td>
  <td>Postfix Increment</td>
  <td>++i</td>
 </tr>
 <tr>
  <td>16</td>
  <td>--</td>
  <td>Postfix Decrement</td>
  <td>--i</td>
 </tr>
 <tr>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
 </tr>
 <tr>
  <td>15</td>
  <td>++</td>
  <td>Prefix Increment</td>
  <td>i++</td>
 </tr>
 <tr>
  <td>15</td>
  <td>--</td>
  <td>Prefix Decrement</td>
  <td>i--</td>
 </tr>
 <tr>
  <td>15</td>
  <td>!</td>
  <td>Logical not</td>
  <td>!(x==y)</td>
 </tr>
 <tr>
  <td>15</td>
  <td>typeof</td>
  <td>Type</td>
  <td>typeof x</td>
 </tr>
 <tr>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
 </tr>
 <tr>
  <td>14</td>
  <td>*</td>
  <td>Multiplication</td>
  <td>10 * 5</td>
 </tr>
 <tr>
  <td>14</td>
  <td>/</td>
  <td>Division</td>
  <td>10 / 5</td>
 </tr>
 <tr>
  <td>14</td>
  <td>%</td>
  <td>Modulo division</td>
  <td>10 % 5</td>
 </tr>
 <tr>
  <td>14</td>
  <td>**</td>
  <td>Exponentiation</td>
  <td>10 ** 2</td>
 </tr>
 <tr>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
 </tr>
 <tr>
  <td>13</td>
  <td>+</td>
  <td>Addition</td>
  <td>10 + 5</td>
 </tr>
 <tr>
  <td>13</td>
  <td>-</td>
  <td>Subtraction</td>
  <td>10 - 5</td>
 </tr>
 <tr>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
 </tr>
 <tr>
  <td>12</td>
  <td>&lt;&lt;</td>
  <td>Shift left</td>
  <td>x &lt;&lt; 2</td>
 </tr>
 <tr>
  <td>12</td>
  <td>&gt;&gt;</td>
  <td>Shift right</td>
  <td>x &gt;&gt; 2</td>
 </tr>
 <tr>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
 </tr>
 <tr>
  <td>11</td>
  <td>&lt;</td>
  <td>Less than</td>
  <td>x &lt; y&nbsp;</td>
 </tr>
 <tr>
  <td>11</td>
  <td>&lt;=</td>
  <td>Less than or equal</td>
  <td>x &lt;= y</td>
 </tr>
 <tr>
  <td>11</td>
  <td>&gt;</td>
  <td>Greater than</td>
  <td>x &gt; y</td>
 </tr>
 <tr>
  <td>11</td>
  <td>&gt;=</td>
  <td>Greater than or equal</td>
  <td>x &gt;= y</td>
 </tr>
 <tr>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
 </tr>
 <tr>
  <td>10</td>
  <td>==</td>
  <td>Equal</td>
  <td>x == y</td>
 </tr>
 <tr>
  <td>10</td>
  <td>===</td>
  <td>Strict equal</td>
  <td>x === y</td>
 </tr>
 <tr>
  <td>10</td>
  <td>!=</td>
  <td>Unequal</td>
  <td>x != y</td>
 </tr>
 <tr>
  <td>10</td>
  <td>!==</td>
  <td>Strict unequal</td>
  <td>x !== y</td>
 </tr>
 <tr>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
 </tr>
 <tr>
  <td>6</td>
  <td>&amp;&amp;</td>
  <td>And</td>
  <td>x &amp;&amp; y</td>
 </tr>
 <tr>
  <td>5</td>
  <td>||</td>
  <td>Or</td>
  <td>x || y</td>
 </tr>
 <tr>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
  <td>&nbsp;</td>
 </tr>
 <tr>
  <td>3</td>
  <td>=</td>
  <td>Assignment</td>
  <td>x = y</td>
 </tr>
 <tr>
  <td>3</td>
  <td>+=</td>
  <td>Assignment</td>
  <td>x += y</td>
 </tr>
 <tr>
  <td>3</td>
  <td>-=</td>
  <td>Assignment</td>
  <td>x -= y</td>
 </tr>
 <tr>
  <td>3</td>
  <td>*=</td>
  <td>Assignment</td>
  <td>x *= y</td>
 </tr>
 <tr>
  <td>3</td>
  <td>/=</td>
  <td>Assignment</td>
  <td>x /= y</td>
 </tr>
</table>
```

### (II) Operador typeof

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
