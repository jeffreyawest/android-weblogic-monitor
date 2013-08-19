package com.jeffreyawest.weblogic.monitor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.monitor.activity.list.ListDomainEntitiesActivity;
import com.jeffreyawest.weblogic.rest.WebLogicHTTPRestAdapter;
import com.jeffreyawest.weblogic.rest.WebLogicRestAdapter;

public class EndpointEntryActivity extends Activity
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_endpoint_entry);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.endpoint_entry, menu);
    return true;
  }

  public void clickNext(View pView)
  {

    Log.v("Endpoint", "ClickGo");

    Intent intent = new Intent(this, ListDomainEntitiesActivity.class);
    EditText editHost = (EditText) findViewById(R.id.wls_host);
    EditText editPort = (EditText) findViewById(R.id.wls_port);
    EditText editUser = (EditText) findViewById(R.id.wls_user);
    EditText editPasswd = (EditText) findViewById(R.id.wls_passwd);

    int port = 7001;
    try
    {
      port = Integer.parseInt(editPort.getText().toString());
    } catch (Exception e)
    {
      e.printStackTrace();
    }

    WebLogicRestAdapter.initializeHTTPAdapter(
        editHost.getText().toString(),
        port,
        editUser.getText().toString(),
        editPasswd.getText().toString());

    startActivity(intent);
  }

  public void clickOffline(View view)
  {

    WebLogicHTTPRestAdapter.initializeDemoAdapter();
    Intent intent = new Intent(this, ListDomainEntitiesActivity.class);
    startActivity(intent);
  }
}
