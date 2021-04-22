package com.guisfco.starwars.mapper;

import com.guisfco.starwars.domain.response.ContentPageResponse;
import org.springframework.data.domain.Page;

import java.util.Collections;

import static java.util.Objects.isNull;

public class ContentPageResponseMapper {

    public static <T> ContentPageResponse<T> apply(final Page<T> from) {

        if (isNull(from)) {
            return new ContentPageResponse<>(0, 0, true, true, Collections.emptyList());
        }

        ContentPageResponse<T> to = new ContentPageResponse<>();

        to.setContent(from.getContent());
        to.setFirst(from.isFirst());
        to.setLast(from.isLast());
        to.setTotalElements(from.getTotalElements());
        to.setTotalPages(from.getTotalPages());

        return to;
    }
}
