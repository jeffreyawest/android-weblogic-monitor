package com.jeffreyawest.weblogic.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jeffreyawest on 8/12/13.
 */
public class WorkManager extends WebLogicEntity
{

  @JsonProperty
  private String name;

  @JsonProperty
  private String server;

  @JsonProperty
  private int pendingRequests;

  @JsonProperty
  private int completedRequests;

  @Override
  public String listViewString()
  {

    return null;
  }

  @Override
  public String toString()
  {

    return "WorkManager{" +
        "name='" + name + '\'' +
        ", server='" + server + '\'' +
        ", pendingRequests=" + pendingRequests +
        ", completedRequests=" + completedRequests +
        '}';
  }
}
