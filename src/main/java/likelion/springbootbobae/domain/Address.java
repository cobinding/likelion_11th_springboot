package likelion.springbootbobae.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
/*
* @Embeddable: 다른 엔티티에 포함되어야 하는 내장 엔티티를 의미.
*
* @Data: @Getter, @Setter, @RequiredArgsConstructor 등의 annotation을 한꺼번에 설정하는 기능.
*       모든 필드를 대상으로 접근자와 생성자가 자동으로 생성되고, find 또는 @Nonull 필드 값을 파라미터로 받는 생성자가 만들어진다.
*       또한 toString, equals, hashCode 메소드도 만들어진다.
*
* @AllArgsConstructor: 인스턴스 변수로 선언된 모든 것을 파라미터로 받는 생성자 생성.
*
* @NoArgsConstructor: 파라미터가 없는 기본 생성자 생성.
*
*  */

public class Address {
    private String city;
    private String state;
    private String street;
    private String zipcode;
}
