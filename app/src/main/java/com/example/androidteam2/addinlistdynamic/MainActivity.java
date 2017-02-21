package com.example.androidteam2.addinlistdynamic;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by AndroidTeam2 on 2/13/2017.
 */

public class MainActivity extends AppCompatActivity {

    private ListView myList;
    private static final String STATE_ITEMS = "items";
    private static final String SHARED_PREFS_NAME = "MY_SHARED_PREF";

    public static String purchaseItemName,purchaseCode,purchQuty,purRate,purDisc,str_details,arrayString;
    EditText edt_name,edt_itemCode,edt_quty,edt_rate,edt_discount;
    Button btn_save;
    int RowCount;
    MyCustomAdapter adapter;
    List<ListItem> listForSearchConcepts = new ArrayList<ListItem>();
    ListItem  item;
    TextView tex_rowCount, txt_total;
    int test, test2 = 0;
    public static int test3;
    public static ArrayList<String> finallist =  new ArrayList<String>(); ;
    Set<String> set;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        tex_rowCount = (TextView) findViewById(R.id.txt_rowCount);
        txt_total = (TextView) findViewById(R.id.txt_quty);

        myList = (ListView) findViewById(R.id.listt);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Row " + position + " clicked", Toast.LENGTH_SHORT).show();
                finallist = getArray();

            }
        });


        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                                                @Override
                                                public boolean onItemLongClick(AdapterView<?> parent, View view,
                                                                               int position, long id) {
                                                    finallist.remove(position);
                                                    adapter.notifyDataSetChanged();
                                                    Toast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_LONG).show();

                                                    return true;
                                                }
        });

            FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMyclick();
            }
        });


    }
    public void doMyclick() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.prchase_entity_dialogbx);
        edt_name = (EditText)dialog.findViewById(R.id.pur_dialog_itemName);
        edt_itemCode = (EditText) dialog.findViewById(R.id.pur_dialog_itemCode);
        edt_quty = (EditText) dialog.findViewById(R.id.pur_dialog_quty);
        edt_rate = (EditText) dialog.findViewById(R.id.pur_dialog_rate);
        edt_discount = (EditText) dialog.findViewById(R.id.purcDialog_disc);

        btn_save = (Button) dialog.findViewById(R.id.save_btn_dialogPur);
        btn_save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                purchaseItemName = edt_name.getText().toString();
                purchaseCode     = edt_itemCode.getText().toString();
                purchQuty = edt_quty.getText().toString();
                purRate =edt_rate.getText().toString();
                purDisc = edt_discount.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("Name", purchaseItemName);

                str_details = "{"+"pro_name="+purchaseItemName + "," + "pro_code="+purchaseCode + "," +"pro_qty=" +purchQuty+ "}";
                arrayString = "[" +str_details +"]";

                test = Integer.parseInt(purchQuty);
                test2 =  test2+test;
                txt_total.setText("" + test2);

                itemAdd();
                dialog.dismiss();
                finallist.add(str_details);

            }

        });
        dialog.show();

    }
    private void itemAdd() {
        item = new ListItem();
        item.setItemName(purchaseItemName);
        item.setItemCode(purchaseCode);
        item.setIteQuty(purchQuty);
        item.setItemRate(purRate);
        item.setItemDisc(purDisc);
        listForSearchConcepts.add(item );
        adapter = new MyCustomAdapter(MainActivity.this, android.R.layout.simple_list_item_1,listForSearchConcepts);
        myList.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        RowCount = listForSearchConcepts.size();
        System.out.println("Size of List :" +RowCount);
        tex_rowCount.setText("" +RowCount);


    }
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (! hasFocus) {
            Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            sendBroadcast(closeDialog);
        }
    }
    public boolean saveArray() {
        SharedPreferences sp = this.getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor mEdit1 = sp.edit();
        set = new HashSet<>();
        set.addAll(finallist);
        mEdit1.putStringSet("list", set);
        System.out.println("Save List" +mEdit1.toString());
        return mEdit1.commit();
    }

    public ArrayList<String> getArray() {
        SharedPreferences sp = this.getSharedPreferences(SHARED_PREFS_NAME, MainActivity.MODE_PRIVATE);
        set = sp.getStringSet("list", new HashSet<String>());
        return new ArrayList<String>(set);
    }
    public void onStop(){
        saveArray();
        super.onStop();
    }

}
