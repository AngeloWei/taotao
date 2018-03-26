package order.service;

import com.taotao.utils.TaotaoResult;
import order.pojo.OrderInfo;

public interface OrderService {

    TaotaoResult createOrder(OrderInfo orderInfo);

}
