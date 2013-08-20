package com.jeffreyawest.weblogic.entity.enums;

import com.jeffreyawest.weblogic.monitor.R;

import java.util.HashMap;

/**
 * Created by jeffreyawest on 8/12/13.
 */
public enum DatasourceInstanceState
{
  Overloaded(40),
  Suspended(30),
  Shutdown(20),
  Unknown(10),
  Running(0);

  private static final HashMap<DatasourceInstanceState, Integer> STATE_COLOR_MAP;

  int intValue;

  DatasourceInstanceState(final int pIntValue)
  {

    intValue = pIntValue;
  }

  static
  {

    STATE_COLOR_MAP = new HashMap<DatasourceInstanceState, Integer>(7);

    STATE_COLOR_MAP.put(DatasourceInstanceState.Running, R.color.state_running);
    STATE_COLOR_MAP.put(DatasourceInstanceState.Overloaded, R.color.state_overloaded);
    STATE_COLOR_MAP.put(DatasourceInstanceState.Shutdown, R.color.state_shutdown);
    STATE_COLOR_MAP.put(DatasourceInstanceState.Suspended, R.color.state_suspended);
    STATE_COLOR_MAP.put(DatasourceInstanceState.Unknown, R.color.state_unknown);
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
