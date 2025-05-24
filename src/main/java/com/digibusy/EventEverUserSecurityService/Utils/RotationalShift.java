package com.digibusy.EventEverUserSecurityService.Utils;

public class RotationalShift {

    public static String generateRotationalShiftKey(String secretKey, int targetLength) {
        StringBuilder shiftedKey = new StringBuilder();
        int originalLength = secretKey.length();

        // Generate rotational shifts
        while (shiftedKey.length() < targetLength) {
            for (int i = 0; i < originalLength; i++) {
                shiftedKey.append(secretKey.substring(i)).append(secretKey.substring(0, i));
                if (shiftedKey.length() >= targetLength) {
                    return shiftedKey.substring(0, targetLength);
                }
            }
        }
        return shiftedKey.substring(0, targetLength);
    }

}

