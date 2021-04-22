package com.guisfco.starwars.validator;

import com.guisfco.starwars.domain.request.PlanetRequest;
import com.guisfco.starwars.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class PlanetRequestValidator implements Consumer<PlanetRequest> {

    @Override
    public void accept(final PlanetRequest request) {

        if (isNull(request)) {
            throw new BadRequestException("You must provide a body in your request.");
        }

        if (isBlank(request.getName())) {
            throw new BadRequestException("Your planet must have a name.");
        }

        if (isBlank(request.getTerrain())) {
            throw new BadRequestException("Your planet must have a terrain.");
        }

        if (isBlank(request.getClimate())) {
            throw new BadRequestException("Your planet must have a climate.");
        }
    }
}
