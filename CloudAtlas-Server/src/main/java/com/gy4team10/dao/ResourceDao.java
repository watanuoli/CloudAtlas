package com.gy4team10.dao;

import com.gy4team10.entity.Resource;

import java.util.List;
import java.util.Set;

/**
 * Created by zhou on 14-6-11.
 */
public interface ResourceDao {
    Set<String> listKeys();

    boolean addResource(Resource resource);

    boolean addResourceList(List<Resource> list);

    void deleteResource(String key);

    void deleteResources(List<String> keys);

    boolean updateResource(Resource resource);

    Resource getResource(String keyId);

    List<Resource> list();
}
