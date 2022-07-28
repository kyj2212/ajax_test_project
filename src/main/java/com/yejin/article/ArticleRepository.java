package com.yejin.article;

import com.yejin.article.dto.ArticleDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleRepository {

    private static ArticleRepository instance;
    private static List<ArticleDto> articleDtoList;

    private static long lastId;

/*
    static {
        articleDtoList=new ArrayList<>();
        lastId=0;
    }
*/

    private ArticleRepository(){
        articleDtoList=new ArrayList<>();
        lastId=0;
    }

    public static ArticleRepository getInstance(){
        if(instance==null)
            instance=new ArticleRepository();
        return instance;
    }




    public long write(String title, String body,String writer){
        long id = ++lastId;
        ArticleDto newArticleDto = new ArticleDto(id,title,body, writer, new Date());
        articleDtoList.add(newArticleDto);
        return id;
    }

    public List<ArticleDto> list() {
        return articleDtoList;
    }

    public List<ArticleDto> findAll() {
        return articleDtoList;
    }

    public ArticleDto articleAt(long id) {
        for(ArticleDto article :  articleDtoList){
            if(article.getId()==id)
                return article;
        }
        return null;
    }

    public ArticleDto findById(long id) {
        for(ArticleDto article :  articleDtoList){
            if(article.getId()==id)
                return article;
        }
        return null;
    }

    public int getIdx(long id) {
        for(int i=0;i<articleDtoList.size();i++){
            if(articleDtoList.get(i).getId()==id)
                return i;
        }
        return -1;
    }

    public void modify(long id, String title, String body,String writer) {
        int idx=getIdx(id);
        ArticleDto newArticleDto = new ArticleDto(id,title,body, writer, new Date());
        //articleDtoList.remove(id);
        articleDtoList.set(idx,newArticleDto);
    }

    public void delete(long id) {
        int idx=getIdx(id);
        articleDtoList.remove(idx);
    }
}
