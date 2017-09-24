package com.dubbo.utils;

/**
*
* 描    述： 手机号工具类
*
* 创 建 者： 刘栋
* 创建时间： 2017/8/7 15:17
* 创建描述：
*
* 修 改 者：
* 修改时间：
* 修改描述：
*
* 审 核 者：
* 审核时间：
* 审核描述：
*
*/
public class MobilePhoneUtil {

    /**
     * 由手机号获取运营商
     * 移动：134(不含1349)\135\136\137\138\139\147\150\151\152\157\158\159\182\183\184\187\188\178(4G)\1703\1705\1706(虚拟运营商)
     * 联通：130\131\132\145(上网卡)\155\156\185\186\176(4G)\175(4G)\171\1704\1707\1708\1709(虚拟运营商)
     * 电信：133\1349(卫星通信)\149\153\180\181\189\177(4G)\173(4G)\1700\1701\1702(虚拟运营商)
     *
     * 摘自百度百科：https://baike.baidu.com/item/%E6%89%8B%E6%9C%BA%E5%8F%B7/1057421
     *
     * 返回：
     * "mobile" ： 中国移动
     * "unicom" ： 中国联通
     * "telecom" ： 中国电信
     * "" : 无法识别
     */
    public static String getOperator(String phoneNo) {

        String mobilePattern = "((135|136|137|138|139|147|150|151|152|157|158|159|182|183|184|187|188|178)[0-9]|(134)[0-8]|(1703|1705|1706))[0-9]{7}";
        if(phoneNo.matches(mobilePattern)) {
            return "mobile";
        }
        String unicomPattern = "((130|131|132|145|155|156|185|186|176|175|171)[0-9]|(1704|1707|1708|1709))[0-9]{7}";
        if(phoneNo.matches(unicomPattern)) {
            return "unicom";
        }
        String telecomPattern = "((133|149|153|180|181|189|177|173)[0-9]|(1349|1700|1701|1702))[0-9]{7}";
        if(phoneNo.matches(telecomPattern)) {
            return "telecom";
        }

        return "";
    }

    public static void main(String[] args) {
        System.out.println(getOperator("17041553214"));
    }

}
