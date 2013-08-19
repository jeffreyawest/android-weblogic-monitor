package com.jeffreyawest.http;

import java.util.HashMap;

/**
 * Created by jeffreyawest on 8/17/13.
 */
public interface HTTPAdapter
{

  String ACCEPT_HEADER_KEY = "Accept";

  String GET(String pURL,
             String pUsername,
             String pPassword,
             String pAccept,
             HashMap<String, String> pAdditionalHeaders);
}
