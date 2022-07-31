package com.yejin.article;

import com.yejin.article.dto.ArticleDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArticleRepository {

 //   private static ArticleRepository instance;
    private static List<ArticleDto> articleDtoList;
    private static long lastId;


        static {
            articleDtoList=new ArrayList<>();
            lastId=0;
            makeTestData();
        }

/*
    static {
        makeTestData();
    }*/
/*    private ArticleRepository(){
        articleDtoList=ArticleRepository.getInstance();
        lastId=0;
    }
    */
    private static void makeTestData() {
        IntStream.rangeClosed(1, 10).forEach(id -> {
            String title = "제목%d".formatted(id);
            String body = "내용%d".formatted(id);
            String writer = "작성자%d".formatted(id);
            write(title, body,writer);
        });
    }


/*

    public static ArticleRepository getInstance(){
        if(instance==null)
            instance=new ArticleRepository();
        return instance;
    }

*/


    // why static ??
    public static long write(String title, String body,String writer){
        long id = ++lastId;
        //ArticleDto newArticleDto = new ArticleDto(id,title,body, writer, new Date());
        ArticleDto newArticleDto = new ArticleDto(id,title,body, writer, LocalDate.now(),LocalDate.now());
        articleDtoList.add(newArticleDto);
        return id;
    }

    public List<ArticleDto> list() {
        return articleDtoList;
    }

    public List<ArticleDto> findAll() {
        return articleDtoList;
    }

    public List<ArticleDto> findAllFromId(long fromId) {
        //List<ArticleDto> articleDtosFromdId = articleDtoList.subList(fromId,articleDtoList.size());
        // list의 index와 article 객체의 인스턴스 id는 다른 값이다.
        // 따라서 id 값이 formId 인 값부터 쭉이니까 for에서 id를 체크해서 크거나 같은 값들만 뿜어야 한다.
/*
        List<ArticleDto> articleDtosFromdId = new ArrayList<>();
        for(ArticleDto articleDto : articleDtoList){
            if(articleDto.getId()>=fromId)
                articleDtosFromdId.add(articleDto);
        }
        return articleDtosFromdId;
*/

        // stream으로 동일하게 구현해보기!!!
        // 처음에 filter에서 변수명을 a 라고 뒀으나, articleDto로 두는게 더 직관적으로 보일것 같음
        return articleDtoList.stream().filter(articleDto->articleDto.getId()>=fromId).collect(Collectors.toList());

    }
    public List<ArticleDto> findGreaterThanId(long fromId) {
        return articleDtoList.stream().filter(articleDto->articleDto.getId()>fromId).collect(Collectors.toList());
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
       // ArticleDto newArticleDto = new ArticleDto(id,title,body, writer,new Date());
        ArticleDto newArticleDto = new ArticleDto(id,title,body, writer,articleAt(id).getCreateDate(), LocalDate.now());
        //articleDtoList.remove(id);
        articleDtoList.set(idx,newArticleDto);
    }

    public void delete(long id) {
        int idx=getIdx(id);
        articleDtoList.remove(idx);
    }



}
