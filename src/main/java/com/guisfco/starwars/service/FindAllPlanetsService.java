package com.guisfco.starwars.service;

import com.guisfco.starwars.entity.Planet;
import com.guisfco.starwars.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindAllPlanetsService {

    private final PlanetRepository repository;

    public List<Planet> findAll() {

        log.info("Finding all planets.");

        return repository.findAll();
    }
}
