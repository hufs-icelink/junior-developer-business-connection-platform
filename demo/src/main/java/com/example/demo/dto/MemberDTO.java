package com.example.demo.dto;

import com.example.demo.model.RoleType;
import com.example.demo.model.SexType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter             //생선된 모든 필드에 대한 getter를 만들어줌
@Setter             //생선된 모든 필드에 대한 setter를 만들어줌
@NoArgsConstructor  //기본생성자를 자동적으로 만들어줌
@ToString           //필드를 출력할때 쓰이는 tostring을 자동으로 만들어줌
public class MemberDTO {
    //private로 생성된 필드를 사용하기위해서 getter,setter로 간접적으로 사용해야한다
    private String id;
    private String user_pw; //받을 데이터의 변수이름이랑 동일해야함
    private String name;
    private Integer age;
    private String address;
    private SexType sex;
    private RoleType role;
    private Integer pay;
    private String skill_1;
    private String skill_2;
    private String skill_3;
    private Integer univer_grade;
    private String job;
    private String mail;
    private String short_res;
}