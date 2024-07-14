package com.attraya.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@AllArgsConstructor
@Getter
@Setter
public class CustomerDto implements Serializable {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String dob; //dd/MM/yyyy
}
