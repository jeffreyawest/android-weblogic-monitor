package com.jeffreyawest.weblogic.monitor.activity.list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Cluster;
import com.jeffreyawest.weblogic.entity.ClusterServer;
import com.jeffreyawest.weblogic.entity.Datasource;
import com.jeffreyawest.weblogic.entity.DatasourceInstance;
import com.jeffreyawest.weblogic.entity.enums.DatasourceInstanceState;
import com.jeffreyawest.weblogic.entity.enums.ServerHealth;
import com.jeffreyawest.weblogic.entity.enums.ServerState;
import com.jeffreyawest.weblogic.monitor.R;

import java.util.List;

/**
 * Created by jeffreyawest on 8/15/13.
 */
public class DatasourceListAdapter extends ArrayAdapter<Datasource>
{

  private final Context context;
  private final List<Datasource> list;

  public DatasourceListAdapter(Context context, int resource, List<Datasource> list)
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

    View rowView = inflater.inflate(R.layout.list_item_datasource, parent, false);

    if (rowView != null)
    {
      TextView textView = null;
      textView = (TextView) rowView.findViewById(R.id.entity_name);

      if (textView != null)
      {
        textView.setText(list.get(position).getName());
      }

      Datasource ds = list.get(position);

      DatasourceInstanceState maxState = DatasourceInstanceState.Running;

      for (DatasourceInstance instance : ds.getInstances())
      {

        if (instance.getState() != null
            && maxState.getIntValue() < instance.getState().getIntValue())
        {

          maxState = instance.getState();
        }
      }

      TextView stateText = (TextView) rowView.findViewById(R.id.state_text);

      if (stateText != null)
        stateText.setText(maxState.toString());

      View stateGraphic = rowView.findViewById(R.id.state_graphic);
      if (stateGraphic != null)
      {
        int color = context.getResources().getColor(maxState.getColorID());
        stateGraphic.setBackgroundColor(color);
      }
    }
    return rowView;
  }
}