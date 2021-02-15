package cn.test.demo.exception; /*
 * @author: Max Yang
 * @date: 2021-01-31 9:13
 * @desc:
 */

import cn.test.demo.enums.ResoultEnum;

public class SellException extends  RuntimeException{
    private  Integer code;
    private String message;
    public  SellException(ResoultEnum resoultEnum){
        super(resoultEnum.getMessage());
        this.code=resoultEnum.getCode();
    }

    public SellException( Integer code, String message1) {
        this.code = code;
        this.message = message1;
    }
}
