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
