package com.task.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
public class Response {
    public final int status;
    public final String message;
    public Object data;

    public Response(Object data){
        this.status = 200;
        this.message = "Oh, hi, Mark!";
        this.data = data;
    }
}
