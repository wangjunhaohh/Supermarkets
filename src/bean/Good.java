package bean;

public class Good {
    String id;
    String Goods;
    int Num;
    String Time;
    String Supplier;
    String photo;

    public String getPhoto() {
        return photo;
    }
    public Good() {
    }
    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
                "id='" + id + '\'' +
                ", Goods='" + Goods + '\'' +
                ", Num=" + Num +
                ", Time='" + Time + '\'' +
                ", Supplier='" + Supplier + '\'' +
                ", photo='" + photo + '\''
                ;
    }

    public Good(String id, String goods, int num, String time, String supplier, String photo) {
        this.id = id;
        Goods = goods;
        Num = num;
        Time = time;
        Supplier = supplier;
        this.photo = photo;
    }
}
