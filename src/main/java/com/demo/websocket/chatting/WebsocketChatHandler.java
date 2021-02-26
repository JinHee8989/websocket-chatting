package com.demo.websocket.chatting;

import com.demo.websocket.chatting.model.dto.ChatMessageDto;
import com.demo.websocket.chatting.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@RequiredArgsConstructor
@Slf4j
@Component
public class WebsocketChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload ::: " + payload);
//        TextMessage textMessage = new TextMessage("여기가 바로 채팅서버!!!");
//        session.sendMessage(textMessage);
        ChatMessageDto chatMessageDto = objectMapper.readValue(payload,ChatMessageDto.class); //웹소켓 클라이언트로부터 메세지를 받아 채팅메세지 객체로 변환
        ChatRoom chatRoom = chatService.findRoomById(chatMessageDto.getRoomId()); //전달받은 메세지에 담긴 채팅방 id로 발송대상 채팅방 정보를 조회
        chatRoom.handlerActions(session, chatMessageDto,chatService);   //채팅방에 입장해 있는 모든 클라이언트에게 타입에 따른 메세지 전송!! 내일은 꼭 공부를!!!
    }
}
