/**
 *
 */
package com.dubbo.cookie;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2015-3-28
 * @author
 * 提供cookie的存取及cookie加解密方法
 *
 */
public class CookieManager
{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private String privateKey = "";
    private String publicKey = "";

    /**
     * 设置Cookie
     *
     * @param Name   cookie名
     * @param Domain 域
     * @param MaxAge cookie生命周期
     * @param Value  值
     * @param Path   路径
     * @param res    response
     */
    public void setCookie(String Name, String Domain, int MaxAge,
                          String Value, String Path, HttpServletResponse res)
    {

        logger.debug("cookie value = " + Value);
        Cookie c = new Cookie(Name, Value);
        c.setDomain(Domain);
        c.setMaxAge(MaxAge);
        c.setPath(Path);
        res.addCookie(c);
    }

    /**
     * 设置Cookie
     *
     * @param Name   cookie名
     * @param Domain 域
     * @param MaxAge cookie生命周期
     * @param Value  值
     * @param Path   路径
     * @return cookie
     */
    public Cookie setCookie(String Name, String Domain, int MaxAge,
                            String Value, String Path)
    {
        Cookie c = new Cookie(Name, Value);
        c.setDomain(Domain);
        c.setMaxAge(MaxAge);
        c.setPath(Path);
        return c;
    }

    /**
     * @param req  request
     * @param name cookie's name
     * @return Cookie obj
     */
    public Cookie getCookie(HttpServletRequest req, String name)
    {
        Cookie cookies[] = req.getCookies();
        if (cookies == null)
        {
            return null;
        }
        for (int x1 = 0; x1 < cookies.length; x1++)
        {
            if (cookies[x1].getName().equals(name))
            {
                return cookies[x1];
            }
        }
        return null;
    }


    
    public String getPrivateKey()
    {
        return privateKey;
    }

    public void setPrivateKey(String privateKey)
    {
        this.privateKey = privateKey;
    }

    public String getPublicKey()
    {
        return publicKey;
    }

    public void setPublicKey(String publicKey)
    {
        this.publicKey = publicKey;
    }
    /**
     * @param src
     * @return
     * @throws
     */
    public String encode(String src)  
    {
        return new String("");
    }

    /**
     * @param src
     * @return
     * @throws
     */
    public String decode(String src) 
    {
        return new String("");
    }
    
    


    public static void main(String[] args) 
    {
//		String txt = "sunmoon0230@sina.com";
//		CookieManager cookieManager = new CookieManager();
//		String encodeTxt = cookieManager.encode(txt);
//		System.out.println(encodeTxt);
//		System.out.println(cookieManager.decode(encodeTxt));
	}

}
