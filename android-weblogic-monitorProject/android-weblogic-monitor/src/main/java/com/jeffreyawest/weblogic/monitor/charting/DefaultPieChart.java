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
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.monitor.WebLogicMonitor;

import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;

/**
 * Created by jeffreyawest on 8/27/13.
 */
public class DefaultPieChart extends Fragment
{

  protected CategorySeries mSeries;
  protected DefaultRenderer mRenderer;

  public DefaultPieChart()
  {
    mRenderer = new DefaultRenderer();
    mSeries = new CategorySeries("");

    mRenderer.setBackgroundColor(Color.BLACK);
    mRenderer.setApplyBackgroundColor(false);
    mRenderer.setExternalZoomEnabled(false);
    mRenderer.setZoomEnabled(false);
    mRenderer.setInScroll(false);
    mRenderer.setShowLabels(false);
    mRenderer.setShowLegend(false);
    mRenderer.setZoomButtonsVisible(false);
    mRenderer.setPanEnabled(false);
    mRenderer.setClickEnabled(false);
    mRenderer.setScale(Charting.PIE_CHART_SCALE);
    mRenderer.setLabelsColor(Color.BLACK);
    mRenderer.setStartAngle(90);

    mRenderer.setMargins(WebLogicMonitor.getInstance().getResources().getIntArray(R.array.chart_margins));

//    mRenderer.setBackgroundColor(WebLogicMonitor.getInstance().getResources().getColor(R.color.chart_background));
//    mRenderer.setChartTitleTextSize(WebLogicMonitor.getInstance().getResources().getDimension(R.dimen.entity_chart_title_size));
//    mRenderer.setLabelsTextSize(WebLogicMonitor.getInstance().getResources().getDimension(R.dimen.entity_chart_label_size));
//    mRenderer.setLegendHeight((int) WebLogicMonitor.getInstance().getResources().getDimension(R.dimen.entity_chart_legend_height));
//    mRenderer.setLegendTextSize(WebLogicMonitor.getInstance().getResources().getDimension(R.dimen.entity_chart_legend_text_size));
  }

  protected final TableRow getRow(final Activity pActivity, int pColor, String pValue)
  {

    TableRow row = new TableRow(pActivity);
    row.setPadding(2, 2, 2, 2);

    pActivity.getResources().getDimension(R.dimen.graph_fragment_legend_graphic_size);

    TextView colorView = new TextView(pActivity);
    colorView.setBackgroundColor(pColor);
    colorView.setHeight((int) pActivity.getResources().getDimension(R.dimen.graph_fragment_legend_graphic_size));
    colorView.setText("   ");
    colorView.setWidth((int) pActivity.getResources().getDimension(R.dimen.graph_fragment_legend_graphic_size));
    row.addView(colorView);

    TextView text = new TextView(pActivity);
    text.setText(pValue);
    text.setPadding(2, 2, 2, 2);
    text.setTextSize(15);
    row.addView(text);

    return row;
  }

  protected final void updateLegend(final String[] pNAME_list, final Integer[] pCOLORS, int pID)
  {
    TableLayout tl = (TableLayout) getActivity().findViewById(pID);

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
