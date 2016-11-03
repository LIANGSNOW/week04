/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author zz
 */
@RequestScoped
@ServerEndpoint("/notews/all")
public class NoteWebSocket {

    @OnOpen
    public void onOpen(Session userSession) {
        UserSessionHandler us = UserSessionHandler.getInstance();
        us.setSession(userSession.getOpenSessions());
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        
    }

    @OnMessage
    public void onMessage(Session s, String message) {
        
    }

}
