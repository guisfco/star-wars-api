package com.guisfco.starwars.controller;

import com.guisfco.starwars.domain.request.PlanetRequest;
import com.guisfco.starwars.entity.Planet;
import com.guisfco.starwars.service.DeletePlanetByIdService;
import com.guisfco.starwars.service.FindAllPlanetsService;
import com.guisfco.starwars.service.FindPlanetByIdService;
import com.guisfco.starwars.service.SavePlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planets")
public class PlanetController implements PlanetContract {

    private final DeletePlanetByIdService deletePlanetByIdService;

    private final FindAllPlanetsService findAllPlanetsService;

    private final FindPlanetByIdService findPlanetByIdService;

    private final SavePlanetService savePlanetService;

    @Override
    @DeleteMapping("/{planetId}")
    public ResponseEntity<Void> deleteById(@PathVariable("planetId") final String planetId) {
        deletePlanetByIdService.deleteById(planetId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Planet>> findAll() {
        return new ResponseEntity<>(findAllPlanetsService.findAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{planetId}")
    public ResponseEntity<Planet> findById(@PathVariable("planetId") final String planetId) {
        return new ResponseEntity<>(findPlanetByIdService.findById(planetId), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<Planet> save(@Valid @RequestBody final PlanetRequest request) {
        return new ResponseEntity<>(savePlanetService.save(request), HttpStatus.CREATED);
    }
}
