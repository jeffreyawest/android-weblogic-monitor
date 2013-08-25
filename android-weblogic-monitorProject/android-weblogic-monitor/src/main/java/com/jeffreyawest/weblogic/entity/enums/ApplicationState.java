/*
 *  Copyright (c) 2013 - Jeffrey A. West Designs
 * ************************************************************************
 * This code is provided for example purposes only.  Neither Oracle nor Jeffrey
 * A. West assume any responsibility or liability for the consequences of using
 * this code. If you choose to use this code for any reason, including but not limited to its use as an example you do so at your own risk and without the support of Oracle.
 * This code is provided under the following licenses:
 *  - GNU General Public License (GPL-2.0)
 * **************************************************************************
 */

package com.jeffreyawest.weblogic.entity.enums;

import com.jeffreyawest.weblogic.monitor.R;

import java.util.HashMap;

/**
 * Created by jeffreyawest on 8/12/13.
 */
public enum ApplicationState
{
  STATE_FAILED(60),
  STATE_ADMIN(50),
  STATE_RETIRED(40),
  STATE_PREPARED(30),
  STATE_UPDATE_PENDING(20),
  STATE_NEW(10),
  STATE_ACTIVE(0);

  private static final HashMap<ApplicationState, Integer> STATE_COLOR_MAP;

  int intValue;

  ApplicationState(final int pIntValue)
  {

    intValue = pIntValue;
  }

  static
  {

    STATE_COLOR_MAP = new HashMap<ApplicationState, Integer>(7);

    STATE_COLOR_MAP.put(ApplicationState.STATE_ACTIVE, R.color.state_active);
    STATE_COLOR_MAP.put(ApplicationState.STATE_ADMIN, R.color.state_admin);
    STATE_COLOR_MAP.put(ApplicationState.STATE_FAILED, R.color.state_failed);
    STATE_COLOR_MAP.put(ApplicationState.STATE_NEW, R.color.state_new);
    STATE_COLOR_MAP.put(ApplicationState.STATE_PREPARED, R.color.state_prepared);
    STATE_COLOR_MAP.put(ApplicationState.STATE_RETIRED, R.color.state_retired);
    STATE_COLOR_MAP.put(ApplicationState.STATE_UPDATE_PENDING, R.color.state_update_pending);
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
