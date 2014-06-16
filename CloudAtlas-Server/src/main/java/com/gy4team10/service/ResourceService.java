package com.gy4team10.service;

import com.gy4team10.entity.Resource;

import java.util.List;
import java.util.Set;

/**
 * Created by zhou on 14-6-11.
 */
public interface ResourceService {
    /**
     * 显示远端所有文件地址列表
     * @return
     */
    Set<String> listResourceRemoteUrls();

    /**
     * 根据远端地址获取资源
     * @param resoureKey
     * @return
     */
    Resource getResource(String resoureKey);
    /**
     * 添加资源
     * @param resource
     * @return
     */
    boolean createResource(Resource resource,String localFileUrl);
    /**
     * 删除资源
     * @param resoureKey 远端地址
     * @return
     */
    void deleteResource(String resoureKey);
    /**
     * 列出cache中所有资源信息
     * @param
     * @return cache资源内所有文件信息
     */
    List<Resource> listResource();
    /**
     * 重命名
     * @param
     * @return cache资源内所有文件信息
     */
    boolean renameResource(String resoureKey,String newName);
    /**
     * 替换内容
     * @param
     * @return cache资源内所有文件信息
     */
    boolean replaceResourceContent(String resoureKey,String localFileUrl);


    /**
     * 删除所有文件信息
     * @param
     * @return cache资源内所有文件信息
     */
    void deleteResources(List<String> resoureKeys);
    /**
     * 添加资源集合
     * @param
     * @return cache资源内所有文件信息
     */
//    boolean addResourceList(List<Resource> list);
}
