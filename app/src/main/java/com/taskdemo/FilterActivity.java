package com.taskdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.taskdemo.Adapter.FilterRecyclerAdapter;
import com.taskdemo.Adapter.FilterValRecyclerAdapter;
import com.taskdemo.model.Filter;
import com.taskdemo.model.FilterDefaultMultipleListModel;
import com.taskdemo.model.MainFilterModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterActivity extends AppCompatActivity {

    RecyclerView filterListView = null;
    private RecyclerView filterValListView;
    private FilterRecyclerAdapter adapter;
    private FilterValRecyclerAdapter filterValAdapter;

    private ArrayList<String> company = new ArrayList<>();
    private ArrayList<String> topics = new ArrayList<>();
    private ArrayList<String> interviewType = new ArrayList<>();
    private ArrayList<String> college = new ArrayList<>();
    private ArrayList<String> jobType = new ArrayList<>();

    private ArrayList<FilterDefaultMultipleListModel> companyMultipleListModels = new ArrayList<>();
    private ArrayList<FilterDefaultMultipleListModel> topicsMultipleListModels = new ArrayList<>();
    private ArrayList<FilterDefaultMultipleListModel> interviewTypeMultipleListModels = new ArrayList<>();

    private ArrayList<FilterDefaultMultipleListModel> collegeMultipleListModels = new ArrayList<>();
    private ArrayList<FilterDefaultMultipleListModel> jobTypeMultipleListModels = new ArrayList<>();

    private ArrayList<MainFilterModel> filterModels = new ArrayList<>();
    private List<String> rootFilters;
    private Button btnClear;
    private Button btnFilter;
    private ArrayList<String> sizeSelected = new ArrayList<String>();
    private ArrayList<String> colorSelected = new ArrayList<String>();
    private ArrayList<String> styleSelected = new ArrayList<String>();

    private ArrayList<String> collegeSelected = new ArrayList<String>();
    private ArrayList<String> jobTypeSelected = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        rootFilters = Arrays.asList(this.getResources().getStringArray(R.array.filter_category));
        for (int i = 0; i < rootFilters.size(); i++) {
            /* Create new MainFilterModel object and set array value to @model
             * Description:
             * -- Class: MainFilterModel.java
             * -- Package:main.shop.javaxerp.com.shoppingapp.model
             * */
            MainFilterModel model = new MainFilterModel();
            /*Title for list item*/
            model.setTitle(rootFilters.get(i));
            /*Subtitle for list item*/
            model.setSub("All");
            /*Example:
             * --------------------------------------------
             * Brand => title
             * All => subtitle
             * --------------------------------------------
             * Color => title
             * All => subtitle
             * --------------------------------------------
             * */

            /*add MainFilterModel object @model to ArrayList*/
            filterModels.add(model);
        }

        filterListView = (RecyclerView) findViewById(R.id.filter_dialog_listview);
        adapter = new FilterRecyclerAdapter(this, R.layout.filter_list_item_layout, filterModels);
        filterListView.setAdapter(adapter);
        filterListView.setLayoutManager(new LinearLayoutManager(this));
        filterListView.setHasFixedSize(true);

        filterValListView = (RecyclerView) findViewById(R.id.filter_value_listview);
        filterValAdapter = new FilterValRecyclerAdapter(this,R.layout.filter_list_val_item_layout, companyMultipleListModels, MainFilterModel.SIZE);
        filterValListView.setAdapter(filterValAdapter);
        filterValListView.setLayoutManager(new LinearLayoutManager(this));
        filterValListView.setHasFixedSize(true);

        btnFilter = (Button) findViewById(R.id.btn_filter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (FilterDefaultMultipleListModel model : companyMultipleListModels) {
                    if (model.isChecked()) {
                        filterModels.get(MainFilterModel.INDEX_SIZE).getSubtitles().add(model.getName());
                    }
                }

                for (FilterDefaultMultipleListModel model : interviewTypeMultipleListModels) {
                    if (model.isChecked()) {
                        filterModels.get(MainFilterModel.INDEX_COLOR).getSubtitles().add(model.getName());
                    }
                }

                for (FilterDefaultMultipleListModel model : topicsMultipleListModels) {
                    if (model.isChecked()) {
                        filterModels.get(MainFilterModel.INDEX_STYLE).getSubtitles().add(model.getName());
                    }

                }
                /*Get value from checked of size checkbox*/
                sizeSelected = filterModels.get(MainFilterModel.INDEX_SIZE).getSubtitles();
                filterModels.get(MainFilterModel.INDEX_SIZE).setSubtitles(new ArrayList<String>());

                /*Get value from checked of color checkbox*/
                colorSelected = filterModels.get(MainFilterModel.INDEX_COLOR).getSubtitles();
                filterModels.get(MainFilterModel.INDEX_COLOR).setSubtitles(new ArrayList<String>());

                /*Get value from checked of price checkbox*/
                styleSelected = filterModels.get(MainFilterModel.INDEX_STYLE).getSubtitles();
                filterModels.get(MainFilterModel.INDEX_STYLE).setSubtitles(new ArrayList<String>());

                collegeSelected = filterModels.get(MainFilterModel.INDEX_SIZE).getSubtitles();
                filterModels.get(MainFilterModel.INDEX_SIZE).setSubtitles(new ArrayList<String>());

                jobTypeSelected = filterModels.get(MainFilterModel.INDEX_SIZE).getSubtitles();
                filterModels.get(MainFilterModel.INDEX_SIZE).setSubtitles(new ArrayList<String>());

//                if(sizeSelected.isEmpty() && colorSelected.isEmpty() && styleSelected.isEmpty()){
//                    Toast.makeText(FilterActivity.this,"Please select size,color,brand", Toast.LENGTH_SHORT).show();
//                }

                if(sizeSelected.size()>1 || colorSelected.size()>1 || styleSelected.size()>1 || collegeSelected.size()>1 || jobTypeSelected.size()>1)
                {
                    Toast.makeText(FilterActivity.this,"select atmost one from every list,eg 1 can have: atmost(1 company+1 topic+..)",Toast.LENGTH_LONG).show();
                }else {
                    if (sizeSelected.isEmpty()) {
                        Filter.company = "ALL";
                    } else {
                        Filter.company = sizeSelected.get(0);
                    }
                    if (colorSelected.isEmpty()) {
                        Filter.topic = "ALL";
                    } else {
                        Filter.topic = colorSelected.get(0);
                    }
                    if (styleSelected.isEmpty()) {
                        Filter.interview = "ALL";
                    } else {
                        Filter.interview = styleSelected.get(0);
                    }
                    if (collegeSelected.isEmpty()) {
                        Filter.college = "ALL";
                    } else {
                        Filter.college = collegeSelected.get(0);
                    }
                    if (jobTypeSelected.isEmpty()) {
                        Filter.job = "ALL";
                    } else {
                        Filter.job = jobTypeSelected.get(0);
                    }

                        Toast.makeText(FilterActivity.this, "Selected Company is " + sizeSelected.toString() + "\n" + "Selected Topic is " + colorSelected.toString() + "\n" + "Selected InterviewType is " + styleSelected.toString() + "\n" + "Selected college is " + collegeSelected.toString() + "\n" + "Selected jobType is " + jobTypeSelected.toString(), Toast.LENGTH_LONG).show();
                    Intent i=new Intent(FilterActivity.this,ViewActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        btnClear = (Button) findViewById(R.id.btn_clear);
        /*TODO: Clear user selected */
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (FilterDefaultMultipleListModel selectedModel : companyMultipleListModels) {
                    selectedModel.setChecked(false);

                }

                for (FilterDefaultMultipleListModel selectedModel : topicsMultipleListModels) {
                    selectedModel.setChecked(false);

                }

                for (FilterDefaultMultipleListModel selectedModel : interviewTypeMultipleListModels) {
                    selectedModel.setChecked(false);

                }
                adapter.notifyDataSetChanged();
                filterValAdapter.notifyDataSetChanged();
            }
        });


        adapter.setOnItemClickListener(new FilterRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                filterItemListClicked(position, v);
                adapter.setItemSelected(position);
            }
        });
        filterItemListClicked(0, null);
        adapter.setItemSelected(0);

//        sizes = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.filter_size)));
        company = new ArrayList<String>(Constants.company);
        for (String size : company) {

            /* Create new FilterDefaultMultipleListModel object for brand and set array value to brand model {@model}
             * Description:
             * -- Class: FilterDefaultMultipleListModel.java
             * -- Package:main.shop.javaxerp.com.shoppingapp.model
             * NOTE: #checked value @FilterDefaultMultipleListModel is false;
             * */
            FilterDefaultMultipleListModel model = new FilterDefaultMultipleListModel();
            model.setName(size);

            /*add brand model @model to ArrayList*/
            companyMultipleListModels.add(model);
        }
        topics = new ArrayList<String>(Constants.topic);
        for (String style : topics) {

            /* Create new FilterDefaultMultipleListModel object for brand and set array value to brand model {@model}
             * Description:
             * -- Class: FilterDefaultMultipleListModel.java
             * -- Package:main.shop.javaxerp.com.shoppingapp.model
             * NOTE: #checked value @FilterDefaultMultipleListModel is false;
             * */
            FilterDefaultMultipleListModel model = new FilterDefaultMultipleListModel();
            model.setName(style);

            /*add brand model @model to ArrayList*/
            topicsMultipleListModels.add(model);
        }
//        colors = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.filter_color)));
        interviewType = new ArrayList<String>(Constants.interviewType);
        for (String color : interviewType) {

            /* Create new FilterDefaultMultipleListModel object for brand and set array value to brand model {@model}
             * Description:
             * -- Class: FilterDefaultMultipleListModel.java
             * -- Package:main.shop.javaxerp.com.shoppingapp.model
             * NOTE: #checked value @FilterDefaultMultipleListModel is false;
             * */
            FilterDefaultMultipleListModel model = new FilterDefaultMultipleListModel();
            model.setName(color);

            /*add brand model @model to ArrayList*/
            interviewTypeMultipleListModels.add(model);
        }


        college = new ArrayList<String>(Constants.college);
        for (String color : college) {

            /* Create new FilterDefaultMultipleListModel object for brand and set array value to brand model {@model}
             * Description:
             * -- Class: FilterDefaultMultipleListModel.java
             * -- Package:main.shop.javaxerp.com.shoppingapp.model
             * NOTE: #checked value @FilterDefaultMultipleListModel is false;
             * */
            FilterDefaultMultipleListModel model = new FilterDefaultMultipleListModel();
            model.setName(color);

            /*add brand model @model to ArrayList*/
            collegeMultipleListModels.add(model);
        }

        jobType = new ArrayList<String>(Constants.jobType);
        for (String color : jobType) {

            /* Create new FilterDefaultMultipleListModel object for brand and set array value to brand model {@model}
             * Description:
             * -- Class: FilterDefaultMultipleListModel.java
             * -- Package:main.shop.javaxerp.com.shoppingapp.model
             * NOTE: #checked value @FilterDefaultMultipleListModel is false;
             * */
            FilterDefaultMultipleListModel model = new FilterDefaultMultipleListModel();
            model.setName(color);

            /*add brand model @model to ArrayList*/
            jobTypeMultipleListModels.add(model);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void filterItemListClicked(int position, View v) {
        if (position == 0) {
            filterValAdapter = new FilterValRecyclerAdapter(this, R.layout.filter_list_val_item_layout, companyMultipleListModels, MainFilterModel.SIZE);
        } else if (position == 1) {
            filterValAdapter = new FilterValRecyclerAdapter(this, R.layout.filter_list_val_item_layout, interviewTypeMultipleListModels, MainFilterModel.COLOR);
        } else if(position==2){
            filterValAdapter = new FilterValRecyclerAdapter(this, R.layout.filter_list_val_item_layout, topicsMultipleListModels, MainFilterModel.STYLE);
        }else if(position==3){
            filterValAdapter = new FilterValRecyclerAdapter(this, R.layout.filter_list_val_item_layout, collegeMultipleListModels, MainFilterModel.STYLE);
        }else{
            filterValAdapter = new FilterValRecyclerAdapter(this, R.layout.filter_list_val_item_layout, jobTypeMultipleListModels, MainFilterModel.STYLE);
        }

        filterValListView.setAdapter(filterValAdapter);

        filterValAdapter.setOnItemClickListener(new FilterValRecyclerAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                filterValitemListClicked(position);
            }
        });
        filterValAdapter.notifyDataSetChanged();
    }

    private void filterValitemListClicked(int position) {
        filterValAdapter.setItemSelected(position);
    }
}