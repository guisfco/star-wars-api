package com.guisfco.starwars.service;

import com.guisfco.starwars.domain.swapi.response.PlanetDetail;
import com.guisfco.starwars.domain.swapi.response.PlanetListResponse;
import com.guisfco.starwars.fixture.PlanetDetailFixture;
import com.guisfco.starwars.fixture.PlanetListResponseFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PlanetDetailServiceTest {

    @InjectMocks
    private PlanetDetailService service;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(service, "getPlanetsUrl", "https://swapi.dev/api/planets/");
    }

    @Test
    @DisplayName("Must find the planet detail by name")
    public void getPlanetDetail() {

        final PlanetDetail expectedPlanetDetail = PlanetDetailFixture.get().random().build();

        final PlanetListResponse planetListResponse = PlanetListResponseFixture.get().random()
                .results(Collections.singletonList(expectedPlanetDetail))
                .build();

        when(restTemplate.getForObject(any(URI.class), eq(PlanetListResponse.class))).thenReturn(planetListResponse);

        final PlanetDetail response = service.getPlanetDetail(expectedPlanetDetail.getName());

        assertNotNull(response);
        assertEquals(expectedPlanetDetail.getName(), response.getName());
    }

    @Test
    @DisplayName("Must return null when planet detail not found")
    public void getNotFoundPlanetDetail() {

        when(restTemplate.getForObject(any(URI.class), eq(PlanetListResponse.class))).thenReturn(new PlanetListResponse());

        final PlanetDetail response = service.getPlanetDetail(randomAlphabetic(5));

        assertNull(response);
    }
}