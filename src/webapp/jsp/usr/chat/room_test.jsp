<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/head.jspf"%>

<div>
        <div>
            ID : ${room.id}
        </div>
        <div>
            TITLE : ${room.title}
        </div>
        <div>
            BODY : ${room.body}
        </div>
</div>

<script>
function ChatRoomSave__submitForm(form){

    form.title.value= form.title.value.trim();
    if(form.title.value.length==0) {
        alert('내용을 입력해주세요');
        form.title.focus();
        return;
    }
    form.body.value = form.body.value.trim();
    if(form.body.value.length==0) {
            alert('내용을 입력해주세요');
            form.body.focus();
            return;
        }
    form.writer.value = form.writer.value.trim();
    if(form.writer.value.length==0) {
            alert('내용을 입력해주세요');
            form.writer.focus();
            return;
        }
    form.submit();
}
</script>

<div class="block p-6 rounded-lg shadow-lg bg-white max-w-md">
  <form method="POST" onsubmit="ChatRoomSave__submitForm(this); return false;">
    <div class="form-group mb-6">
      <input name ="title" type="text" class="form-control block
        w-full
        px-3
        py-1.5
        text-base
        font-normal
        text-gray-700
        bg-white bg-clip-padding
        border border-solid border-gray-300
        rounded
        transition
        ease-in-out
        m-0
        focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" id="exampleInput7"
             placeholder="제목을 입력해주세요">
    </div>
    <div class="form-group mb-6">
      <input name="writer" type="text" class="form-control block
        w-full
        px-3
        py-1.5
        text-base
        font-normal
        text-gray-700
        bg-white bg-clip-padding
        border border-solid border-gray-300
        rounded
        transition
        ease-in-out
        m-0
        focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none" id="exampleInput8"
             placeholder="이름을 입력해주세요.">
    </div>
    <div class="form-group mb-6">
      <textarea name="body"
              class="
        form-control
        block
        w-full
        px-3
        py-1.5
        text-base
        font-normal
        text-gray-700
        bg-white bg-clip-padding
        border border-solid border-gray-300
        rounded
        transition
        ease-in-out
        m-0
        focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none
      "
              id="exampleFormControlTextarea13"
              rows="3"
              placeholder="내용을 입력해주세요"
      ></textarea>
    </div>
    <!--div class="form-group form-check text-center mb-6">
      <input type="checkbox"
             class="form-check-input appearance-none h-4 w-4 border border-gray-300 rounded-sm bg-white checked:bg-blue-600 checked:border-blue-600 focus:outline-none transition duration-200 mt-1 align-top bg-no-repeat bg-center bg-contain mr-2 cursor-pointer"
             id="exampleCheck87" checked>
      <label class="form-check-label inline-block text-gray-800" for="exampleCheck87">Send me a copy of this message</label>
    </div-->
    <button type="submit" class="
      w-full
      px-6
      py-2.5
      bg-blue-600
      text-white
      font-medium
      text-xs
      leading-tight
      uppercase
      rounded
      shadow-md
      hover:bg-blue-700 hover:shadow-lg
      focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0
      active:bg-blue-800 active:shadow-lg
      transition
      duration-150
      ease-in-out">메세지 작성</button>
  </form>
      <a href = "/usr/chat/roomList" class="mt-5 text-center block mb-4 px-3 py-2 text-xs font-bold rounded no-underline hover:shadow bg-blue-600 text-white">목록</a>

  <!-- ajax 동적으로 채팅 메세지 가져오기-->
  <script>
    let Messages__lastId = 0;
    let Messages;
    function Messages__loadMore(){
        fetch(`/usr/chat/getMessages/free?fromId=\${Messages__lastId}`) // 현재 리스트의 마지막 id 보다 큰 값들만 불러와
            .then((response)=>response.json()) // json 파싱하는 역할
            .then((responseData) => {
                articles = responseData.data;
                if(articles.length >0){
                    Messages__lastId=articles[Messages.length-1].id;
                }
                for(key in Messages){
                    const Message=Messages[key];
                   //console.log(Messages[key].title);
                    //const html =`<p>\${Messages[key].title}</p>`;
                    //const html =`<p>\${Messages.title}</p>`;
                    //$('.place-Message').append(html);
                    addArticle(Message);
                }
                setTimeout(Messages__loadMore,3000); // 이렇게 안에 넣으면 한번이라도 버튼을 눌러서 fetch를 실행해야 이게 반복되는데?
            });
    }
    function addMessage(Message){
        const html =`
            <article class="mb-12">
            <h2 class="mb-4">
                <a href="#" class="text-black text-xl md:text-2xl no-underline hover:underline">
                    \${Message.title}
                </a>
            </h2>
            <!--div class="mb-4 text-sm text-gray-700">
                by <a href="#" class="text-gray-700"> \${article.writer} </a>
                <span class="font-bold mx-1"> | </span>
                <a href="#" class="text-gray-700">on \${article.createDate} </a>
            </div-->
            <p class="text-gray-700 leading-normal">
                \${Message.body}
            </p>
            </article>
        `;
        $('.place-Message').append(html);
    }
    </script>

			<div class="container max-w-4xl mx-auto md:flex items-start py-8 px-12 md:px-0">
				<!-- articles -->
				<div class="w-full md:pr-12 mb-12">
				    <section class="place-Message"></section>

                <!--/ articles -->
                     <button class="block mb-4 px-3 py-2 text-xs font-bold rounded-full no-underline hover:shadow bg-blue-600 text-white"
                     onclick="Messages__loadMore()">불러오기</button>
			    </div>

            </div>
        </div>

<%@ include file="../common/foot.jspf"%>