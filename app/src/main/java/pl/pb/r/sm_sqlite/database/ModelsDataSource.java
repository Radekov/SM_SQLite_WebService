package pl.pb.r.sm_sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radosław Naruszewicz on 2016-11-30.
 */

public class ModelsDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = {
            DatabaseHelper.ID, DatabaseHelper.NAME, DatabaseHelper.VALUE
    };

    public ModelsDataSource(Context contex){
        dbHelper =  new DatabaseHelper(contex);
    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }

    public Model createModel(Model m){
        return createModel(m.getName(),m.getValue());
    }

    public Model createModel(String name, Double value){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.NAME, name);
        values.put(DatabaseHelper.VALUE, value);
        long insertId = database.insert(DatabaseHelper.TABLE,null,values);
        Cursor cursor = database.query(DatabaseHelper.TABLE,allColumns,DatabaseHelper.ID + " = " +insertId,
                null,null,null,null);
        cursor.moveToFirst();
        Model newModel = cursorToModel(cursor);
        cursor.close();
        return newModel;
    }

    public void deleteModel(Model model){
        long id = model.getId();
        database.delete(DatabaseHelper.TABLE, DatabaseHelper.ID + " = " + id,null);
    }

    public List<Model> getAllModels(){
        List<Model> models =  new ArrayList<>();

        Cursor cursor = database.query(DatabaseHelper.TABLE,allColumns,null,
                null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Model model = cursorToModel(cursor);
            models.add(model);
            cursor.moveToNext();
        }
        cursor.close();
        return models;
    }

    public void updateModel(Model model){
        long id = model.getId();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.NAME,model.getName());
        values.put(DatabaseHelper.VALUE,model.getValue());
        database.update(DatabaseHelper.TABLE,values,DatabaseHelper.ID + " = " + model.getId(),
                null);
    }

    private Model cursorToModel(Cursor cursor){
        Model model = new Model();
        model.setId(cursor.getLong(0));
        model.setName(cursor.getString(1));
        model.setValue(cursor.getDouble(2));
        return model;
    }
}
