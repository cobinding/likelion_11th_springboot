package likelion.springbootbobae.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static likelion.springbootbobae.domain.DeliveryStatus.ESTABLISHED;

@Entity
/*
* 아무런 매개 변수가 없는 생성자를 생성하고, 생성자 접근 level에 설정값을 주는 것이다.
* public, protected, private 등 접근 제한을 사용할 수 있다.
*/
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery
{
    /*
     * ID annotation이 붙은 값은 entity의 PK로, 해당 Entity의 식별자이다.
     * 보통 db에서 auto increse로 설정하는 것을 @GenerateValue가 담당한다.
     */
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    /*
    * enum class를 사용할 때 Type을 String으로 정한다.
    */
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Embedded
    private Address address;

    /*
    * Delivery와 1:1 매핑을 설정한다. 이때 관계의 소유자는 선언을 한 쪽인 Order Entity이다.
    * fetch 속성을 LAZY로 설정하여 지연 로딩 방식을 선언함으로써 필요한 시점에만 로드된다.
    */
    @OneToOne(fetch = LAZY,mappedBy = "delivery")
    public Order order;

    // 메서드를 통해 객체 생성.
    public static Delivery createDelivery(Order order, Address address)
    {
        Delivery delivery = new Delivery();
        delivery.order = order;
        delivery.deliveryStatus = ESTABLISHED;
        delivery.address = address;
        return delivery;
    }
}