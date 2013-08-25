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
import android.content.res.Resources;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Server;
import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.monitor.WebLogicMonitor;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.text.DecimalFormat;

/**
 *
 * Created by jeffreyawest on 8/10/13.
 */
public class CPUPieChart
{
  private CategorySeries mSeries;
  private DefaultRenderer mRenderer;
  private static final DecimalFormat percentFormat = new DecimalFormat("#");

  public CPUPieChart()
  {

    mRenderer = new DefaultRenderer();
    mSeries = new CategorySeries("");
  }

  public void update(Activity activity, Server pServer)
  {

    Resources r = activity.getResources();
    int[] COLORS = new int[]{
        r.getColor(R.color.cpu_free),
        r.getColor(R.color.cpu_used)};

    String[] NAME_LIST = new String[]{
        activity.getString(R.string.unused_cpu),
        activity.getString(R.string.jvm_cpu)};

    updateLegend(activity, NAME_LIST, COLORS);

    TextView cpuHeader = (TextView) activity.findViewById(R.id.cpu_chart_header);
    if (cpuHeader != null)
      cpuHeader.setText(activity.getString(R.string.jvm_cpu) + "- " + percentFormat.format(pServer.getJvmProcessorLoad()) + "% " + activity.getString(R.string.load));

    double[] VALUES = new double[]{1.0 - pServer.getJvmProcessorLoad(), pServer.getJvmProcessorLoad()};

    mRenderer.setApplyBackgroundColor(false);
    mRenderer.setExternalZoomEnabled(false);
    mRenderer.setZoomEnabled(false);
    mRenderer.setInScroll(false);
    mRenderer.setFitLegend(true);
    mRenderer.setShowLabels(false);
    mRenderer.setShowLegend(false);
    mRenderer.setZoomButtonsVisible(false);
    mRenderer.setClickEnabled(false);
    mRenderer.setScale(Charting.PIE_CHART_SCALE);

    mRenderer.setMargins(activity.getResources().getIntArray(R.array.chart_margins));

    mRenderer.setBackgroundColor(activity.getResources().getColor(R.color.chart_background));
    mRenderer.setChartTitle(activity.getString(R.string.jvm_cpu));
    mRenderer.setChartTitleTextSize(WebLogicMonitor.getInstance().getResources().getDimension(R.dimen.entity_chart_title_size));

    mRenderer.setLabelsColor(Color.BLACK);
    mRenderer.setLabelsTextSize(WebLogicMonitor.getInstance().getResources().getDimension(R.dimen.entity_chart_label_size));

//    mRenderer.setLegendHeight(70);
    mRenderer.setLegendHeight((int) WebLogicMonitor.getInstance().getResources().getDimension(R.dimen.entity_chart_legend_height));
    mRenderer.setLegendTextSize(WebLogicMonitor.getInstance().getResources().getDimension(R.dimen.entity_chart_legend_text_size));

    mRenderer.setStartAngle(90);

    for (int i = 0; i < VALUES.length; i++)
    {
      mSeries.add(NAME_LIST[i] + " " + VALUES[i], VALUES[i]);
      SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
      renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);
      mRenderer.addSeriesRenderer(renderer);
    }

    GraphicalView mChartView = ChartFactory.getPieChartView(activity, mSeries, mRenderer);

    LinearLayout layout = (LinearLayout) activity.findViewById(R.id.cpu_chart_graphic);
    if (layout != null)
      layout.addView(mChartView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

    mRenderer.setSelectableBuffer(10);

    mChartView.repaint();
  }

  private void updateLegend(final Activity pActivity, final String[] pNAME_list, final int[] pCOLORS)
  {
    TableLayout tl = (TableLayout) pActivity.findViewById(R.id.cpu_chart_legend);

    if (tl != null)
    {
      tl.removeAllViews();
      for (int x = 0; x < pNAME_list.length; x++)
      {
        tl.addView(getRow(pActivity, pCOLORS[x], pNAME_list[x]));
      }
    }
  }

  public TableRow getRow(final Activity pActivity, int pColor, String pValue)
  {

    TableRow row = new TableRow(pActivity);

    TextView colorView = new TextView(pActivity);
    colorView.setBackgroundColor(pColor);
    colorView.setTextSize(15);
    colorView.setText(" ");
    colorView.setWidth(15);
    row.addView(colorView);

    TextView text = new TextView(pActivity);
    text.setText(pValue);
    text.setTextSize(15);
    row.addView(text);

    return row;
  }
}
