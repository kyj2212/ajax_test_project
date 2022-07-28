package com.yejin.article;

import com.yejin.Rq;
import com.yejin.article.dto.ArticleDto;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleController {


    private ArticleService articleService;
    private List<ArticleDto> articleDtos;

    public ArticleController(){
        articleService=new ArticleService();
    }

    public void showList(Rq rq) {

       // System.out.println("showlist");
        List<ArticleDto> articleDtos = articleService.findAll();
        rq.setAttr("articles",articleDtos);
        rq.view("/usr/article/list");

    }


    public void showWrite(Rq rq) {
       // System.out.println("showwrite");
        rq.view("/usr/article/write");
    }

/*
    public void list(Rq rq){
        articleDtos=articleService.list();
        rq.setAttr("articles",articleDtos);
        rq.view("/usr/article/list");
    }
*/

    public void doWrite(Rq rq) {
      //  System.out.println("dowrite");
        String title= rq.getParam("title","");
        String body = rq.getParam("body","");
        String writer = rq.getParam("writer","");

        // title,body 값이 없는 경우 앞단의 jsp 에서 js를 통해 이미 체크함
        long id = articleService.write(title,body,writer);
        rq.appendBody("<div>%d 번 게시물이 등록되었습니다.</div>".formatted(id));
        rq.appendBody("<div>title : %s</div>".formatted(title));
        rq.appendBody("<div>body : %s</div>".formatted(body));
        //rq.appendBody("<li><a href =\"/usr/article/detail/free/%d\">%d</a></li>".formatted(id,id));
        rq.appendBody("<button><a href =\"/usr/article/detail/free/%d\">게시물 %d</a></button>".formatted(id,id));
        rq.appendBody("<button><a href =\"/usr/article/list/free/\">게시물 목록</a></button>".formatted(id,id));

        //rq.replace("<div>%d 번 게시물이 등록되었습니다.</div>".formatted(id));
        //showList(rq);
    }

    public void showDetail(Rq rq){
        long id = rq.getLongPathValueByIndex(1,0);// url/"" 뒤에 오는 1번째 오는 param  가져오기
        System.out.println(id);
        ArticleDto articleDto = articleService.findById(id);
        rq.setAttr("article",articleDto);
        rq.view("usr/article/detail");
    }

    public void showArticle(Rq rq){
        long id=rq.getLongParam("id",0);
        rq.setAttr("id",id);
        ArticleDto articleDto = articleService.articleAt(id);
        rq.setAttr("article",articleDto);
        rq.view("usr/article/content");
    }

    public void showModify(Rq rq) {
        long id = rq.getLongPathValueByIndex(1,0);
        ArticleDto articleDto = articleService.articleAt(id);
        rq.setAttr("article",articleDto);
        rq.view("usr/article/modify");
    }

    public void doModify(Rq rq) {
        //  System.out.println("dowrite");
        long id = rq.getLongParam("id",0);
        if (articleService.findById(id) == null) {
            rq.historyBack("해당 글이 존재하지 않습니다.");
            return;
        }
        String title= rq.getParam("title","");
        String body = rq.getParam("body","");
        String writer = rq.getParam("writer","");

        articleService.modify(id,title,body,writer);
        //rq.appendBody("<div>%d 번 게시물이 수정되었습니다.</div>".formatted(id));
        //rq.appendBody("<button><a href =\"/usr/article/detail/free/%d\">해당 게시물로 이동</a></button>".formatted(id,id));

        //showList(rq);
        rq.replace("/usr/article/detail/free/%d".formatted(id), "%d번 게시물이 수정되었습니다.".formatted(id));
    }

    public void doDelete(Rq rq) {
        System.out.println(rq.getPath());
        long id = rq.getLongPathValueByIndex(1,0);

        if (id == 0) {
            rq.appendBody("번호를 입력해주세요.");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);

        if (articleDto == null) {
            rq.appendBody("해당 글이 존재하지 않습니다.");
            return;
        }


        articleService.delete(id);
        rq.replace("/usr/article/list/free", "%d번 게시물이 삭제되었습니다.".formatted(id));

       // rq.appendBody("<div>%d 번 게시물이 삭제되었습니다.</div>".formatted(id));
        // rq.appendBody("<div>title : %s</div>".formatted(title));
        // rq.appendBody("<div>body : %s</div>".formatted(body));
       // rq.appendBody("<button><a href =\"/usr/article/list/free\">게시물 목록</a></button>");
    }
}
