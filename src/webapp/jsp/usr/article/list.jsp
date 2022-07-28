<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


		<!-- blog -->
		<div class="w-full bg-white">

			<!-- title -->
			<div class="text-center px-6 py-12 mb-6 bg-gray-100 border-b">
				<h1 class=" text-xl md:text-4xl pb-4">Articles</h1>
				<p class="leading-loose text-gray-dark">
					여기는 게시글 목록입니다
				</p>
			</div>
			<!-- /title -->

			<div class="container max-w-4xl mx-auto md:flex items-start py-8 px-12 md:px-0">
				<!-- articles -->

				<div class="w-full md:pr-12 mb-12">
                    <!--%for( ArticleDto article : articles) {%-->
                    <c:forEach items="${articles}" var="article">
					<article class="mb-12">
						<h2 class="mb-4">
							<a href="/usr/article/detail/free/${article.id}" class="text-black text-xl md:text-2xl no-underline hover:underline">
								${article.title}
							</a>
						</h2>
						<div class="mb-4 text-sm text-gray-700">
							by <a href="#" class="text-gray-700"> ${article.writer} </a>
							<span class="font-bold mx-1"> | </span>
							<a href="#" class="text-gray-700">on ${article.date} </a>
							<!--span class="font-bold mx-1"> | </span-->
							<!--a href="#" class="text-gray-700">0 Comments</a-->
						</div>
						<p class="text-gray-700 leading-normal">
							${article.body}
						</p>
					</article>
                    </c:forEach>

                     <!--/ articles -->
                     <div class="btn">
                     <button class="block mb-4 px-3 py-2 text-xs font-bold rounded-full no-underline hover:shadow bg-blue-600 text-white"
                     onclick="location.href='/usr/article/write/free'">자유게시판 글쓰기</button>
                     </div>
			    </div>

            </div>
        </div>

<%@ include file="../common/foot.jspf"%>