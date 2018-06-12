package com.unifonic.util;

/**
 * Some defaulted configuration constants
 * @author Shareef Hiasat
 */
public class ConfigConstants {

    private ConfigConstants() {
    }

    /**
     * Send high priority messages, possible value is " High " only.
     * High priority sending is available for advanced plans and through balance
     */
    public static final String DEFAULT_PRIORITY = "High";

    /**
     * Getting pre configured default senderId
     * Dummy for mocking sender ID is 1
     */
    public static final String DEFAULT_SENDER_ID = "1";

    /**
     * Default balance is 10000 USD
     */
    public static final String DEFAULT_BALANCE = "10000";

    /**
     * Default AppSid
     */
    public static final String DEFAULT_APP_SID = "1";

    /**
     * Default cost is 1 per unit
     */
    public static final int DEFAULT_COST = 1;

    /**
     * Default currency
     */
    public static final String DEFAULT_CURRENCY = "USD";

    /**
     * Default size of the one unit
     */
    public static final int DEFAULT_UNIT_SIZE = 160;

    /**
     * add status of "Queued" other possiblities are , "Sent", "Failed" and "Rejected"
     */
    public static final int QUEUED_STATUS_ID = 1;
    public static final String QUEUED_STATUS_DESC = "Queued";
}
