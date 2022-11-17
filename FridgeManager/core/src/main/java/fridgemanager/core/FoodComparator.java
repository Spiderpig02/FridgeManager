package fridgemanager.core;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class FoodComparator implements Comparator<Food>, Serializable {

    @Override
    public int compare(Food food1, Food food2) {
        LocalDate today = LocalDate.now();
        long difference1 = ChronoUnit.DAYS.between(today, food1.getExpirationDate());
        long difference2 = ChronoUnit.DAYS.between(today, food2.getExpirationDate());
        return Math.toIntExact(difference1 - difference2);
    }
}
