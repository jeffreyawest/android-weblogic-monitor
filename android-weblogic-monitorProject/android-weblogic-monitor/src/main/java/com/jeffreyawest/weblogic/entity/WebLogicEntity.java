package com.jeffreyawest.weblogic.entity;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

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
