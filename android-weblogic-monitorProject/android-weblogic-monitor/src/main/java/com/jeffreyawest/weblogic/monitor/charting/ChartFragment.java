package com.jeffreyawest.weblogic.monitor.charting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeffreyawest.weblogic.monitor.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffreyawest on 8/18/13.
 */
public class ChartFragment extends Fragment
{

  List<String> labels;

  public ChartFragment()
  {

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState)
  {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.heap_chart_view, container, false);
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
}
