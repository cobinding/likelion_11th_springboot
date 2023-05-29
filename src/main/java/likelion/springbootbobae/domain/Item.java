package likelion.springbootbobae.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item {
    /* ID annotation이 붙은 값은 entity의 PK로, 해당 Entity의 식별자이다.
    * 보통 db에서 auto increse로 설정하는 것을 @GenerateValue가 담당한다.
    */
    @Id @GeneratedValue
    private Long id;

    /*
    *  OrderItem Entity와 1:N 매핑 관계를 맺는다.
    *  N TO Many는 fetch 전략 defalut가 LAZE이다.
    *  Item Entity가 "N(다)" 역할으로, 관계의 주인이 된다.
    */
    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    private String brand;
    private String name;
    private Integer price;
    private Integer stock;

    /*
     * Domain에 직접 비즈니스 로직을 작성한다.
     * 필드값 변경에 대한 로직은 도메인에 직접 구현하여 정확도를 높인다.
     */
    @Comment("재고 추가")
    public void addStock(int quantity) {
        this.stock += quantity;
    }

    @Comment("재고 감소")
    public void remoteStock(int stockQuantity) {
        int restStock = this.stock - stockQuantity;
        if (restStock < 0) {
            throw new IllegalStateException("남은 수량과 삭제 수량을 확인해주세요.");
        }
        this.stock = restStock;
    }


}
