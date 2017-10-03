package com.github.rglv.uuid;

import java.util.UUID;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UUIDsTest {
    @Test
    public void testFromString() throws Exception {
        String[] testCases = {
            "00000000-0000-0000-0000-000000000000",
            "00000000-0000-0001-0000-000000000001",
            "ffffffff-ffff-ffff-ffff-ffffffffffff",
            "FEDCBA12-3456-7890-fedc-ba1234567890"
        };

        for (String testCase : testCases) {
            UUID original = UUID.fromString(testCase);
            UUID util = UUIDs.fromString(testCase);

            assertEquals(original, util);
        }
    }

    @Test
    public void testFromStringNoDashes() throws Exception {
        String[] testCases = {
            "00000000000000000000000000000000",
            "00000000000000010000000000000001",
            "ffffffffffff-ffffffff-ffffffffffff",
            "FEDCBA1234567890-fedcba1234567890"
        };
        for (String testCase : testCases) {
            UUID util = UUIDs.fromString(testCase);
            String reverse = util.toString();

            assertEquals(testCase.replaceAll("-","").toLowerCase(), reverse.replaceAll("-", ""));
        }

    }

    @Test
    public void testToString() throws Exception {
        UUID[] testCases = {
            new UUID(0L, 0L),
            new UUID(1L, 1L),
            new UUID(0xFFFFFFFFFFFFFFFFL, 0xFFFFFFFFFFFFFFFFL),
            new UUID(0xFEDCBA1234567890L, 0xFEDCBA1234567890L)
        };

        for (UUID testCase : testCases) {
            String original = testCase.toString();
            String util = UUIDs.toString(testCase);

            assertEquals(original, util);
        }
    }

}