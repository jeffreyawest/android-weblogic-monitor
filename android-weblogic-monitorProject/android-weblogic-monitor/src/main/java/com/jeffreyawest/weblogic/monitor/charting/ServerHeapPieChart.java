package com.jeffreyawest.weblogic.monitor.charting;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
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
public class ServerHeapPieChart extends Fragment
{

  private String ARG_STATE = "arg-state";
  private CategorySeries mSeries;
  private DefaultRenderer mRenderer;
  private Activity activity;
  private static final DecimalFormat percentFormat = new DecimalFormat("#");

  Parcelable state;

  public ServerHeapPieChart()
  {

    super();

    Parcelable p;

    mRenderer = new DefaultRenderer();
    mSeries = new CategorySeries("");
  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {

    super.onCreate(savedInstanceState);

    Bundle args = getArguments();
    state = args.getParcelable(ARG_STATE);
  }

  public void update(Activity activity, Server pServer)
  {

    this.activity = activity;

    double percentHeapUnallocated = pServer.getPercentHeapUnallocated();
    double percentHeapCurrentUsed = pServer.getPercentHeapCurrentUsed();
    double percentHeapCurrentFree = pServer.getPercentHeapCurrentFree();

    TextView heapHeader = (TextView) activity.findViewById(R.id.heap_chart_header);
    heapHeader.setText(activity.getString(R.string.jvm_heap) + "- " + percentFormat.format(percentHeapCurrentUsed) + "% " + activity.getString(R.string.used));

    this.update(percentHeapUnallocated, percentHeapCurrentFree, percentHeapCurrentUsed);
  }

  public void update(double pctFree, double pctAllocUnused, double pctAllocUsed)
  {

    String[]
        NAME_LIST = new String[]{
        activity.getString(R.string.heap_unallocated),
        activity.getString(R.string.heap_allocated_free),
        activity.getString(R.string.heap_allocated_used)};

    double[] VALUES = new double[]{pctFree, pctAllocUnused, pctAllocUsed};
    Resources r = activity.getResources();
    int[] COLORS = new int[]{
        r.getColor(R.color.heap_unused),
        r.getColor(R.color.heap_alloc_unused),
        r.getColor(R.color.heap_alloc_used)};

    mRenderer.setApplyBackgroundColor(true);
    mRenderer.setBackgroundColor(activity.getResources().getColor(R.color.LightGrey));
    mRenderer.setChartTitle(activity.getString(R.string.jvm_heap));
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

    LinearLayout layout = (LinearLayout) activity.findViewById(R.id.heap_chart);
    layout.addView(mChartView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

    mRenderer.setClickEnabled(false);
    mRenderer.setSelectableBuffer(10);

    mChartView.repaint();
  }
}
