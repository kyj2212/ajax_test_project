package com.yejin.chat;

import com.yejin.chat.dto.ChatMessageDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChatMessageRepository {
    private static List<ChatMessageDto> chatMessageDtos;
    private static long lastId;


    static {
        chatMessageDtos=new ArrayList<>();
        lastId=0;
        makeTestData();
    }

    private static void makeTestData() {
        IntStream.rangeClosed(1, 10).forEach(roomId -> {
            IntStream.rangeClosed(1, 2).forEach(id -> {
                String body = "메세지 %d".formatted(id);
                String writer = "예진 %d".formatted(id);
                write(roomId, body,writer);
            });
        });
    }

    public static long write(long roomId, String body, String writer) {
        long id = ++lastId;
        ChatMessageDto newChatMessageDto = new ChatMessageDto(id, roomId, body,writer);

        chatMessageDtos.add(newChatMessageDto);

        return id;
    }

    public static ChatMessageDto writeAjax(long roomId, String body,String writer) {
        long id = ++lastId;
        ChatMessageDto newChatMessageDto = new ChatMessageDto(id, roomId, body,writer);

        chatMessageDtos.add(newChatMessageDto);

        return newChatMessageDto;
    }
    public List<ChatMessageDto> findByRoomId(long roomId) {
        return chatMessageDtos
                .stream()
                .filter(chatMessageDto -> chatMessageDto.getRoomId() == roomId)
                .collect(Collectors.toList());
    }

    public List<ChatMessageDto> findGreaterThanId(long roomId, long fromId) {
        return chatMessageDtos.stream().filter(chatMessageDto -> chatMessageDto.getRoomId()==roomId).filter(chatMessageDto->chatMessageDto.getId()>fromId).collect(Collectors.toList());
    }

    public void deleteMessage(long id) {
        ChatMessageDto chatMessageDto = findById(id);

        if (chatMessageDto == null) {
            return;
        }

        chatMessageDtos.remove(chatMessageDto);
    }

    public  ChatMessageDto findById(long id) {
        for (ChatMessageDto chatMessageDto : chatMessageDtos) {
            if (chatMessageDto.getId() == id) {
                return chatMessageDto;
            }
        }

        return null;
    }

    public long modifyMessageAjax(long id, String body) {
        ChatMessageDto chatMessageDto=findById(id);
        ChatMessageDto newchatMessageDto=new ChatMessageDto(id,chatMessageDto.getRoomId(),body,chatMessageDto.getWriter());
        for (int i=0;i<chatMessageDtos.size();i++) {
            if (chatMessageDtos.get(i).getId() == id) {

                chatMessageDtos.set(i,newchatMessageDto);
            }
        }
        return id;
    }

    public void modifyMessage(long id, String body) {
        ChatMessageDto chatMessageDto=findById(id);
        if (chatMessageDto == null) {
            return;
        }
        chatMessageDto.setBody(body);
    }
}
