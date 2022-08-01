package com.yejin.chat;

import com.yejin.chat.dto.ChatRoomDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ChatRoomRepository {

    private static List<ChatRoomDto> chatRoomDtoList;
    private static long lastId;


    static {
        chatRoomDtoList=new ArrayList<>();
        lastId=0;
        makeTestData();
    }
    private static void makeTestData() {
        IntStream.rangeClosed(1, 3).forEach(id -> {
            String title = "채팅방%d".formatted(id);
            String body = "채팅방이름%d".formatted(id);
            String writer = "채팅방주인%d".formatted(id);
            create(title, body,writer);
        });
    }
    public static long create(String title, String body, String writer) {
        long id = ++lastId;
        //ArticleDto newArticleDto = new ArticleDto(id,title,body, writer, new Date());
        ChatRoomDto newChatRoomDto = new ChatRoomDto(id,title,body,writer);
        chatRoomDtoList.add(newChatRoomDto);
        return id;
    }
    public List<ChatRoomDto> findAll() {
        return chatRoomDtoList;
    }

    public ChatRoomDto findById(long id) {
        for (ChatRoomDto chatRoomDto : chatRoomDtoList) {
            if (chatRoomDto.getId() == id) {
                return chatRoomDto;
            }
        }
        return null;
    }

    public void modify(long id, String title, String body) {
        ChatRoomDto chatRoomDto = findById(id);

        chatRoomDto.setTitle(title);
        chatRoomDto.setBody(body);
    }
    public void deleteRoom(long id) {
        ChatRoomDto chatRoomDto = findById(id);

        if ( chatRoomDto == null ) {
            return;
        }

        chatRoomDtoList.remove(chatRoomDto);
    }
}
