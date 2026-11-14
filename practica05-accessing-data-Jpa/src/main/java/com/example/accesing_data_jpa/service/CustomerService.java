package com.example.accesing_data_jpa.service;


import com.example.accesing_data_jpa.dto.CustomerDto;
import com.example.accesing_data_jpa.repository.CustomerRepository;
import com.example.accesing_data_jpa.model.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

 @Autowired
 private CustomerRepository customerRepository;

 public List<CustomerDto> getAllCustomers(){

  List<Customer> lc = customerRepository.findAll();
  List<CustomerDto> cdto = lc.stream()
          .map(customer -> new CustomerDto(customer.getFirstName(), customer.getLastName()))
          .toList();
  return cdto;
 }

 //AÑADÍ UN MÉTODO NUEVO
 //En una frase: busca en la base de datos los clientes con un apellido concreto y los devuelve convertidos a DTO.

 public List<CustomerDto> getCustomersByLastName(String lastName) {
  return customerRepository.findByLastName(lastName).stream()
          .map(customer -> new CustomerDto(customer.getFirstName(), customer.getLastName()))
          .toList();
 }
}
