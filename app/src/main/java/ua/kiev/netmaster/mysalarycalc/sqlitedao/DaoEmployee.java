package ua.kiev.netmaster.mysalarycalc.sqlitedao;

import java.util.List;

import ua.kiev.netmaster.mysalarycalc.domain.Employee;

/**
 * Created by ПК on 24.10.2015.
 */
public interface DaoEmployee {
    Integer create(Employee employee);
    Employee read (Long id);
    boolean update (Employee employee);
    boolean delete (Employee employee);
    List<Employee> getAll();
}
