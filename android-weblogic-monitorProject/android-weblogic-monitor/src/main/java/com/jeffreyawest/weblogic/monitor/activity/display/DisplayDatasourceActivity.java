package com.jeffreyawest.weblogic.monitor.activity.display;

/*
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
 * Created by jeffreyawest
 */

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Datasource;
import com.jeffreyawest.weblogic.entity.DatasourceInstance;
import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.monitor.charting.DatasourceInstancePieChart;

public class DisplayDatasourceActivity extends DisplayEntityActivity<Datasource>
{

  private DatasourceInstancePieChart datasourceInstancePieChart;

  public DisplayDatasourceActivity()
  {

    super(Datasource.class);
    datasourceInstancePieChart = new DatasourceInstancePieChart();
  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_datasource);
  }

  @Override
  public void updateDisplay(Datasource ds)
  {

    super.updateDisplay(ds);

    TextView serverHeader = (TextView) DisplayDatasourceActivity.this.findViewById(R.id.monitor_header);
    serverHeader.setText("Datasource: " + ds.getName() + " (" + ds.getType() + ")");

    datasourceInstancePieChart.update(DisplayDatasourceActivity.this, ds);

    LinearLayout tableContainer = (LinearLayout) DisplayDatasourceActivity.this.findViewById(R.id.data_container);

    TableLayout summaryTable = new TableLayout(DisplayDatasourceActivity.this);
    tableContainer.addView(summaryTable);

    summaryTable.addView(getRow("Name:", ds.getName()));
    summaryTable.addView(getRow("Type:", ds.getType().toString()));

    for (DatasourceInstance instance : ds.getInstances())
    {
      TextView header = new TextView(DisplayDatasourceActivity.this);
      header.setTextSize(this.getResources().getDimension(R.dimen.entity_details_table_text_size));
      header.setText("Instance: " + instance.getName() + " (" + instance.getState().toString() + ")");
      tableContainer.addView(header);

      tableContainer.addView(getInstanceTable(instance));
    }
  }

  private View getInstanceTable(DatasourceInstance instance)
  {

    TableLayout instanceTable = new TableLayout(DisplayDatasourceActivity.this);

    instanceTable.addView(getRow("Server:", instance.getServer()));
    instanceTable.addView(getRow("Enabled:", instance.getEnabled()));

    return instanceTable;
  }
}