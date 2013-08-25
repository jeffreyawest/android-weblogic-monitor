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
public class MinThreadsConstraint extends WebLogicEntity
{

  @JsonProperty
  private String server;
  @JsonProperty
  private int pendingRequests;
  @JsonProperty
  private int completedRequests;
  @JsonProperty
  private int executingRequests;
  @JsonProperty
  private int outOfOrderExecutionCount;
  @JsonProperty
  private int mustRunCount;
  @JsonProperty
  private int maxWaitTime;
  @JsonProperty
  private int currentWaitTime;

  @Override
  public String listViewString()
  {

    return null;
  }
}
