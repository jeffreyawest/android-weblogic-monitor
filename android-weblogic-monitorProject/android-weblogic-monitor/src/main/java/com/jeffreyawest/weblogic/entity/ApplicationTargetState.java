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