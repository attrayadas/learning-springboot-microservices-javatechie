package com.attraya.dto;

import com.attraya.entity.Appointment;
import com.attraya.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientAppointmentRequest {
    private Patient patient;
    private Appointment appointment;
}
