import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolr {
    @Test
    public void testDocument() throws  Exception {
        //1.创建server对象，获取连接
        SolrServer server = new HttpSolrServer("http://192.168.2.129:8080/solr/core1");
        //2.创建document对象，进行索引添加
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id","123123");
        document.addField("item_tile", "这是一个标题");
        document.addField("item_sell_point",123123);
        document.addField("item_desc","描述，商品还不错，但是不好用啊啊啊");
        //3.添加索引内容
        server.add(document);
        //4.提交添加内容
        server.commit();
    }
    public void  testQuery() throws Exception {

    }
}
