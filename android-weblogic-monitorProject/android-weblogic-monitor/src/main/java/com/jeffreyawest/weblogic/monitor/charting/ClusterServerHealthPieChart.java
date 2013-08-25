/*
 *  Copyright (c) 2013 - Jeffrey A. West Designs
 * ************************************************************************
 * This code is provided for example purposes only.  Neither Oracle nor Jeffrey
 * A. West assume any responsibility or liability for the consequences of using
 * this code. If you choose to use this code for any reason, including but not limited to its use as an example you do so at your own risk and without the support of Oracle.
 * This code is provided under the following licenses:
 *  - GNU General Public License (GPL-2.0)
 * **************************************************************************
 */

package com.jeffreyawest.weblogic.monitor.charting;

import android.app.Activity;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Cluster;
import com.jeffreyawest.weblogic.entity.ClusterServer;
import com.jeffreyawest.weblogic.entity.enums.ServerHealth;
import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.monitor.WebLogicMonitor;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jeffreyawest on 8/10/13.
 */
public class ClusterServerHealthPieChart
{

  private static final DecimalFormat percentFormat = new DecimalFormat("#");

  private CategorySeries mSeries;
  private DefaultRenderer mRenderer;

  public ClusterServerHealthPieChart()
  {

    mRenderer = new DefaultRenderer();
    mSeries = new CategorySeries("");
  }

  public void update(Activity activity, Cluster pCluster)
  {

    TextView healthHeader = (TextView) activity.findViewById(R.id.server_state_chart_header);
    healthHeader.setText(activity.getString(R.string.server_health));

    HashMap<ServerHealth, Integer> countMap = new HashMap<ServerHealth, Integer>(7);

    for (ClusterServer server : pCluster.getServers())
    {
      ServerHealth health = server.getHealth();

      if (health == null || health.toString().equals("null"))
      {
        health = ServerHealth.UNKNOWN;
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

    for (Map.Entry<ServerHealth, Integer> entry : countMap.entrySet())
    {
      colorList.add(activity.getResources().getColor(entry.getKey().getColorID()));
      valueList.add(entry.getValue());
      nameList.add(entry.getKey().toString());
    }

    Integer[] VALUES = valueList.toArray(new Integer[valueList.size()]);
    Integer[] COLORS = colorList.toArray(new Integer[valueList.size()]);
    String[] NAME_LIST = nameList.toArray(new String[valueList.size()]);

    mRenderer.setApplyBackgroundColor(true);
    mRenderer.setBackgroundColor(activity.getResources().getColor(R.color.chart_background));
    mRenderer.setChartTitle(WebLogicMonitor.getInstance().getString(R.string.server_health));
    mRenderer.setChartTitleTextSize(WebLogicMonitor.getInstance().getResources().getDimension(R.dimen.entity_chart_title_size));

    mRenderer.setLabelsColor(Color.BLACK);
    mRenderer.setShowLabels(false);
    mRenderer.setLabelsTextSize(WebLogicMonitor.getInstance().getResources().getDimension(R.dimen.entity_chart_label_size));

    mRenderer.setMargins(new int[]{10, 10, 15, 10});
    mRenderer.setZoomButtonsVisible(false);

    mRenderer.setFitLegend(true);
    mRenderer.setLegendHeight((int) WebLogicMonitor.getInstance().getResources().getDimension(R.dimen.entity_chart_legend_height));
    mRenderer.setLegendTextSize(WebLogicMonitor.getInstance().getResources().getDimension(R.dimen.entity_chart_legend_text_size));
    mRenderer.setShowLegend(true);

    mRenderer.setStartAngle(90);

    for (int i = 0; i < VALUES.length; i++)
    {
      mSeries.add(NAME_LIST[i] + " " + VALUES[i], VALUES[i]);
      SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
      renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);
      mRenderer.addSeriesRenderer(renderer);
    }

    GraphicalView mChartView = ChartFactory.getPieChartView(activity, mSeries, mRenderer);

    LinearLayout layout = (LinearLayout) activity.findViewById(R.id.server_health_chart);
    layout.addView(mChartView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

    mRenderer.setClickEnabled(false);
    mRenderer.setSelectableBuffer(10);

    mChartView.repaint();
  }
}
