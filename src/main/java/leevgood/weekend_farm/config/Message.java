package leevgood.weekend_farm.config;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Message {

    private StatusEnum status;
    private String message;
    private Object data;

    public Message(){
        this.status = StatusEnum.BAD_REQUEST;
        this.message = null;
        this.data = null;
    }

}
