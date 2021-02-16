package com.demo.websocket.chatting.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {

    public enum MessageType{
        ENTER, TALK //입장,채팅
    }

    private MessageType type;       //메세지타입
    private String roomId;          //방번호
    private String sender;          //보낸사람
    private String message;         //메세지
}
