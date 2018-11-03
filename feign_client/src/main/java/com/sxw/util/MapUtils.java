package com.sxw.util; /*
 * @(#)MapUtils.java 1.0 2018/10/30
 * @Copyright:  Copyright © 2007-2018 ky-express.com.All Rights Reserved.
 * @Description: 
 * 
 * @Modification History:
 * @Date:        2018/10/30
 * @Author:      
 * @Version:     1.0.0.0
 * @Description: (Initialize)
 * @Reviewer:    
 * @Review Date: 
 */

import java.util.Map;

public class MapUtils {

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }
    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }
}
