package com.guisfco.starwars.controller;

import com.guisfco.starwars.domain.response.ContentPageResponse;
import com.guisfco.starwars.domain.dto.PlanetDto;
import com.guisfco.starwars.domain.request.PlanetRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlanetContract {

    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "The planet was deleted"),
            @ApiResponse(code = 404, message = "The planet was not found and could not be deleted")
    })
    @ApiOperation("Deletes the planet that matches with the provided id")
    ResponseEntity<Void> deleteById(String planetId);

    @ApiResponses(value = {@ApiResponse(code = 200, message = "List of planets returned successfully")})
    @ApiOperation("Returns all the planets in a single list")
    ResponseEntity<List<PlanetDto>> findAll();

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The planet was found and returned"),
            @ApiResponse(code = 404, message = "The planet was not found and could not be returned")
    })
    @ApiOperation("Returns any planet that matches with the provided id")
    ResponseEntity<PlanetDto> findById(String planetId);

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The planet was created successfully"),
            @ApiResponse(code = 400, message = "A required information was not provided"),
            @ApiResponse(code = 412, message = "There was an error while getting planet details")
    })
    @ApiOperation("Inserts a new planet and returns it")
    ResponseEntity<PlanetDto> save(PlanetRequest request);

    @ApiResponses(value = {@ApiResponse(code = 200, message = "Paginated list of planets returned successfully")})
    @ApiOperation("Search filtering by text (name, climate and terrain included in this filter) and returns a paginated list of planets")
    ResponseEntity<ContentPageResponse<PlanetDto>> searchByText(String text, int page, int size);
}
