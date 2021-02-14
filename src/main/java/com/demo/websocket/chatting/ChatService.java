package com.demo.websocket.chatting;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ObjectMapper objectMapper;
    private Map<String,ChatRoom> chatRooms;

    @PostConstruct
    private void init(){
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom(){
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId){        //채팅방 조회(채팅방 Map에 담긴 정보 조회)
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(String name){        //채팅방 생성(randomId로 구별된 ID를 가진 채팅방 객체 생성 후 Map에 추가)
        String randomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder()
                .name(name)
                .roomId(randomId)
                .build();
        chatRooms.put(randomId,chatRoom);
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message){       //메세지 발송(지정한 webSocket세션에 메시지 발송)
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        }catch(IOException e){
            log.error(e.getMessage(),e);
        }
    }
}

