package com.example.easywallet;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.easywallet.adapter.ChordListAdapter;
import com.example.easywallet.db.DatabaseHelper;
import com.example.easywallet.model.Chord;

import java.util.ArrayList;

import static com.example.easywallet.R.layout.item;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;

    private ArrayList<Chord> mChordList = new ArrayList<>();
    private ChordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DatabaseHelper(this);
        mDb = mHelper.getReadableDatabase();

        loadDataFromDb();

        mAdapter = new ChordListAdapter(
                this,
                item,
                mChordList
        );

        ListView lv = (ListView) findViewById(R.id.list_view);
        lv.setAdapter(mAdapter);


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                String[] chord = new String[]{"แก้ไขข้อมูล", "ลบข้อมูล"};

                dialog.setItems(chord, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (i == 1) { // ลบข้อมูล
                            Chord chord = mChordList.get(position);
                            int phoneId = chord.id;

                            mDb.delete(
                                    DatabaseHelper.TABLE_NAME,
                                    DatabaseHelper.COL_ID + "=?",
                                    new String[]{String.valueOf(phoneId)}
                            );
                            loadDataFromDb();
                            //  mAdapter.notifyDataSetChanged();
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    private  void loadDataFromDb(){
        Cursor cursor = mDb.query(
                DatabaseHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        mChordList.clear();

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_STR));
            String picture = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PIC));

            Chord chord = new Chord(id , title,picture);
            mChordList.add(chord);
        }

    }
}
