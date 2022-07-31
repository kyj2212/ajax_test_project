package com.yejin.article;

import com.yejin.article.dto.ArticleDto;

import java.util.List;

public class ArticleService {

    private ArticleRepository articleRepository;

    ArticleService(){
        //articleRepository = new ArticleRepository();
       // articleRepository = ArticleRepository.getInstance();
        articleRepository = new ArticleRepository();
    }

    public long write(String title, String body,String writer){

        return articleRepository.write(title,body,writer);
    }

    public List<ArticleDto> list() {
        return articleRepository.list();
    }

    public List<ArticleDto> findAll() {
        return articleRepository.findAll();
    }

    public List<ArticleDto> findAllFromId(long fromId) {
        return articleRepository.findAllFromId(fromId);
    }
    public List<ArticleDto> findGreaterThanId(long fromId) {
        return articleRepository.findGreaterThanId(fromId);
    }
    public ArticleDto articleAt(long id) {
        return articleRepository.articleAt(id);
    }

    public ArticleDto findById(long id){
        return articleRepository.findById(id);
    }

    public void modify(long id, String title, String body,String writer) {
        articleRepository.modify(id,title,body,writer);
    }

    public void delete(long id) {
        articleRepository.delete(id);
    }


}
