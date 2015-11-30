package ua.kiev.netmaster.mysalarycalc.sqlitedao;

import java.util.List;

import ua.kiev.netmaster.mysalarycalc.domain.WorkTypePrice;

/**
 * Created by ПК on 24.10.2015.
 */
public interface DaoWorkTypePrice {
    Integer create(WorkTypePrice workTypePrice);
    WorkTypePrice read (Integer id);
    boolean update (WorkTypePrice workTypePrice);
    boolean delete (WorkTypePrice workTypePrice);
    List<WorkTypePrice> getAll();
}
