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

package com.jeffreyawest.weblogic.monitor.activity.display;

/*
 * Created by jeffreyawest
 */

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.TableLayout;

import com.jeffreyawest.weblogic.entity.Server;
import com.jeffreyawest.weblogic.monitor.Constants;
import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.monitor.charting.JVMCPUPieChart;
import com.jeffreyawest.weblogic.monitor.charting.JVMHeapPieChart;

public class DisplayServerActivity extends DisplayEntityActivity<Server>
{

  public DisplayServerActivity()
  {

    super(Server.class);
  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_server);
  }

  @Override
  public void updateDisplay(Server server)
  {

    super.updateDisplay(server);

    StringBuilder sb = new StringBuilder();

    if (server.getClusterName() != null
        && !server.getClusterName().isEmpty()
        && !server.getClusterName().equals("null"))
    {

      sb.append(server.getClusterName()).append(".");
    }

    sb.append(server.getName());
    sb.append(" (").append(server.getState()).append(")");

    setTitle(sb.toString());

    String clusterName = server.getClusterName();

    if (clusterName == null
        || clusterName.isEmpty()
        || "null".equals(clusterName))
    {
      clusterName = getResources().getString(R.string.n_a);
    }

    String currentMachine = server.getCurrentMachine();

    if (currentMachine == null
        || currentMachine.isEmpty()
        || "null".equals(currentMachine))
    {
      currentMachine = getResources().getString(R.string.n_a);
    }

    int heapMaxMB = server.getHeapSizeMax() / Constants.ONE_MB;
    int heapFreeMB = heapMaxMB - (server.getHeapSizeCurrent() / Constants.ONE_MB);

    int heapCurrentMB = server.getHeapSizeCurrent() / Constants.ONE_MB;
    int heapCurrentUsedMB = (server.getHeapSizeCurrent() - server.getHeapFreeCurrent()) / Constants.ONE_MB;
    int heapCurrentFreeMB = server.getHeapFreeCurrent() / Constants.ONE_MB;

    TableLayout summaryTable = (TableLayout) this.findViewById(R.id.display_entity_detail_table);

    summaryTable.addView(getRow(R.string.server_name, server.getName()));
    summaryTable.addView(getRow(R.string.server_state, String.valueOf(server.getState())));
    summaryTable.addView(getRow(R.string.server_health, String.valueOf(server.getHealth())));
    summaryTable.addView(getRow(R.string.cluster_name, clusterName));
    summaryTable.addView(getRow(R.string.current_machine, currentMachine));
    summaryTable.addView(getRow(R.string.wls_version, server.getWeblogicVersion()));
    summaryTable.addView(getRow(R.string.operating_system, server.getoSName() + " " + server.getoSVersion()));
    summaryTable.addView(getRow(R.string.os_version, server.getoSVersion()));
    summaryTable.addView(getRow(R.string.jvm_version, server.getJavaVersion()));
    summaryTable.addView(getRow(R.string.open_sockets, String.valueOf(server.getOpenSocketsCurrentCount())));

    summaryTable.addView(getRow(R.string.heap_max, String.valueOf(heapMaxMB) + " MB"));
    summaryTable.addView(getRow(R.string.heap_free, String.valueOf(heapFreeMB) + " MB"));

    summaryTable.addView(getRow(R.string.heap_current, String.valueOf(heapCurrentMB) + " MB"));
    summaryTable.addView(getRow(R.string.heap_allocated_used, String.valueOf(heapCurrentUsedMB) + " MB"));
    summaryTable.addView(getRow(R.string.heap_allocated_free, String.valueOf(heapCurrentFreeMB) + " MB"));

    FragmentManager fm = getSupportFragmentManager();

    JVMCPUPieChart cpuChart = (JVMCPUPieChart) fm.findFragmentById(R.id.cpu_chart_fragment);
    cpuChart.update(server);

    JVMHeapPieChart heapChart = (JVMHeapPieChart) fm.findFragmentById(R.id.heap_chart_fragment);
    heapChart.update(server);
  }
}
