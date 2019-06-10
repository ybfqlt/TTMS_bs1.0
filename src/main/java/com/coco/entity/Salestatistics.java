package com.coco.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Salestatistics implements Serializable {
    private Integer salesStatisticsId;

    private Integer movieId;

    private Long saleCount;

    private BigDecimal saleMoneyCount;

    private static final long serialVersionUID = 1L;

    public Salestatistics(Integer movieId, Long saleCount, BigDecimal saleMoneyCount){
        this.movieId=movieId;
        this.saleCount=saleCount;
        this.saleMoneyCount=saleMoneyCount;
    }

    public Integer getSalesStatisticsId() {
        return salesStatisticsId;
    }

    public void setSalesStatisticsId(Integer salesStatisticsId) {
        this.salesStatisticsId = salesStatisticsId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Long getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Long saleCount) {
        this.saleCount = saleCount;
    }

    public BigDecimal getSaleMoneyCount() {
        return saleMoneyCount;
    }

    public void setSaleMoneyCount(BigDecimal saleMoneyCount) {
        this.saleMoneyCount = saleMoneyCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", salesStatisticsId=").append(salesStatisticsId);
        sb.append(", movieId=").append(movieId);
        sb.append(", saleCount=").append(saleCount);
        sb.append(", saleMoneyCount=").append(saleMoneyCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}