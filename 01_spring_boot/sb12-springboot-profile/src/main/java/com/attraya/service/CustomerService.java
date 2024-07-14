package com.attraya.service;

import com.attraya.dto.CustomerDto;
import com.attraya.entity.Customer;
import com.attraya.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Profile(value = {"dev", "stg", "prod"})
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Value("${application.message}")
    private String message;

    @PostConstruct
    public void init(){
        System.out.println("::::::::::::: ::::::::::: ::::::: :::: ::: :: > "+message+" <");
    }

    public List<Customer> addNewCustomers(List<Customer> customers){
        return customerRepository.saveAll(customers);
    }

    public List<CustomerDto> getCustomers(){
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream().map(customer -> new CustomerDto(customer.getId(), customer.getName(), customer.getEmail(),
                        customer.getPhone(), getDateFormat(customer.getDob())))
                .collect(Collectors.toList());
    }

    private String getDateFormat(Date date){
        return new SimpleDateFormat("MM/dd/yyyy").format(date);
    }
}
