package leevgood.weekend_farm.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue
    @Column(name = "orders_id")
    private Long id;

    //TODO 1:N 으로 바꿔
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order_item> order_items = new ArrayList<>();

    private int total_price;

    public void orders(Member member, List<Order_item> orderItems){
        this.member = member;
        this.order_items.addAll(orderItems);
        this.total_price = this.order_items.stream()
                .mapToInt(v -> v.getPrice())
                .sum();
    }
}
