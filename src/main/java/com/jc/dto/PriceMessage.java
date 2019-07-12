package com.jc.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PriceMessage {
    private String messageId;
    private String baseProductId;
    private String variantCode;
    private Double recommendedRetailPrice;
    private Double retailPrice;
    private String colorId;
    private String size;
    private String sizeRun;

    public Double getRecommendedRetailPrice() {
        return recommendedRetailPrice;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public String getBaseProductId() {
        return baseProductId;
    }

    public String getColorId() {
        return colorId;
    }

    public String getSize() {
        return size;
    }

    public String getSizeRun() {
        return sizeRun;
    }

    public String getVariantCode() {
        return variantCode;
    }

    public void setBaseProductId(String baseProductId) {
        this.baseProductId = baseProductId;
    }

    public void setVariantCode(String variantCode) {
        this.variantCode = variantCode;
    }

    public void setRecommendedRetailPrice(Double recommendedRetailPrice) {
        this.recommendedRetailPrice = recommendedRetailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setSizeRun(String sizeRun) {
        this.sizeRun = sizeRun;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
