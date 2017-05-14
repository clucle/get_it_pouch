package kr.edcan.getitpouch.models;

/**
 * Created by Junseok Oh on 2017-05-14.
 */

public class Costemic {
    //private String title, content, productId;
    public String productId, status, name, dDay, purchaseTime, imageUrl, brandName, price;

    public Costemic() {
    }

    public Costemic(String name, String productId, String status, String imageUrl,
                    String brandName, String dDay, String purchaseTime) {
        this.name = name;
        this.productId = productId;
        this.status = status;
        this.imageUrl = imageUrl;
        this.brandName = brandName;
        this.dDay = dDay;
        this.purchaseTime = purchaseTime;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDday() {
        return "D-" + dDay;
    }

    public void setdDay(String dDay) {
        this.dDay = dDay;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
