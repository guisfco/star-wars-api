package com.guisfco.starwars.service;

import com.guisfco.starwars.domain.dto.PlanetDto;
import com.guisfco.starwars.domain.request.PlanetRequest;
import com.guisfco.starwars.entity.Planet;
import com.guisfco.starwars.mapper.PlanetDtoMapper;
import com.guisfco.starwars.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SavePlanetService {

    private final PlanetRepository repository;

    public PlanetDto save(final PlanetRequest request) {

        log.info("Inserting planet: {}.", request);

        final Planet planet = Planet.builder()
                .climate(request.getClimate())
                .name(request.getName())
                .terrain(request.getTerrain())
                .build();

        return PlanetDtoMapper.apply(repository.save(planet));
    }
}
