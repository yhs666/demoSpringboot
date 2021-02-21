package cn.test.demo.servie; /*
 * @author: Max Yang
 * @date: 2021-02-21 7:59
 * @desc:
 */

import cn.test.demo.dataobject.SellerInfo;

public interface SellerService {
    /**
     *  通过openid 查询卖家信息
     * @param Openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String Openid);
}
