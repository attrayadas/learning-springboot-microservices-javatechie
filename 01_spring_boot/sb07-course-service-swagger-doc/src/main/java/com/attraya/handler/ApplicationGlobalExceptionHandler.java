package com.attraya.handler;

import com.attraya.dto.ErrorDTO;
import com.attraya.dto.ServiceResponse;
import com.attraya.exception.CourseServiceBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationGlobalExceptionHandler {

    // MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ServiceResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        ServiceResponse<?> serviceResponse = new ServiceResponse<>();
        List<ErrorDTO> errorDTOList = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> errorDTOList.add(new ErrorDTO(error.getField()+" : "+error.getDefaultMessage())));
        serviceResponse.setStatus(HttpStatus.BAD_REQUEST);
        serviceResponse.setError(errorDTOList);
        return serviceResponse;
    }

    @ExceptionHandler(CourseServiceBusinessException.class)
    public ServiceResponse<?> handleCourseServiceBusinessException(CourseServiceBusinessException exception) {
        ServiceResponse<?> serviceResponse = new ServiceResponse<>();
        List<ErrorDTO> errorDTOList = new ArrayList<>();
        errorDTOList.add(new ErrorDTO(exception.getMessage()));
        serviceResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        serviceResponse.setError(errorDTOList);
        return serviceResponse;
    }
}
