package cn.test.demo.utils; /*
 * @author: Max Yang
 * @date: 2021-01-31 1:05
 * @desc:
 */

import cn.test.demo.ViewObject.ResoultObject;

public class ResoultVOUtil {

    public  static ResoultObject success(Object object){
        ResoultObject resoultObject = new ResoultObject();
        resoultObject.setData(object);
        resoultObject.setCode(0);
        resoultObject.setMsg("Success");

        return  resoultObject;
    }

    public  static ResoultObject success(){

        return  success(null);
    }

    public  static ResoultObject error(Integer code,String msg){
        ResoultObject resoultObject = new ResoultObject();
        //resoultObject.setDate(object);
        resoultObject.setCode(code);
        resoultObject.setMsg(msg);

        return  resoultObject;
    }
}


