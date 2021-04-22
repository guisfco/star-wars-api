package com.guisfco.starwars.service;

import com.guisfco.starwars.domain.dto.PlanetDto;
import com.guisfco.starwars.domain.request.PlanetRequest;
import com.guisfco.starwars.entity.Planet;
import com.guisfco.starwars.exception.BadRequestException;
import com.guisfco.starwars.fixture.PlanetFixture;
import com.guisfco.starwars.fixture.PlanetRequestFixture;
import com.guisfco.starwars.repository.PlanetRepository;
import com.guisfco.starwars.validator.PlanetRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class SavePlanetServiceTest {

    @InjectMocks
    private SavePlanetService service;

    @Mock
    private PlanetRepository repository;

    @Mock
    private PlanetRequestValidator validator;

    @Mock
    private PlanetDetailService planetDetailService;

    @BeforeEach
    public void setup() {
        doNothing().when(validator).accept(any(PlanetRequest.class));
    }

    @Test
    @DisplayName("Must save a new planet entity and return it")
    public void save() {

        final Planet fixture = PlanetFixture.get().random().build();

        when(planetDetailService.getPlanetDetail(anyString())).thenReturn(null);
        when(repository.save(any(Planet.class))).thenReturn(fixture);

        final PlanetDto response = service.save(PlanetRequestFixture.get().random().build());

        assertNotNull(response);
        assertEquals(0, response.getFilmsAppearances());
        verify(repository).save(any(Planet.class));
    }

    @Test
    @DisplayName("Must throw an error and not save the planet when providing an invalid request body")
    public void saveInvalidRequestBody() {

        doThrow(BadRequestException.class).when(validator).accept(any(PlanetRequest.class));

        assertThrows(BadRequestException.class, () -> service.save(new PlanetRequest()));

        verifyNoInteractions(repository, planetDetailService);
    }
}