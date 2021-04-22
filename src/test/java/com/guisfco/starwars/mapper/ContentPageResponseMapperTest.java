package com.guisfco.starwars.mapper;

import com.guisfco.starwars.domain.response.ContentPageResponse;
import com.guisfco.starwars.domain.dto.PlanetDto;
import com.guisfco.starwars.fixture.PlanetDtoFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class ContentPageResponseMapperTest {

    @Test
    @DisplayName("Must return a mapped page containing a list of items")
    public void apply() {

        final ContentPageResponse<PlanetDto> response = ContentPageResponseMapper.apply(new PageImpl<>(PlanetDtoFixture.get().randomList()));

        assertNotNull(response);
        assertFalse(response.getContent().isEmpty());
    }

    @Test
    @DisplayName("Must return a mapped page even if the origin is empty")
    public void applyEmptyPage() {
        assertNotNull(ContentPageResponseMapper.apply(Page.empty()));
    }

    @Test
    @DisplayName("Must return a mapped page even if the origin is null")
    public void applyFromNull() {
        assertNotNull(ContentPageResponseMapper.apply(null));
    }
}