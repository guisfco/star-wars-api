package com.guisfco.starwars.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanetDto implements Serializable {

    private static final long serialVersionUID = 4832095933433975258L;

    private String id;

    private String name;

    private String climate;

    private String terrain;

    private int filmsAppearances;
}
