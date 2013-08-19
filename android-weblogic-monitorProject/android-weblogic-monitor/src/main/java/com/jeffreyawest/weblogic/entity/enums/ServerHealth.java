package com.jeffreyawest.weblogic.entity.enums;

import com.jeffreyawest.weblogic.monitor.R;

import java.util.HashMap;

/**
 * Created by jeffreyawest on 8/12/13.
 */
public enum ServerHealth
{
  HEALTH_OK(0),
  UNKNOWN(10),
  HEALTH_WARN(20),
  HEALTH_CRITICAL(30),
  HEALTH_OVERLOADED(40),
  HEALTH_FAILED(50);

  private static final HashMap<ServerHealth, Integer> HEALTH_COLOR_MAP;

  private int intValue;

  static
  {
    HEALTH_COLOR_MAP = new HashMap<ServerHealth, Integer>(7);

    HEALTH_COLOR_MAP.put(ServerHealth.HEALTH_OK, R.color.state_running);
    HEALTH_COLOR_MAP.put(ServerHealth.HEALTH_WARN, R.color.state_warn);
    HEALTH_COLOR_MAP.put(ServerHealth.HEALTH_CRITICAL, R.color.state_critical);
    HEALTH_COLOR_MAP.put(ServerHealth.HEALTH_FAILED, R.color.state_failed);
    HEALTH_COLOR_MAP.put(ServerHealth.HEALTH_OVERLOADED, R.color.state_overloaded);
    HEALTH_COLOR_MAP.put(ServerHealth.UNKNOWN, R.color.state_unknown);
  }

  ServerHealth(int i)
  {

    intValue = i;
  }

  public int getIntValue()
  {

    return intValue;
  }

  public int getColorID()
  {

    return HEALTH_COLOR_MAP.get(this);
  }

}
