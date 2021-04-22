package com.guisfco.starwars.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class PageUtilsTest {

    @Test
    @DisplayName("Must return the page index based on page number")
    public void getFixedPageIndex() {
        assertEquals(0, PageUtils.getFixedPageIndex(-1));
        assertEquals(0, PageUtils.getFixedPageIndex(0));
        assertEquals(0, PageUtils.getFixedPageIndex(1));
        assertEquals(1, PageUtils.getFixedPageIndex(2));
    }

    @Test
    @DisplayName("Must return a page size greater than 0")
    public void getSafePageSize() {
        assertEquals(1, PageUtils.getSafePageSize(-1));
        assertEquals(1, PageUtils.getSafePageSize(0));
        assertEquals(1, PageUtils.getSafePageSize(1));
        assertEquals(2, PageUtils.getSafePageSize(2));
    }
}