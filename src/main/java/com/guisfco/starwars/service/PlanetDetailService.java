package com.guisfco.starwars.service;

import com.guisfco.starwars.domain.swapi.PlanetDetail;
import com.guisfco.starwars.domain.swapi.response.PlanetListResponse;
import com.guisfco.starwars.exception.PlanetDetailingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.util.Objects.isNull;
import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanetDetailService {

    @Value("${access.getPlanets}")
    private String getPlanetsUrl;

    private final RestTemplate restTemplate;

    public PlanetDetail getPlanetDetail(final String planetName) {

        log.info("Searching planet details by name: {}.", planetName);

        final URI uri = UriComponentsBuilder.fromHttpUrl(getPlanetsUrl)
                .queryParam("search", planetName)
                .build()
                .toUri();

        PlanetListResponse response;

        try {
            response = restTemplate.getForObject(uri, PlanetListResponse.class);
        } catch (Exception e) {
            log.error("Could not get planet details.", e);
            throw new PlanetDetailingException();
        }

        if (isNull(response) || isEmpty(response.getResults())) {
            return null;
        }

        return response.getResults().stream()
                .filter(planet -> planetName.equalsIgnoreCase(planet.getName()))
                .findFirst()
                .orElse(null);
    }
}
