import java.awt.geom.Line2D;
import java.time.LocalDate;
import java.util.*;

public class West2FriedChickenRestaurant implements FriedChickenRestaurant{
    private double balance = 0; //余额
    private LinkedList<Beer> beerList; //啤酒列表
    private LinkedList<Juice> juiceList; //果汁列表
    private static List<SetMeal> mealList = new LinkedList<>(); //套餐列表
    //使用LinkedList的原因：
    //      LinkedList新增和删除数据效率较高，相比ArrayList，对于需要频繁进行增删操作的列表来说，LinkedList比较占优势

    public West2FriedChickenRestaurant(double balance, LinkedList<Beer> beerList, LinkedList<Juice> juiceList) {

        this.balance = balance;
        this.beerList = beerList;
        this.juiceList = juiceList;

        Drinks beer1 = new Beer("啤酒", 50.0, LocalDate.parse("2020-12-01"), 0.75f);
        Drinks beer2 = new Beer("啤酒", 50.0, LocalDate.parse("2020-12-01"), 0.50f);
        Drinks beer3 = new Beer("啤酒", 50.0, LocalDate.parse("2020-12-01"), 0.25f);

        Drinks juice1 = new Juice("果汁", 66.0, LocalDate.parse("2020-12-10"));
        Drinks juice2 = new Juice("果汁", 66.0, LocalDate.parse("2020-12-10"));
        Drinks juice3 = new Juice("果汁", 66.0, LocalDate.parse("2020-12-10"));

        SetMeal mealList1 = new SetMeal("套餐1：起飞", 66.0+1.0, "炸鸡翅膀", juice1);
        SetMeal mealList2 = new SetMeal("套餐2：芜湖", 50.0+5.0, "炸鸡腿", beer1);
        SetMeal mealList3 = new SetMeal("套餐3：鸡兄", 66.0+50.0, "炸全鸡", juice3);

        mealList.add(mealList1);
        mealList.add(mealList2);
        mealList.add(mealList3);
    }

    public double getBalance(){
        return this.balance;
    }

    public List<SetMeal> getMeal() {
        return this.mealList;
    }

    public void use(Beer beer) throws IngredientSortOutException{
        Iterator it = beerList.iterator();
        boolean sign = false; //判断啤酒是否已卖光
        while(it.hasNext()){
            Beer beer1 = (Beer) it.next();
            if(beer.getDrinkName() == beer1.getDrinkName()){
                sign = true;
                beerList.remove(beer1); //出售套餐同时移除
                break;
            }
        }
        if(!sign) throw new IngredientSortOutException("啤酒已售光！请及时进货！");
    }

    public void use(Juice juice) throws IngredientSortOutException{
        Iterator it = beerList.iterator();
        boolean sign = false; //判断果汁是否已卖光
        while(it.hasNext()){
            Juice juice1 = (Juice) it.next();
            if(juice.getDrinkName() == juice1.getDrinkName()){
                sign = true;
                beerList.remove(juice1); //出售套餐同时移除
                break;
            }
        }
        if(!sign) throw new IngredientSortOutException("果汁已售光！请及时进货！");
    }

    @Override
    public void sell(SetMeal setMeal) throws IngredientSortOutException{
        //判断饮料类型是啤酒还是果汁
        String type = "";
        if(setMeal.getDrinkName() instanceof Beer){
            type = "啤酒";
        }else if(setMeal.getDrinkName() instanceof Juice){
            type = "果汁";
        }

        try {
            if(beerList.size() == 0 && "啤酒".equals(type)) throw new IngredientSortOutException("啤酒已售光,请及时进货！");
            if(juiceList.size() == 0 && "果汁".equals(type)) throw new IngredientSortOutException("果汁已售光,请及时进货！");

            //判断饮料是否已过期，并打印信息
            if("啤酒".equals(type) && beerList.get(0).isPast() || "果汁".equals(type) && juiceList.get(0).isPast()){
                if("啤酒".equals(type)){
                    beerList.remove(0);
                    System.out.println("啤酒已过期，无法售卖！");
                }else{
                    juiceList.remove(0);
                    System.out.println("果汁已过期，无法售卖！");
                }
            }else{
                String mealName = setMeal.getMealName();

                switch (mealName){
                    case "套餐1：起飞":
                        balance += 67.0;
                        if(juiceList.isEmpty()){
                            throw new IngredientSortOutException("苹果汁已售光,请及时进货！");
                        }else{
                            juiceList.remove(0); //如果存在选择出售第一个符合条件的
                            System.out.println("成功购买：" + setMeal);
                        }
                        break;
                    case "套餐2：芜湖":
                        balance += 55.0;
                        if(beerList.isEmpty()){
                            throw new IngredientSortOutException("百威已售光，请及时进货！"); //抛出异常
                        }else{
                            beerList.remove(0); //如果存在选择出售第一个符合条件的
                            System.out.println("成功购买：" + setMeal);
                        }
                        break;
                    case "套餐3：鸡兄":
                        balance += 116.0;
                        if(juiceList.isEmpty()){
                            throw new IngredientSortOutException("菠萝汁已售光，请及时进货！");
                        }else{
                            juiceList.remove(0);
                            System.out.println("成功购买：" + setMeal);
                        }
                }
            }
        }catch (IngredientSortOutException e){
            e.printStackTrace(); //打印异常堆栈信息
        }
    }

    @Override
    public void purchase(Drinks drinks, int num, int totalCost) throws OverdraftBalanceException {
        if(drinks instanceof Beer){
            if(totalCost > balance){
                double dif1 = totalCost - balance;
                throw new OverdraftBalanceException("余额不足，还需要：" + dif1 + "元");
            }else{
                if("百威".equals(drinks.drinkName)){
                    for(int i = 0 ; i < num ; i++){
                        beerList.add(new Beer("百威", 50.0, drinks.getProDate(), 0.75f));
                    }
                    balance -= totalCost;
                    System.out.println("成功购买" + num + "瓶百威");
                }else if("青岛".equals(drinks.drinkName)){
                    for(int i = 0 ; i < num ; i++){
                        beerList.add(new Beer("青岛", 50.0, drinks.getProDate(), 0.50f));
                    }
                    balance -= totalCost;
                    System.out.println("成功购买" + num + "瓶青岛");
                }else if("雪花".equals(drinks.drinkName)){
                    for(int i = 0 ; i < num ; i++){
                        beerList.add(new Beer("雪花", 50.0, drinks.getProDate(), 0.25f));
                    }
                    balance -= totalCost;
                    System.out.println("成功购买" + num + "瓶雪花");
                }

            }
        }else if(drinks instanceof Juice){
            if(totalCost > balance){
                double dif2 = totalCost - balance;
                throw new OverdraftBalanceException("余额不足，还需要：" + dif2 + "元");
            }else{
                if("苹果汁" == drinks.drinkName){
                    for(int i = 0 ; i < num ; i++){
                        juiceList.add(new Juice("苹果汁", 66.0, drinks.getProDate()));
                    }
                    balance -= totalCost;
                    System.out.println("成功购买" + num + "瓶苹果汁");
                }else if("西瓜汁" == drinks.drinkName){
                    for(int i = 0 ; i < num ; i++){
                        juiceList.add(new Juice("西瓜汁", 66.0, drinks.getProDate()));
                    }
                    balance -= totalCost;
                    System.out.println("成功购买" + num + "瓶西瓜汁");
                }else if("菠萝汁" == drinks.drinkName){
                    for(int i = 0 ; i < num ; i++){
                        juiceList.add(new Juice("菠萝汁", 66.0, drinks.getProDate()));
                    }
                    balance -= totalCost;
                    System.out.println("成功购买" + num + "瓶菠萝汁");
                }
            }
        }
    }


}
