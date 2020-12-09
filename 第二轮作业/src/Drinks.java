import java.time.LocalDate;
//饮料类
public abstract class Drinks {
    protected String drinkName; //饮料名
    protected double drinkCost; //成本
    protected LocalDate proDate; //生产日期
    protected int expirDate; //保质期

    public Drinks(){
    }

    public Drinks(String drinkName, double drinkCost, LocalDate proDate, int expirDate) {
        this.drinkName = drinkName;
        this.drinkCost = drinkCost;
        this.proDate = proDate;
        this.expirDate = expirDate;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public void setDrinkCost(double drinkCost) {
        this.drinkCost = drinkCost;
    }

    public LocalDate getProDate() {
        return proDate;
    }

    public void setProDate(LocalDate proDate) {
        this.proDate = proDate;
    }

    public int getExpirDate() {
        return expirDate;
    }

    public void setExpirDate(int expirDate) {
        this.expirDate = expirDate;
    }

    public abstract boolean isPast(); //是否过期方法

    public abstract String toString(); //抽象的toString()方法

}
