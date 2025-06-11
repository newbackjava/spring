package org.scoula.ex03.dto;

import lombok.Data;

@Data
public class SampleDTO {

    private String name;
    private int age;

    //public메서드인 setters/getters메서드를 통해서 가방에 데이터를 넣거나 꺼낸다.
    //가방에 넣을 때는 setName(), setAge()를 사용
    //가방에서 꺼낼 때는 getName(), getAge()를 사용
    //핸들러어댑터가 이 가방에 http로 전달된 데이터를 넣어주는데
    //set메서드를 자동으로 불러서 넣어줌.
    //set메서드가 없으면 가방에 못넣음. 에러발생!!
}
