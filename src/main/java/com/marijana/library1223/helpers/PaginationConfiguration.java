package com.marijana.library1223.helpers;

import org.springframework.context.annotation.Configuration;

@Configuration
public class PaginationConfiguration {

    public static final int DEFAULT_LIMIT = 10;
    public static final int DEFAULT_OFFSET = 0;


    public static int calculateStartIndex(int offset, int listSize) {
        return Math.min(offset, listSize);
    }

    public static int calculateEndIndex(int offset, int limit, int listSize) {
        return Math.min(offset + limit, listSize);
    }
}
