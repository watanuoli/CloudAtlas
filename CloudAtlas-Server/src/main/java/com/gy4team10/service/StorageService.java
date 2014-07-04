package com.gy4team10.service;

import com.gy4team10.entity.HDFSFile;
import com.gy4team10.entity.Resource;

import java.util.List;

/**
 * Created by zhou on 14-6-11.
 */
public interface StorageService {
    /**
     * 列表
     */
    public List<HDFSFile> list(String path) throws Exception;
    /**
     * 根据url获取文件内容
     */
    public void mkDir(String folder);
    public void rmDir(String folder);
    public void rename(String src, String dst);
    public void createFile(String file, String content);
    public void uploadFile(String local, String remote);
    public void downloadFile(String remote, String local);

    void rmFile(String key);
    /**
     * 将文件上传到storage内
     */

    /**
     * 删除url指定的文件
     */
}
