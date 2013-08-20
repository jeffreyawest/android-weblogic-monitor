package com.jeffreyawest.weblogic.monitor.activity.list.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Server;
import com.jeffreyawest.weblogic.monitor.R;

import java.util.List;

/**
 * Created by jeffreyawest on 8/15/13.
 */
public class ServerListAdapter extends ArrayAdapter<Server>
{
  private static final String LOG_TAG = "ServerListAdapter";
  private final Context context;
  private final List<Server> list;

  public ServerListAdapter(Context context, int resource, List<Server> list)
  {

    super(context, resource, list);

    this.context = context;
    this.list = list;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent)
  {

    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    View rowView = inflater.inflate(R.layout.list_item_server, parent, false);

    if (rowView != null)
    {
      TextView textView = null;
      textView = (TextView) rowView.findViewById(R.id.entity_name);

      Server server = list.get(position);

      if (textView != null)
      {
        textView.setText(server.getName());
      }

      //state
      textView = (TextView) rowView.findViewById(R.id.state_text);

      if (textView != null)
      {
        if (server.getState() != null)
        {
          textView.setText(server.getState().toString());
//          textView.setBackgroundColor(context.getResources().getColor(server.getState().getColorID()));

          int stateColor = context.getResources().getColor(server.getState().getColorID());

          Log.v(LOG_TAG, "Server Name=[" + server.getName()
              + "] state=[" + server.getState()
              + "] stateColor=[" + stateColor + "]");

          View healthGraphic = rowView.findViewById(R.id.state_graphic);
          healthGraphic.setBackgroundColor(stateColor);
        }
        else
        {
          textView.setText("N/A");
        }
      }

      //health
      textView = (TextView) rowView.findViewById(R.id.health_text);

      if (textView != null)
      {
        if (server.getHealth() != null)
        {
          textView.setText(server.getHealth().toString());
//          textView.setBackgroundColor(context.getResources().getColor(server.getHealth().getColorID()));

          int healthColor = context.getResources().getColor(server.getHealth().getColorID());

          Log.v(LOG_TAG, "Server Name=[" + server.getName()
              + "] state=[" + server.getState()
              + "] stateColor=[" + healthColor + "]");

          View healthGraphic = rowView.findViewById(R.id.health_graphic);
          healthGraphic.setBackgroundColor(healthColor);
        }
        else
        {
          textView.setText("N/A");
        }
      }
    }

    return rowView;
  }
}
