package com.guisfco.starwars.service;

import com.guisfco.starwars.entity.Planet;
import com.guisfco.starwars.exception.PlanetNotFoundException;
import com.guisfco.starwars.fixture.PlanetFixture;
import com.guisfco.starwars.repository.PlanetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FindPlanetByIdServiceTest {

    @InjectMocks
    private FindPlanetByIdService service;

    @Mock
    private PlanetRepository repository;

    @Test
    @DisplayName("Must find the planet by id and return the entity")
    public void findById() {

        final String planetId = randomAlphanumeric(5);

        when(repository.findById(planetId)).thenReturn(Optional.of(PlanetFixture.get().random().build()));

        final Planet response = service.findById(planetId);

        assertNotNull(response);
        verify(repository).findById(planetId);
    }

    @Test
    @DisplayName("Must throw an error when planet id not found")
    public void findByIdNotPresent() {

        final String planetId = randomAlphanumeric(5);

        when(repository.findById(planetId)).thenReturn(Optional.empty());

        assertThrows(PlanetNotFoundException.class, () -> service.findById(planetId));

        verify(repository).findById(planetId);
    }

}