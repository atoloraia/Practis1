package com.practis.utils;

import static java.lang.String.valueOf;
import static java.time.Instant.now;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

    public static String currentDate() {
        final var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        return formatter.format(LocalDateTime.now());
    }

    public static String timestamp() {
        return valueOf(now().getEpochSecond() + now().getNano());
    }

    public static String random() {
        return UUID.randomUUID().toString();
    }

    /** Generates random phone number. */
    public static String phone() {
        Random rand = new Random();
        int num1 = (rand.nextInt(7) + 1) * 100 + (rand.nextInt(8) * 10) + rand.nextInt(8);
        int num2 = rand.nextInt(743);
        int num3 = rand.nextInt(10000);

        DecimalFormat df3 = new DecimalFormat("000"); // 3 zeros
        DecimalFormat df4 = new DecimalFormat("0000"); // 4 zeros

        return df3.format(num1) + df3.format(num2) + df4.format(num3);
    }
}
