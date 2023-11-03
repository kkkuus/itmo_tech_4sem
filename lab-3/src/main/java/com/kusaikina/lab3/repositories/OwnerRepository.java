package com.kusaikina.lab3.repositories;

import com.kusaikina.lab3.entities.Cat;
import com.kusaikina.lab3.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner save(Owner owner);
    void deleteById(long id);
    void deleteAll();
    Owner getById(long id);
    List<Owner> findAll();

    @Query(value = "SELECT DISTINCT * FROM cats WHERE OwnerId = :ownerId", nativeQuery = true)
    List<Cat> getAllCatsByOwnerId(long ownerId);


    List<Owner> getAllByName(String name);

}
