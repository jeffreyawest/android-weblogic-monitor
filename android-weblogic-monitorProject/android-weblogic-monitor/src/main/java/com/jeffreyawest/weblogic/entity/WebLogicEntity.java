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

import java.io.Serializable;

/**
 * Created by jeffreyawest on 8/9/13.
 */
public abstract class WebLogicEntity
    implements Serializable
{

  private static final String LOG_TAG = WebLogicEntity.class.getSimpleName();

  private String originalJSON;

  @JsonProperty
  private String name;

  public String getName()
  {

    return name;
  }

  public String listViewString()
  {

    return name;
  }

  public void setOriginalJSON(String originalJSON)
  {

    this.originalJSON = originalJSON;
  }

  public String getOriginalJSON()
  {

    return originalJSON;
  }
}
