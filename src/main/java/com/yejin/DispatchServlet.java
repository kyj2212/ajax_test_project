package com.yejin;

import com.yejin.article.ArticleController;
import com.yejin.chat.ChatController;
import com.yejin.member.MemberController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/usr/*")
public class DispatchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Rq rq = new Rq(req,resp);

        MemberController memberController = new MemberController();
        ArticleController articleController = new ArticleController();
        ChatController chatController = new ChatController();

        String url = req.getRequestURI();

        System.out.println(url);
        System.out.println(rq.getMethod());
        System.out.println(rq.getPath());

        switch(rq.getMethod()){

            case "GET":
                switch (rq.getActionPath()) {
                    // article
                    case "/usr/article/list":
                        articleController.showList(rq);
                        break;
                    case "/usr/article/listAuto":
                        articleController.showListAuto(rq);
                        break;
                    case "/usr/article/write":
                        articleController.showWrite(rq);
                        break;
                    case "/usr/article/detail":
                        articleController.showDetail(rq);
                        break;
                    case "/usr/article/content":
                        articleController.showArticle(rq);
                        break;
                    case "/usr/article/modify":
                        articleController.showModify(rq);
                        break;
                    case "/usr/article/delete":
                        articleController.doDelete(rq);
                        break;
                    case "/usr/article/getArticles":
                        articleController.getArticles(rq);
                        break;

                    // member
                    case "/usr/member/login":
                        memberController.showLogin(rq);
                        break;

                    // chat
                    case "/usr/chat/createRoom":
                        chatController.showCreateRoom(rq);
                        break;
                    case "/usr/chat/modifyRoom":
                        chatController.showModifyRoom(rq);
                        break;
                    case "/usr/chat/deleteRoom":
                        chatController.deleteRoom(rq);
                        break;
                    case "/usr/chat/roomList":
                        chatController.showRoomList(rq);
                        break;
                    case "/usr/chat/room":
                        chatController.showRoom(rq);
                        break;
                    case "/usr/chat/roomManual":
                        chatController.showRoomManual(rq);
                        break;
                    case "/usr/chat/getMessages":
                        chatController.getMessages(rq);
                        break;
                    case "/usr/chat/deleteMessage":
                        chatController.deleteMessage(rq);
                        break;
                }
                break;
            case "POST":
                switch (rq.getActionPath()) {
                    // article
                    case "/usr/article/write":
                        articleController.doWrite(rq);
                        break;
                    case "/usr/article/modify":
                        articleController.doModify(rq);
                        break;
                    // chat
                    case "/usr/chat/createRoom":
                        chatController.doCreateRoom(rq);
                        break;
                    case "/usr/chat/writeMessage":
                        chatController.doWriteMessage(rq);
                        break;
                    case "/usr/chat/writeMessageAjax":
                        chatController.doWriteMessageAjax(rq);
                        break;
                    case "/usr/chat/deleteMessageAjax":
                        chatController.deleteMessageAjax(rq);
                        break;
                    case "/usr/chat/modifyRoom":
                        chatController.doModifyRoom(rq);
                        break;
                    case "/usr/chat/modifyMessageAjax":
                        chatController.modifyMessageAjax(rq);
                        break;
                }
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
