package com.guisfco.starwars.repository;

import com.guisfco.starwars.entity.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {
}
