package com.guisfco.starwars.service;

import com.guisfco.starwars.domain.response.ContentPageResponse;
import com.guisfco.starwars.domain.dto.PlanetDto;
import com.guisfco.starwars.fixture.PlanetFixture;
import com.guisfco.starwars.repository.PlanetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class SearchPlanetsByTextServiceTest {

    @InjectMocks
    private SearchPlanetsByTextService service;

    @Mock
    private PlanetRepository repository;

    @Test
    @DisplayName("Must return a paginated list of planets when searching by text")
    public void searchByText() {

        final String text = randomAlphabetic(5);

        when(repository.searchByText(eq(text), any(PageRequest.class))).thenReturn(new PageImpl<>(PlanetFixture.get().randomList()));

        final ContentPageResponse<PlanetDto> response = service.searchByText(text, nextInt(1, 11), nextInt(1, 11));

        assertNotNull(response);
        verify(repository).searchByText(eq(text), any(PageRequest.class));
        verify(repository, never()).findAll(any(PageRequest.class));
    }

    @Test
    @DisplayName("Must return a paginated list of all planets when not searching by text")
    public void searchByBlankText() {

        final String text = "";

        when(repository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(PlanetFixture.get().randomList()));

        final ContentPageResponse<PlanetDto> response = service.searchByText(text, nextInt(1, 11), nextInt(1, 11));

        assertNotNull(response);
        verify(repository).findAll(any(PageRequest.class));
        verify(repository, never()).searchByText(eq(text), any(PageRequest.class));
    }
}