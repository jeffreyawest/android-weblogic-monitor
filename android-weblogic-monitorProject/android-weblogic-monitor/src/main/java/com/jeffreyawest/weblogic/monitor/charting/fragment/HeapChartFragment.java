package com.jeffreyawest.weblogic.monitor.charting.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeffreyawest.weblogic.entity.Server;
import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.monitor.charting.HeapPieChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffreyawest on 8/18/13.
 */
public class HeapChartFragment extends Fragment
{

  private List<String> labels;
  private HeapPieChart chart;

  public HeapChartFragment()
  {
    chart = new HeapPieChart();
  }

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState)
  {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.heap_chart_view, container, false);

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
