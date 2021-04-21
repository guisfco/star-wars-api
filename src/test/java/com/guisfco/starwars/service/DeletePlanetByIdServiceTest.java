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

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class DeletePlanetByIdServiceTest {

    @InjectMocks
    private DeletePlanetByIdService service;

    @Mock
    private PlanetRepository repository;

    @Mock
    private FindPlanetByIdService findPlanetByIdService;

    @Test
    @DisplayName("Must delete an existing planet by its id")
    public void deleteById() {

        final String planetId = randomAlphanumeric(5);
        final Planet foundPlanet = PlanetFixture.get().random().build();

        when(findPlanetByIdService.findById(planetId)).thenReturn(foundPlanet);

        service.deleteById(planetId);

        verify(findPlanetByIdService).findById(planetId);
        verify(repository).delete(foundPlanet);
    }

    @Test
    @DisplayName("Must throw an error when trying to delete a non existing planet")
    public void deleteByNotExistingId() {

        final String planetId = randomAlphanumeric(5);

        when(findPlanetByIdService.findById(planetId)).thenThrow(PlanetNotFoundException.class);

        assertThrows(PlanetNotFoundException.class, () -> service.deleteById(planetId));

        verify(findPlanetByIdService).findById(planetId);
        verifyNoInteractions(repository);
    }
}