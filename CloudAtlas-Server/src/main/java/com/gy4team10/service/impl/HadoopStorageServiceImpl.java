package com.gy4team10.service.impl;

import com.gy4team10.entity.HDFSFile;
import com.gy4team10.service.StorageService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobConf;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by zhou on 14-6-11.
 */
public class HadoopStorageServiceImpl implements StorageService {
    private static final Logger LOGGER = Logger.getLogger(HadoopStorageServiceImpl.class.getName());

    private String hdfsPath;
    private Configuration conf;

    public HadoopStorageServiceImpl() {

    }
    public void setHdfsPath(String hdfsPath) {
        this.hdfsPath = hdfsPath;
    }
    public void setConfigurationResourceList(List<String> resourceList) {
        this.conf = new JobConf();
        for (String resource : resourceList) {
            conf.addResource(resource);
        }
//        conf.addResource("classpath:/hadoop/core-site.xml");
//        conf.addResource("classpath:/hadoop/hdfs-site.xml");
//        conf.addResource("classpath:/hadoop/mapred-site.xml");
    }

    @Override
    public List<HDFSFile> list(String folder) throws Exception {
        List<HDFSFile> fileList = new ArrayList<HDFSFile>();
        Path path = new Path(folder);
        FileSystem fs = null;
        try {
            fs = FileSystem.get(URI.create(hdfsPath), conf);
            FileStatus[] list = fs.listStatus(path);

            for (FileStatus f : list) {
                fileList.add(new HDFSFile(f.getPath(), f.isDir(), f.getLen()));
            }

        } catch (Exception exc) {
            LOGGER.throwing("HadoopStorageServiceImpl", "list", exc);
            throw exc;
        } finally {
            if (fs != null)
                try {
                    fs.close();
                } catch (IOException e) {

                }
        }
        return fileList;
    }

    public void mkDir(String folder) {
        Path path = new Path(folder);
        FileSystem fs = null;
        try {
            fs = FileSystem.get(URI.create(hdfsPath), conf);
            if (!fs.exists(path)) {
                fs.mkdirs(path);
                System.out.println("Create: " + folder);
            }
        } catch (Exception exc) {
            LOGGER.throwing("HadoopStorageServiceImpl", "mkdirs", exc);
        } finally {
            if (fs != null)
                try {
                    fs.close();
                } catch (IOException e) {

                }
        }

    }

    /**
     * 删除目录
     *
     * @param folder
     */
    public void rmDir(String folder) {
        Path path = new Path(folder);
        FileSystem fs = null;
        try {
            fs = FileSystem.get(URI.create(hdfsPath), conf);
            fs.deleteOnExit(path);
        } catch (Exception exc) {
            LOGGER.throwing("HadoopStorageServiceImpl", "mkdirs", exc);
        } finally {
            if (fs != null)
                try {
                    fs.close();
                } catch (IOException e) {

                }
        }
    }

    /**
     * 删除目录
     *
     * @param src 源文件目录
     * @param dst 文件目录
     */
    public void rename(String src, String dst) {
        Path name1 = new Path(src);
        Path name2 = new Path(dst);
        FileSystem fs = null;
        try {
            fs = FileSystem.get(URI.create(hdfsPath), conf);
            fs.rename(name1, name2);
        } catch (Exception exc) {
            LOGGER.throwing("HadoopStorageServiceImpl", "mkdirs", exc);
        } finally {
            if (fs != null)
                try {
                    fs.close();
                } catch (IOException e) {

                }
        }
    }

    /**
     * 创建文件
     *
     * @param file
     * @param content
     */
    public void createFile(String file, String content) {
        byte[] buff = content.getBytes();

        FileSystem fs = null;
        FSDataOutputStream os = null;
        try {
            fs = FileSystem.get(URI.create(hdfsPath), conf);
            os = fs.create(new Path(file));
            os.write(buff, 0, buff.length);
        } catch (Exception exc) {
            LOGGER.throwing("HadoopStorageServiceImpl", "mkdirs", exc);
        } finally {
            if (os != null)
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 本地文件上传到云存储
     *
     * @param local
     * @param remote
     */
    public void uploadFile(String local, String remote) {
        FileSystem fs = null;
        try {
            fs = FileSystem.get(URI.create(hdfsPath), conf);
            fs.copyFromLocalFile(new Path(local), new Path(remote));
            System.out.println("copy from: " + local + " to " + remote);
        } catch (Exception exc) {
            LOGGER.throwing("HadoopStorageServiceImpl", "mkdirs", exc);
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {

                }
            }
        }
    }

    /**
     * 从云存储下载到本地
     *
     * @param local
     * @param remote
     */
    public void downloadFile(String remote, String local) {
        FileSystem fs = null;

        try {
            Path path = new Path(remote);
            fs = FileSystem.get(URI.create(hdfsPath), conf);
            fs.copyToLocalFile(path, new Path(local));
        } catch (Exception exc) {
            LOGGER.throwing("HadoopStorageServiceImpl", "downloadFile", exc);
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {

                }
            }
        }
    }

    @Override
    public void rmFile(String key) {

    }
}
