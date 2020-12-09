import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
//啤酒类
public class Beer extends Drinks{
    private float content; //酒精度数
    private static final int qualDate = 30; //保质期30天

    public Beer() {
    }

    public Beer(String drinkName, double drinkCost, LocalDate proDate, float content) {
        super(drinkName, drinkCost, proDate, qualDate);
        this.content = content;
    }

    //getter
    public float getContent() {
        return content;
    }
    //setter
    public void setContent(float content) {
        this.content = content;
    }

    @Override
    public boolean isPast() {
        int days = Math.abs(this.proDate.compareTo(LocalDate.now())); //今天和生产日期相差的天数
        return days > expirDate ? true : false;
    }

    @Override
    public String toString() {
        return "Beer{ " +
                "drinkName: " + drinkName +
                ", drinkCost: " + drinkCost +
                ", proDate: " + proDate +
                ", expirDate: " + expirDate +
                ", content: " + content +
                " }";
    }

}
