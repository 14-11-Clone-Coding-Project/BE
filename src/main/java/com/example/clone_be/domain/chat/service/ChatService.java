package com.example.clone_be.domain.chat.service;

import com.example.clone_be.domain.chat.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatService {

    public ChatDto enterChatRoom(ChatDto chatDto, SimpMessageHeaderAccessor headerAccessor) {
        // 예외처리
        //반환 결과를 socket session에 사용자의 id로 저장
        headerAccessor.getSessionAttributes().put("nickname", chatDto.getSender());
        headerAccessor.getSessionAttributes().put("roomId", chatDto.getRoomId());

        chatDto.setMessage(chatDto.getSender() + "님 입장!! ο(=•ω＜=)ρ⌒☆");
        return chatDto;
    }

    public ChatDto disconnectChatRoom(SimpMessageHeaderAccessor headerAccessor) {
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
        String nickName = (String) headerAccessor.getSessionAttributes().get("nickname");

//        chatRoomRepository.deleteByRoomId(roomId);

        ChatDto chatDto = ChatDto.builder()
                .type(ChatDto.MessageType.LEAVE)
                .roomId(roomId)
                .sender(nickName)
                .message(nickName + "님 퇴장!! ヽ(*。>Д<)o゜")
                .build();

        return chatDto;
    }
}
