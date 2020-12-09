public class SetMeal {
    private String mealName; //套餐名
    private double mealPrice; //价格
    private String chickenName; //炸鸡名
    private Drinks drinkType; //饮料类型：beer或juice

    public SetMeal() {
    }

    public SetMeal(String mealName, double mealPrice, String chickenName, Drinks drinkName) {
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.chickenName = chickenName;
        this.drinkType = drinkName;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public double getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(double mealPrice) {
        this.mealPrice = mealPrice;
    }

    public String getChickenName() {
        return chickenName;
    }

    public void setChickenName(String chickenName) {
        this.chickenName = chickenName;
    }

    public Drinks getDrinkName() {
        return drinkType;
    }

    public void setDrinkName(Drinks drinkName) {
        this.drinkType = drinkName;
    }

    @Override
    public String toString() {
        return "SetMeal{" +
                "mealName='" + mealName + '\'' +
                ", mealCost=" + mealPrice +
                ", chickenName='" + chickenName + '\'' +
                ", drinkType='" + drinkType + '\'' +
                '}';
    }
}
