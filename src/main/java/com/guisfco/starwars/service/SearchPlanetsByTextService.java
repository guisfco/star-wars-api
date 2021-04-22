package com.guisfco.starwars.service;

import com.guisfco.starwars.domain.response.ContentPageResponse;
import com.guisfco.starwars.domain.dto.PlanetDto;
import com.guisfco.starwars.mapper.PlanetDtoMapper;
import com.guisfco.starwars.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.guisfco.starwars.mapper.ContentPageResponseMapper.apply;
import static com.guisfco.starwars.util.PageUtils.getFixedPageIndex;
import static com.guisfco.starwars.util.PageUtils.getSafePageSize;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchPlanetsByTextService {

    private final PlanetRepository repository;

    public ContentPageResponse<PlanetDto> searchByText(final String text, final int page, final int size) {

        log.info("Searching planets by text: text={}, page={}, size={}.", text, page, size);

        if (isBlank(text)) {
            return apply(repository.findAll(PageRequest.of(getFixedPageIndex(page), getSafePageSize(size))).map(PlanetDtoMapper::apply));
        }

        return apply(repository.searchByText(text, PageRequest.of(getFixedPageIndex(page), getSafePageSize(size))).map(PlanetDtoMapper::apply));
    }
}
