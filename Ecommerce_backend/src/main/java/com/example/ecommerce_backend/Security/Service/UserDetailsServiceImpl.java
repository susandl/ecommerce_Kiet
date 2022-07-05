package com.example.ecommerce_backend.Security.Service;

import com.example.ecommerce_backend.Data.Entity.Customer;
import com.example.ecommerce_backend.Data.Repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    CustomerRepository customerRepository;

    @Autowired
    public UserDetailsServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (customerRepository.existsByName(username)) {
            throw new UsernameNotFoundException("User name " + username + " not found");
        }
        Customer customer = customerRepository.findByName(username);
        return UserDetailsImpl.build(customer);
    }

}
