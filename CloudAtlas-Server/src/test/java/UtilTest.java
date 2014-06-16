import com.gy4team10.entity.Resource;
import com.gy4team10.entity.User;
import com.gy4team10.util.JSONConvertUtil;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by orange on 14-6-16.
 */
public class UtilTest {
    @Test
    public void testAddUser() {
        Resource resource = new Resource();
        String value = JSONConvertUtil.convertJSON(resource);
        System.out.println(" value " + value);
//        Assert.assertTrue(result);
    }
}
