package sell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sell.dataobject.SellerInfo;
import sell.repositor.SellerInfoRepository;
import sell.service.SellerInfoService;

@Service
public class SellerInfoServiceImpl implements SellerInfoService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findSellerInfoByOpenid(openid);
    }
}
