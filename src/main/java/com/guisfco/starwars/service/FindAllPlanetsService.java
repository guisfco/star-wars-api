package com.guisfco.starwars.service;

import com.guisfco.starwars.domain.dto.PlanetDto;
import com.guisfco.starwars.mapper.PlanetDtoMapper;
import com.guisfco.starwars.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindAllPlanetsService {

    private final PlanetRepository repository;

    public List<PlanetDto> findAll() {

        log.info("Finding all planets.");

        return repository.findAll().stream()
                .map(PlanetDtoMapper::apply)
                .collect(Collectors.toList());
    }
}
