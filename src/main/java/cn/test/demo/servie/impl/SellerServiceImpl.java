package cn.test.demo.servie.impl; /*
 * @author: Max Yang
 * @date: 2021-02-21 8:01
 * @desc:
 */

import cn.test.demo.dataobject.SellerInfo;
import cn.test.demo.repository.SellerInfoRepository;
import cn.test.demo.servie.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository repository;
    @Override
    public SellerInfo findSellerInfoByOpenid(String Openid) {
        return repository.findByOpenid(Openid);
    }
}
