package cn.test.demo.utils; /*
 * @author: Max Yang
 * @date: 2021-02-14 4:10
 * @desc:
 */

public class MathUtil {

    private  static  final  Double Money_Range=0.01;
    /**
     * 比较两个金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public  static Boolean equals(Double d1,Double d2){
        Double result = Math.abs(d1-d2);
        if (result < Money_Range){
            return  true;
        }else {
            return  false;
        }


    }
}
