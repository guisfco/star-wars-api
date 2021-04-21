package com.guisfco.starwars.controller;

import com.guisfco.starwars.domain.dto.PlanetDto;
import com.guisfco.starwars.domain.request.PlanetRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlanetContract {

    @ApiOperation("Deletes the planet that matches with the provided id")
    ResponseEntity<Void> deleteById(String planetId);

    @ApiOperation("Returns all the planets")
    ResponseEntity<List<PlanetDto>> findAll();

    @ApiOperation("Returns any planet that matches with the provided id")
    ResponseEntity<PlanetDto> findById(String planetId);

    @ApiOperation("Inserts a new planet and returns it")
    ResponseEntity<PlanetDto> save(PlanetRequest request);
}
