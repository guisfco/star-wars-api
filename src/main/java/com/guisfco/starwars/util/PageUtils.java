package com.guisfco.starwars.util;

public class PageUtils {

    private static final int MINIMUM_PAGE_INDEX = 0;
    private static final int MINIMUM_PAGE_SIZE = 1;

    /**
     * Returns the fixed page index by selected page number
     *
     * @param pageNumber selected page number - starting at 1
     * @return fixed page index for using in {@link org.springframework.data.domain.PageRequest}
     */
    public static int getFixedPageIndex(final int pageNumber) {
        return Math.max(pageNumber - 1, MINIMUM_PAGE_INDEX);
    }

    /**
     * Returns the safe page size for searching items
     *
     * @param pageSize maximum number of items per page
     * @return page size that is at least the minimum allowed
     */
    public static int getSafePageSize(final int pageSize) {
        return Math.max(pageSize, MINIMUM_PAGE_SIZE);
    }
}
