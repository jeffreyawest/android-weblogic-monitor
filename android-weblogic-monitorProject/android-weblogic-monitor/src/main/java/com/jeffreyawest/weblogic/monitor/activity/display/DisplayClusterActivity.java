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
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Cluster;
import com.jeffreyawest.weblogic.entity.ClusterServer;
import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.monitor.charting.ClusterServerHealthPieChart;
import com.jeffreyawest.weblogic.monitor.charting.ClusterServerStatePieChart;

public class DisplayClusterActivity
    extends DisplayEntityActivity<Cluster>
{

  private ClusterServerHealthPieChart healthPieChart;
  private ClusterServerStatePieChart statePieChart;

  public DisplayClusterActivity()
  {

    super(Cluster.class);
    healthPieChart = new ClusterServerHealthPieChart();
    statePieChart = new ClusterServerStatePieChart();
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

    super.updateDisplay(result);

    healthPieChart.update(DisplayClusterActivity.this, result);
    statePieChart.update(DisplayClusterActivity.this, result);

    LinearLayout tableContainer = (LinearLayout) DisplayClusterActivity.this.findViewById(R.id.data_container);

    TableLayout summaryTable = new TableLayout(DisplayClusterActivity.this);
    tableContainer.addView(summaryTable);

    summaryTable.addView(getRow("Cluster Name:", result.getName()));

    for (ClusterServer server : result.getServers())
    {
      TextView header = new TextView(DisplayClusterActivity.this);
      header.setTextSize(this.getResources().getDimension(R.dimen.entity_details_table_text_size));
      header.setText(server.getName() + " (" + server.getState().toString() + ")");
      tableContainer.addView(header);

      tableContainer.addView(getServerTable(server));
    }
  }

  private View getServerTable(ClusterServer server)
  {

    TableLayout instanceTable = new TableLayout(DisplayClusterActivity.this);

    instanceTable.addView(getRow("Cluster Master:", server.getClusterMaster()));
    instanceTable.addView(getRow("Health:", server.getHealth() == null ? "N/A" : server.getHealth().toString()));
    instanceTable.addView(getRow("Dropout Freq:", server.getDropOutFrequency()));
    instanceTable.addView(getRow("Fragments Sent:", server.getFragmentsSentCount()));
    instanceTable.addView(getRow("Fragments Received:", server.getFragmentsReceivedCount()));

    return instanceTable;
  }
}
