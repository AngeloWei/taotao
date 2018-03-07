import com.taotao.jedis.JedisClient;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;


public class ContentTest {

    @Test
    public void testJedisCluster()throws Exception{
        Set<HostAndPort> node = new HashSet<>();
        node.add(new HostAndPort("192.168.2.128", 7000));
        node.add(new HostAndPort("192.168.2.128", 7001));
        node.add(new HostAndPort("192.168.2.128", 7002));
        node.add(new HostAndPort("192.168.2.128", 7003));
        node.add(new HostAndPort("192.168.2.128", 7004));
        node.add(new HostAndPort("192.168.2.128", 7005));
        JedisCluster jedisCluster = new JedisCluster(node);
        jedisCluster.set("key","1231232");
        String result = jedisCluster.get("key");
        System.out.println(result);
        jedisCluster.close();
    }
    @Test
    public void testJedis()throws Exception{
        Jedis jedis = new Jedis("192.168.2.128", 6379);
        jedis.set("123","123");
        System.out.println(jedis.get("123"));
        jedis.close();
    }
}
