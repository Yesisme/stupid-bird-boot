package com.epsoft.demo.base64;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.epsoft.demo.utils.Base64Util;
public class RSASignature {

	private static Logger log = LoggerFactory.getLogger(RSASignature.class);
    /**
     * 签名算法
     */
    private static final String SIGN_ALGORITHMS = "SHA256WithRSA";

    private static final String SIGN = "sign";

    /**
     * RSA签名
     *
     * @param content    待签名数据
     * @param privateKey 商户私钥
     * @param encode     字符集编码
     * @return 签名值
     */
    public static String sign(String content, String privateKey, String encode) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64Util.decode(privateKey));

            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            byte[] bytes = content.getBytes(encode);
            signature.update(bytes);

            byte[] signed = signature.sign();

            return Base64Util.encode(signed);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;
    }

    public static String sign(String content, String privateKey) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64Util.decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);
            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update(content.getBytes());
            byte[] signed = signature.sign();
            return Base64Util.encode(signed);
        } catch (Exception e) {
            log.error("加签失败:",e);
        }
        return null;
    }

    /**
     * RSA验签名检查
     *
     * @param content   待签名数据
     * @param sign      签名值
     * @param publicKey 分配给开发商公钥
     * @param encode    字符集编码
     * @return 布尔值
     */
    public static boolean doCheck(String content, String sign, String publicKey, String encode) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64Util.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));


            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes(encode));

            boolean bverify = signature.verify(Base64Util.decode(sign));
            return bverify;

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return false;
    }

    /**
     * RSA验签名检查
     *
     * @param content   待签名数据
     * @param sign      签名值
     * @param publicKey 分配给开发商公钥
     * @param encode    字符集编码
     * @return 布尔值
     */
    public static boolean doCheck(String content, String sign, String publicKey, String encode, String type) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(type);
            byte[] encodedKey = Base64Util.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));


            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes(encode));

            boolean bverify = signature.verify(Base64Util.decode(sign));
            return bverify;

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return false;
    }

    public static boolean doCheck(String content, String sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64Util.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));


            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes());
            byte[] decode = Base64Util.decode(sign);
            boolean bverify = signature.verify(decode);
            return bverify;

        } catch (Exception e) {
            log.error("验签失败:",e);
        }

        return false;
    }
    
    public static String getSignContent(Map sortedParams) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            if (SIGN.endsWith(key)) {
                continue;
            }
            String value = String.valueOf(sortedParams.get(key));

            if (!StringUtils.isEmpty(key)&&!StringUtils.isEmpty(value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }

    public static String getSignContent(Object obj) {
        if (obj == null) {
            return null;
        }
        StringBuffer content = new StringBuffer();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            int index = 0;
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if (value != null && !"sign".equals(key)) {
                        if (!StringUtils.isEmpty(key)&&!StringUtils.isEmpty(value)) {
                            content.append((index == 0 ? "" : "&") + key + "=" + String.valueOf(value));
                            index++;
                        }
                    }
                }

            }
        } catch (Exception e) {
            log.error("排列参数失败:",e);
            return null;
        }
        return content.toString();

    }

    public static void main(String[] args) {
		/* String pri = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCRl92LidWdkVO16jZ7jOhJ+kKBsem8+0cKpOcsqYMSSNrTGtyIOIDHU58VQBYkAErokDMhDF9hD6N9XrdWVclg2RGw8pg8SzTwp4kHT9h1BeeOYRzJWIY5ENqwRPJngJ5uB4ZOZx+MijwlA5Qus7zr2/I3UdUgsZtYY0dpqGpsV3cONecbSQam0ZMPugmIzm5g28YPTBMcORm2U9mirkVNl4neibm7BOGdX3jKLmvVL8jpSHSAX1mZypdDdGh+Y0LrnQbB+3XUOmf2m8Nhr6JEx3nF6Kq/LNIVW8UQNJJmrnL+e3IhE6Na91xSyuJJAVrwvvfiyxVoU/LL8x5wulQVAgMBAAECggEAKGoPhXxtX5/HrOem2AT1NhPSAk5wo5Tc04eS5ELpr1mp5I/AR8/nb202658uhi/Om1/r0XCtyTOeHl78ZWrKy6d10biFqbb2qxbWYsE3RMSu0kcCxqFcINsc2XFKGJELaV+NWJYnm8uDhoLHz271MkOLqO9+SeUDSjXEzP+DCy+OEQsgn/yj58gFfTodVZQt8msKzKe3oGxCSFWEKsHWq1an70qcYoT4coPeeFHq4hp9lxBN/omKgJmb+vRl96mCiA/StxzLqDpzqjacgohEZoDxRBfp3WiQCgdsXOH/fgo7uXOVcpmXmQpRa+hkt2LS2+wA74Dv69tyNf8vexAQAQKBgQD08+n10uV3P0qtASEwP0wfxT+bwsfgL3d3LyxaQ4Kjdw/gbvPTl0MjACgvTIvOmruNZxewkTZd5oc12wwBwMqVsYlZ4d9w7IIyMVQ4+AKPDCKj1YJMalLyTMeX09mGEbiuqJk2C5cMe1Gr4JtSm58bYwUgByDi7SBbDNXrBmvT1QKBgQCYKM1h7bC7so9+L+zoYHQSn8LuTLTFnFJe6smoWI50ZYpRUSdoxPKsfxjgn2cg4wTJG8Zr6lNo+3rRBRwPqVcDcE5J721CwHTyYkrSyJ+xhZJDhKWqW8vzVd7WfaxhNuCwsfs+Bfbt+JzC0jA6WNhPVQDPlzESjfRaCLhBOwffQQKBgQDuwXxu/lRpqghYnvxBccD6SqGYaf+2da3FPvbFOH5yZI/WG2+P8yUeTX3dtIelJs7eofjGQpQybIuoI80NPGR6CiUtteq7v9ubntdP+/VKhvKQrICniQ82Wz5Er9qbQlS38V/8/MkLAnx1wqTCnSs1X4vUTv5wEd9ywA0WWxjozQKBgEwyap92u0FTav5DG71Gx/mnnaNeMEyhOrKjmva92iC51KliHX0e23O8dgXObYOPjA715MiX4Ms63Ecd6u6A270yGVJ3Ht0FqcrTpCqoIyhv/k3neFq2e4zQ3LXG23MXWy/BAVMPA5gqHr6FKXeelDwuEijiysVC5zHLGhI4cHnBAoGAY0XSfprXPEktN4BVIvvA/arte5SrLltwHvQnz13cy/xAcJ07uPC71estNtvsrgMudxa5hsZ6l6llIKgOiXqdcvyz799PN0wd+z5/JqjELmoItp4WhqOKgPyj1pi3+1NmOouC67Sagm2hbfJTcZALLjlzkPFtR42Z79lAf/mDET0=";
		ResultParm resultParm = new ResultParm();
		resultParm.setBody(null);
		String signContent1 = RSASignature.getSignContent(resultParm);
		System.out.println(signContent1);
		String sign = RSASignature.sign(signContent1, pri);
		System.out.println(sign);
		resultParm.setSign(sign);
		String pub = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkZfdi4nVnZFTteo2e4zoSfpCgbHpvPtHCqTnLKmDEkja0xrciDiAx1OfFUAWJABK6JAzIQxfYQ+jfV63VlXJYNkRsPKYPEs08KeJB0/YdQXnjmEcyViGORDasETyZ4CebgeGTmcfjIo8JQOULrO869vyN1HVILGbWGNHaahqbFd3DjXnG0kGptGTD7oJiM5uYNvGD0wTHDkZtlPZoq5FTZeJ3om5uwThnV94yi5r1S/I6Uh0gF9ZmcqXQ3RofmNC650Gwft11Dpn9pvDYa+iRMd5xeiqvyzSFVvFEDSSZq5y/ntyIROjWvdcUsriSQFa8L734ssVaFPyy/MecLpUFQIDAQAB";
		//        String s = "{\"bizNo\":\"c52GgtLjSNBKFNXn2WPA6V1WcJNSEx6P4jrbR7xsaGT9N4MpgbsE04E2VTENZkkV\",\"platCode\":\"\",\"platMsg\":\"前置未配置转换后的hosId\",\"sign\":\"Wh4MSy4IroOm25Z7pt5XLUfFn3mvui6S7RXob2J4+xmAgaBWyLwUZHKBwslYl/+81JKe9SdcreXMrN7dywZvI3xIo67s+MizSwGobhTa7oRDFNpWJc/KmOSw3snUpdU4Es9KmfabpV6bknJ1ioQtcqPl8+EaeddGy9SIJvGnUtin9S1l+7ZxUKCWB3+KeG40TCzvam7o1XOyvtp6Cpl7rILnYTQt9QNDJxu2NocK/EZQFzy1/kfyRS+sZHJ8c1yrHgcB0WN0Jnv394COw/XDcrPfRJ9Icb7ksX8OB/EMkddXNisqE0V256XHy0d5HlvFuSbuEsvEengwLh/uoSaVWA==\"}";
		String s = "{\"platCode\":\"00000000\",\"platMsg\":null,\"sign\":\"R+/RVjbG15KHUFTllZDBZDAjuem5MlgmcOb6W12AhVyhGH2t2yDEyLlQCHJXSc4/xthcUl6ZI9tUOarHc/kEvpLSSiQCVbzm9XQPPn662zERiWLAh6OezTfRoNPe4OIIX5iKMvVNYrA33rR038nWH3ohtl+nQDZZQGoPqGkVTbupRKsMKIKeELFBNQfD+DaSwLFtAjSnsIKUNcgVFd+YjHMy2Djr9UGVlX4/KZhciBwenMtR1ST+wyPCwHTCLeaW1rgXUT/HM1858NxdKKeUjR7sFf46jZ9QGVgfDw3SsopK1wW/45FD+2C9rArET9/8lCsUo/gdzw3RZzo2MpDYqg==\",\"bizNo\":\"wA5I9aryf56m7dABFx4JhJvp2rGFyNROs9hbbNBWOlYhonU4HlyGi147otA5xQO8\",\"body\":{\"patCardNo\":null,\"patCardType\":null,\"accessPatId\":null,\"verifyFlag\":null,\"idCardType\":null,\"crtDate\":null}}";
		
		//        ResultParm entity = JSONObject.parseObject(s, ResultParm.class);
		String signContent = RSASignature.getSignContent(resultParm);
		System.out.println(signContent);
		boolean f = RSASignature.doCheck(signContent, resultParm.getSign(), pub);
		System.out.println(f);*/
    	String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzFEMUKsh7N/sFsNnMOiW6INRocM2j/4Tp3l3XmT5F3MrfCo+D16wvOzfYuBpYUwkxwEZ/8WJW5VUjOIGq3P58p8YpyvmES9YUAT06QQ/Uqu8i5oirfqx9ollR7XHfArxTcC5U1oKDaO0y9Ig6SF2MgjiVxiIzFmp7NnoxBr4T1FrNbV+hRuW/yutARfbCFjKnHBcgI65qJpSxvCZeYAXEJ/RiNe4nhsczA62VZRzj7B2OGG5DL94iBn026JqvBa+BWWkDVtXNfZY2eLV+2Vp5VhAcjvImyAUsNhORURTy5cjKNh9C9h2y9Nrvu49yPnbw1da9UxxZkDEJ5WKJRKN+QIDAQAB";
    	String content = "data=[{\"accessFeeId\":\"5423056\",\"accessPatId\":\"944386\",\"accessVisitNo\":\"944386\",\"crtRepTime\":\"2019-06-21 08:55:58\",\"deptName\":\"夏中医骨伤科\",\"docName\":\"陈超\",\"feePayType\":1,\"feeType\":1,\"hisBillNo\":\"5423056\",\"hosDistId\":0,\"orderType\":0,\"totalFee\":20.0},{\"accessFeeId\":\"5423051\",\"accessPatId\":\"944386\",\"accessVisitNo\":\"944386\",\"crtRepTime\":\"2019-06-21 08:55:33\",\"deptName\":\"夏中医骨伤科\",\"docName\":\"陈超\",\"feePayType\":1,\"feeType\":1,\"hisBillNo\":\"5423051\",\"hosDistId\":0,\"orderType\":0,\"totalFee\":10.0},{\"accessFeeId\":\"13632457\",\"accessPatId\":\"944386\",\"accessVisitNo\":\"944386\",\"crtRepTime\":\"2019-06-21 08:55:47\",\"deptName\":\"夏中医骨伤科\",\"docName\":\"陈超\",\"feePayType\":1,\"feeType\":1,\"hisBillNo\":\"13632457\",\"hosDistId\":0,\"orderType\":0,\"totalFee\":38.06}]&message=获取成功&status=1";
    	String sign = "StIGlRCVFtiNnr0GoHCj1TKENStlx/fXk+ndQ96VKEjLjIYCkSF8nPZI1axkqyuLPwWsduN1Cb9pXYAyOyntgGyHbH3SI8xGO7oDl53VZTBbWsW/b3rZWosXiY8VUbTZ239tP02NJhc3mgpE+5jNlgOYpGe4VWf3sn1krkRB0wHJt4KrTpXSNUrA/IFN3A0EyJhC4FiV/Bm72W4XeMLb0Jn2WafeN0ZdYhTcJQq3hc0vQqrSfhtfyqWq8a3QpnwOe2b4INWAOzPlFw3IInL2HqLYgr4WtqxgNjpLICsQ9j7a+HphtvKjSCIpMA4LPPsSbjQGLwx5fXqiSTrKHL03qw==";
    	boolean doCheck = RSASignature.doCheck(content, sign, publicKey);
    	System.out.println(doCheck);
    }
}
