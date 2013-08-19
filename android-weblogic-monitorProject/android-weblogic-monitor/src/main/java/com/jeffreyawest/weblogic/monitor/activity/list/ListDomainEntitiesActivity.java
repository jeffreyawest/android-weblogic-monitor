package com.jeffreyawest.weblogic.monitor.activity.list;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jeffreyawest.weblogic.monitor.Constants;
import com.jeffreyawest.weblogic.monitor.R;

public class ListDomainEntitiesActivity
    extends Activity
    implements View.OnTouchListener, View.OnClickListener
{

  private static final String LOG_TAG = "ListDomainEntitiesActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_domain_entities);

    TextView tView;
    tView = (TextView) findViewById(R.id.servers);
    tView.setOnTouchListener(this);

    tView = (TextView) findViewById(R.id.clusters);
    tView.setOnTouchListener(this);

    tView = (TextView) findViewById(R.id.datasources);
    tView.setOnTouchListener(this);

    tView = (TextView) findViewById(R.id.applications);
    tView.setOnTouchListener(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.list_entities, menu);
    return true;
  }

  @Override
  public boolean onTouch(View v, MotionEvent event)
  {

    Log.v(LOG_TAG, "View Touched - ID=[" + v.getId()
        + "] class=[" + v.getClass().getSimpleName()
        + "] event: " + event.toString());

    if (v instanceof TextView)
    {
      TextView tv = (TextView) v;
      Log.v(LOG_TAG, "TextView Touched - Text: " + tv.getText());
      String listType = tv.getText().toString();

      try
      {

        String className = "com.jeffreyawest.weblogic.monitor.activity.list.List" + listType + "Activity";

        Log.v(LOG_TAG, "Attempting to start activity [" + className + "]");

        Class theClass = Class.forName(className);

        Intent intent = new Intent(ListDomainEntitiesActivity.this, theClass);

        intent.putExtra(Constants.ENTITY_CLASS, listType);

        ListDomainEntitiesActivity.this.startActivity(intent);
      } catch (ClassNotFoundException e)
      {
        Log.e(LOG_TAG, Log.getStackTraceString(e));
        e.printStackTrace();
      }
    }

    return false;
  }

  @Override
  public void onClick(View v)
  {

    Log.v(LOG_TAG, "View Clicked: " + v.getId() + " Class: " + v.getClass().getName());
  }
}
