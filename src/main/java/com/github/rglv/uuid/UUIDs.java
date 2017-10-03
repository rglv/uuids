package com.github.rglv.uuid;

import java.util.UUID;

/**
 * Utility class with slighlty faster string conversion methods compared to JDK8 version of java.util.UUID.
 */
public class UUIDs {
    /**
     * Parses string to {@link UUID} instance.
     * <p>
     *     This function is a bit better than standard JDK8 version in terms of memory management:
     *     it doesn't use Long.parse() which uses substrings, so on heavy loads it makes less stress on GC.
     * </p>
     * @param str Hexademical UUID representation. Can contain dashes.
     * @return A UUID with the specified value
     */
    public static UUID fromString(String str) {
        if (str == null || str.length() < 32 || str.length() > 36) {
            throw new IllegalArgumentException("Invalid UUID string: " + str);
        }

        long leastSignificantBits = 0;
        long mostSignificantBits = 0;

        int n = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch != '-') {
                long val =
                    ch >= '0' && ch <= '9' ? ch - '0'
                        : ch >= 'A' && ch <= 'F' ? ch - 'A' + 10
                            : ch >= 'a' && ch <= 'f' ? ch - 'a' + 10
                                : -1;

                if (val == -1) {
                    throw new IllegalArgumentException("Invalid UUID string: " + str);
                }

                if (n < 16) {
                    mostSignificantBits <<= 4;
                    mostSignificantBits ^= val;
                } else {
                    leastSignificantBits <<= 4;
                    leastSignificantBits ^= val;
                }

                n++;
            }
        }

        return new UUID(mostSignificantBits, leastSignificantBits);
    }

    /**
     * Returns a {@code String} object representing {@code UUID}.
     * <p>
     *     This function is a bit better than standard JDK8 version in terms of memory management:
     *     it doesn't use Long.toHexString() and substrings, so on heavy loads it makes less stress on GC.
     * </p>
     *
     * @param uuid Object to represent in String.
     * @return  A string representation of uuid
     */
    public static String toString(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UUID should not be null");
        }

        final long l = uuid.getLeastSignificantBits();
        final long m = uuid.getMostSignificantBits();

        final char[] chars = new char[36];

        appendDigits(m >> 32, 8, chars, 0);
        chars[8] = '-';
        appendDigits(m >> 16, 4, chars, 9);
        chars[13] = '-';
        appendDigits(m, 4, chars, 14);
        chars[18] = '-';
        appendDigits(l >> 48, 4, chars, 19);
        chars[23] = '-';
        appendDigits(l, 12, chars, 24);

        return new String(chars);
    }

    private static void appendDigits(long val, int digits, char[] chars, int st) {
        for (int i = 0; i < digits; i++) {
            int digit = (int) (val >> 4 * (digits - i - 1)) & 0xf;
            char ch = digit < 10 ? (char) ('0' + digit) :  (char) ('a' - 10 + digit);
            chars[st + i] = ch;
        }
    }
}
