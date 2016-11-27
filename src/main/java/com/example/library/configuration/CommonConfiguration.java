package com.example.library.configuration;

import org.aeonbits.owner.Config;

import java.math.BigDecimal;

/**
 * CommonConfiguration interface are mapping on common.properties
 * file.
 *
 * @author Liliya Rafikova
 */
@Config.Sources({"classpath:config/common.properties"})
public interface CommonConfiguration extends Config {

    /**
     * Loading rent fee per month from common.properties
     *
     * @return fee
     */
    @Key("rentFee")
    BigDecimal rentFee();

    /**
     * Loading a discount from common.properties
     *
     * @return fee
     */
    @Key("discount")
    BigDecimal discount();

    /**
     * Loading a top from common.properties
     *
     * @return top who own the most money
     */
    @Key("top")
    int top();
}
