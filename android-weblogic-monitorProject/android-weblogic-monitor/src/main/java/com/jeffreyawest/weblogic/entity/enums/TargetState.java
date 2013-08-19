package com.jeffreyawest.weblogic.entity.enums;

import com.jeffreyawest.weblogic.monitor.R;

import java.util.HashMap;

/**
 * Created by jeffreyawest on 8/12/13.
 */
public enum TargetState
{
  STATE_NEW,
  STATE_FAILED,
  STATE_RETIRED,
  STATE_PREPARED,
  STATE_ADMIN,
  STATE_ACTIVE,
  STATE_UPDATE_PENDING,
  STATE_UNKNOWN;

  private static final HashMap<TargetState, Integer> STATE_COLOR_MAP;

  static
  {
    STATE_COLOR_MAP = new HashMap<TargetState, Integer>(7);

    STATE_COLOR_MAP.put(TargetState.STATE_ACTIVE, R.color.state_active);
    STATE_COLOR_MAP.put(TargetState.STATE_ADMIN, R.color.state_admin);
    STATE_COLOR_MAP.put(TargetState.STATE_FAILED, R.color.state_failed);
    STATE_COLOR_MAP.put(TargetState.STATE_NEW, R.color.state_new);
    STATE_COLOR_MAP.put(TargetState.STATE_PREPARED, R.color.state_prepared);
    STATE_COLOR_MAP.put(TargetState.STATE_RETIRED, R.color.state_retired);
    STATE_COLOR_MAP.put(TargetState.STATE_UPDATE_PENDING, R.color.state_update_pending);
    STATE_COLOR_MAP.put(TargetState.STATE_UNKNOWN, R.color.state_unknown);
    STATE_COLOR_MAP.put(null, R.color.state_unknown);
  }

  public int getColorID()
  {

    return STATE_COLOR_MAP.get(this);
  }
}
