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

            <script>
                setInterval(function Articles__loadLatest(){
                    fetch('/usr/article/getArticles/free')
                        .then((response)=>response.json()) <!-- json 파싱하는 역할 -->
                        .then((responseData) => {
                            var articles = responseData.data;
                            for(key in articles){
                               // console.log(articles[key].title);
                              //  addArticle(article);
                            }
                        });
                },10000000);

                let Articles__lastId = 0;
                let articles;
                function Articles__loadMore(){
                    fetch(`/usr/article/getArticles/free?fromId=\${Articles__lastId}`) // 현재 리스트의 마지막 id 보다 큰 값들만 불러와
                        .then((response)=>response.json()) // json 파싱하는 역할
                        .then((responseData) => {
                            articles = responseData.data;
                            if(articles.length >0){
                                Articles__lastId=articles[articles.length-1].id;
                            }
                            for(key in articles){
                                const article=articles[key];
                               //console.log(articles[key].title);
                                //const html =`<p>\${articles[key].title}</p>`;
                                //const html =`<p>\${article.title}</p>`;
                                //$('.place-article').append(html);
                                addArticle(article);
                            }
                            setTimeout(Articles__loadMore,3000); // 이렇게 안에 넣으면 한번이라도 버튼을 눌러서 fetch를 실행해야 이게 반복되는데?
                        });
                }
                function addArticle(article){
                    const html =`
                        <article class="mb-12">
						<h2 class="mb-4">
							<a href="/usr/article/detail/free/\${article.id}" class="text-black text-xl md:text-2xl no-underline hover:underline">
								\${article.title}
							</a>
						</h2>
						<div class="mb-4 text-sm text-gray-700">
							by <a href="#" class="text-gray-700"> \${article.writer} </a>
							<span class="font-bold mx-1"> | </span>
							<a href="#" class="text-gray-700">on \${article.createDate} </a>
							<!--span class="font-bold mx-1"> | </span-->
							<!--a href="#" class="text-gray-700">0 Comments</a-->
						</div>
						<p class="text-gray-700 leading-normal">
							\${article.body}
						</p>
						</article>
                    `;
                    $('.place-article').append(html);
                }



            </script>


			<div class="container max-w-4xl mx-auto md:flex items-start py-8 px-12 md:px-0">
				<!-- articles -->
				<div class="w-full md:pr-12 mb-12">
				    <section class="place-article"></section>

                <!--/ articles -->

                     <button class="block mb-4 px-3 py-2 text-xs font-bold rounded-full no-underline hover:shadow bg-blue-600 text-white"
                     onclick="location.href='/usr/article/write/free'">자유게시판 글쓰기</button>
                     <button class="block mb-4 px-3 py-2 text-xs font-bold rounded-full no-underline hover:shadow bg-blue-600 text-white"
                     onclick="Articles__loadMore()">불러오기</button>
			    </div>

            </div>
        </div>

<%@ include file="../common/foot.jspf"%>