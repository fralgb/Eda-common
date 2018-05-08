package com.omniselling.common;

import java.util.TimeZone;

/**
 * 初始化系统默认时区
 * @author log
 *
 */
public class InitZoneConfig
{

    private String defaultTimezone;

    public InitZoneConfig(String defaultTimezone)
    {
        this.defaultTimezone = defaultTimezone;
    }

    public InitZoneConfig()
    {
    }

    public void initZone()
    {
        if (defaultTimezone == null)
        {
            TimeZone.setDefault(TimeZone.getTimeZone(defaultTimezone));
        }


    }

}
