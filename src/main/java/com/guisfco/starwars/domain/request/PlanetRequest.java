package com.guisfco.starwars.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanetRequest implements Serializable {

    private static final long serialVersionUID = -4802373724381802920L;

    @NotBlank(message = "Required planet field (name) not found.")
    private String name;

    @NotBlank(message = "Required planet field (climate) not found.")
    private String climate;

    @NotBlank(message = "Required planet field (terrain) not found.")
    private String terrain;
}
