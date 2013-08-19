package com.jeffreyawest.weblogic.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeffreyawest.weblogic.entity.enums.DatasourceType;

import java.util.List;

/**
 * **************************************************************************
 * This code is provided for example purposes only.  Neither Oracle nor Jeffrey
 * A. West assume any responsibility or liability for the consequences of using
 * this code.
 * <p/>
 * If you choose to use this code for any reason, including but not limited
 * to its use as an example you do so at your own risk and without the support
 * of Oracle.
 * <p/>
 * This code is provided under the following licenses:
 * <p/>
 * GNU General Public License (GPL-2.0)
 * <p/>
 * ****************************************************************************
 * <p/>
 * Created by jeffreyawest  on 8/9/13.
 */
public class Datasource extends WebLogicEntity
{

  private static final String LOG_TAG = "Datasource";

  @JsonProperty
  protected List<DatasourceInstance> instances;

  @JsonProperty
  protected String name;

  @JsonProperty
  protected DatasourceType type;

  public List<DatasourceInstance> getInstances()
  {

    return instances;
  }

  public String getName()
  {

    return name;
  }

  public DatasourceType getType()
  {

    return type;
  }

  @Override
  public String listViewString()
  {

    return name + " / " + type;
  }

  @Override
  public String toString()
  {

    return "Datasource{" +
        "instances=" + instances +
        ", name='" + name + '\'' +
        ", type='" + type + '\'' +
        '}';
  }
}
