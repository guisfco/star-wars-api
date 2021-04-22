package com.guisfco.starwars.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetRequest implements Serializable {

    private static final long serialVersionUID = -4802373724381802920L;

    private String name;

    private String climate;

    private String terrain;
}
