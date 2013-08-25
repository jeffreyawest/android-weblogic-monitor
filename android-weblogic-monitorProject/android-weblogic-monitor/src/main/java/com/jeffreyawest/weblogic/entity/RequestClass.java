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
public class RequestClass extends WebLogicEntity
{

  /*
  ver – (string) Name of the server the request is associated with.
requestClassType – (string) Type of request class, which can either be "fairshare", "responsetime" or 'context"
completedCount – (number) Total number of completed requests since the server was started.
totalThreadUse – (number) Total amount of thread use time in millisecond used by the request class since the server was started.
pendingRequestCount – (number) Number of pending requests waiting for a thread to become available.
virtualTimeIncrement – (number) Current priority of the request class. The priority is relative to other request class priorities. The priority is frequently, dynamically calculated, and so can change.
   */

  @JsonProperty
  private String server;

  @JsonProperty
  private String requestClassType;

  @JsonProperty
  private String completedCount;

  @JsonProperty
  private String totalThreadUse;

  @JsonProperty
  private String pendingRequestCount;

  @JsonProperty
  private String virtualTimeIncrement;

  @Override
  public String listViewString()
  {

    return toString();
  }

  @Override
  public String toString()
  {

    return "RequestClass{" +
        "server='" + server + '\'' +
        ", requestClassType='" + requestClassType + '\'' +
        ", completedCount='" + completedCount + '\'' +
        ", totalThreadUse='" + totalThreadUse + '\'' +
        ", pendingRequestCount='" + pendingRequestCount + '\'' +
        ", virtualTimeIncrement='" + virtualTimeIncrement + '\'' +
        '}';
  }
}
