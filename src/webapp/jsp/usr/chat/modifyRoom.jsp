<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jspf"%>

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

<div class="m-auto block p-6 rounded-lg shadow-lg bg-white max-w-md">
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
             placeholder="제목을 입력해주세요" value="${room.title}">
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
             placeholder="이름을 입력해주세요." value="${room.writer}">
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
      >${room.body}</textarea>
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
      ease-in-out">수정</button>
  </form>
      <a href = "/usr/chat/roomList" class="mt-5 text-center block mb-4 px-3 py-2 text-xs font-bold rounded no-underline hover:shadow bg-blue-600 text-white">목록</a>

<%@ include file="../common/foot.jspf"%>