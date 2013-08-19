package com.jeffreyawest.weblogic.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeffreyawest.weblogic.entity.enums.DatasourceInstanceState;

import java.util.List;

/**
 * Created by jeffreyawest on 8/12/13.
 */
public class DatasourceInstance extends WebLogicEntity
{

  @JsonProperty
  private String server;
  @JsonProperty
  protected DatasourceInstanceState state;
  @JsonProperty
  private String enabled;
  @JsonProperty
  private String VersionJDBCDriver;
  @JsonProperty
  private int activeConnectionsAverageCount;
  @JsonProperty
  private int activeConnectionsCurrentCount;
  @JsonProperty
  private int activeConnectionsHighCount;
  @JsonProperty
  private int connectionDelayTime;
  @JsonProperty
  private int connectionsTotalCount;
  @JsonProperty
  private int currCapacity;
  @JsonProperty
  private int currCapacityHighCount;
  @JsonProperty
  private int failedReserveRequestCount;
  @JsonProperty
  private int failuresToReconnectCount;
  @JsonProperty
  private int highestNumAvailable;
  @JsonProperty
  private int leakedConnectionCount;
  @JsonProperty
  private int numAvailable;
  @JsonProperty
  private int numUnavailable;
  @JsonProperty
  private int prepStmtCacheAccessCount;
  @JsonProperty
  private int prepStmtCacheAddCount;
  @JsonProperty
  private int prepStmtCacheCurrentSize;
  @JsonProperty
  private int prepStmtCacheDeleteCount;
  @JsonProperty
  private int prepStmtCacheHitCount;
  @JsonProperty
  private int prepStmtCacheMissCount;
  @JsonProperty
  private int reserveRequestCount;
  @JsonProperty
  private int waitSecondsHighCount;
  @JsonProperty
  private int waitingForConnectionCurrentCount;
  @JsonProperty
  private int waitingForConnectionFailureTotal;
  @JsonProperty
  private int waitingForConnectionHighCount;
  @JsonProperty
  private int waitingForConnectionSuccessTotal;
  @JsonProperty
  private int waitingForConnectionTotal;
  @JsonProperty
  private int successfulRclbBasedBorrowCount;
  @JsonProperty
  private int failedRCLBBasedBorrowCount;
  @JsonProperty
  private int successfulAffinityBasedBorrowCount;
  @JsonProperty
  private int failedAffinityBasedBorrowCount;
  @JsonProperty
  private List<RacInstance> racInstances;

  public String getServer()
  {

    return server;
  }

  public DatasourceInstanceState getState()
  {

    return state;
  }

  public String getEnabled()
  {

    return enabled;
  }

  public String getVersionJDBCDriver()
  {

    return VersionJDBCDriver;
  }

  public int getActiveConnectionsAverageCount()
  {

    return activeConnectionsAverageCount;
  }

  public int getActiveConnectionsCurrentCount()
  {

    return activeConnectionsCurrentCount;
  }

  public int getActiveConnectionsHighCount()
  {

    return activeConnectionsHighCount;
  }

  public int getConnectionDelayTime()
  {

    return connectionDelayTime;
  }

  public int getConnectionsTotalCount()
  {

    return connectionsTotalCount;
  }

  public int getCurrCapacity()
  {

    return currCapacity;
  }

  public int getCurrCapacityHighCount()
  {

    return currCapacityHighCount;
  }

  public int getFailedReserveRequestCount()
  {

    return failedReserveRequestCount;
  }

  public int getFailuresToReconnectCount()
  {

    return failuresToReconnectCount;
  }

  public int getHighestNumAvailable()
  {

    return highestNumAvailable;
  }

  public int getLeakedConnectionCount()
  {

    return leakedConnectionCount;
  }

  public int getNumUnavailable()
  {

    return numUnavailable;
  }

  public int getPrepStmtCacheAccessCount()
  {

    return prepStmtCacheAccessCount;
  }

  public int getPrepStmtCacheAddCount()
  {

    return prepStmtCacheAddCount;
  }

  public int getPrepStmtCacheCurrentSize()
  {

    return prepStmtCacheCurrentSize;
  }

  public int getPrepStmtCacheDeleteCount()
  {

    return prepStmtCacheDeleteCount;
  }

  public int getPrepStmtCacheHitCount()
  {

    return prepStmtCacheHitCount;
  }

  public int getPrepStmtCacheMissCount()
  {

    return prepStmtCacheMissCount;
  }

  public int getReserveRequestCount()
  {

    return reserveRequestCount;
  }

  public int getWaitSecondsHighCount()
  {

    return waitSecondsHighCount;
  }

  public int getWaitingForConnectionCurrentCount()
  {

    return waitingForConnectionCurrentCount;
  }

  public int getWaitingForConnectionFailureTotal()
  {

    return waitingForConnectionFailureTotal;
  }

  public int getWaitingForConnectionHighCount()
  {

    return waitingForConnectionHighCount;
  }

  public int getWaitingForConnectionSuccessTotal()
  {

    return waitingForConnectionSuccessTotal;
  }

  public int getWaitingForConnectionTotal()
  {

    return waitingForConnectionTotal;
  }

  public int getSuccessfulRclbBasedBorrowCount()
  {

    return successfulRclbBasedBorrowCount;
  }

  public int getFailedRCLBBasedBorrowCount()
  {

    return failedRCLBBasedBorrowCount;
  }

  public int getSuccessfulAffinityBasedBorrowCount()
  {

    return successfulAffinityBasedBorrowCount;
  }

  public int getFailedAffinityBasedBorrowCount()
  {

    return failedAffinityBasedBorrowCount;
  }

  @Override
  public String listViewString()
  {

    return toString();
  }

  @Override
  public String toString()
  {

    return "Instance{" +
        "server='" + server + '\'' +
        ", state='" + state + '\'' +
        ", enabled='" + enabled + '\'' +
        ", VersionJDBCDriver=" + VersionJDBCDriver +
        ", activeConnectionsAverageCount=" + activeConnectionsAverageCount +
        ", activeConnectionsCurrentCount=" + activeConnectionsCurrentCount +
        ", activeConnectionsHighCount=" + activeConnectionsHighCount +
        ", connectionDelayTime=" + connectionDelayTime +
        ", connectionsTotalCount=" + connectionsTotalCount +
        ", currCapacity=" + currCapacity +
        ", currCapacityHighCount=" + currCapacityHighCount +
        ", failedReserveRequestCount=" + failedReserveRequestCount +
        ", failuresToReconnectCount=" + failuresToReconnectCount +
        ", highestNumAvailable=" + highestNumAvailable +
        ", leakedConnectionCount=" + leakedConnectionCount +
        ", numUnavailable=" + numUnavailable +
        ", prepStmtCacheAccessCount=" + prepStmtCacheAccessCount +
        ", prepStmtCacheAddCount=" + prepStmtCacheAddCount +
        ", prepStmtCacheCurrentSize=" + prepStmtCacheCurrentSize +
        ", prepStmtCacheDeleteCount=" + prepStmtCacheDeleteCount +
        ", prepStmtCacheHitCount=" + prepStmtCacheHitCount +
        ", prepStmtCacheMissCount=" + prepStmtCacheMissCount +
        ", reserveRequestCount=" + reserveRequestCount +
        ", waitSecondsHighCount=" + waitSecondsHighCount +
        ", waitingForConnectionCurrentCount=" + waitingForConnectionCurrentCount +
        ", waitingForConnectionFailureTotal=" + waitingForConnectionFailureTotal +
        ", waitingForConnectionHighCount=" + waitingForConnectionHighCount +
        ", waitingForConnectionSuccessTotal=" + waitingForConnectionSuccessTotal +
        ", waitingForConnectionTotal=" + waitingForConnectionTotal +
        ", successfulRclbBasedBorrowCount=" + successfulRclbBasedBorrowCount +
        ", failedRCLBBasedBorrowCount=" + failedRCLBBasedBorrowCount +
        ", successfulAffinityBasedBorrowCount=" + successfulAffinityBasedBorrowCount +
        ", failedAffinityBasedBorrowCount=" + failedAffinityBasedBorrowCount +
        '}';
  }
}