package com.jeffreyawest.weblogic.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeffreyawest.weblogic.entity.enums.ServerHealth;
import com.jeffreyawest.weblogic.entity.enums.ServerState;

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
public class Server extends WebLogicEntity
{

  @JsonProperty
  protected String name;
  @JsonProperty
  protected ServerState state;
  @JsonProperty
  protected ServerHealth health;

  @JsonProperty
  protected String clusterName;
  @JsonProperty
  protected String currentMachine;
  @JsonProperty
  protected String weblogicVersion;
  @JsonProperty
  protected String javaVersion;
  @JsonProperty
  protected String oSName;
  @JsonProperty
  protected String oSVersion;
  @JsonProperty
  protected double jvmProcessorLoad;

  @JsonProperty
  protected int openSocketsCurrentCount;
  @JsonProperty
  protected int heapSizeCurrent;
  @JsonProperty
  protected int heapSizeMax;
  @JsonProperty
  protected int heapFreeCurrent;

  public double getPercentHeapCurrent()
  {

    return (double) this.getHeapSizeCurrent() / (double) this.getHeapSizeMax();
  }

  public double getPercentHeapUnallocated()
  {

    return 1.0 - (double) this.getHeapSizeCurrent() / (double) this.getHeapSizeMax();
  }

  public double getPercentHeapCurrentUsed()
  {

    return ((double) getHeapSizeCurrent() - (double) getHeapFreeCurrent()) / (double) getHeapSizeMax();
  }

  public double getPercentHeapCurrentFree()
  {

    return (double) getHeapFreeCurrent() / (double) getHeapSizeMax();
  }

  @Override
  public String listViewString()
  {

    return name + " / " + state + " / " + health;
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

  public String getClusterName()
  {

    return clusterName;
  }

  public String getCurrentMachine()
  {

    return currentMachine;
  }

  public String getWeblogicVersion()
  {

    return weblogicVersion;
  }

  public String getJavaVersion()
  {

    return javaVersion;
  }

  public String getoSVersion()
  {

    return oSVersion;
  }

  public int getOpenSocketsCurrentCount()
  {

    return openSocketsCurrentCount;
  }

  public int getHeapSizeMax()
  {

    return heapSizeMax;
  }

  public int getHeapSizeFree()
  {

    return heapSizeMax - heapSizeCurrent;
  }

  public int getHeapSizeCurrent()
  {

    return heapSizeCurrent;
  }

  public int getHeapFreeCurrent()
  {

    return heapFreeCurrent;
  }

  public int getHeapUsedCurrent()
  {

    return heapSizeCurrent - heapFreeCurrent;
  }

  public String getoSName()
  {

    return oSName;
  }

  public double getJvmProcessorLoad()
  {

    return jvmProcessorLoad;
  }

  public void setName(String name)
  {

    this.name = name;
  }

  public void setState(ServerState state)
  {

    this.state = state;
  }

  public void setHealth(ServerHealth health)
  {

    this.health = health;
  }

  public void setClusterName(String clusterName)
  {

    this.clusterName = clusterName;
  }

  public void setCurrentMachine(String currentMachine)
  {

    this.currentMachine = currentMachine;
  }

  public void setWeblogicVersion(String weblogicVersion)
  {

    this.weblogicVersion = weblogicVersion;
  }

  public void setJavaVersion(String javaVersion)
  {

    this.javaVersion = javaVersion;
  }

  public void setoSName(String oSName)
  {

    this.oSName = oSName;
  }

  public void setoSVersion(String oSVersion)
  {

    this.oSVersion = oSVersion;
  }

  public void setJvmProcessorLoad(double jvmProcessorLoad)
  {

    this.jvmProcessorLoad = jvmProcessorLoad;
  }

  public void setOpenSocketsCurrentCount(int openSocketsCurrentCount)
  {

    this.openSocketsCurrentCount = openSocketsCurrentCount;
  }

  public void setHeapSizeCurrent(int heapSizeCurrent)
  {

    this.heapSizeCurrent = heapSizeCurrent;
  }

  public void setHeapSizeMax(int heapSizeMax)
  {

    this.heapSizeMax = heapSizeMax;
  }

  public void setHeapFreeCurrent(int heapFreeCurrent)
  {

    this.heapFreeCurrent = heapFreeCurrent;
  }

  @Override
  public String toString()
  {

    return "Server{" +
        "name='" + name + '\'' +
        ", state='" + state + '\'' +
        ", health='" + health + '\'' +
        ", clusterName='" + clusterName + '\'' +
        ", currentMachine='" + currentMachine + '\'' +
        ", weblogicVersion='" + weblogicVersion + '\'' +
        ", javaVersion='" + javaVersion + '\'' +
        ", oSName='" + oSName + '\'' +
        ", oSVersion='" + oSVersion + '\'' +
        ", jvmProcessorLoad='" + jvmProcessorLoad + '\'' +
        ", openSocketsCurrentCount=" + openSocketsCurrentCount +
        ", heapSizeCurrent=" + heapSizeCurrent +
        ", heapSizeMax=" + heapSizeMax +
        ", heapFreeCurrent=" + heapFreeCurrent +
        '}';
  }
}
