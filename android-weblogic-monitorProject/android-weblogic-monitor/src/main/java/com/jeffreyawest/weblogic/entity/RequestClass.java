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
