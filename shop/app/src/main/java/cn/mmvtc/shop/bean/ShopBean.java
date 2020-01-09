package cn.mmvtc.shop.bean;

public class ShopBean {
    private String id;
    private String title;//商品的名字
    private String price;//商品的价格

    public String getId(){
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice(){
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

}
