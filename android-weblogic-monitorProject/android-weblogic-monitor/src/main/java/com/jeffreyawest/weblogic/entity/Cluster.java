package com.jeffreyawest.weblogic.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
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
