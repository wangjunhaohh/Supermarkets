package bean;

public class Sales {
    String id;
    String Goods;
    int Num;
    String OutTime;
    String Supplier;
    String photo;
    int TotalPrice;

    public Sales() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoods() {
        return Goods;
    }

    public void setGoods(String goods) {
        Goods = goods;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public String getOutTime() {
        return OutTime;
    }

    public void setOutTime(String outTime) {
        OutTime = outTime;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id='" + id + '\'' +
                ", Goods='" + Goods + '\'' +
                ", Num=" + Num +
                ", OutTime='" + OutTime + '\'' +
                ", Supplier='" + Supplier + '\'' +
                ", photo='" + photo + '\'' +
                ", TotalPrice=" + TotalPrice +
                '}';
    }
}
