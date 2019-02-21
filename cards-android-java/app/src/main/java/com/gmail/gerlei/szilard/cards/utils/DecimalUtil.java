package com.gmail.gerlei.szilard.cards.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public final class DecimalUtil {

    private static final String DEFAULT_DECIMAL_FORMAT_PATTERN = "###,##0.00";

    public static String formatByDefaultDecimalFormat(int value) {
        DecimalFormatSymbols formatSymbols = DecimalFormatSymbols.getInstance();
        formatSymbols.setGroupingSeparator('\'');
        return new DecimalFormat(DEFAULT_DECIMAL_FORMAT_PATTERN, formatSymbols).format(value);
    }

}

