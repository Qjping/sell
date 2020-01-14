package sell.enmu;

import lombok.Getter;
import org.aopalliance.reflect.Code;

@Getter
public enum ResultEnum implements CodeEnum {
    SUCCESS(0,"成功"),
        PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不足"),
    PRODUCT_STATUS_ERROR(12,"商品状态错误"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单不正确"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),
    ORDER_CANCEL_SUCCESS(22, "订单取消成功"),

    ORDER_FINISH_SUCCESS(23, "订单完结成功"),

    CART_EMPTY(18,"购物车不能为空"),

    ORDER_OWNER_ERROR(19,"该订单不属于ciyonghu"),


    WECHAT_MP_ERROR(43000,"授权信息获取失败"),
    WECHAT_NOTIFY_MONEY_VERIFE_ERROR(43000,"授权信息获取失败"),


    LOGIN_FAIL(25, "登录失败, 登录信息不正确"),
    LOGOUT_SUCCESS(26, "登出成功"),
            ;
            ;




    private  Integer code;
    private  String message;

    ResultEnum(Integer code, String message) {
        this.code=code;
        this.message=message;
    }
}
