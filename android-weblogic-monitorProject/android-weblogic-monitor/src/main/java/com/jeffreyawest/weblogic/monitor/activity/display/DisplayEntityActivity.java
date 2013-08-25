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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TableRow;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.WebLogicEntity;
import com.jeffreyawest.weblogic.monitor.Constants;
import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.monitor.task.RetrieveWebLogicEntityTask;

public abstract class DisplayEntityActivity<T extends WebLogicEntity> extends FragmentActivity
{

  private static final String LOG_TAG = "DisplayEntityActivity<T>";
  protected Class<T> theClass;

  public DisplayEntityActivity(Class<T> theClass)
  {

    this.theClass = theClass;
  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {

    super.onCreate(savedInstanceState);

    Intent intent = getIntent();
    String entityName = intent.getStringExtra(Constants.ENTITY_NAME);

    TextView tView = (TextView) findViewById(R.id.monitor_header);
    if (tView != null)
      tView.setText(theClass.getSimpleName() + ": " + entityName);

    if (tView != null)
    {
      tView = (TextView) findViewById(R.id.original_json);
      tView.setText("Getting data for " + theClass.getSimpleName() + ":" + entityName);
    }

    new RetrieveWebLogicEntityTask<T>(this, theClass).execute(entityName);
  }

  public void updateDisplay(T entity)
  {

    try
    {
      TextView txtJSON = (TextView) this.findViewById(R.id.original_json);
      txtJSON.setText(entity.getOriginalJSON());
    } catch (Exception e)
    {
      Log.e(LOG_TAG,
            "Error setting original JSON", e);
    }
  }

  public TableRow getRow(String pLabel, String pValue)
  {

    TableRow row = new TableRow(this);

    TextView textView = new TextView(this);
    textView.setText(pLabel);

    float dimension = this.getResources().getDimension(R.dimen.entity_details_table_text_size);

    textView.setTextSize(dimension);
    row.addView(textView);

    textView = new TextView(this);
    textView.setText(pValue);
    textView.setTextSize(dimension);
    row.addView(textView);

    return row;
  }
}
