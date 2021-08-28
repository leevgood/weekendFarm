package leevgood.weekend_farm.config;

import lombok.Builder;
import lombok.Data;

@Data
public class Message {

    private StatusEnum status;
    private String message;
    private Object data;


    @Builder
    private Message(StatusEnum status, String message, Object data){
        this.status = StatusEnum.BAD_REQUEST;
        this.message = null;
        this.data = null;
    }

    public static Message failMessage(String message, StatusEnum status){
        return Message.builder()
                .status(status)
                .message(message)
                .data(null)
                .build();
    }

    public static Message okMessage(Object data){
        return Message.builder()
                .status(StatusEnum.OK)
                .message("ok")
                .data(data)
                .build();
    }
}
