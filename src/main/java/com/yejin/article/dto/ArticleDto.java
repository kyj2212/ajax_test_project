package com.yejin.article.dto;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
public class ArticleDto {

    private long id;
    private String title;
    private String body;
    private String writer;
    //private LocalDateTime datetime;
    //private LocalDate createDate;
   // private LocalDate modifyDate;
    private Date date;


}
