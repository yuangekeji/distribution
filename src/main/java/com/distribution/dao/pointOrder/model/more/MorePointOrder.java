package com.distribution.dao.pointOrder.model.more;
import com.distribution.dao.pointOrder.model.PointOrder;

public class MorePointOrder extends PointOrder{
    private Integer goodsCd;
    private String goodsNm;
    private String memberName;
    private String memberPhone;

    public Integer getGoodsCd() {
        return goodsCd;
    }

    public void setGoodsCd(Integer goodsCd) {
        this.goodsCd = goodsCd;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }
}