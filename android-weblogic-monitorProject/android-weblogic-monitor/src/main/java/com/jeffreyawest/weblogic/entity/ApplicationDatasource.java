package com.jeffreyawest.weblogic.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeffreyawest.weblogic.entity.enums.DatasourceInstanceState;

/**
 * Created by jeffreyawest on 8/12/13.
 */
public class ApplicationDatasource extends WebLogicEntity
{

  @JsonProperty
  protected String name;

  @JsonProperty
  protected String server;

  @JsonProperty
  protected DatasourceInstanceState state;

  @Override
  public String toString()
  {

    return "ApplicationDatasource{" +
        "name='" + name + '\'' +
        ", server='" + server + '\'' +
        ", state='" + state + '\'' +
        '}';
  }
}
