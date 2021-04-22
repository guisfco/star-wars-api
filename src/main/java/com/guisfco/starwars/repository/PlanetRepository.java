package com.guisfco.starwars.repository;

import com.guisfco.starwars.entity.Planet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {

    @Query("{ $or: [{'name': {$regex: /.*?0.*/, $options: 'i'}}, {'terrain': {$regex: /.*?0.*/, $options: 'i'}}, {'climate': {$regex: /.*?0.*/, $options: 'i'}}] }")
    Page<Planet> searchByText(@Param("text") String text, Pageable pageable);

    Page<Planet> findAll(Pageable pageable);
}
