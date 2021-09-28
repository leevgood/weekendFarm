package leevgood.weekend_farm.config;

import lombok.Data;

@Data
public class Message {

    private StatusEnum status;
    private String message;
    private Object data;


    private Message(StatusEnum status, String message, Object data){
        this.status = StatusEnum.BAD_REQUEST;
        this.message = null;
        this.data = null;
    }

    public static Message failMessage(String message, StatusEnum status){
        return new Message(status, message, null);
    }

    public static Message okMessage(Object data){
        return new Message(StatusEnum.OK, "ok", data);
    }
}
