package com.example.demo.Bootstrap;

import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.dao.ExcursionRepository;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BootstrapData implements CommandLineRunner {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final CartItemRepository cartItemRepository;

    @Autowired
    private final ExcursionRepository excursionRepository;

    @Autowired
    private final DivisionRepository divisionRepository;

    public BootstrapData(CustomerRepository customerRepository, CartItemRepository cartItemRepository, ExcursionRepository excursionRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.cartItemRepository = cartItemRepository;
        this.excursionRepository = excursionRepository;
        this.divisionRepository = divisionRepository;
    }


    @Override
    public void run(String... args) throws Exception {


        Set<Customer> customersToAdd = new HashSet<>(customerRepository.findAll());
        List<String> firstNames = List.of("Andrew", "Abbey", "Navy", "Belle", "Poppy", "Wendee");

        while (customersToAdd.size() < 6) {
            String firstName = firstNames.get(customersToAdd.size());
            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName("Doe");
            customer.setPhone("1234567890");
            customer.setAddress("123 Street Road");
            customer.setPostal_code("12345");
            Set<Division> divisions = new HashSet<>(divisionRepository.findAll());
            if (!divisions.isEmpty()) {
                customer.setDivision(divisions.iterator().next());
            }
            customersToAdd.add(customer);
            customerRepository.save(customer);
        }


    }

}