package com.guisfco.starwars.service;

import com.guisfco.starwars.domain.dto.PlanetDto;
import com.guisfco.starwars.exception.PlanetNotFoundException;
import com.guisfco.starwars.mapper.PlanetDtoMapper;
import com.guisfco.starwars.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindPlanetByIdService {

    private final PlanetRepository repository;

    public PlanetDto findById(final String planetId) {

        log.info("Finding planet by id: {}.", planetId);

        return repository.findById(planetId)
                .map(PlanetDtoMapper::apply)
                .orElseThrow(PlanetNotFoundException::new);
    }
}
