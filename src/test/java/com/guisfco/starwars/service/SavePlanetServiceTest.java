package com.guisfco.starwars.service;

import com.guisfco.starwars.domain.dto.PlanetDto;
import com.guisfco.starwars.entity.Planet;
import com.guisfco.starwars.fixture.PlanetFixture;
import com.guisfco.starwars.fixture.PlanetRequestFixture;
import com.guisfco.starwars.repository.PlanetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SavePlanetServiceTest {

    @InjectMocks
    private SavePlanetService service;

    @Mock
    private PlanetRepository repository;

    @Mock
    private PlanetDetailService planetDetailService;

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
}