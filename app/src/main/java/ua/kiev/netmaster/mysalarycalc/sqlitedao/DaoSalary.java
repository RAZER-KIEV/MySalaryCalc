package ua.kiev.netmaster.mysalarycalc.sqlitedao;

import java.util.List;

import ua.kiev.netmaster.mysalarycalc.domain.Salary;

/**
 * Created by ПК on 24.10.2015.
 */
public interface DaoSalary {
    Integer create(Salary salary);
    Salary read (Integer id);
    boolean update (Salary salary);
    boolean delete (Salary salary);
    List<Salary> getAll();
}

