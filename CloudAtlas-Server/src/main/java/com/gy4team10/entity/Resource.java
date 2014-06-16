package com.gy4team10.entity;

/**
 * Created by zhou on 14-6-11.
 */
public class Resource {
    private String fileName;

    private long createTime;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    /**
     * 远端路径
     */
    private String remoteURL;

    public String getRemoteURL() {
        return remoteURL;
    }

    public void setRemoteURL(String remoteURL) {
        this.remoteURL = remoteURL;
    }

    public String getLocalURL() {
        return localURL;
    }

    public void setLocalURL(String localURL) {
        this.localURL = localURL;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public boolean isSyncFlag() {
        return syncFlag;
    }

    public void setSyncFlag(boolean syncFlag) {
        this.syncFlag = syncFlag;
    }

    /**
     * 本地文件路径
     */

    private String localURL;
    /**
     * 保存内容的md5信息
     */
    private String md5;
    /**
     * 同步标识
     */
    private boolean syncFlag;

    public String getKey() {
        return this.fileName;
    }
}
