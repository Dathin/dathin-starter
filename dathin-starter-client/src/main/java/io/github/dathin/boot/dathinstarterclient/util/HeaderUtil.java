package io.github.dathin.boot.dathinstarterclient.util;

import java.util.HashMap;
import java.util.Map;

public class HeaderUtil {

    public static Map<String, String> buildAuthHeader(String authorization) {
        var authHeaderMap = new HashMap<String, String>();
        authHeaderMap.put("Authorization", authorization);
        return authHeaderMap;
    }

}
