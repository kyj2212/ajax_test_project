package com.yejin;


import lombok.AllArgsConstructor;
import lombok.Data;


// getter, setter가 필요할까? 일단 강사님은 Data를 추가하셨음. 왜?

// noargsconstructor가 필요할까?
@Data
@AllArgsConstructor
public class ResultData<T> { // 제네릭으로 하자! 불필요한 형변환을 줄일 수 있다!!
    // article에 한정되는 것이 아닌 현재 프로젝트에 범용적으로 사용될 예정

    private String resultCode;
    private String msg;
    //private final String msg;
    // msg 를 final 로 둔 이유? --> 모르겠음


    private T data;
}
