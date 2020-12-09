/**
 * 果汁或啤酒售完异常
 */
public class IngredientSortOutException extends RuntimeException{
    public IngredientSortOutException() {
    }

    public IngredientSortOutException(String message) {
        super(message);
    }
}
