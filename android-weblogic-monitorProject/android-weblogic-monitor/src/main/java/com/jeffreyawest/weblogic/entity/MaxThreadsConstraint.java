package com.jeffreyawest.weblogic.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jeffreyawest on 8/12/13.
 */
public class MaxThreadsConstraint extends WebLogicEntity
{

  @JsonProperty
  private String server;
  @JsonProperty
  private int executingRequests;
  @JsonProperty
  private int deferredRequests;

  @Override
  public String listViewString()
  {

    return null;
  }

  @Override
  public String toString()
  {

    return "MaxThreadsConstraint{" +
        "server='" + server + '\'' +
        ", executingRequests=" + executingRequests +
        ", deferredRequests=" + deferredRequests +
        '}';
  }
}
