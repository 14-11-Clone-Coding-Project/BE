package com.example.clone_be.domain.chat.service;

import com.example.clone_be.domain.chat.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatService {

    public static Set<String> chatUserList = Collections.synchronizedSet(new HashSet<>());

    public ChatDto enterChatRoom(ChatDto chatDto, SimpMessageHeaderAccessor headerAccessor) {

        String nickName = chatDto.getSender();

        //반환 결과를 socket session에 사용자의 id로 저장
        headerAccessor.getSessionAttributes().put("nickname", nickName);
        headerAccessor.getSessionAttributes().put("roomId", "");
        chatUserList.add(chatDto.getSender());

        return ChatDto.builder()
                .isSys(true)
                .sender(nickName)
                .message(nickName + "님이 입장하셨습니다.")
                .chatUserList(chatUserList)
                .build();
    }

    public ChatDto disconnectChatRoom(SimpMessageHeaderAccessor headerAccessor) {

//        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
        String nickName = (String) headerAccessor.getSessionAttributes().get("nickname");
//        chatRoomRepository.deleteByRoomId(roomId);
        chatUserList.remove(nickName);

        return ChatDto.builder()
                .isSys(true)
//                .roomId("")
                .sender(nickName)
                .message(nickName + "님이 퇴장하였습니다.")
                .chatUserList(chatUserList)
                .build();
    }

    public Set<String> getChatUserList() {
        return chatUserList;
    }
}
