package com.attraya.dto;

import com.attraya.annotation.CourseTypeValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {

    @NotBlank(message = "Course name shouldn't be NULL or EMPTY")
    private String name;
    @NotEmpty(message = "Trainer name should be defined")
    private String trainerName;
    @NotNull(message = "Duration must need to specify")
    private String duration;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Past(message = "Start date can't be before date from current")
    private Date startDate;
    @CourseTypeValidation
    private String courseType; // Live or Recording
    @Min(value = 1500, message = "Course price can't be less than Rs 1500")
    @Max(value = 5000, message = "Course price can't be more than Rs 5000")
    private double fees;
    private boolean isCertificateAvailable;
    @NotEmpty(message = "Description must be present")
    @Length(min = 5, max = 100)
    private String description;
    @Email(message = "Invalid email address")
    private String emailId;
    @Pattern(regexp = "^[0-9]{10}$")
    private String contact;
}
