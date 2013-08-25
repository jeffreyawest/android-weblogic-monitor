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
public enum ServerState
{
  ADMIN(50),
  STANDBY(40),
  SHUTDOWN(30),
  STARTING(20),
  RESUMING(10),
  RUNNING(0);

  private static final HashMap<ServerState, Integer> STATE_COLOR_MAP;

  int intValue;

  ServerState(int intValue)
  {

    this.intValue = intValue;
  }

  static
  {
    STATE_COLOR_MAP = new HashMap<ServerState, Integer>(7);
    STATE_COLOR_MAP.put(ServerState.RUNNING, R.color.state_running);
    STATE_COLOR_MAP.put(ServerState.STARTING, R.color.state_starting);
    STATE_COLOR_MAP.put(ServerState.STANDBY, R.color.state_standby);
    STATE_COLOR_MAP.put(ServerState.ADMIN, R.color.state_admin);
    STATE_COLOR_MAP.put(ServerState.RESUMING, R.color.state_resuming);
    STATE_COLOR_MAP.put(ServerState.SHUTDOWN, R.color.state_shutdown);
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
