/**
 * Created by zhou on 14-6-11.
 */

import com.gy4team10.entity.HDFSFile;
import com.gy4team10.entity.User;
import com.gy4team10.rest.webapp.StorageResource;
import com.gy4team10.service.StorageService;
import com.gy4team10.service.UserService;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.List;

/**
 * 测试
 * @author http://blog.csdn.net/java2000_wl
 * @version <b>1.0</b>
 */
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class HadoopTest extends AbstractJUnit4SpringContextTests {



    @Autowired
    private StorageService storageService;

    /**
     * 新增
     * <br>------------------------------<br>
     */
    @Test
    public void testList() {

        List<HDFSFile> result= null;
        try {
            result = storageService.list("/");
            System.out.println("size" + result.size());
            for(HDFSFile file : result){
                System.out.println("size" + file.getPath() + " " + file.isDir() + " " + file.getLength());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(result.size(), 3);
    }

    @Test
    public void testMkDir() {

        List<HDFSFile> result= null;
        try {
            storageService.mkDir("/0616");
            result = storageService.list("/");
            System.out.println("size" + result.size());
            for(HDFSFile file : result){
                System.out.println("size" + file.getPath() + " " + file.isDir() + " " + file.getLength());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(result.size(), 4);
    }

    @Test
    public void testRmDir() {

    }

    @Test
    public void testRename() {

    }

    @Test
    public void testCreateFile() {

        List<HDFSFile> result= null;
        try {
            storageService.createFile("/0616/test.txt","Hello Hadoop 2!");
            result = storageService.list("/0616");
            System.out.println("size" + result.size());
            for(HDFSFile file : result){
                System.out.println("size" + file.getPath() + " " + file.isDir() + " " + file.getLength());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(result.size(), 1);
    }
    @Test
    public void testUploadFile() {

        List<HDFSFile> result= null;
        try {
            storageService.uploadFile("C:\\迅雷下载\\dataguru\\06.txt","/0616/06.txt");
            result = storageService.list("/0616");
            System.out.println("size" + result.size());
            for(HDFSFile file : result){
                System.out.println("size" + file.getPath() + " " + file.isDir() + " " + file.getLength());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(result.size(), 1);
    }

    @Test
    public void testDownloadFile() {

        List<HDFSFile> result= null;
        try {
            storageService.downloadFile("/0616/test.txt","c:\\hadoop-test.txt");
            result = storageService.list("/0616");
            System.out.println("size" + result.size());
            for(HDFSFile file : result){
                System.out.println("size" + file.getPath() + " " + file.isDir() + " " + file.getLength());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(result.size(), 2);
    }
    /**
     * storageService
     * @param storageService the userDao to set
     */
    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }
}
