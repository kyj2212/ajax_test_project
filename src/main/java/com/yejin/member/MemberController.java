package com.yejin.member;

import com.yejin.Rq;

public class MemberController {
    public void showLogin(Rq rq) {
        rq.appendBody("<title>로그인</title>");
        rq.appendBody("<h1>로그인</h1>");
    }
}
