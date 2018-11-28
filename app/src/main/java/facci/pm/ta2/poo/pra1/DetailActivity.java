package facci.pm.ta2.poo.pra1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import facci.pm.ta2.poo.datalevel.DataException;
import facci.pm.ta2.poo.datalevel.DataObject;
import facci.pm.ta2.poo.datalevel.DataQuery;
import facci.pm.ta2.poo.datalevel.GetCallback;

public class DetailActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private View mpriceView;
    private ListView mListView;
    public ResultListAdapter m_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String precio = getIntent().getStringExtra("price");


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PR1 :: Detail");


        // INICIO - CODE6

        mListView = (ListView) findViewById(R.id.listView);
        mpriceView = findViewById(R.id.price);

        mListView.setOnItemClickListener(this);



        DataQuery query = DataQuery.get("item");
        query.getInBackground("Object_id" , new GetCallback<DataObject>()  {


            @Override
            public void done(DataObject object, DataException e) {
                if (e == null) {

                    m_adapter = new ResultListAdapter(DetailActivity.this, null);


                    m_adapter.mActivity = DetailActivity.this;

                    mListView.setAdapter(m_adapter);

                } else {
                    // Error

                }
            }
        });
        // FIN - CODE6

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
