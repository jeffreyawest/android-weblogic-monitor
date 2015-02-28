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

import java.util.List;

/**
 * Unless otherwise stated explicitly this code is licensed under a GPL license, the specific
 * version of which can be found at the root of this project.
 * <p/>
 * Created by jeffreyawest  on 8/9/13.
 */
public class Cluster extends WebLogicEntity
{

  @JsonProperty
  protected List<ClusterServer> servers;

  @JsonProperty
  protected String name;

  @Override
  public String listViewString()
  {

    return name + " / Servers: " + servers.size();
  }

  @Override
  public String toString()
  {

    StringBuilder sb = new StringBuilder("Cluster{" +
                                             "name='" + name + '\'' +
                                             ", servers=");

    for (ClusterServer server : servers)
    {
      sb.append(server.toString()).append(',');
    }

    sb.append('}');

    return sb.toString();
  }

  public String getName()
  {

    return name;
  }

  public List<ClusterServer> getServers()
  {

    return servers;
  }
}
