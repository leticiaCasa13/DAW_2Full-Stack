package com.example.accesing_data_jpa.controller.rest;


import com.example.accesing_data_jpa.dto.CustomerDto;
import com.example.accesing_data_jpa.model.Customer;
import com.example.accesing_data_jpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping(CustomerResource.CUSTOMERS)
public class CustomerResource {
    public static final String CUSTOMERS = "/customers";

    @Autowired
    CustomerService  customerService;

@GetMapping

public ResponseEntity<List<CustomerDto>> getCustomers() {
    List<CustomerDto> lc = customerService.getAllCustomers();
    return ResponseEntity.ok(lc);
   }

//AÑADIMOS ENDPOINT CON GETMAPPING
//En una frase: atiende la petición GET /customers/lastname/Bauer,
// le pide los datos al service y responde con la lista en JSON y código 200.

//Definie q petición atiende este método. Las llaves {lastName} indican que esa parte de la URL es variable.

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<CustomerDto>> getCustomersByLastName(@PathVariable String lastName) {  //PathVariable toma el valor
                                                                                           //que va en esa Url y lo guarda en lastName
        List<CustomerDto> lc = customerService.getCustomersByLastName(lastName);
        return ResponseEntity.ok(lc);
    }
}
