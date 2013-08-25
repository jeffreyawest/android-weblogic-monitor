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

package com.jeffreyawest.weblogic.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jeffreyawest on 8/12/13.
 */
public class RacInstance extends WebLogicEntity
{

  @JsonProperty
  private String instanceName;
  @JsonProperty
  private String state;
  @JsonProperty
  private String enabled;
  @JsonProperty
  private String signature;

  @JsonProperty
  private int currentWeight;
  @JsonProperty
  private int activeConnectionsCurrentCount;
  @JsonProperty
  private int reserveRequestCount;
  @JsonProperty
  private int connectionsTotalCount;
  @JsonProperty
  private int currCapacity;
  @JsonProperty
  private int numAvailable;
  @JsonProperty
  private int numUnavailable;

  @Override
  public String listViewString()
  {

    return toString();
  }

  @Override
  public String toString()
  {

    return "RacInstance{" +
        "instanceName='" + instanceName + '\'' +
        ", state='" + state + '\'' +
        ", enabled='" + enabled + '\'' +
        ", signature='" + signature + '\'' +
        ", currentWeight=" + currentWeight +
        ", activeConnectionsCurrentCount=" + activeConnectionsCurrentCount +
        ", reserveRequestCount=" + reserveRequestCount +
        ", connectionsTotalCount=" + connectionsTotalCount +
        ", currCapacity=" + currCapacity +
        ", numAvailable=" + numAvailable +
        ", numUnavailable=" + numUnavailable +
        '}';
  }
}