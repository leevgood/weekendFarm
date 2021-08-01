package leevgood.weekend_farm.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    Long id;

    String login_id;
    String password;

    String member_name;
    String email;
    String tel_number;

    LocalDateTime create_date;
    LocalDateTime update_date;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Orders orders;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Cart cart;



}
