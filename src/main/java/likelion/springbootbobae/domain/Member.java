package likelion.springbootbobae.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;

    /*
    * @Embaddale 어노테이션이 붙은 내장 클래스 Address를 내장하기 위해
    * @Embedded 어노테이션을 활용한다.
    */
    @Embedded
    private Address address;

    /**
     *  Order Entity와 1:N 매핑 관계를 맺는다.
     *  N TO Many는 fetch 전략 defalut가 LAZE이다.
     *  Member Entity가 "N(다)" 역할으로, 관계의 주인이 된다.
     */
    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();

    // 객체 생성을 위한 createMember 메서드 정의
    public static Member createMember(String name, Address address)
    {
        Member member = new Member();
        member.name = name;
        member.address = address;
        return member;
    }

}