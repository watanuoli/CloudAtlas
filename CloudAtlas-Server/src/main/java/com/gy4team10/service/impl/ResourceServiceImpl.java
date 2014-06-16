package com.gy4team10.service.impl;

import com.gy4team10.dao.ResourceDao;
import com.gy4team10.entity.Resource;
import com.gy4team10.service.ResourceService;
import com.gy4team10.service.StorageService;
import com.gy4team10.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by zhou on 14-6-11.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private StorageService storageService;

    @Override
    public Set<String> listResourceRemoteUrls() {
        return resourceDao.listKeys();
    }

    @Override
    public Resource getResource(String keyId) {
        Resource resource = null;
        try {
            resource = resourceDao.getResource(keyId);
            String currentFileMD5= MD5Util.generateMD5ByFileName(resource.getLocalURL());
            if(!currentFileMD5.equals(resource.getMd5())){
                storageService.downloadFile(resource.getRemoteURL(),resource.getLocalURL());
            }
            //storageService.downloadFile(resource.getRemoteURL(),resource.getLocalURL());
            //storageService.uploadFile(resource.getLocalURL(), resource.getRemoteURL());
//            resource.setMd5();
//            return resourceDao.addResource(resource);
        } catch (Exception exc) {

        }
        return resource;
    }

    @Override
    public boolean createResource(Resource resource,String localFile) {
        try {
            storageService.uploadFile(localFile, resource.getRemoteURL());
            resource.setMd5(MD5Util.generateMD5ByFileName(localFile));
            return resourceDao.addResource(resource);
        } catch (Exception exc) {

        }
        return false;
    }

    @Override
    public void deleteResource(String key) {
        resourceDao.deleteResource(key);
        storageService.rmFile(key);
    }

    @Override
    public List<Resource> listResource() {
        return resourceDao.list();
    }

    @Override
    public boolean renameResource(String resoureKey,String newFileName) {
        Resource resource = getResource(resoureKey);
        resource.setFileName(newFileName);
        resourceDao.deleteResource(resoureKey);
        resourceDao.addResource(resource);
//        storageService.uploadFile(resource.getLocalURL(), resource.getRemoteURL());
//        resource.setMd5(MD5Util.generateMD5ByFileName(resource.getLocalURL()));
        return true;
    }

    @Override
    public boolean replaceResourceContent(String resoureKey, String localFileUrl) {
        Resource resource = getResource(resoureKey);
        resource.setMd5(MD5Util.generateMD5ByFileName(localFileUrl));
        storageService.uploadFile(localFileUrl,resource.getRemoteURL());
        return true;
    }

    @Override
    public void deleteResources(List<String> keys) {
        for (String key : keys) {
            try {
                resourceDao.deleteResource(key);
                storageService.rmFile(key);
            } catch (Exception exc) {

            }
        }
//        resourceDao.deleteResources(keys);
    }

//    @Override
//    public boolean addResourceList(List<Resource> list) {
//        for (Resource resource : list) {
//            addResource(resource);
//        }
//        return true;
//    }
}
