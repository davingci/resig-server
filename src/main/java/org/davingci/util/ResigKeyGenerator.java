/**
 *@author huangdongxu
 *@Date Nov 17, 2017
*/

package org.davingci.util;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

public class ResigKeyGenerator  {


    public static Key generateKey() {
        String keyString = "org.davingci";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
        return key;
    }
}
