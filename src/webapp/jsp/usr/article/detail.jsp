<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../common/head.jspf"%>

<div>
        <div>
            ID : ${article.id}
        </div>
        <div>
            TITLE : ${article.title}
        </div>
        <div>
            BODY : ${article.body}
        </div>
        <div>
            writer : ${article.writer}
        </div>
</div>

<div class="flex center text-xs">
<button class="block mb-4 px-3 py-2 text-xs font-bold rounded-full no-underline hover:shadow bg-blue-600 text-white"
onclick="location.href='/usr/article/list/free'">자유게시판 목록</button>
<button class="block mb-4 px-3 py-2 text-xs font-bold rounded-full no-underline hover:shadow bg-blue-600 text-white"
onclick="location.href='/usr/article/modify/free/${article.id}'">게시글 수정</button>
</button>
<button class="block mb-4 px-3 py-2 text-xs font-bold rounded-full no-underline hover:shadow bg-blue-600 text-white"
onclick="location.href='/usr/article/delete/free/${article.id}'">게시글 삭제</button>
</div>
 <div class="flex center text-xs">
     <a href="/usr/article/modify/free/${article.id}" class="bg-black text-white no-underline py-2 px-3 rounded" >수정</a>
     <a href="/usr/article/delete/free/${article.id}" class="bg-black text-white no-underline py-2 px-3 rounded">삭제</a>
 </div>


<%@ include file="../common/foot.jspf"%>