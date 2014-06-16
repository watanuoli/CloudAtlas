package com.gy4team10.entity;

import org.apache.hadoop.fs.Path;

/**
 * Created by chenqiong on 14-6-11.
 */
public class HDFSFile {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean isDir) {
        this.isDir = isDir;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    private boolean isDir;

    private long length;
    public HDFSFile(Path path, boolean dir, long len) {
        this.path = path.toString();
        this.isDir = dir;
        this.length = len;
    }


}
