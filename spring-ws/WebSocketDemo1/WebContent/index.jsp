
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebSocket</title>

        <script language="javascript" type="text/javascript">

	        var path = window.location.pathname;
	        var contextoWeb = path.substring(0, path.indexOf('/', 1));
	        var endPointURL = "ws://" + window.location.host + contextoWeb + "/chat";
	
	        var chatClient = null;
	
	        function connect () {
	            chatClient = new WebSocket(endPointURL);
	            chatClient.onmessage = function (event) {
	                var messagesArea = document.getElementById("mensagens");
	                var jsonObj = JSON.parse(event.data);
	                var message = jsonObj.user + ": " + jsonObj.message + "\r\n";
	                messagesArea.innerHTML = messagesArea.value + message;
	                messagesArea.scrollTop = messagesArea.scrollHeight;
	            };
	        }
	
	        function disconnect () {
	            chatClient.close();
	        }
	
	        function enviaMensagem() {
	            var user = document.getElementById("usuario").value.trim();
	            if (user === "")
	                alert ("Digite o seu nome!");
	            
	            var inputElement = document.getElementById("messageInput");
	            var message = inputElement.value.trim();
	            if (message !== "") {
	                var jsonObj = {"user" : user, "message" : message};
	                chatClient.send(JSON.stringify(jsonObj));
	                inputElement.value = "";
	            }
	            inputElement.focus();
	        }
        </script>
    </head>




    <body onload="connect();" onunload="disconnect();">
        <h1> Exemplo de Chat </h1>

        <textarea id="mensagens" readonly></textarea>

        <div class="panel input-area">
            <input id="usuario" type="text" placeholder="Nome"/>
            <input id="messageInput" type="text" placeholder="Digite a mensagem"
                   onkeydown="if (event.keyCode == 13) enviaMensagem();" />
            <input class="button" type="submit" value="Enviar" onclick="enviaMensagem();" />
        </div>

    </body>


</html>
