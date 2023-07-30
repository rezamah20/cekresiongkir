package com.checkapp.cekresiongkir.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.checkapp.cekresiongkir.R;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    static final String database_name = "db_resi";
    private static final int database_version = 1;


    public static final String table_resi = "tb_resi";
    public static final String key_id = "id";
    public static final String key_label = "label";
    public static final String key_resi = "no_resi";
    public static final String key_kurir = "kurir";
    public static final String key_status = "status";


    public DatabaseHandler(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table_resi = "CREATE TABLE " + table_resi + "("+key_id+" INTEGER PRIMARY KEY autoincrement, "+key_label+" TEXT, "+key_resi+" TEXT, "+key_kurir+" TEXT, "+key_status+" TEXT)";
        sqLiteDatabase.execSQL(create_table_resi);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +table_resi);
        onCreate(db);
    }

    public ArrayList<ResiModel> getResi(){
        ArrayList <ResiModel> resiModelsArrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " +table_resi;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()){
            do {
                ResiModel resiModel = new ResiModel();
                resiModel.setId(c.getString(0));
                resiModel.setLabel(c.getString(1));
                resiModel.setResi(c.getString(2));
                resiModel.setKurir(c.getString(3));
                resiModel.setStatus(c.getString(4));
                resiModelsArrayList.add(resiModel);
            }while (c.moveToNext());
        }
        return resiModelsArrayList;
    }

    public void insert(String label, String resi, String kurir, String status){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM " + table_resi+ " WHERE " +key_resi+ " = " +resi, null);
        boolean exist = (c.getCount() > 0);
      //  if (exist){
            Log.d("ini json", "resi sudah ada");
      //  }else {
            String query = "INSERT INTO " +table_resi+"("+key_label+","+key_resi+","+key_kurir+","+key_status+")VALUES('"+label+"','"+resi+"','"+kurir+"','"+status+"')";
            database.execSQL(query);
      //  }
    }

    public void delete(String id){
        SQLiteDatabase database = this.getWritableDatabase();

        String query = "DELETE FROM " +table_resi+ " WHERE " +key_id+ " = " +id;
        database.execSQL(query);
    }

    public void update( String label, String resi, String kurir, String status){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "UPDATE "+table_resi+" SET "+key_label+" = '"+label+"',"+key_resi+" = '"+resi+"',"+key_kurir+" = '"+kurir+"',"+key_status+" = '"+status+"' WHERE " +key_resi+ " = " +resi;
        database.execSQL(query);

    }

}
