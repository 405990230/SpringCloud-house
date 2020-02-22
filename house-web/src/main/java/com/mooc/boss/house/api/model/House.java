package com.mooc.boss.house.api.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties({"houseFiles", "floorPlanFiles"})
public class House {

    private Long id;
    private Integer type;
    private Integer price;
    private String name;
    private String images;
    private Integer area;
    private Integer beds;
    private Integer baths;
    private Double rating;

    private Integer roundRating = 0;
    private String remarks;
    private String properties;
    private String floorPlan;
    private String tags;
    private Date createTime;
    private Integer cityId;
    private Integer communityId;
    private String address;

    private String firstImg;

    private List<String> imageList = Lists.newArrayList();


    private List<String> floorPlanList = Lists.newArrayList();
    private List<String> featureList = Lists.newArrayList();

    @JSONField(deserialize = false, serialize = false)
    private List<MultipartFile> houseFiles;
    @JSONField(deserialize = false, serialize = false)
    private List<MultipartFile> floorPlanFiles;


    private String priceStr;

    private String typeStr;


    private Long userId;

    private boolean bookmarked;

    private Integer state;

    private List<Long> ids;

    private String sort = "time_desc";//price_desc,price_asc,time_desc

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
        if (Objects.equal(type, 1) && Objects.equal(type, 0)) {
            this.typeStr = "For Sale";
        } else {
            this.typeStr = "For Rent";
        }
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
        this.priceStr = this.price + "万";
    }

    public void setProperties(String properties) {
        this.properties = properties;
        this.featureList = Splitter.on(",").splitToList(properties);
    }

    public String getFloorPlan() {
        return floorPlan;
    }

    public void setFloorPlan(String floorPlan) {
        this.floorPlan = floorPlan;
        if (StringUtils.isNotBlank(floorPlan)) {
            this.floorPlanList = Splitter.on(",").splitToList(floorPlan);
        }

    }

    public void setImages(String images) {
        this.images = images;
        if (StringUtils.isNotBlank(images) && firstImg == null) {
            List<String> list = Splitter.on(",").splitToList(images);
            if (list.size() > 0) {
                this.firstImg = list.get(0);
                this.imageList = list;
            }
        }
    }

    public void setFeatureList(List<String> featureList) {
        this.featureList = featureList;
        this.properties = Joiner.on(",").join(featureList);
    }

    public void setRating(Double rating) {
        this.rating = rating;
        this.roundRating = (int) Math.round(rating);
    }

    @Override
    public String toString() {
        return "House [id=" + id + ", type=" + type + ", price=" + price + ", name=" + name
                + ", images=" + images + ", area=" + area + ", beds=" + beds + ", baths=" + baths
                + ", rating=" + rating + ", roundRating=" + roundRating + ", remarks=" + remarks
                + ", properties=" + properties + ", floorPlan=" + floorPlan + ", tags=" + tags + ", createTime=" + createTime + ", cityId=" + cityId
                + ", communityId=" + communityId + ", address=" + address + ", firstImg=" + firstImg
                + ", floorPlanList=" + floorPlanList + ", featureList=" + featureList + ", houseFiles="
                + houseFiles + ", floorPlanFiles=" + floorPlanFiles + ", priceStr=" + priceStr
                + ", typeStr=" + typeStr + ", userId=" + userId + ", bookmarked="
                + bookmarked + ", state=" + state + ", ids=" + ids + ", sort=" + sort + "]";
    }

}
