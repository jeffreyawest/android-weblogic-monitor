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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Application;
import com.jeffreyawest.weblogic.entity.ApplicationTargetState;
import com.jeffreyawest.weblogic.entity.enums.TargetState;
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
public class ApplicationTargetStatePieChart extends DefaultPieChart
{

  private static final DecimalFormat percentFormat = new DecimalFormat("#");

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState)
  {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.chart_view_app_target_state, container, false);

    return view;
  }

  public void update(Application pApp)
  {

    TextView healthHeader = (TextView) getActivity().findViewById(R.id.app_target_state_chart_header);
    healthHeader.setText(getActivity().getString(R.string.application_target_state));

    HashMap<TargetState, Integer> countMap = new HashMap<TargetState, Integer>(7);

    for (ApplicationTargetState instance : pApp.getTargetStates())
    {
      TargetState state = instance.getState();

      if (state == null || state.toString().equals("null"))
      {
        state = TargetState.STATE_UNKNOWN;
      }

      Integer theCount = countMap.get(state);

      if (theCount == null)
      {
        theCount = 0;
      }

      countMap.put(state, ++theCount);
    }

    List<Integer> valueList = new ArrayList<Integer>(countMap.size());
    List<Integer> colorList = new ArrayList<Integer>(countMap.size());
    List<String> nameList = new ArrayList<String>(countMap.size());

    for (Map.Entry<TargetState, Integer> entry : countMap.entrySet())
    {
      colorList.add(getActivity().getResources().getColor(entry.getKey().getColorID()));
//      colorList.add(activity.getResources().getColor(STATE_COLOR_MAP.get(entry.getKey())));
      valueList.add(entry.getValue());
      nameList.add(entry.getKey().toString());
    }

    Integer[] VALUES = valueList.toArray(new Integer[valueList.size()]);
    Integer[] COLORS = colorList.toArray(new Integer[valueList.size()]);
    String[] NAME_LIST = nameList.toArray(new String[valueList.size()]);

    updateLegend(NAME_LIST, COLORS, R.id.app_target_state_chart_legend);

    for (int i = 0; i < VALUES.length; i++)
    {
      mSeries.add(NAME_LIST[i] + " " + VALUES[i], VALUES[i]);
      SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
      renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);
      mRenderer.addSeriesRenderer(renderer);
    }

    GraphicalView pieChartView = ChartFactory.getPieChartView(getActivity(), mSeries, mRenderer);

    LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.app_target_state_chart_graphic);
    layout.addView(pieChartView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

    pieChartView.repaint();
  }
}
