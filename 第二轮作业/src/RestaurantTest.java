import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class RestaurantTest {

    public static void main(String[] args) {

        LinkedList<Beer> beerList = new LinkedList<Beer>();
        LinkedList<Juice> juiceList = new LinkedList<Juice>();

        West2FriedChickenRestaurant res = new West2FriedChickenRestaurant(50000, beerList, juiceList);
        //West2FriedChickenRestaurant res = new West2FriedChickenRestaurant(0, beerList, juiceList);
        //可测试balance=0时报OverdraftBalanceException异常
        Scanner sc = new Scanner(System.in);
        List<SetMeal> mealList = res.getMeal();
        System.out.println("欢迎使用西二炸鸡店管理系统！请选择以下数字进入相应功能");
        System.out.println("{ 1-->批量进货, 2-->售卖, 3-->查询余额, exit-->退出系统 }");

        while(sc.hasNext()) {
            String input = sc.next();
            if ("exit".equals(sc.next())) {
                System.out.println("西二炸鸡店管理系统已退出，欢迎您的下次使用！");
                break;
            }

            switch (input) {
                case "1":
                    System.out.println("===============================");
                    System.out.println("欢迎进入进货界面！");
                    System.out.println("请输入以下所要进货的饮料编号(a,b,c,d,e,f)：");
                    System.out.println("a: 百威");
                    System.out.println("b: 青岛");
                    System.out.println("c: 雪花");
                    System.out.println("d: 苹果汁");
                    System.out.println("e: 西瓜汁");
                    System.out.println("f: 菠萝汁");
                    System.out.println("按s退出");
                    while(sc.hasNext()){
                        try {
                            if ("a".equals(sc.next())) {
                                System.out.print("请输入购买数量：");
                                Beer beer1 = new Beer("百威", 50.0, LocalDate.parse("2020-12-01"), 0.75f);
                                int num = sc.nextInt();
                                res.purchase(beer1, num, 50 * num);
                            } else if ("b".equals(sc.next())) {
                                System.out.print("请输入购买数量：");
                                Beer beer2 = new Beer("青岛", 50.0, LocalDate.parse("2020-12-01"), 0.50f);
                                int num = sc.nextInt();
                                res.purchase(beer2, num, 50 * num);
                            } else if ("c".equals(sc.next())) {
                                System.out.print("请输入购买数量：");
                                Beer beer3 = new Beer("雪花", 50.0, LocalDate.parse("2020-12-01"), 0.25f);
                                int num = sc.nextInt();
                                res.purchase(beer3, num, 50 * num);
                            } else if ("d".equals(sc.next())) {
                                System.out.print("请输入购买数量：");
                                Juice juice1 = new Juice("苹果汁", 66.0, LocalDate.parse("2020-12-10"));
                                int num = sc.nextInt();
                                res.purchase(juice1, num, 66 * num);
                            } else if ("e".equals(sc.next())) {
                                System.out.print("请输入购买数量：");
                                Juice juice2 = new Juice("西瓜汁", 66.0, LocalDate.parse("2020-12-10"));
                                int num = sc.nextInt();
                                res.purchase(juice2, num, 66 * num);
                            } else if ("f".equals(sc.next())) {
                                System.out.print("请输入购买数量：");
                                Juice juice3 = new Juice("菠萝汁", 66.0, LocalDate.parse("2020-12-10"));
                                int num = sc.nextInt();
                                res.purchase(juice3, num, 66 * num);
                            } else if ("s".equals(sc.next())) {
                                break;
                            } else {
                                System.out.println("输入错误，请重试！");
                            }
                        } catch (OverdraftBalanceException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "2":
                    System.out.println("===============================");
                    System.out.println("欢迎进入售卖界面！");
                    System.out.println("请输入以下所要购买的套餐编号(a,b,c)：");
                    System.out.println("a: 套餐1");
                    System.out.println("b: 套餐2");
                    System.out.println("c: 套餐3");
                    System.out.println("按s退出");
                    while(sc.hasNext()){
                        try{
                            if("a".equals(sc.next())){
                                Juice juice1 = new Juice("苹果汁", 66.0, LocalDate.parse("2020-12-10"));
                                SetMeal setMeal1 = new SetMeal("套餐1：起飞", 66.0+1.0, "炸鸡翅膀", juice1);
                                res.sell(setMeal1);
                            }else if("b".equals(sc.next())){
                                Beer beer1 = new Beer("百威", 50.0, LocalDate.parse("2020-12-01"), 0.75f);
                                SetMeal setMeal2 = new SetMeal("套餐2：芜湖", 50.0+5.0, "炸鸡腿", beer1);
                                res.sell(setMeal2);
                            }else if("c".equals(sc.next())){
                                Juice juice3 = new Juice("菠萝汁", 66.0, LocalDate.parse("2020-12-10"));
                                SetMeal setMeal3 = new SetMeal("套餐3：鸡兄", 66.0+50.0, "炸全鸡", juice3);
                                res.sell(setMeal3);
                            }else if("s".equals(sc.next())){
                                break;
                            }else{
                                System.out.println("输入错误，请重试！");
                            }
                        }catch (IngredientSortOutException e){
                            e.printStackTrace();
                        }
                    }
                    break;
                case "3":
                    System.out.println("===============================");
                    System.out.println("您的当前余额为：" + res.getBalance());
                    break;
                default:
                    System.out.println("输入错误！请重新输入！");
                    break;
            }
            System.out.println("===============================");
            System.out.println("{ 1-->批量进货, 2-->售卖, 3-->查询余额, exit-->退出系统 }");
            System.out.println("===============================");
        }
    }
}
