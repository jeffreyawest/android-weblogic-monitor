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
 * **************************************************************************
 * This code is provided for example purposes only.  Neither Oracle nor Jeffrey
 * A. West assume any responsibility or liability for the consequences of using
 * this code.
 *
 * If you choose to use this code for any reason, including but not limited
 * to its use as an example you do so at your own risk and without the support
 * of Oracle.
 *
 * This code is provided under the following licenses:
 *
 * GNU General Public License (GPL-2.0)
 *
 * ****************************************************************************
*/

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Cluster;
import com.jeffreyawest.weblogic.entity.ClusterServer;
import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.monitor.charting.ClusterServerHealthPieChart;
import com.jeffreyawest.weblogic.monitor.charting.ClusterServerStatePieChart;

public class DisplayClusterActivity
    extends DisplayEntityActivity<Cluster>
{

  public DisplayClusterActivity()
  {

    super(Cluster.class);
  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_cluster);
  }

  @Override
  public void updateDisplay(Cluster result)
  {
    setTitle(result.getName());

    super.updateDisplay(result);
    FragmentManager fm = getSupportFragmentManager();

    ClusterServerHealthPieChart healthPieChart = (ClusterServerHealthPieChart) fm.findFragmentById(R.id.server_health_chart_fragment);

    if (healthPieChart != null)
      healthPieChart.update(result);

    ClusterServerStatePieChart statePieChart = (ClusterServerStatePieChart) fm.findFragmentById(R.id.server_state_chart_fragment);

    if (statePieChart != null)
      statePieChart.update(result);

    LinearLayout tableContainer = (LinearLayout) DisplayClusterActivity.this.findViewById(R.id.data_container);

    TableLayout summaryTable = new TableLayout(DisplayClusterActivity.this);
    tableContainer.addView(summaryTable);

    summaryTable.addView(getRow(R.string.cluster_name, result.getName()));

    for (ClusterServer server : result.getServers())
    {
      tableContainer.addView(getSeparatorRow());
      tableContainer.addView(getServerTable(server));
    }
  }

  private View getSeparatorRow()
  {
    TableRow row = new TableRow(this);
    TextView sep;

    sep = new TextView(this);
    sep.setText("---");
    row.addView(sep);
    return row;
  }

  private View getServerTable(ClusterServer server)
  {

    TableLayout instanceTable = new TableLayout(DisplayClusterActivity.this);

    instanceTable.addView(getRow(R.string.server_name, server.getName()));
    instanceTable.addView(getRow(R.string.cluster_master, server.getClusterMaster()));
    instanceTable.addView(getRow(R.string.server_health, server.getHealth() == null ?
        getResources().getString(R.string.fragments_received) : server.getHealth().toString()));
    instanceTable.addView(getRow(R.string.dropout_frequency, server.getDropOutFrequency()));
    instanceTable.addView(getRow(R.string.fragments_sent, server.getFragmentsSentCount()));
    instanceTable.addView(getRow(R.string.fragments_received, server.getFragmentsReceivedCount()));

    return instanceTable;
  }
}
