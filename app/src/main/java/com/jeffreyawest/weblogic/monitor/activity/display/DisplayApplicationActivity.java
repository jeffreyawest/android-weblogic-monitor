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
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Application;
import com.jeffreyawest.weblogic.entity.ApplicationTargetState;
import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.monitor.charting.ApplicationTargetStatePieChart;

//test
public class DisplayApplicationActivity extends DisplayEntityActivity<Application>
{

  private static final String LOG_TAG = "DisplayApplicationActivity";

  public DisplayApplicationActivity()
  {

    super(Application.class);
  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_application);
  }

  @Override
  public void updateDisplay(Application app)
  {

    super.updateDisplay(app);

    setTitle(app.getName() + " (" + app.getType() + ")");
//app_target_state_chart_fragment

    FragmentManager fm = getSupportFragmentManager();

    ApplicationTargetStatePieChart targetStatePieChart = (ApplicationTargetStatePieChart) fm.findFragmentById(R.id.app_target_state_chart_fragment);
    targetStatePieChart.update(app);

    LinearLayout tableContainer = (LinearLayout) DisplayApplicationActivity.this.findViewById(R.id.data_container);

    TextView header = new TextView(DisplayApplicationActivity.this);
    header.setTextSize(this.getResources().getDimension(R.dimen.entity_details_table_text_size));
    header.setText("Application Target States");

    TableLayout stateTable = new TableLayout(DisplayApplicationActivity.this);
    tableContainer.addView(stateTable);

    for (ApplicationTargetState state : app.getTargetStates())
    {
      stateTable.addView(getRow(state.getTarget(), state.getState().toString()));
    }
  }
}
