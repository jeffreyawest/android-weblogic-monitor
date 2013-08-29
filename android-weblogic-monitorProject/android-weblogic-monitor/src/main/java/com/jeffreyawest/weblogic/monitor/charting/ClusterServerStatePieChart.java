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
import android.widget.TableLayout;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Cluster;
import com.jeffreyawest.weblogic.entity.ClusterServer;
import com.jeffreyawest.weblogic.entity.enums.ServerState;
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
public class ClusterServerStatePieChart extends DefaultPieChart
{

  private static final DecimalFormat percentFormat = new DecimalFormat("#");

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState)
  {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.chart_view_server_state, container, false);

    return view;
  }

  public void update(Cluster pCluster)
  {

    TextView healthHeader = (TextView) getActivity().findViewById(R.id.server_state_chart_header);
    healthHeader.setText(getActivity().getString(R.string.server_state));

    HashMap<ServerState, Integer> countMap = new HashMap<ServerState, Integer>(7);

    for (ClusterServer server : pCluster.getServers())
    {
      if (server.getState() == null || server.getState().toString().equals("null"))
      {
        continue;
      }
      Integer theCount = countMap.get(server.getState());

      if (theCount == null)
      {
        theCount = 0;
      }

      countMap.put(server.getState(), ++theCount);
    }

    List<Integer> valueList = new ArrayList<Integer>(countMap.size());
    List<Integer> colorList = new ArrayList<Integer>(countMap.size());
    List<String> nameList = new ArrayList<String>(countMap.size());

    for (Map.Entry<ServerState, Integer> entry : countMap.entrySet())
    {
      colorList.add(getActivity().getResources().getColor(entry.getKey().getColorID()));
      valueList.add(entry.getValue());
      nameList.add(entry.getKey().toString());
    }

    Integer[] VALUES = valueList.toArray(new Integer[valueList.size()]);
    Integer[] COLORS = colorList.toArray(new Integer[valueList.size()]);
    String[] NAME_LIST = nameList.toArray(new String[valueList.size()]);

    updateLegend(NAME_LIST, COLORS);

    mRenderer.setChartTitle(getActivity().getString(R.string.jvm_cpu));

    for (int i = 0; i < VALUES.length; i++)
    {
      mSeries.add(NAME_LIST[i] + " " + VALUES[i], VALUES[i]);
      SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
      renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);
      mRenderer.addSeriesRenderer(renderer);
    }

    GraphicalView pieChartView = ChartFactory.getPieChartView(getActivity(), mSeries, mRenderer);

    LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.server_state_chart_graphic);
    layout.addView(pieChartView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

    pieChartView.repaint();
  }

  private void updateLegend(final String[] pNAME_list, final Integer[] pCOLORS)
  {
    TableLayout tl = (TableLayout) getActivity().findViewById(R.id.server_state_chart_legend);

    if (tl != null)
    {
      tl.removeAllViews();
      for (int x = 0; x < pNAME_list.length; x++)
      {
        tl.addView(getRow(getActivity(), pCOLORS[x], pNAME_list[x]));
      }
    }
  }
}
