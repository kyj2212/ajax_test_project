package com.yejin.article.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class ArticleDto {

    private long id;
    private String title;
    private String body;
    private String writer;
    private Date date;


}
