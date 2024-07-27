package com.attraya.controller;

import com.attraya.dto.PatientAppointmentRequest;
import com.attraya.service.PractoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/practo")
@AllArgsConstructor
public class PractoController {

    private PractoService practoService;

    @PostMapping("/book")
    public String bookDoctorsAppointment(@RequestBody PatientAppointmentRequest request){
        return practoService.bookAppointment(request);
    }
}
