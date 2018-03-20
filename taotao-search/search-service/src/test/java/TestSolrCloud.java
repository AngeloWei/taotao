
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/applicationContext-solr.xml")
public class TestSolrCloud {

    @Autowired
    private SolrClient solrClient;

    @Test
    public  void testCloud() throws IOException, SolrServerException {
        //执行添加
       // HttpSolrClient build = new HttpSolrClient.Builder("http://192.168.2.129:8080/solr/core1").build();
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "213122222");
        document.addField("item_sell_point", "卖点描述？？");
        solrClient.add(document);
        solrClient.commit();
    }
    @Test
    public void testCloud1() throws IOException, SolrServerException {
        String zkHost = "192.168.2.128:2281,192.168.2.129:2281,192.168.2.129:2282";
        CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder().withZkHost(zkHost).build();
        cloudSolrClient.setDefaultCollection("collection2");
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "test2131");
        document.addField("item_desc", "这是一个描述");
        cloudSolrClient.add(document);
        cloudSolrClient.commit();

    }
}
