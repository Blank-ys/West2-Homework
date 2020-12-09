import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//果汁类
public class Juice extends Drinks{
    private static final int qualDate = 2; //保质期2天

    public Juice() {
    }

    public Juice(String drinkName, double drinkCost, LocalDate proDate) {
        super(drinkName, drinkCost, proDate, qualDate);
    }

    @Override
    public boolean isPast() {
        //return this.proDate.until(LocalDate.now(), ChronoUnit.DAYS) > qualDate;
        int days = Math.abs(this.proDate.compareTo(LocalDate.now())); //今天和生产日期相差的天数
        return days > expirDate ? true : false;
    }

    @Override
    public String toString() {
        return "Juice{ " +
                "drinkName: " + drinkName +
                ", drinkCost: " + drinkCost +
                ", proDate: " + proDate +
                ", expirDate: " + expirDate +
                " }";
    }

}
