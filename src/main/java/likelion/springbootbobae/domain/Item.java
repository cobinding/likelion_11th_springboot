package likelion.springbootbobae.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item {
    @Id @GeneratedValue
    private Long id;

    private String brand;
    private String name;
    private Integer price;
    private Integer stock;

    /*
     * @Commnet: sql 컬럼 코멘트 추가 어노테이션
     * */
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
