package com.yejin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
        System.out.println(param+" : "+value);
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
}
