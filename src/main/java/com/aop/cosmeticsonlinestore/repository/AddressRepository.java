package com.aop.cosmeticsonlinestore.repository;

import com.aop.cosmeticsonlinestore.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Override
    <S extends Address> S save(S entity);
}
