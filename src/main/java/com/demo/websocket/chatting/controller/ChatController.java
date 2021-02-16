package com.demo.websocket.chatting.controller;

import com.demo.websocket.chatting.ChatRoom;
import com.demo.websocket.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom(@RequestParam String name){      //채팅룸 생성
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoom> findAllRoom(){                        //채팅룸 조회
        return chatService.findAllRoom();
    }
}
