package junit.zyb.com.restaurant.bean;

/**
 * Created by zhouyibo on 2017/4/18.
 */

public class ShoppingCartBean {
    /**
     * id : 77
     * goods : shangpin
     * count : 3
     * explain : 说明
     * price : 100.00
     * img : /1442219020234.jpg
     */
    private boolean isChoosed = false;

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean ischoosed) {
        this.isChoosed = ischoosed;
    }

    private String id;
    private String goods;
    private String count;
    private String explain;
    private String price;
    private String img;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
