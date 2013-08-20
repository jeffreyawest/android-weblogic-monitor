package com.jeffreyawest.weblogic.entity.enums;

import com.jeffreyawest.weblogic.monitor.R;

import java.util.HashMap;

/**
 * Created by jeffreyawest on 8/12/13.
 */
public enum ApplicationHealth
{
  HEALTH_FAILED(50),
  HEALTH_CRITICAL(40),
  HEALTH_OVERLOADED(30),
  HEALTH_WARN(20),
  UNKNOWN(10),
  HEALTH_OK(0);

  private static final HashMap<ApplicationHealth, Integer> STATE_COLOR_MAP;

  int intValue;

  ApplicationHealth(final int pIntValue)
  {

    intValue = pIntValue;
  }

  static
  {

    STATE_COLOR_MAP = new HashMap<ApplicationHealth, Integer>(7);

    STATE_COLOR_MAP.put(ApplicationHealth.HEALTH_FAILED, R.color.state_failed);
    STATE_COLOR_MAP.put(ApplicationHealth.HEALTH_CRITICAL, R.color.state_critical);
    STATE_COLOR_MAP.put(ApplicationHealth.HEALTH_OVERLOADED, R.color.state_overloaded);
    STATE_COLOR_MAP.put(ApplicationHealth.HEALTH_WARN, R.color.state_warn);
    STATE_COLOR_MAP.put(ApplicationHealth.UNKNOWN, R.color.state_unknown);
    STATE_COLOR_MAP.put(ApplicationHealth.HEALTH_OK, R.color.state_running);
  }

  public int getIntValue()
  {
    return intValue;
  }

  public int getColorID()
  {
    return STATE_COLOR_MAP.get(this);
  }

}
