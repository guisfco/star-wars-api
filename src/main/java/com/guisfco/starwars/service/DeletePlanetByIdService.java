package com.guisfco.starwars.service;

import com.guisfco.starwars.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeletePlanetByIdService {

    private final PlanetRepository repository;

    private final FindPlanetByIdService findPlanetByIdService;

    public void deleteById(final String planetId) {

        log.info("Deleting planet by id: {}.", planetId);

        repository.delete(findPlanetByIdService.findById(planetId));
    }
}
