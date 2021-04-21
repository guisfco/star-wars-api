package com.guisfco.starwars.mapper;

import com.guisfco.starwars.domain.dto.PlanetDto;
import com.guisfco.starwars.entity.Planet;
import com.guisfco.starwars.fixture.PlanetFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PlanetDtoMapperTest {

    @Test
    @DisplayName("Must map all the attributes to the new class type")
    public void apply() {

        final Planet from = PlanetFixture.get().random().build();
        final PlanetDto to = PlanetDtoMapper.apply(from);

        assertNotNull(to);

        assertEquals(from.getClimate(), to.getClimate());
        assertEquals(from.getId(), to.getId());
        assertEquals(from.getName(), to.getName());
        assertEquals(from.getTerrain(), to.getTerrain());
        assertEquals(from.getFilmsAppearances(), to.getFilmsAppearances());
    }

    @Test
    @DisplayName("Must return null when from null")
    public void applyFromNull() {
        assertNull(PlanetDtoMapper.apply(null));
    }
}