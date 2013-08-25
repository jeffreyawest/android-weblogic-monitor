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

package com.jeffreyawest.weblogic.monitor.activity.display;

/*
 * Created by jeffreyawest
 */

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Server;
import com.jeffreyawest.weblogic.monitor.Constants;
import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.monitor.charting.CPUPieChart;
import com.jeffreyawest.weblogic.monitor.charting.HeapPieChart;
import com.jeffreyawest.weblogic.monitor.charting.fragment.CPUChartFragment;
import com.jeffreyawest.weblogic.monitor.charting.fragment.HeapChartFragment;

public class DisplayServerActivity extends DisplayEntityActivity<Server>
{

  private HeapPieChart mHeapPieChart;
  private CPUPieChart mJvmCPUPieChart;

  public DisplayServerActivity()
  {

    super(Server.class);
    mHeapPieChart = new HeapPieChart();
    mJvmCPUPieChart = new CPUPieChart();
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

    StringBuilder sb = new StringBuilder("Server: ");

    if (server.getClusterName() != null
        && !server.getClusterName().isEmpty()
        && !server.getClusterName().equals("null"))
    {

      sb.append(server.getClusterName()).append(".");
    }

    sb.append(server.getName());
    sb.append(" (").append(server.getState()).append(")");

    setTitle(sb.toString());

    TextView serverHeader = (TextView) this.findViewById(R.id.monitor_header);
    serverHeader.setText(sb.toString());

    mHeapPieChart.update(this, server);
    mJvmCPUPieChart.update(this, server);

    String clusterName = server.getClusterName();

    if (clusterName == null
        || clusterName.isEmpty()
        || "null".equals(clusterName))
    {
      clusterName = "N/A";
    }

    String currentMachine = server.getCurrentMachine();

    if (currentMachine == null
        || currentMachine.isEmpty()
        || "null".equals(currentMachine))
    {
      currentMachine = "N/A";
    }

    int heapMaxMB = server.getHeapSizeMax() / Constants.ONE_MB;
    int heapFreeMB = heapMaxMB - (server.getHeapSizeCurrent() / Constants.ONE_MB);

    int heapCurrentMB = server.getHeapSizeCurrent() / Constants.ONE_MB;
    int heapCurrentUsedMB = (server.getHeapSizeCurrent() - server.getHeapFreeCurrent()) / Constants.ONE_MB;
    int heapCurrentFreeMB = server.getHeapFreeCurrent() / Constants.ONE_MB;

    LinearLayout tableContainer = (LinearLayout) this.findViewById(R.id.data_container);

    TableLayout summaryTable = new TableLayout(this);
    tableContainer.addView(summaryTable);

    summaryTable.addView(getRow("Server Name:", server.getName()));
    summaryTable.addView(getRow("Server State:", String.valueOf(server.getState())));
    summaryTable.addView(getRow("Server Health:", String.valueOf(server.getHealth())));
    summaryTable.addView(getRow("Cluster Name:", clusterName));
    summaryTable.addView(getRow("Current Machine:", currentMachine));
    summaryTable.addView(getRow("WLS Version:", server.getWeblogicVersion()));
    summaryTable.addView(getRow("Operating System:", server.getoSName() + " " + server.getoSVersion()));
    summaryTable.addView(getRow("Operating System:", server.getName()));
    summaryTable.addView(getRow("JVM Version:", server.getJavaVersion()));
    summaryTable.addView(getRow("Open Socket Count:", String.valueOf(server.getOpenSocketsCurrentCount())));

    summaryTable.addView(getRow("Heap Max:", String.valueOf(heapMaxMB) + " MB"));
    summaryTable.addView(getRow("Heap Free:", String.valueOf(heapFreeMB) + " MB"));

    summaryTable.addView(getRow("Heap Current:", String.valueOf(heapCurrentMB) + " MB"));
    summaryTable.addView(getRow("Heap Current Used:", String.valueOf(heapCurrentUsedMB) + " MB"));
    summaryTable.addView(getRow("Heap Current Free:", String.valueOf(heapCurrentFreeMB) + " MB"));

    serverHeader.setText(sb.toString());
    FragmentManager fm = getSupportFragmentManager();

    CPUChartFragment cpuChart = (CPUChartFragment) fm.findFragmentById(R.id.cpu_chart_fragment);
    cpuChart.updateDisplay(server);

    HeapChartFragment heapChart = (HeapChartFragment) fm.findFragmentById(R.id.heap_chart_fragment);
    heapChart.updateDisplay(server);
  }
}
