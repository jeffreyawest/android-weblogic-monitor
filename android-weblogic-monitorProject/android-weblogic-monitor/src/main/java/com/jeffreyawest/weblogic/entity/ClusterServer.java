/*
 *  Copyright (c) 2013 - Jeffrey A. West Designs
 * ************************************************************************
 * This code is provided for example purposes only.  Neither Oracle nor Jeffrey
 * A. West assume any responsibility or liability for the consequences of using
 * this code. If you choose to use this code for any reason, including but not limited to its use as an example you do so at your own risk and without the support of Oracle.
 * This code is provided under the following licenses:
 *  - GNU General Public License (GPL-2.0)
 * **************************************************************************
 */

package com.jeffreyawest.weblogic.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeffreyawest.weblogic.entity.enums.ServerHealth;
import com.jeffreyawest.weblogic.entity.enums.ServerState;

/**
 * Created by jeffreyawest on 8/12/13.
 */
public class ClusterServer extends WebLogicEntity
{

  @JsonProperty
  protected String name;
  @JsonProperty
  protected ServerState state;
  @JsonProperty
  protected ServerHealth health;
  @JsonProperty
  protected String clusterMaster;
  @JsonProperty
  protected String dropOutFrequency;
  @JsonProperty
  protected String resendRequestsCount;
  @JsonProperty
  protected String fragmentsSentCount;
  @JsonProperty
  protected String fragmentsReceivedCount;

  @Override
  public String listViewString()
  {

    return "Server: " + name;
  }

  public String getName()
  {

    return name;
  }

  public ServerState getState()
  {

    return state;
  }

  public ServerHealth getHealth()
  {

    return health;
  }

  public String getClusterMaster()
  {

    return clusterMaster;
  }

  public String getDropOutFrequency()
  {

    return dropOutFrequency;
  }

  public String getResendRequestsCount()
  {

    return resendRequestsCount;
  }

  public String getFragmentsSentCount()
  {

    return fragmentsSentCount;
  }

  public String getFragmentsReceivedCount()
  {

    return fragmentsReceivedCount;
  }

  @Override
  public String toString()
  {

    return "ClusterServer{" +
        "state='" + state + '\'' +
        ", name='" + name + '\'' +
        ", health='" + health + '\'' +
        ", clusterMaster='" + clusterMaster + '\'' +
        ", dropOutFrequency='" + dropOutFrequency + '\'' +
        ", resendRequestsCount='" + resendRequestsCount + '\'' +
        ", fragmentsSentCount='" + fragmentsSentCount + '\'' +
        ", fragmentsReceivedCount='" + fragmentsReceivedCount + '\'' +
        '}';
  }
}
