package com.gy4team10.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.gy4team10.dao.AbstractBaseRedisDao;
import com.gy4team10.dao.IUserDao;
import com.gy4team10.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;


/**
 * Dao
 *
 * @author http://blog.csdn.net/java2000_wl
 * @version <b>1.0</b>
 */
public class UserDao extends AbstractBaseRedisDao<String, User> implements IUserDao {
    private static final String C_USER_PREFIX = "user:";

    public Set<String> listKeys() {
        Set<String> result = redisTemplate.keys(C_USER_PREFIX+"*");
        return result;
    }
    /**
     * 新增
     * <br>------------------------------<br>
     *
     * @param user
     * @return
     */
    public boolean add(final User user) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize(C_USER_PREFIX + user.getId());
                byte[] name = serializer.serialize(user.getName());
                return connection.setNX(key, name);
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
    public boolean add(final List<User> list) {
        Assert.notEmpty(list);
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                for (User user : list) {
                    byte[] key = serializer.serialize(C_USER_PREFIX + user.getId());
                    byte[] name = serializer.serialize(user.getName());
                    connection.setNX(key, name);
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
    public void delete(String key) {
        List<String> list = new ArrayList<String>();
        list.add(key);
        delete(list);
    }

    /**
     * 删除多个
     * <br>------------------------------<br>
     *
     * @param keys
     */
    public void delete(List<String> keys) {
        List<String> userKeys = new ArrayList();
        for(String key:keys){
            userKeys.add(C_USER_PREFIX +key);
        }
        redisTemplate.delete(userKeys);
    }

    /**
     * 修改
     * <br>------------------------------<br>
     *
     * @param user
     * @return
     */
    public boolean update(final User user) {
        String key = user.getId();
        if (get(key) == null) {
            throw new NullPointerException("数据行不存在, key = " + key);
        }
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize(C_USER_PREFIX +user.getId());
                byte[] name = serializer.serialize(user.getName());
                connection.set(key, name);
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
    public User get(final String keyId) {
        User result = redisTemplate.execute(new RedisCallback<User>() {
            public User doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize(C_USER_PREFIX +keyId);
                byte[] value = connection.get(key);
                if (value == null) {
                    return null;
                }
                String name = serializer.deserialize(value);
                return new User(keyId, name, null);
            }
        });
        return result;
    }

    public List<User> list() {
        Set<String> keyList = redisTemplate.keys(C_USER_PREFIX +"*");
        List<User> result = new ArrayList<User>();
        for(String key : keyList){
            result.add(get(key));
        }
        return result;
    }
}