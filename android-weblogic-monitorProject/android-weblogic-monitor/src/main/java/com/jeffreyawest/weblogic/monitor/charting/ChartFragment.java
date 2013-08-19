package com.jeffreyawest.weblogic.monitor.charting;

import android.app.Fragment;
import android.os.Bundle;

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
  public void onCreate(Bundle savedInstanceState)
  {

    super.onCreate(savedInstanceState);

    labels = new ArrayList<String>(2);
    labels.add("label1");
    labels.add("label2");
    labels.add("label3");
  }
}
