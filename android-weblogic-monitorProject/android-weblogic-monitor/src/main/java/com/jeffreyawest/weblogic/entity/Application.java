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
import com.jeffreyawest.weblogic.entity.enums.ApplicationHealth;
import com.jeffreyawest.weblogic.entity.enums.ApplicationState;

import java.util.List;

/**
 * Created by jeffreyawest  on 8/9/13.
 */
public class Application extends WebLogicEntity
{

  @JsonProperty
  protected String name;

  @JsonProperty
  protected ApplicationHealth health;

  @JsonProperty
  protected ApplicationState state;

  @JsonProperty
  protected String type;

  @JsonProperty
  protected List<ApplicationTargetState> targetStates;

  @JsonProperty
  protected List<MaxThreadsConstraint> maxThreadsConstraints;

  @JsonProperty
  protected List<MinThreadsConstraint> minThreadsConstraints;

  @JsonProperty
  protected List<WorkManager> workManagers;

  @JsonProperty
  protected List<RequestClass> requestClasses;

  @JsonProperty
  protected List<ApplicationDatasource> dataSources;

  public Application()
  {

  }

  public String getName()
  {

    return name;
  }

  public ApplicationHealth getHealth()
  {

    return health;
  }

  public ApplicationState getState()
  {

    return state;
  }

  public String getType()
  {

    return type;
  }

  public List<ApplicationTargetState> getTargetStates()
  {

    return targetStates;
  }

  public List<MaxThreadsConstraint> getMaxThreadsConstraints()
  {

    return maxThreadsConstraints;
  }

  public List<MinThreadsConstraint> getMinThreadsConstraints()
  {

    return minThreadsConstraints;
  }

  public List<WorkManager> getWorkManagers()
  {

    return workManagers;
  }

  public List<RequestClass> getRequestClasses()
  {

    return requestClasses;
  }

  public List<ApplicationDatasource> getDataSources()
  {

    return dataSources;
  }

  @Override
  public String toString()
  {

    return "Application{" +
        "name='" + name + '\'' +
        ", health=" + health +
        ", state=" + state +
        ", type='" + type + '\'' +
        ", targetStates=" + targetStates +
        '}';
  }

  @Override
  public String listViewString()
  {

    StringBuilder sb = new StringBuilder();
    sb.append(name);
    sb.append(" / ").append(type);
    sb.append(" / ").append(state);

    sb.append(" / ").append(health);

    return sb.toString();
  }
}
