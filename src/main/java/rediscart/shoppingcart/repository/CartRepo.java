package rediscart.shoppingcart.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepo {
    @Autowired
    @Qualifier("repo")
    private RedisTemplate<String, String> redisTemplate;

    public void save() {
        // Code here
    }

    public Boolean check(String name) {
        return redisTemplate.hasKey(name);
    }

    public void addItem(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    public List<String> getCart(String key) {
        ListOperations<String, String> ops = redisTemplate.opsForList();
        Long size = ops.size(key);
        return ops.range(key, 0, size);
    }
}
