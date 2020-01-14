package sell.service;

import sell.dataobject.SellerInfo;

public interface SellerInfoService {
    SellerInfo findSellerInfoByOpenid(String openid);
}
