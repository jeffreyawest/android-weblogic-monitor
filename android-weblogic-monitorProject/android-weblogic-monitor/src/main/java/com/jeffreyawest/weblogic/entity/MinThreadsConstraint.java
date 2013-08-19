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
