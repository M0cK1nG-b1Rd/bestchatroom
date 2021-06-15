package com.bestchatroom.message;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString(callSuper = true)
public abstract class AbstractResponseMessage extends Message {
    private boolean success;
    private String reason;
    private Date currentDate;

    public AbstractResponseMessage() {
    }

    public AbstractResponseMessage(boolean success, String reason) {
        this.success = success;
        this.reason = reason;
        this.currentDate = new Date();
    }
}
