package com.example.clone_be.domain.chat.dto;

import lombok.*;

import java.util.Set;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {

    private boolean isSys;
    private String sender;
//    private String roomId;
    private String message;
    private Set<String> chatUserList;


//    @Override
//    public String toString() {
//        return "ChatDto{" +
//                "type=" + type +
//                ", sender='" + sender + '\'' +
////                ", roomId='" + roomId + '\'' +
////                ", roomName='" + roomName + '\'' +
//                ", message='" + message + '\'' +
//                '}';
//    }
}

