package order.pojo;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderShipping;

import java.util.List;

public class OrderInfo extends TbOrder {

    private List<TbItem> itemList; //商品明细信息

    private TbOrderShipping tbOrderShipping; //商品配送信息

    public TbOrderShipping getTbOrderShipping() {
        return tbOrderShipping;
    }

    public void setTbOrderShipping(TbOrderShipping tbOrderShipping) {
        this.tbOrderShipping = tbOrderShipping;
    }

    public List<TbItem> getItemList() {

        return itemList;
    }

    public void setItemList(List<TbItem> itemList) {
        this.itemList = itemList;
    }
}
