package com.guisfco.starwars.service;

import com.guisfco.starwars.domain.dto.PlanetDto;
import com.guisfco.starwars.fixture.PlanetFixture;
import com.guisfco.starwars.repository.PlanetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FindAllPlanetsServiceTest {

    @InjectMocks
    private FindAllPlanetsService service;

    @Mock
    private PlanetRepository repository;

    @Test
    @DisplayName("Must return a list of planet entities")
    public void findAll() {

        when(repository.findAll()).thenReturn(PlanetFixture.get().randomList());

        final List<PlanetDto> response = service.findAll();

        assertNotNull(response);
        verify(repository).findAll();
    }
}