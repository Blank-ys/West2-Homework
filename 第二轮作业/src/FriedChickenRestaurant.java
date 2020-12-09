public interface FriedChickenRestaurant{
    void sell(SetMeal setMeal) throws IngredientSortOutException; //出售套餐

    void purchase(Drinks drinks, int num, int totalCost) throws OverdraftBalanceException; //批量进货
}
