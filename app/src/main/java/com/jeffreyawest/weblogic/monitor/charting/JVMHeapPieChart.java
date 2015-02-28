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

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Server;
import com.jeffreyawest.weblogic.monitor.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.text.DecimalFormat;

/**
 * Created by jeffreyawest on 8/10/13.
 */
public class JVMHeapPieChart extends DefaultPieChart
{

  private String ARG_STATE = "arg-state";
  private static final DecimalFormat percentFormat = new DecimalFormat("#");

  public JVMHeapPieChart()
  {

    super();
  }

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState)
  {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.chart_view_heap, container, false);

    return view;
  }

  public void update(Server pServer)
  {

    double pctFree = pServer.getPercentHeapUnallocated();
    double pctAllocUsed = pServer.getPercentHeapCurrentUsed();
    double pctAllocUnused = pServer.getPercentHeapCurrentFree();

    TextView heapHeader = (TextView) getActivity().findViewById(R.id.heap_chart_header);

    if (heapHeader != null)
      heapHeader.setText(getActivity().getString(R.string.jvm_heap) + " - " + percentFormat.format(pctAllocUsed * 100) + "% " + getActivity().getString(R.string.used));

    String[]
        NAME_LIST = new String[]{
        getActivity().getString(R.string.heap_unallocated),
        getActivity().getString(R.string.heap_allocated_free),
        getActivity().getString(R.string.heap_allocated_used)};

    double[] VALUES = new double[]{pctFree, pctAllocUnused, pctAllocUsed};
    Resources r = getActivity().getResources();
    Integer[] COLORS = new Integer[]{
        r.getColor(R.color.heap_unused),
        r.getColor(R.color.heap_alloc_unused),
        r.getColor(R.color.heap_alloc_used)};

    updateLegend(NAME_LIST, COLORS, R.id.heap_chart_legend);

    mRenderer.setChartTitle(getActivity().getString(R.string.jvm_heap));

    for (int i = 0; i < VALUES.length; i++)
    {
      mSeries.add(NAME_LIST[i] + " " + VALUES[i], VALUES[i]);
      SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
      renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);
      mRenderer.addSeriesRenderer(renderer);
    }

    GraphicalView pieChartView = ChartFactory.getPieChartView(getActivity(), mSeries, mRenderer);

    LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.heap_chart_graphic);
    if (layout != null)
      layout.addView(pieChartView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

    pieChartView.repaint();
  }
}
