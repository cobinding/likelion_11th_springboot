package likelion.springbootbobae.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Data
@Table(name = "orders")
public class Order
{
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    /*
    * */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    /*
     * 해당 entity와 Delivery entity의 1:1 연관관계를 매핑한다.
     * DB n+1문제를 방지하기 위해 fetch 전략은 반드시 lazy를 사용한다.
     * cascade = ALL을 통해 모든 상태 변화를 Delevery와 같이한다.
     */
    @OneToOne(fetch = LAZY,cascade = ALL)
    @JoinColumn(name = "delivery_id")//FK를 매핑할 때 사용하며 delibery_id, 즉 Delivery 엔티티의 PK를 기준으로 매핑할 것임을 명시한다.
    private Delivery delivery;

    /*
     * 해당 entity와 OrderItem entity의 1:N 연관관계를 매핑한다.
     * XToMany 의 경우 기본 fetch 전략이 lazy이므로 생략한다.
     * mappedBy 를 통해 OrderItem의 주인을 지정하여 양방향 연관관계를 설정한다.
     */
    @OneToMany(mappedBy = "order",cascade = ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

    private LocalDateTime orderDate;

    /*
     * enum class를 사용할 때 Type을 String으로 정한다.
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    /*
    * setter 함수에 OrderList를 추가하는 로직 정의.
    * 양방향 관계 설정을 위한
    * */
    public void setMember(Member member)
    {
        this.member = member;
        member.getOrderList().add(this);
    }

    // 객체 생성을 위한 createOrder 메서드 정의
    public static Order createOrder(Member member, OrderItem... orderItems)
    {
        Order order = new Order();
        order.setMember(member);
        order.orderDate = LocalDateTime.now();
        order.orderStatus = OrderStatus.ORDERED;
        order.delivery = Delivery.createDelivery(order, member.getAddress());
        for (OrderItem orderItem : orderItems)
        {
            order.orderItemList.add(orderItem);
            orderItem.setOrder(order);
        }
        return order;
    }


}