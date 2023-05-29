package likelion.springbootbobae.domain;

import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity

@Getter
public class OrderItem
{
    /*
     * ID annotation이 붙은 값은 entity의 PK로, 해당 Entity의 식별자이다.
     * 보통 db에서 auto increse로 설정하는 것을 @GenerateValue가 담당한다.
     */
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    /*
    * Item 필드와의 관계를 매핑해주기 위해 ManyToOne annotation을 지정한다.
    * 또한 order_id에 대한 외래키를 @JoinColumn을 통해 지정해준다.
    */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;


    /*
     * Order 필드와의 관계를 매핑해주기 위해 ManyToOne annotation을 지정한다.
     * 또한 item_id에 대한 외래키를 @JoinColumn을 통해 지정해준다.
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer price;
    private Integer count;

    // setter 메소드
    public void setOrder(Order order)
    {
        this.order = order;
    }
}