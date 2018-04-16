package com.greentown.chameleonadapter.homepage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.greentown.annotations.BindItem;
import com.greentown.chameleonadapter.ChameleonAdapter;
import com.greentown.chameleonadapter.ItemViewBinder;
import com.greentown.chameleonadapter.R;
import com.greentown.chameleonadapter.data.FirstItemEntity;
import com.greentown.chameleonadapter.homepage.data_entity.HomePageEntity;

import static com.greentown.chameleonadapter.R.id.txt_hello;

/**
 * @author zhengkaituo
 */
public class MainActivity extends AppCompatActivity {

    TextView txtHello;

    @BindItem(value = HomePageEntity.class, layout = R.layout.activity_main)
    ItemViewBinder binders;

    ChameleonAdapter mAdapter;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHello = findViewById(txt_hello);

        mAdapter = ChameleonAdapter.with(this);


    }
}
