package com.aop.cosmeticsonlinestore.service;

import com.aop.cosmeticsonlinestore.model.Address;
import com.aop.cosmeticsonlinestore.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
