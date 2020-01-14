package sell.repositor;

import org.springframework.data.jpa.repository.JpaRepository;
import sell.dataobject.SellerInfo;

public  interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findSellerInfoByOpenid(String openid);

}
