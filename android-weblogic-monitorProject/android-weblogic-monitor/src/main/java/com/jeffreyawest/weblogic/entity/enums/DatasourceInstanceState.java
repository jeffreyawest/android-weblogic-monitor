/*
 * *************************************************************************
 *
 * Copyright (c) 2013 - Jeffrey A. West Designs
 *
 * This code is provided for example purposes only.  Neither Oracle nor
 * Jeffrey A. West assume any responsibility or liability for the consequences
 *  of using this code. If you choose to use this code for any reason,
 * including but not limited to its use as an example you do so at your own
 * risk and without the support of Oracle.
 *
 * This code is provided under the following licenses:
 *  - GNU General Public License (GPL-2.0)
 *
 * **************************************************************************
 */

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
