package ua.kiev.netmaster.mysalarycalc.sqlitedao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.kiev.netmaster.mysalarycalc.domain.Post;

/**
 * Created by ПК on 24.10.2015.
 * //Post tab;
 db.execSQL("create table Posts ("
 + "id integer primary key autoincrement,"
 + "postName text,"
 + "comment text);");
 *
 */
public class DaoPostImpl implements DaoPost {
    private SQLiteDatabase database;
    private SQLDAO sqldao;
    private Map<String,String> onePostMap;
    private List<Post> postList;
    private ContentValues contentValues;
    private Post thisPost;
    private String posts= "Posts",id="id", postName="postName", comment="comment";

    public DaoPostImpl(Context context) {
        sqldao = SQLDAO.getInstance(context);
        database = sqldao.getWritableDatabase();
        onePostMap = new HashMap<>();
    }

    @Override
    public Integer create(Post post) {
        contentValues = new ContentValues();
        contentValues.put( "postName",post.getPostName());
        contentValues.put("comment", post.getComment());

        int rowId = (int)database.insert(posts,null,contentValues);

        return rowId;
    }

    @Override
    public Post read(Integer id) {
        Cursor cursor = database.query("Posts", null,"id = ?",new String[]{id.toString()},null,null,null);
        if(cursor!=null&&cursor.moveToFirst()){
            for(String colname: cursor.getColumnNames()){
                onePostMap.put(colname, cursor.getString(cursor.getColumnIndex(colname)));
            }
            thisPost = new Post(Integer.parseInt(onePostMap.get(id)),onePostMap.get(postName),onePostMap.get(comment));
        }
        return thisPost;
    }

    @Override
    public boolean update(Post post) {
        contentValues = new ContentValues();
        contentValues.put(postName, post.getPostName());
        contentValues.put(comment, post.getComment());
        int res = database.update(posts,contentValues,"id = ?",new String[]{post.getId().toString()});

        if(res>0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Post post) {
        int delCount = database.delete(posts, "id = " + post.getId(), null);

        if(delCount>0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Post> getAll() {
        postList = new ArrayList<>();
        Cursor cursor = database.query(posts, null, null, null, null, null, null);
        if(cursor!=null&&cursor.moveToFirst()){
            int idColIndex = cursor.getColumnIndex(id);
            int postNameColIndex = cursor.getColumnIndex(postName);
            int commentColIndex = cursor.getColumnIndex(comment);
            do{
                     Post curPost = new Post(Integer.parseInt(cursor.getString(idColIndex)),cursor.getString(postNameColIndex), cursor.getString(commentColIndex));
                     postList.add(curPost);
                }while (cursor.moveToNext());
        }

        return postList;
    }
}
