package com.jeffreyawest.weblogic.monitor.activity.list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Application;
import com.jeffreyawest.weblogic.monitor.R;

import java.util.List;

/**
 * Created by jeffreyawest on 8/15/13.
 */
public class ApplicationListAdapter extends ArrayAdapter<Application>
{

  private final Context context;
  private final List<Application> list;

  public ApplicationListAdapter(Context context, int resource, List<Application> list)
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

    View rowView = inflater.inflate(R.layout.list_item_application, parent, false);

    if (rowView != null)
    {
      TextView textView = null;
      textView = (TextView) rowView.findViewById(R.id.entity_name);

      if (textView != null)
      {
        textView.setText(list.get(position).getName());
      }
    }

    return rowView;
  }
}
