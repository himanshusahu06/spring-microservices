package org.hsahu.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiErrorResponse {
    private Date timestamp;
    private String message;
    private String details;
    private int status;
    private String reason;
}
