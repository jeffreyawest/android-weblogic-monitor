package com.jeffreyawest.weblogic.monitor.charting;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
public class JVMCPUPieChart
{

  static Resources r = Resources.getSystem();

  private CategorySeries mSeries;
  private DefaultRenderer mRenderer;
  private Activity activity;
  private static final DecimalFormat percentFormat = new DecimalFormat("#");

  public JVMCPUPieChart()
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

    TextView cpuHeader = (TextView) activity.findViewById(R.id.cpu_chart_header);
    cpuHeader.setText(activity.getString(R.string.jvm_cpu) + "- " + percentFormat.format(pServer.getJvmProcessorLoad()) + "% " + activity.getString(R.string.load));

    double[] VALUES = new double[]{1.0 - pServer.getJvmProcessorLoad(), pServer.getJvmProcessorLoad()};

    mRenderer.setApplyBackgroundColor(true);
    mRenderer.setBackgroundColor(activity.getResources().getColor(R.color.LightGrey));
    mRenderer.setChartTitle(activity.getString(R.string.jvm_cpu));
    mRenderer.setChartTitleTextSize(WebLogicMonitor.getInstance().getResources().getDimension(R.dimen.entity_chart_title_size));

    mRenderer.setLabelsColor(Color.BLACK);
    mRenderer.setShowLabels(false);
    mRenderer.setLabelsTextSize(WebLogicMonitor.getInstance().getResources().getDimension(R.dimen.entity_chart_label_size));

    mRenderer.setMargins(new int[]{10, 10, 15, 10});
    mRenderer.setZoomButtonsVisible(false);

    mRenderer.setFitLegend(true);
//    mRenderer.setLegendHeight(70);
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

    LinearLayout layout = (LinearLayout) activity.findViewById(R.id.cpu_chart);
    layout.addView(mChartView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

    mRenderer.setClickEnabled(false);
    mRenderer.setSelectableBuffer(10);

    mChartView.repaint();
  }
}
