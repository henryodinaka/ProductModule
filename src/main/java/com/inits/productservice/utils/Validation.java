package com.inits.productservice.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@Slf4j
public class Validation {
    public static String validateAmountAndQnty(double amount, int qnt) {
        if (amount < 0)return "negative amount not allowed";
        if (!validData(qnt < 1)) return "Quantity must be at least 1 ";
        return null;
    }

    public static boolean validData(Object obj) {
        if (obj == null || String.valueOf(obj).isEmpty()) {
            return false;
        }
        if (obj.equals("null")) {
            return false;
        }
        if (obj instanceof String) {
            String s = ((String) obj).trim();
            if (s.length() < 1) return false;
        }
        return !obj.equals(0);
    }
}
