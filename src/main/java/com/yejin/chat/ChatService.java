package com.yejin.chat;

import com.yejin.article.dto.ArticleDto;
import com.yejin.chat.dto.ChatMessageDto;
import com.yejin.chat.dto.ChatRoomDto;

import java.util.List;

public class ChatService {
    private ChatRoomRepository chatRoomRepository;
    private ChatMessageRepository chatMessageRepository;

    ChatService(){

        chatRoomRepository= new ChatRoomRepository();
        chatMessageRepository = new ChatMessageRepository();
    }

    public long createRoom(String title, String body, String writer) {
        return chatRoomRepository.create(title,body,writer);
    }
    public List<ChatRoomDto> findAllRooms() {
        return chatRoomRepository.findAll();
    }

    public ChatRoomDto findRoomById(long id) {
       return chatRoomRepository.findById(id);
    }

    public void modifyRoom(long id, String title, String body) {
        chatRoomRepository.modify(id, title, body);
    }
    public void deleteRoom(long id) {
        chatRoomRepository.deleteRoom(id);
    }

    public void writeMessage(long roomId, String body,String writer) {
        chatMessageRepository.write(roomId, body,writer);
    }
    public long writeMessageAjax(long roomId, String body,String writer) {
        return chatMessageRepository.write(roomId, body,writer);
    }

    public List<ChatMessageDto> findMessagesByRoomId(long id) {
        return chatMessageRepository.findByRoomId(id);
    }

    public List<ChatMessageDto> findGreaterThanId(long roomId, long fromId) {
            return chatMessageRepository.findGreaterThanId(roomId, fromId);
    }

    public void deleteMessage(long id) {
        chatMessageRepository.deleteMessage(id);
    }

    public ChatMessageDto findMessageById(long id) {
        return chatMessageRepository.findById(id);
    }

    public long modifyMessageAjax(long messageId, String body) {
        return chatMessageRepository.modifyMessageAjax(messageId,body);
    }

    public void modifyMessage(long id, String body) {
        chatMessageRepository.modifyMessage(id,body);

    }
}
