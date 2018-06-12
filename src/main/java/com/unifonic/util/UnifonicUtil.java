package com.unifonic.util;

import static com.unifonic.util.ConfigConstants.*;

/**
 * Contains logic for mocking up like setting priority.
 * Has functions for mocking up like calculating # of units and balance/cost
 *
 * @author Shareef Hiasat
 */
public class UnifonicUtil {

    /**
     * for parsing with null returned when NumberFormateException
     *
     * @param value
     * @return
     */
    private static Integer parseInt(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * for parsing with null returned when NumberFormateException
     *
     * @param value
     * @return
     */
    private static Double parseDouble(String value) {
        try {
            return Double.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * convert string to int with default 0 as fall back exception
     *
     * @param value
     * @return
     */
    public static int parsePrimitiveInt(String value) {
        Integer result = parseInt(value);
        return result == null ? 0 : result;
    }

    /**
     * convert string to double with default 0 as fall back exception
     *
     * @param value
     * @return
     */
    public static double parsePrimitiveDouble(String value) {
        Double result = parseDouble(value);
        return result == null ? 0 : result;
    }

    /**
     * Getting pre configured default priority
     * Send high priority messages, possible value is " High " only.
     * High priority sending is available for advanced plans and through balance
     *
     * @return
     */
    public static String getDefaultPriority() {
        return DEFAULT_PRIORITY;
    }

    /**
     * Getting pre configured default senderId
     * Dummy for mocking sender ID is 1
     *
     * @return
     */
    public static String getDefaultSenderId(String senderId) {
        if (senderId != null && !senderId.isEmpty())
            return senderId;

        return DEFAULT_SENDER_ID;
    }

    /**
     * Getting pre configured default priority
     * Send high priority messages, possible value is " High " only.
     * High priority sending is available for advanced plans and through balance
     *
     * @return
     */
    public static String getDefaultBalance() {
        return DEFAULT_BALANCE;
    }

    public static String getDefaultAppSid() {
        return DEFAULT_APP_SID;
    }

    /**
     * Assume for mockin the cost is 1 per unit
     *
     * @return
     */
    public static int getCalculatedCost(int numberOfUtnis) {
        return numberOfUtnis * DEFAULT_COST;
    }

    /**
     * Assume for mockin the cost is 1 per unit
     *
     * @return
     */
    public static int getCalculatedNumberOfUntis(String body) {
        int numberOfUnits = body.length() / DEFAULT_UNIT_SIZE;
        if (numberOfUnits == 0) return 1;
        return body.length() / DEFAULT_UNIT_SIZE;
    }
}
