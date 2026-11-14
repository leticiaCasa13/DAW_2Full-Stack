package com.example.accesing_data_jpa.repository;

import java.util.List;

import com.example.accesing_data_jpa.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
//    List<Customer> findByFirstName(String firstName);
//    List<Customer> findByLastNameAndFirstName(String lastName, String firstName);
    Customer findById(long id);
    //Customer save(Customer customer);
}