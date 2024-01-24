package com.demo.domian;

import common.library.constants.app.FwConstants;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApiResponse<T>  {

    private T payload;

    private String status;

    private String message;

    private Long token;

    private List<Error> errors;

    public static long getUniqueToken() {
        Calendar calendar = Calendar.getInstance();
        long tokenValue = calendar.getTimeInMillis();
        int randomValue = new Random().nextInt(100000);
        return (tokenValue + randomValue);
    }

    public void serverError() {
        setToken(getUniqueToken());
        setStatus(Response.Status.INTERNAL_SERVER_ERROR.name());
        setMessage(FwConstants.SERVER_ERROR + getToken());
    }

    public void applicationError(String pErrorMessage) {
        setStatus(Response.Status.INTERNAL_SERVER_ERROR.name());
        if (StringUtils.isNotBlank(pErrorMessage)) {
            setMessage(pErrorMessage);
        } else {
            setToken(getUniqueToken());
            setMessage(FwConstants.SERVER_ERROR + getToken());
        }
    }

}
