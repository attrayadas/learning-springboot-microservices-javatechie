package com.attraya.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "CUSTOMERS")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String email;
    private String phone;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dob;
}
