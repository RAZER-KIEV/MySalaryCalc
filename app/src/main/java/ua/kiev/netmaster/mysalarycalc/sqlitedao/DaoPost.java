package ua.kiev.netmaster.mysalarycalc.sqlitedao;

import java.util.List;

import ua.kiev.netmaster.mysalarycalc.domain.Post;

/**
 * Created by ПК on 24.10.2015.
 */
public interface DaoPost {
    Integer create(Post post);
    Post read (Integer id);
    boolean update (Post post);
    boolean delete (Post post);
    List<Post> getAll();
}
