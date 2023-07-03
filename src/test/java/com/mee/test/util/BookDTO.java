package com.mee.test.util;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author: funnyzpc
 **/
public class BookDTO {

    private Long id;

    private String name;

    private String label;

    private Double price;

    private Date datePublication;
    private Boolean statusPublication;

    private BigDecimal serialPublication;

    private Timestamp createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public Boolean getStatusPublication() {
        return statusPublication;
    }

    public void setStatusPublication(Boolean statusPublication) {
        this.statusPublication = statusPublication;
    }

    public BigDecimal getSerialPublication() {
        return serialPublication;
    }

    public void setSerialPublication(BigDecimal serialPublication) {
        this.serialPublication = serialPublication;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}