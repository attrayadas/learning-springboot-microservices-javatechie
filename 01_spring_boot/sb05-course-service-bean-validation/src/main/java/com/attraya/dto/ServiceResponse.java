package com.attraya.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse<T> {
    private HttpStatus status;
    private T response;
    private List<ErrorDTO> error;

    public ServiceResponse(HttpStatus status, T response) {
        this.status = status;
        this.response = response;
    }
}
