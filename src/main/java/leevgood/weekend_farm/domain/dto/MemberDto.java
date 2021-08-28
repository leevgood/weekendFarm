package leevgood.weekend_farm.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class MemberDto {
    Long id;

    String login_id;
    String password;

    String member_name;
    String email;
    String tel_number;

    LocalDateTime create_date;
    LocalDateTime update_date;

    @Builder
    public MemberDto(){

    }
}
