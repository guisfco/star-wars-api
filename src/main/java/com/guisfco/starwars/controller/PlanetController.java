package com.guisfco.starwars.controller;

import com.guisfco.starwars.domain.dto.PlanetDto;
import com.guisfco.starwars.domain.request.PlanetRequest;
import com.guisfco.starwars.domain.response.ContentPageResponse;
import com.guisfco.starwars.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planets")
public class PlanetController implements PlanetContract {

    private final DeletePlanetByIdService deletePlanetByIdService;

    private final FindAllPlanetsService findAllPlanetsService;

    private final FindPlanetByIdService findPlanetByIdService;

    private final SavePlanetService savePlanetService;

    private final SearchPlanetsByTextService searchPlanetsByTextService;

    @Override
    @DeleteMapping("/{planetId}")
    public ResponseEntity<Void> deleteById(@PathVariable("planetId") final String planetId) {
        deletePlanetByIdService.deleteById(planetId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<PlanetDto>> findAll() {
        return new ResponseEntity<>(findAllPlanetsService.findAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{planetId}")
    public ResponseEntity<PlanetDto> findById(@PathVariable("planetId") final String planetId) {
        return new ResponseEntity<>(findPlanetByIdService.findById(planetId), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<PlanetDto> save(@RequestBody final PlanetRequest request) {
        return new ResponseEntity<>(savePlanetService.save(request), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<ContentPageResponse<PlanetDto>> searchByText(@RequestParam(required = false) final String text,
                                                                       @RequestParam(defaultValue = "1", required = false) final int page,
                                                                       @RequestParam(defaultValue = "16", required = false) final int size) {

        return new ResponseEntity<>(searchPlanetsByTextService.searchByText(text, page, size), HttpStatus.OK);
    }
}
