<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jspf"%>

<script>
function ArticleSave__submitForm(form){

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

    <!--h1>게시물 작성</h1-->
    <!--div>
    <form method="POST" onsubmit="ArticleSave__submitForm(this); return false;">
        <div>
            <span>제목</span>
            <div>
                <input name="title" type="text" maxlength="50" placeholder="제목을 입력해주세요." />
            </div>
        </div>

        <div>
            <span>내용</span>
            <div>
                <input name="body" type="text" maxlength="300" placeholder="내용을 입력해주세요." />
            </div>
        </div>
        <div>
            <span>작성자</span>
            <div>
                <input name="writer" type="text" maxlength="300" placeholder="작성자를 입력해주세요." />
            </div>
        </div>
        <div>
            <span>작성</span>
            <div>
                <input type="submit" value="작성" />
            </div>
        </div>
    </form>
    </div-->
<div>
    <div class="md:grid md:grid-cols-6 md:gap-6">
        <div class="md:col-span-1">
            <div class="mx-4 mt-4 text-center px-4 sm:px-0">
                <h3 class="text-lg font-medium leading-6 text-gray-900">Article</h3>
                <p class="mt-1 text-sm text-gray-600">
                    게시글 작성
                </p>
            </div>
        </div>
        <div class="mt-5 md:mt-0 md:col-span-4">
            <form method="POST" onsubmit="ArticleSave__submitForm(this); return false;">
                <div class="shadow sm:rounded-md sm:overflow-hidden">
                    <div class="px-4 py-5 bg-white space-y-6 sm:p-6">
                        <div class="grid grid-cols-3 gap-6">
                            <div class="col-span-3 sm:col-span-2">
                                <label for="title" class="block text-sm font-medium text-gray-700">
                                    제목
                                </label>
                                <div class="mt-1 flex rounded-md shadow-sm">
                                    <input type="text" name="title" id="title" class="focus:ring-indigo-500 focus:border-indigo-500 flex-1 block w-full rounded-none rounded-r-md sm:text-sm border-gray-300" placeholder="제목을 입력하세요">
                                </div>
                            </div>
                        </div>

                        <div>
                            <label for="body" class="block text-sm font-medium text-gray-700">
                                내용
                            </label>
                            <div class="mt-1">
                                <textarea id="body" name="body" rows="3" class="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 mt-1 block w-full sm:text-sm border-gray-300 rounded-md" placeholder="내용을 입력하세요."></textarea>
                            </div>
                            <p class="mt-2 text-sm text-gray-500">
                                ...
                            </p>
                        </div>
                        <div>
                            <label for="writer" class="block text-sm font-medium text-gray-700">
                                작성자
                            </label>
                            <div class="mt-1">
                                <textarea id="writer" name="writer" rows="3" class="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 mt-1 block w-full sm:text-sm border-gray-300 rounded-md" placeholder="작성자을 입력하세요."></textarea>
                            </div>
                        </div>
                        <div>
                            <label class="block text-sm font-medium text-gray-700">
                                Photo
                            </label>
                            <div class="mt-1 flex items-center">
                <span class="inline-block h-12 w-12 rounded-full overflow-hidden bg-gray-100">
                  <svg class="h-full w-full text-gray-300" fill="currentColor" viewBox="0 0 24 24">
                    <path d="M24 20.993V24H0v-2.996A14.977 14.977 0 0112.004 15c4.904 0 9.26 2.354 11.996 5.993zM16.002 8.999a4 4 0 11-8 0 4 4 0 018 0z" />
                  </svg>
                </span>
                                <button type="button" class="ml-5 bg-white py-2 px-3 border border-gray-300 rounded-md shadow-sm text-sm leading-4 font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                                    Change
                                </button>
                            </div>
                        </div>

                        <div>
                            <label class="block text-sm font-medium text-gray-700">
                                Cover photo
                            </label>
                            <div class="mt-1 flex justify-center px-6 pt-5 pb-6 border-2 border-gray-300 border-dashed rounded-md">
                                <div class="space-y-1 text-center">
                                    <svg class="mx-auto h-12 w-12 text-gray-400" stroke="currentColor" fill="none" viewBox="0 0 48 48" aria-hidden="True">
                                        <path d="M28 8H12a4 4 0 00-4 4v20m32-12v8m0 0v8a4 4 0 01-4 4H12a4 4 0 01-4-4v-4m32-4l-3.172-3.172a4 4 0 00-5.656 0L28 28M8 32l9.172-9.172a4 4 0 015.656 0L28 28m0 0l4 4m4-24h8m-4-4v8m-12 4h.02" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
                                    </svg>
                                    <div class="flex text-sm text-gray-600">
                                        <label for="file-upload" class="relative cursor-pointer bg-white rounded-md font-medium text-indigo-600 hover:text-indigo-500 focus-within:outline-none focus-within:ring-2 focus-within:ring-offset-2 focus-within:ring-indigo-500">
                                            <span>Upload a file</span>
                                            <input id="file-upload" name="file-upload" type="file" class="sr-only">
                                        </label>
                                        <p class="pl-1">or drag and drop</p>
                                    </div>
                                    <p class="text-xs text-gray-500">
                                        PNG, JPG, GIF up to 10MB
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="px-4 py-3 bg-gray-50 text-right sm:px-6">

                        <button type="submit" class="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                            Save
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="../common/foot.jspf"%>