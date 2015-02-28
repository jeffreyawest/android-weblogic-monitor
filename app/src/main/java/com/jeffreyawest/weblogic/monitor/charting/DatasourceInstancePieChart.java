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

package com.jeffreyawest.weblogic.monitor.charting;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Datasource;
import com.jeffreyawest.weblogic.entity.DatasourceInstance;
import com.jeffreyawest.weblogic.entity.enums.DatasourceInstanceState;
import com.jeffreyawest.weblogic.monitor.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jeffreyawest on 8/10/13.
 */
public class DatasourceInstancePieChart extends DefaultPieChart
{

  private static final DecimalFormat percentFormat = new DecimalFormat("#");

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState)
  {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.chart_view_ds_instance_state, container, false);

    return view;
  }

  public void update(Activity activity, Datasource pDatasource)
  {

    TextView healthHeader = (TextView) activity.findViewById(R.id.instance_chart_header);
    if (healthHeader != null)
      healthHeader.setText(activity.getString(R.string.datasource_instance_state));

    HashMap<DatasourceInstanceState, Integer> countMap = new HashMap<DatasourceInstanceState, Integer>(7);

    for (DatasourceInstance instance : pDatasource.getInstances())
    {
      DatasourceInstanceState health = instance.getState();

      if (health == null || health.toString().equals("null"))
      {
        health = DatasourceInstanceState.Unknown;
      }

      Integer theCount = countMap.get(health);

      if (theCount == null)
      {
        theCount = 0;
      }

      countMap.put(health, ++theCount);
    }

    List<Integer> valueList = new ArrayList<Integer>(countMap.size());
    List<Integer> colorList = new ArrayList<Integer>(countMap.size());
    List<String> nameList = new ArrayList<String>(countMap.size());

    for (Map.Entry<DatasourceInstanceState, Integer> entry : countMap.entrySet())
    {
      colorList.add(activity.getResources().getColor(entry.getKey().getColorID()));
      valueList.add(entry.getValue());
      nameList.add(entry.getKey().toString());
    }

    Integer[] VALUES = valueList.toArray(new Integer[valueList.size()]);
    Integer[] COLORS = colorList.toArray(new Integer[valueList.size()]);
    String[] NAME_LIST = nameList.toArray(new String[valueList.size()]);

    updateLegend(NAME_LIST, COLORS, R.id.ds_instance_state_chart_legend);

    for (int i = 0; i < VALUES.length; i++)
    {
      mSeries.add(NAME_LIST[i] + " " + VALUES[i], VALUES[i]);
      SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
      renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);
      mRenderer.addSeriesRenderer(renderer);
    }

    GraphicalView pieChartView = ChartFactory.getPieChartView(activity, mSeries, mRenderer);

    LinearLayout layout = (LinearLayout) activity.findViewById(R.id.ds_instance_state_chart_graphic);
    layout.addView(pieChartView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

    pieChartView.repaint();
  }
}
