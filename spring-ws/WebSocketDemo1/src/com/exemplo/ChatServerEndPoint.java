package com.exemplo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/chat")
@Singleton
public class ChatServerEndPoint {
   
    Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void abrir(Session userSession) {
        System.out.println("Nova solicitação recebida. Id: " + userSession.getId());
        userSessions.add(userSession);
    }
    

    @OnClose
    public void fechar(Session userSession) {
        System.out.println("Conexão encerrada. Id: " + userSession.getId());
        userSessions.remove(userSession);
    }
    

    @OnMessage
    public void recebeMensagem(String mensagem, Session userSession) {
        System.out.println("Mensagem Recebida: " + mensagem);
        for (Session session : userSessions) {
            System.out.println("Enviando para " + session.getId());
            session.getAsyncRemote().sendText(mensagem);
        }
    }
}


