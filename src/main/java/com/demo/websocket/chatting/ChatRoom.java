package com.demo.websocket.chatting;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name){
        this.roomId = roomId;
        this.name = name;
    }

    public void handlerActions(WebSocketSession session, ChatMessageDto message,ChatService chatService){
        if(message.getType().equals(ChatMessageDto.MessageType.ENTER)){
            sessions.add(session);
            message.setMessage(message.getSender()+"님이 입장하셨습니다.");

        }
        sendMessage(message,chatService);
    }

    private void sendMessage(ChatMessageDto message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> charService.sendMessage(session,message));
    }

}
