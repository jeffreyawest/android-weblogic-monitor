package com.jeffreyawest.weblogic.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.json.JSONObject;

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