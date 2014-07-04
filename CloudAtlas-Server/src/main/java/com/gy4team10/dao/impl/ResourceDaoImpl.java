package com.gy4team10.dao.impl;

import com.gy4team10.dao.AbstractBaseRedisDao;
import com.gy4team10.dao.ResourceDao;
import com.gy4team10.entity.Resource;
import com.gy4team10.util.JSONConvertUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by zhou on 14-6-11.
 */

public class ResourceDaoImpl extends AbstractBaseRedisDao<String, Resource> implements ResourceDao {
    private static final String C_RESOURCE_PREFIX = "resource:";

    @Override
    public Set<String> listKeys() {
        Set<String> result = redisTemplate.keys(C_RESOURCE_PREFIX + "*");
        return result;
    }

    /**
     * 新增
     * <br>------------------------------<br>
     *
     * @param resource
     * @return
     */
    @Override
    public boolean addResource(final Resource resource) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize(C_RESOURCE_PREFIX + resource.getKey());
                byte[] content = serializer.serialize(JSONConvertUtil.convertJSON(resource));
                return connection.setNX(key, content);
            }
        });
        return result;
    }

    /**
     * 批量新增 使用pipeline方式
     * <br>------------------------------<br>
     *
     * @param list
     * @return
     */
    @Override
    public boolean addResourceList(final List<Resource> list) {
        Assert.notEmpty(list);
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                for (Resource resource : list) {
                    byte[] key = serializer.serialize(C_RESOURCE_PREFIX + resource.getKey());
                    byte[] content = serializer.serialize(JSONConvertUtil.convertJSON(resource));
                    connection.setNX(key, content);
                }
                return true;
            }
        }, false, true);
        return result;
    }

    /**
     * 删除
     * <br>------------------------------<br>
     *
     * @param key
     */
    @Override
    public void deleteResource(String key) {
        List<String> list = new ArrayList<String>();
        list.add(key);
        deleteResources(list);
    }

    /**
     * 删除多个
     * <br>------------------------------<br>
     *
     * @param keys
     */
    @Override
    public void deleteResources(List<String> keys) {
        List<String> resourceKeys = new ArrayList();
        for (String key : keys) {
            resourceKeys.add(C_RESOURCE_PREFIX + key);
        }
        redisTemplate.delete(resourceKeys);
    }

    /**
     * 修改
     * <br>------------------------------<br>
     *
     * @param resource
     * @return
     */
    @Override
    public boolean updateResource(final Resource resource) {
        String key = resource.getRemoteURL();
        if (getResource(key) == null) {
            throw new NullPointerException("数据行不存在, key = " + key);
        }
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize(C_RESOURCE_PREFIX + resource.getKey());
                byte[] content = serializer.serialize(JSONConvertUtil.convertJSON(resource));
                connection.set(key, content);
                return true;
            }
        });
        return result;
    }

    /**
     * 通过key获取
     * <br>------------------------------<br>
     *
     * @param keyId
     * @return
     */
    @Override
    public Resource getResource(final String keyId) {
        Resource result = redisTemplate.execute(new RedisCallback<Resource>() {
            public Resource doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize(C_RESOURCE_PREFIX + keyId);
                byte[] value = connection.get(key);
                if (value == null) {
                    return null;
                }
                return JSONConvertUtil.convertResource(serializer.deserialize(value));
//                return new User(keyId, name, null);
            }
        });
        return result;
    }

    @Override
    public List<Resource> list() {
        Set<String> keyList = redisTemplate.keys(C_RESOURCE_PREFIX + "*");
        List<Resource> result = new ArrayList<Resource>();
        for (String key : keyList) {
            result.add(getResource(key));
        }
        return result;
    }
}
