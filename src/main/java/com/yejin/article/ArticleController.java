package com.yejin.article;

import com.yejin.ResultData;
import com.yejin.Rq;
import com.yejin.article.dto.ArticleDto;
import com.yejin.chat.dto.ChatMessageDto;
import com.yejin.util.Ut;


import java.util.*;

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
    public void showListAuto(Rq rq) {
        rq.view("/usr/article/listAuto");

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

        // title,body 값이 없는 경우 앞단의 jsp 에서 js를 통해 이미 체크하였지만 체크하기
        if (title.length() == 0) {
            rq.historyBack("제목을 입력해주세요.");
            return;
        }
        if (body.length() == 0) {
            rq.historyBack("내용을 입력해주세요.");
            return;
        }
        if (writer.length() == 0) {
            rq.historyBack("작성자를 입력해주세요.");
            return;
        }
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

    public void getArticles(Rq rq) {

        // 값이 없으면 id 에 상관없이 findAll 해야함, id가 음수일 수 없다면, 그냥 defaultValue를 0으로 하면 되는 것이 아닌지?
        long fromId = rq.getLongParam("fromId",0);

        // 강사님 코드, defulatValue=-1 로 두고, -1 이면 findAll()로 받고, -1이 아니면 값을 넣음
/*
        long fromId = rq.getLongParam("fromId", -1);
        List<ArticleDto> articleDtos = null;
        if ( fromId == -1 ) {
            articleDtos = articleService.findAll();
        }
        else {
            articleDtos = articleService.findGreaterThanId(fromId);
        }
*/

        // 모든 articles 다 json 으로
        //List<ArticleDto> articleDtos = articleService.findAll();

        // 이제 fromId 부터 끝까지 json 으로
        // fromid 미포함 formid 보다 큰값부터
        List<ArticleDto> articleDtos = articleService.findGreaterThanId(fromId);
        // fromid 포함
       // List<ArticleDto> articleDtos = articleService.findAllFromId(fromId);


       // String articles = Ut.json.toStr(articleDtos, "");
        //rq.println(articles);
       // rq.println(rq.json(articleDtos));
        //rq.json(articleDtos);


//        Map<String,Object> resultData = Ut.mapOf("resultCode","S-1","msg","성공","data",articleDtos);
        // 항상 모든 json 은 resultcode 를 가진 data로 표현되어야 하므로, ResultData 클래스를 만들어버린다.
       // ResultData<List<ArticleDto>> resultData = new ResultData("S-1","성공",articleDtos);
       // rq.json(new ResultData("S-1","성공",articleDtos));
        // 아예 rq에서 성공인지도 체크해
        rq.successJson(articleDtos);
    }


}
