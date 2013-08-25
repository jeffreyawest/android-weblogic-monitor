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

package com.jeffreyawest.weblogic.monitor.charting.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeffreyawest.weblogic.entity.Server;
import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.monitor.charting.CPUPieChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffreyawest on 8/18/13.
 */
public class CPUChartFragment extends Fragment
{

  private List<String> labels;
  private CPUPieChart chart;

  public CPUChartFragment()
  {
    chart = new CPUPieChart();
  }

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState)
  {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.cpu_chart_view, container, false);

    return view;
  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    labels = new ArrayList<String>(2);
    labels.add("label1");
    labels.add("label2");
    labels.add("label3");
  }

  public void updateDisplay(Server pServer)
  {
    chart.update(getActivity(), pServer);
  }
}
