package com.gy4team10.util;

import com.gy4team10.entity.Resource;
import com.gy4team10.entity.User;
import net.sf.json.JSONObject;
/**
 * Created by orange on 14-6-16.
 */
public class JSONConvertUtil {
    public static String convertJSON(Resource resource){
        JSONObject obj = new JSONObject();
        obj.put("resource", resource);
        return obj.toString();
    }
    public static Resource convertResource(String content) {
        JSONObject object = JSONObject.fromObject(content);
        Resource resource = new Resource();
        resource.setCreateTime(object.getInt("createTime"));
        resource.setFileName(object.getString("fileName"));
        resource.setLocalURL(object.getString("localURL"));
        resource.setRemoteURL(object.getString("remoteURL"));
        resource.setSyncFlag(object.getBoolean("syncFlag"));
        resource.setMd5(object.getString("md5"));
        return resource;
    }

}
