package likelion.springbootbobae.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;


import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private Address address;

    public static Member creatMember(String name, Address address) {
        Member member = new Member();
        member.name = name;
        member.address = address;
        return member;
    }
}
