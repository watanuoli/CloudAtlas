import com.gy4team10.entity.Resource;
import com.gy4team10.service.ResourceService;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * 测试
 * @author http://blog.csdn.net/java2000_wl 
 * @version <b>1.0</b> 
 */
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class RedisResourceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ResourceService resourceDao;

    /**
     * 新增
     * <br>------------------------------<br>
     */
    @Test
    public void testAddResource() {
        Resource res = new Resource();
        res.setRemoteURL("Resource1");
        res.setFileName("java2000_wl");
        boolean result = resourceDao.createResource(res,"");
        Assert.assertTrue(result);
    }

//    /**
//     * 批量新增 普通方式
//     * <br>------------------------------<br>
//     */
//    @Test
//    public void testAddResources1() {
//        List<Resource> list = new ArrayList<Resource>();
//        for (int i = 10; i < 50000; i++) {
//            Resource Resource = new Resource();
//            Resource.setRemoteURL("Resource1");
//            Resource.setFileName("java2000_wl");
//            list.add(Resource);
//        }
//        long begin = System.currentTimeMillis();
//        for (Resource Resource : list) {
//            resourceDao.addResource(Resource);
//        }
//        System.out.println(System.currentTimeMillis() -  begin);
//    }
//
//    /**
//     * 批量新增 pipeline方式
//     * <br>------------------------------<br>
//     */
//    @Test
//    public void testAddResources2() {
//        List<Resource> list = new ArrayList<Resource>();
//        for (int i = 10; i < 1500000; i++) {
//            Resource Resource = new Resource();
//            Resource.setRemoteURL("Resource" + i);
//            Resource.setLocalURL("java2000_wl" + i);
//            list.add(Resource);
//        }
//        long begin = System.currentTimeMillis();
//        boolean result = resourceDao.addResourceList(list);
//        System.out.println(System.currentTimeMillis() - begin);
//        Assert.assertTrue(result);
//    }
//
//    /**
//     * 修改
//     * <br>------------------------------<br>
//     */
//    @Test
//    public void testUpdate() {
//        Resource Resource = new Resource();
//        Resource.setId("Resource1");
//        Resource.setName("new_password");
//        boolean result = ResourceDao.update(Resource);
//        Assert.assertTrue(result);
//    }
//
//    /**
//     * 通过key删除单个
//     * <br>------------------------------<br>
//     */
//    @Test
//    public void testDelete() {
//        String key = "Resource1";
//        ResourceDao.delete(key);
//    }
//
//    /**
//     * 批量删除
//     * <br>------------------------------<br>
//     */
//    @Test
//    public void testDeletes() {
//        List<String> list = new ArrayList<String>();
//        for (int i = 0; i < 10; i++) {
//            list.add("Resource" + i);
//        }
//        ResourceDao.delete(list);
//    }
//
//    /**
//     * 获取
//     * <br>------------------------------<br>
//     */
//    @Test
//    public void testGetResource() {
//        String id = "Resource1";
//        Resource Resource = ResourceDao.get(id);
//        Assert.assertNotNull(Resource);
//        Assert.assertEquals(Resource.getName(), "java2000_wl");
//    }
//
//    /**
//     * 获取
//     * <br>------------------------------<br>
//     */
//    @Test
//      public void testListKey() {
//        String id = "Resource";
//        Set<String> keys = ResourceDao.listKeys();
////        System.out.println("size " + keys);
//        Assert.assertEquals(keys.size(),5);
//        //Assert.assertEquals(Resource.getName(), "java2000_wl");
//    }
//
//    @Test
//    public void testList() {
//        List<Resource> keys = ResourceDao.list();
//        System.out.println("size " + keys);
//        Assert.assertEquals(keys.size(),5);
//        //Assert.assertEquals(Resource.getName(), "java2000_wl");
//    }
//    /**
//     * 设置ResourceDao
//     * @param ResourceDao the ResourceDao to set
//     */
//    public void setResourceDao(ResourceService ResourceDao) {
//        this.ResourceDao = ResourceDao;
//    }
}