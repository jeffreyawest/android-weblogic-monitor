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

    setTitle(ds.getName() + " (" + ds.getType() + ")");

    datasourceInstancePieChart.update(DisplayDatasourceActivity.this, ds);

    LinearLayout tableContainer = (LinearLayout) DisplayDatasourceActivity.this.findViewById(R.id.data_container);

    TableLayout summaryTable = new TableLayout(DisplayDatasourceActivity.this);
    tableContainer.addView(summaryTable);

    summaryTable.addView(getRow(R.string.name, ds.getName()));
    summaryTable.addView(getRow(R.string.type, ds.getType().toString()));

    for (DatasourceInstance instance : ds.getInstances())
    {
      TextView header = new TextView(DisplayDatasourceActivity.this);
      header.setTextSize(this.getResources().getDimension(R.dimen.entity_details_table_text_size));
      header.setText(getResources().getString(R.string.instance) + ": " + instance.getName() + " (" + instance.getState().toString() + ")");
      tableContainer.addView(header);

      tableContainer.addView(getInstanceTable(instance));
    }
  }

  private View getInstanceTable(DatasourceInstance instance)
  {
    TableLayout instanceTable = new TableLayout(DisplayDatasourceActivity.this);

    instanceTable.addView(getRow(R.string.server, instance.getServer()));
    instanceTable.addView(getRow(R.string.enabled, instance.getEnabled()));

    return instanceTable;
  }
}
