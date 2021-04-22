package com.guisfco.starwars.validator;

import com.guisfco.starwars.exception.BadRequestException;
import com.guisfco.starwars.fixture.PlanetRequestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class PlanetRequestValidatorTest {

    @InjectMocks
    private PlanetRequestValidator validator;

    @Test
    @DisplayName("Must accept the request body")
    public void accept() {
        validator.accept(PlanetRequestFixture.get().random().build());
    }

    @Test
    @DisplayName("Must throw an error when request body not provided")
    public void acceptNullBody() {
        assertThrows(BadRequestException.class, () -> validator.accept(null));
    }

    @Test
    @DisplayName("Must throw an error when planet doesn't have a name")
    public void acceptBlankName() {
        assertThrows(BadRequestException.class, () -> validator.accept(PlanetRequestFixture.get().random().name("").build()));
    }

    @Test
    @DisplayName("Must throw an error when planet doesn't have a climate")
    public void acceptBlankClimate() {
        assertThrows(BadRequestException.class, () -> validator.accept(PlanetRequestFixture.get().random().climate("").build()));
    }

    @Test
    @DisplayName("Must throw an error when planet doesn't have a terrain")
    public void acceptBlankTerrain() {
        assertThrows(BadRequestException.class, () -> validator.accept(PlanetRequestFixture.get().random().terrain("").build()));
    }
}