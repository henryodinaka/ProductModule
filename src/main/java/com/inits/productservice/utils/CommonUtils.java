package com.inits.productservice.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@Slf4j
public final class CommonUtils {

    private CommonUtils() {
    }

    public static String dateToStringFormated(LocalDateTime date) {
        if (date != null && date.toString().length() > 19)
            return date.toString().substring(0, 19).replace("T", " ");
        return null;
    }
}

