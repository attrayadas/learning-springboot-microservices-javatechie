package com.attraya.service;

import com.attraya.dto.PatientAppointmentRequest;
import com.attraya.entity.Appointment;
import com.attraya.entity.Patient;
import com.attraya.repository.AppointmentRepository;
import com.attraya.repository.PatientRepository;
import com.attraya.util.PromoCodeValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PractoService {

    private PatientRepository patientRepository;
    private AppointmentRepository appointmentRepository;

    @Transactional
    public String bookAppointment(PatientAppointmentRequest request) {
        // Save patient
        Patient patient = request.getPatient();
        long patientId = patientRepository.save(patient).getPatientId();

        // Save appointment
        Appointment appointment = request.getAppointment();

        // validate user promo code
        if (appointment.getPromoCode() != null) {
            PromoCodeValidator.validatePromoCode(appointment.getPromoCode());
        }

        appointment.setPatientId(patientId);
        Long appointmentId = appointmentRepository.save(appointment).getAppointmentId();
        return "Hi " + patient.getName() + "! Your appointment is booked successfully & appointment number is " + appointmentId;

    }
}
