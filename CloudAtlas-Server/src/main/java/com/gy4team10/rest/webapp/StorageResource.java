package com.gy4team10.rest.webapp;

import com.gy4team10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * 存储服务
 * Created by zhou on 14-6-11.
 */
@Path("/storage")
public class StorageResource {
    private static final Logger LOGGER = Logger.getLogger(StorageResource.class.getName());

    @Autowired
    private UserService userService;


    public StorageResource() {
        LOGGER.fine("UserResource()");
    }

    /**
     * 返回所有的列表
     * @return
     */
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public String listJSON() {
        return "";
    }

    /**
     * 返回某个内容的详细信息
     * @return
     */
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(String key) {
        return "";
    }

    /**
     * 添加新的内容到集群
     * @return
     */
    @POST
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public String addJson(String key) {
        return "";
    }

    /**
     * 删除key的对象
     * @return
     */
    @GET
    @Path("/delete/")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteJson(String key) {
        return "";
    }

    /**
     * 更新key的对象
     * @return
     */
    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateJson(String key) {
        return "";
    }
}
