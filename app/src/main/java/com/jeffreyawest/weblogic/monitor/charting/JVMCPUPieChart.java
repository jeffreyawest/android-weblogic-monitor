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
import android.widget.TableLayout;
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
public class JVMCPUPieChart extends DefaultPieChart
{
  private static final DecimalFormat percentFormat = new DecimalFormat("##.#");

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState)
  {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.chart_view_cpu, container, false);

    return view;
  }

  public void update(Server pServer)
  {

    Resources r = getActivity().getResources();
    int[] COLORS = new int[]{
        r.getColor(R.color.cpu_free),
        r.getColor(R.color.cpu_used)};

    String[] NAME_LIST = new String[]{
        getActivity().getString(R.string.unused_cpu),
        getActivity().getString(R.string.jvm_cpu)};

    updateLegend(NAME_LIST, COLORS);

    TextView cpuHeader = (TextView) getActivity().findViewById(R.id.cpu_chart_header);
    if (cpuHeader != null)
      cpuHeader.setText(getActivity().getString(R.string.jvm_cpu) + " - " + percentFormat.format(pServer.getJvmProcessorLoad() * 100) + "% " + getActivity().getString(R.string.load));

    double[] VALUES = new double[]{1.0 - pServer.getJvmProcessorLoad(), pServer.getJvmProcessorLoad()};

    mRenderer.setChartTitle(getActivity().getString(R.string.jvm_cpu));

    for (int i = 0; i < VALUES.length; i++)
    {
      mSeries.add(NAME_LIST[i] + " " + VALUES[i], VALUES[i]);
      SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
      renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);
      mRenderer.addSeriesRenderer(renderer);
    }

    GraphicalView pieChartView = ChartFactory.getPieChartView(getActivity(), mSeries, mRenderer);

    LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.cpu_chart_graphic);
    if (layout != null)
      layout.addView(pieChartView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

    pieChartView.repaint();
  }

  private void updateLegend(final String[] pNAME_list, final int[] pCOLORS)
  {
    TableLayout tl = (TableLayout) getActivity().findViewById(R.id.cpu_chart_legend);

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
