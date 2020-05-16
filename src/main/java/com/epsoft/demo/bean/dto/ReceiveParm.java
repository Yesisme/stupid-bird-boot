package com.epsoft.demo.bean.dto;

/**
 * @Description 医疗云公共入参
 **/

public class ReceiveParm {
    private String platId;
    private Long hosId;
    private String accHosId;
    private String bizType;
    private String format;
    private String charset;
    private String signType;
    private String sign;
    private String timestamp;
    private String version;
    private Object bizContent;
   
	public String getPlatId() {
        return platId;
    }

    public void setPlatId(String platId) {
        this.platId = platId;
    }

    public Long getHosId() {
        return hosId;
    }

    public void setHosId(Long hosId) {
        this.hosId = hosId;
    }

    public String getAccHosId() {
        return accHosId;
    }

    public void setAccHosId(String accHosId) {
        this.accHosId = accHosId;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object getBizContent() {
        return bizContent;
    }

    public void setBizContent(Object bizContent) {
        this.bizContent = bizContent;
    }
}
