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

package com.jeffreyawest.weblogic.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeffreyawest.weblogic.entity.enums.TargetState;

/**
 * Created by jeffreyawest on 8/12/13.
 */
public class ApplicationTargetState extends WebLogicEntity
{

  @JsonProperty
  private String target;

  @JsonProperty
  private TargetState state;

  @Override
  public String listViewString()
  {

    StringBuilder sb = new StringBuilder();
    sb.append("[").append(target);
    sb.append(':').append(state).append("]");
    return null;
  }

  public String getTarget()
  {

    return target;
  }

  public TargetState getState()
  {

    return state;
  }

  @Override
  public String toString()
  {

    return "ApplicationTargetState{" +
        "target='" + target + '\'' +
        ", state='" + state + '\'' +
        '}';
  }
}