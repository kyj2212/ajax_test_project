package com.yejin.chat.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDto {
    private long id;
    private String title;
    private String body;
    private String writer;

}

