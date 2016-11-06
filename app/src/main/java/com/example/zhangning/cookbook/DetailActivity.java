package com.example.zhangning.cookbook;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhangning.cookbook.cookbook.beans.CookBook;
import com.example.zhangning.cookbook.cookbook.beans.Step;
import com.example.zhangning.cookbook.util.DensityUtil;
import com.squareup.picasso.Picasso;
import com.wefika.flowlayout.FlowLayout;

import java.util.List;

/**
 * Created by zhangning on 16/10/9.
 */

public class DetailActivity extends AppCompatActivity {
    ListView mainListview, fuListview, steplistview;
    CookBook cookbook;
    String[] mainList;
    String[] fulist;
    MainlistViewAdapter mainlistViewAdapter;
    FulistViewAdapter fulistViewAdapter;
    List<Step> steplist;
    SteplistAdapter steplistAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activitydetail);

        Intent intent = getIntent();

        CookBook cookBook = (CookBook) intent.getSerializableExtra("cookbook");
        this.cookbook = cookBook;
        mainlistViewAdapter = new MainlistViewAdapter();
        fulistViewAdapter = new FulistViewAdapter();

        Toolbar mToolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        String url = cookbook.getAlbums().get(0);
        Picasso.with(DetailActivity.this).load(url).into(iv);

        TextView titletv = (TextView) findViewById(R.id.tvtitle);
        TextView introtv = (TextView) findViewById(R.id.intro);
        titletv.setText(cookbook.getTitle());
        introtv.setText(cookbook.getImtro());


        FlowLayout flowlayout = (FlowLayout) findViewById(R.id.flowlayout);
        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = DensityUtil.px2dip(this, 30);
        params.rightMargin = DensityUtil.px2dip(this, 2);
        params.topMargin = DensityUtil.px2dip(this, 2);
        params.bottomMargin = DensityUtil.px2dip(this, 2);
        params.gravity = Layout.BREAK_STRATEGY_SIMPLE;
        String tags = cookbook.getTags();

        String[] arry = tags.split(",");

        for (String t : arry) {
            TextView tv = new TextView(this);
            tv.setText(t);
            tv.setTextColor(Color.WHITE);
            tv.setBackgroundColor(Color.GRAY);
            flowlayout.addView(tv, params);
        }

        //主食材

        mainListview = (ListView) findViewById(R.id.mainListView);

        mainList = cookbook.getIngredients().split(";");
        mainListview.setAdapter(mainlistViewAdapter);

        int count = mainlistViewAdapter.getCount();

        int h = DensityUtil.dip2px(this, 50) * count;

        mainListview.getLayoutParams().height = h;
        fuListview = (ListView) findViewById(R.id.fuListview);

        fulist = cookbook.getBurden().split(";");


        fuListview.setAdapter(fulistViewAdapter);

        int count2 = fulistViewAdapter.getCount();

        int h2 = DensityUtil.dip2px(this, 40) * count2;

        fuListview.getLayoutParams().height = h2;

        //制作步骤

        steplistview = (ListView) findViewById(R.id.listview_step);

        steplist = cookbook.getSteps();

        steplistAdapter = new SteplistAdapter();

        steplistview.setAdapter(steplistAdapter);

        int count3 = steplistAdapter.getCount();

        int h3 = DensityUtil.dip2px(this, 350) * count3;

        steplistview.getLayoutParams().height = h3;
    }

    class SteplistAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return steplist.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myview;
            if (convertView == null) {
                myview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.step_listview_detail, null);
            } else {
                myview = convertView;
            }

            ImageView iv = (ImageView) myview.findViewById(R.id.imageView);
            TextView num = (TextView) myview.findViewById(R.id.number);
            TextView text = (TextView) myview.findViewById(R.id.text11);

            Step step = steplist.get(position);
            num.setText("" + (position + 1));

            text.setText(step.getStep());

            Picasso.with(getApplicationContext()).load(step.getImg()).into(iv);

            return myview;
        }
    }


    class FulistViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return fulist.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myView;
            if (convertView == null) {
                myView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.listview_cookbook_detail_item, null);
            } else {
                myView = convertView;
            }

            TextView left = (TextView) myView.findViewById(R.id.Left);
            TextView right = (TextView) myView.findViewById(R.id.Right);

            String item = fulist[position];
            left.setText(item.split(",")[0]);
            right.setText(item.split(",")[1]);
            return myView;
        }
    }

    class MainlistViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mainList.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myView;
            if (convertView == null) {
                myView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.listview_cookbook_detail_item, null);
            } else {
                myView = convertView;
            }

            TextView left = (TextView) myView.findViewById(R.id.Left);
            TextView right = (TextView) myView.findViewById(R.id.Right);

            String item = mainList[position];
            left.setText(item.split(",")[0]);
            right.setText(item.split(",")[1]);
            return myView;
        }
    }
}
