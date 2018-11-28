package facci.pm.ta2.poo.pra1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import facci.pm.ta2.poo.datalevel.DataException;
import facci.pm.ta2.poo.datalevel.DataObject;
import facci.pm.ta2.poo.datalevel.DataQuery;
import facci.pm.ta2.poo.datalevel.FindCallback;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity implements  ListView.OnItemClickListener {

    private View mProgressView;
    private ListView mListView;
    public ResultListAdapter m_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        String user_email = getIntent().getStringExtra("user_email");

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PR1 :: Results");

        mListView = (ListView) findViewById(R.id.listView);
        mProgressView = findViewById(R.id.progress);


        mListView.setOnItemClickListener(this);

        showProgress(true);

        // ************************************************************************
        // INICIO - CODE3
        //
        DataQuery query = DataQuery.get("item");
        query.findInBackground("Object_id", "", DataQuery.OPERATOR_ALL, new FindCallback<DataObject>() {
            @Override
            public void done(ArrayList<DataObject> dataObjects, DataException e) {
                if (e == null) {
                    if (dataObjects.size() != 0) {
                        m_adapter = new ResultListAdapter(ResultsActivity.this, null);

                        m_adapter.m_array = dataObjects;
                        m_adapter.mActivity = ResultsActivity.this;

                        showProgress(false);
                        mListView.setAdapter(m_adapter);
                    }
                } else {
                    // Error

                }
            }
        });
        // FIN - CODE3
        // ************************************************************************


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {


        // INICIO - CODE5
        //
        DataObject Object_id = (DataObject) m_adapter.m_array.get(position);



        Bundle parmetros = new Bundle();
        parmetros.putString("Object_id", String.valueOf(Object_id));

        Intent i = new Intent(this, DetailActivity.class);
        i.putExtras(parmetros);
        startActivity(i);



        // FIN - CODE5


    }

    private void showProgress(final boolean show) {

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mListView.setVisibility(show ? View.GONE : View.VISIBLE);

    }



}
