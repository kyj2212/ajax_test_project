package com.yejin;

import com.yejin.article.dto.ArticleDto;
import com.yejin.util.Ut;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class Rq {

    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    public Rq(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;

        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
    }


    public int getIntParam(String param, int defaultValue) {
        String value = req.getParameter(param);

        if (value == null)
            return defaultValue;
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public void appendBody(String str) {
        try {
            resp.getWriter().append(str);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void setAttr(String name, Object value){
        req.setAttribute(name,value);
    }

    public void view(String path){
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/"+path+".jsp");
        try {
            requestDispatcher.forward(req,resp);
        } catch (ServletException e) {
            System.out.println("/jsp/"+path+".jsp 없음");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getMethod(){
        return req.getMethod();
    }

    public String getPath() {
        return req.getRequestURI();
    }

    public String getParam(String param,String defaultValue) {
        String value = req.getParameter(param);

        if (value == null)
            return defaultValue;
        try {
            return value;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public long getLongParam(String param, long defaultValue){

        String value = req.getParameter(param);
       // System.out.println(param+" : "+value);
        if(value==null)
            return defaultValue;
        try{
           // System.out.println(param+" : "+value);
            return Long.parseLong(value);
        } catch (NumberFormatException e){
            return defaultValue;
        }
    }


    public String getActionPath(){
        //String path = req.getRequestURI();
        String[] bits = req.getRequestURI().split("/");
        // / 기준 세가지 단계만 보면 됨. 그 이후는 action이 아님
        return "/%s/%s/%s".formatted(bits[1], bits[2], bits[3]);
    }

    public long getLongPathValueByIndex(int idx, long defaultValue){
        String value= getPathValueByIndex(idx,null);
        if(value==null){
            return defaultValue;
        }
        try {
            return Long.parseLong(value);
        }catch(NumberFormatException e){
            System.out.println("NumberFormatException");
            return defaultValue;
        }
    }

    public String getPathValueByIndex(int idx, String defaultValue){
        String[] bits= req.getRequestURI().split("/");
        try {
            // bits[0] = "" //  첫번째 / 을 기준으로 왼쪽값이 없으므로 bits[0] 은 null
            System.out.println("bits[0]"+bits[0]); // URI = /usr/article/(list/write/detail)/free/1/2/3/4/5
            System.out.println("bits[4]"+bits[4]);
            System.out.println("bits[4+idx]"+bits[4+idx]);
            return bits[4+idx]; // [1]usr + [2]article + [3](list/write/detail) + [4]free + [4+idx]여기에 오는 path value
        }catch(ArrayIndexOutOfBoundsException e){
            return defaultValue;
        }
    }



    public void replace(String uri, String msg) {
        if (msg != null && msg.trim().length() > 0) {
            println("""
                    <script>
                    alert("%s");
                    </script>
                    """.formatted(msg));
        }

        println("""
                <script>
                location.replace("%s");
                </script>
                """.formatted(uri));
    }


    public void println(String str) {
        print(str+"\n");
    }

    public void print(String str) {
        try{
            resp.getWriter().append(str);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void historyBack(String msg) {
        if (msg != null && msg.trim().length() > 0) {
            println("""
                    <script>
                    alert("%s");
                    </script>
                    """.formatted(msg));
        }

        println("""
                <script>
                history.back();
                </script>
                """);
    }

    public void setContentType(String str){
        resp.setContentType(str);
    }

    public void json(Object resultData){
        // response 응답의 contentType 을 json 으로 바꾸고
        setContentType("application/json; charset=utf-8");

        // date를 json으로 바꾼다.
       // return Ut.json.toStr(data,"");
        String jsonStr = Ut.json.toStr(resultData,"");
        // println 하는것까지 json에 넣어버리기
        println(jsonStr);

    }

    // 결과 코드와 msg 를 매개변수로 아예 받아버리는 경우도 있고
    public void json(String resultCode, String msg,Object resultData){
        json(new ResultData<>(resultCode,msg,resultData));
    }

    // 성공여부만 확인하고 싶을 때도 있고, 성공여부만 확인하는 케이스가 많기때문에 따로 method를 뺀다.
    public void successJson(Object resultData) {
        successJson("S-1","성공",resultData);
    }

    // 성공 여부만 확인하는데, S-2,3,4 이렇게 여러개 잇을 수 있으니까
    public void successJson(String resultCode, String msg,Object resultData) {
        try{
            json(new ResultData<>(resultCode,msg,resultData));

        } catch (RuntimeException e){ // 만약 처리하다가 에러가 난다면? fail로 간다고 생각했는데... 강사님은 예외처리 하지 않았음
            failJson(resultData);
        }
    }


    // 실패 여부만 확인하고 싶을 때도 있으니까
    public void failJson(Object resultData) {
        failJson(new ResultData<>("F-1","실패",resultData));
    }
    public void failJson(String resultCode, String msg,Object resultData) {
        json(new ResultData<>(resultCode,msg,resultData));
    }
}
