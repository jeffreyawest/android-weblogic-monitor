package com.jeffreyawest.weblogic.monitor.charting;

import android.app.Activity;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Cluster;
import com.jeffreyawest.weblogic.entity.ClusterServer;
import com.jeffreyawest.weblogic.entity.enums.ServerState;
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
 * * **************************************************************************
 * This code is provided for example purposes only.  Neither Oracle nor Jeffrey
 * A. West assume any responsibility or liability for the consequences of using
 * this code.
 * <p/>
 * If you choose to use this code for any reason, including but not limited
 * to its use as an example you do so at your own risk and without the support
 * of Oracle.
 * <p/>
 * This code is provided under the following licenses:
 * <p/>
 * GNU General Public License (GPL-2.0)
 * <p/>
 * ****************************************************************************
 * <p/>
 * Created by jeffreyawest on 8/10/13.
 */
public class ClusterServerStatePieChart
{

  private static final DecimalFormat percentFormat = new DecimalFormat("#");

  private CategorySeries mSeries;
  private DefaultRenderer mRenderer;

  public ClusterServerStatePieChart()
  {

    mRenderer = new DefaultRenderer();
    mSeries = new CategorySeries("");
  }

  public void update(Activity activity, Cluster pCluster)
  {

    TextView healthHeader = (TextView) activity.findViewById(R.id.server_state_chart_header);
    healthHeader.setText(activity.getString(R.string.server_state));

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
      colorList.add(activity.getResources().getColor(entry.getKey().getColorID()));
      valueList.add(entry.getValue());
      nameList.add(entry.getKey().toString());
    }

    Integer[] VALUES = valueList.toArray(new Integer[valueList.size()]);
    Integer[] COLORS = colorList.toArray(new Integer[valueList.size()]);
    String[] NAME_LIST = nameList.toArray(new String[valueList.size()]);

    mRenderer.setApplyBackgroundColor(false);
//    mRenderer.setBackgroundColor(Color.argb(100, 50, 50, 50));
    mRenderer.setChartTitle(WebLogicMonitor.getInstance().getString(R.string.cpu_usage));
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

    LinearLayout layout = (LinearLayout) activity.findViewById(R.id.server_state_chart);
    layout.addView(mChartView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

    mRenderer.setClickEnabled(false);
    mRenderer.setSelectableBuffer(10);

    mChartView.repaint();
  }
}