package kr.edcan.getitpouch.models;

/**
 * Created by Junseok Oh on 2017-05-14.
 */

public class Costemic {
    //private String title, content, productId;
    private String productId, status, name, dDay, purchaseTime, imageUrl, brandName;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDday() {
        return "D-" + dDay;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBrandName() {
        return brandName;
    }




}
