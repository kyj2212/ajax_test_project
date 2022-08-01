<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../common/head.jspf"%>

<div class ="mt 3 p-6 ">
        <div>
            ID : ${room.id}
        </div>
        <div>
            TITLE : ${room.title}
        </div>
        <div>
            BODY : ${room.body}
        </div>
        <div class="flex center p-1 text-xs">
        <button class="block mb-4 px-3 py-2 text-xs font-bold rounded-full no-underline hover:shadow bg-blue-600 text-white"
        onclick="location.href='/usr/chat/roomList'">채팅방 목록</button>
        <button class="block mb-4 px-3 py-2 text-xs font-bold rounded-full no-underline hover:shadow bg-blue-600 text-white"
        onclick="location.href='/usr/chat/modifyRoom/${room.id}'">채팅방 수정</button>
        </button>
        <button class="block mb-4 px-3 py-2 text-xs font-bold rounded-full no-underline hover:shadow bg-blue-600 text-white"
        onclick="location.href='/usr/chat/delete/${room.id}'">채팅방 삭제</button>
        </div>
</div>


<script>
function ChatMessageSave__submitForm(form) {
    form.body.value = form.body.value.trim();
    if ( form.body.value.length == 0 ) {
        form.body.focus();
        return false;
    }
    form.writer.value = form.writer.value.trim();
    if ( form.writer.value.length == 0 ) {
        form.writer.focus();
        return false;
    }

    $.post(
        '/usr/chat/writeMessageAjax/${room.id}', // 주소, action
        {
            writer: form.writer.value,
            body: form.body.value // 폼 내용, input name, value
        },
        function(data) { // 콜백 메서드, 통신이 완료된 후, 실행
        },
        'json' // 받은 데이터를 json 으로 해석하겠다.
    );
    form.body.value = '';
    form.body.focus();
    form.writer.value = '';
    form.writer.focus();
}
</script>


<div class="block p-6 rounded-lg shadow-lg bg-white max-w-md">
  <form method="POST" onsubmit="ChatMessageSave__submitForm(this); return false;">
    <div class="form-group mb-6">
      <input autofocus name="writer" type="text" class="form-control block
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


  <!-- ajax 동적으로 채팅 메세지 가져오기-->
  <script>
    let Messages__lastId = 0;
    let Messages;
    function Messages__loadMore(){
        fetch(`/usr/chat/getMessages/${room.id}?fromId=\${Messages__lastId}`) // 현재 리스트의 마지막 id 보다 큰 값들만 불러와
            .then((response)=>response.json()) // json 파싱하는 역할
            .then((responseData) => {
                messages = responseData.data;
                if(messages.length >0){
                    Messages__lastId=messages[messages.length-1].id;
                }
                for(key in messages){
                    const message=messages[key];
                    addMessage(message);
                }
                setTimeout(Messages__loadMore,3000); // 이렇게 안에 넣으면 한번이라도 버튼을 눌러서 fetch를 실행해야 이게 반복되는데?
            });
    }
    function addMessage(message){
        const html =`
            <article class="mb-12 mt-3">
            <h2 class="mb-4">
                <a href="#" class="text-black text-xl md:text-2xl no-underline hover:underline">
                    \${message.writer}
                </a>
            </h2>
            <p class="mb-5 text-gray-700 leading-normal">
                 \${message.body}
            </p>
             <a href="/usr/chat/deleteMessage/\${message.id}" class="bg-black text-white no-underline py-2 px-3 rounded" >수정</a>
             <a href="/usr/chat/deleteMessage/${room.id}/\${message.id}" class="bg-black text-white no-underline py-2 px-3 rounded">삭제</a>
             <button class="mt-5 block mb-4 px-3 py-2 text-xs font-bold rounded-full no-underline hover:shadow bg-blue-600 text-white"
             onclick="Messages__remove(\${message.id},this);">삭제2</button>
            </article>
        `;
        $('.place-Message').append(html);
    }
    </script>

    <div class="container max-w-4xl mx-auto md:flex items-start py-8 px-12 md:px-0">
        <!-- articles -->
        <div class="w-full md:pr-12 mb-12">
            <section class="place-Message">

            </section>

        <!--/ articles -->
             <button class="mt-5 block mb-4 px-3 py-2 text-xs font-bold rounded-full no-underline hover:shadow bg-blue-600 text-white"
             onclick="Messages__loadMore()">리프레시</button>
        </div>

    </div>
</div>


  <script>

//    let Messages__lastId = 0;
 //   let Messages;
    function Messages__loadAll(){
        fetch(`/usr/chat/getMessages/${room.id}`) // 현재 리스트의 마지막 id 보다 큰 값들만 불러와
            .then((response)=>response.json()) // json 파싱하는 역할
            .then((responseData) => {
                messages = responseData.data;
                if(messages.length >0){
                    Messages__lastId=messages[messages.length-1].id;
                }
                for(key in messages){
                    const message=messages[key];
                    addMessage(message);
                }
               // setTimeout(Messages__loadMore,3000);
            });
    }
    function Messages__remove(messageId, btn){
        alert(messageId);

            $.post(
                `/usr/chat/deleteMessageAjax/\${messageId}`, // 주소, action
                {

                },
                function(data) { // 콜백 메서드, 통신이 완료된 후, 실행
                    if(data.msg){
                        alert(data.msg);
                    }
                    $(btn).parent().remove();
                },
                'json' // 받은 데이터를 json 으로 해석하겠다.
            );

    }

    </script>



<script>
// 현재는 새로 등록된 글만 load
Messages__loadMore();
// 남들이 삭제,수정한 내용도 실시간으로 load 를 바로바로하지는 않음. 일반적인 네이버, 유튜브 댓글의 수정,삭제를 바로바로 반영하지는 않음.
</script>

<%@ include file="../common/foot.jspf"%>