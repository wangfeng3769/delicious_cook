package com.example.zhangning.cookbook.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhangning.cookbook.DetailActivity;
import com.example.zhangning.cookbook.R;
import com.example.zhangning.cookbook.books.CookBookPresenter;
import com.example.zhangning.cookbook.books.CookBookPresenterImpl;
import com.example.zhangning.cookbook.books.CookBookView;
import com.example.zhangning.cookbook.cookbook.beans.CookBook;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangning on 16/10/4.
 */

public class SearchFragment extends Fragment implements CookBookView{
    String key;
    List<CookBook> listCookBook;
    CookBookPresenter cookBookPresenter;
    ListView listview;
    MyAdapter myAdapter;

    public static SearchFragment newInstance(String key){
        SearchFragment searchFragment=new SearchFragment();

        Bundle b=new Bundle();

        b.putString("key",key);

        searchFragment.setArguments(b);

        return searchFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        cookBookPresenter=new CookBookPresenterImpl(this);
        Bundle b=this.getArguments();

        key=b.getString("key");

        cookBookPresenter.getCookBookListByKey(getActivity(),key);

    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return listCookBook.size();
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
            if (convertView==null){
                myView=getActivity().getLayoutInflater().inflate(R.layout.list_cookbook_item,null);
            }else {
                myView=convertView;
            }

            ImageView iv=(ImageView)myView.findViewById(R.id.imageView);
            TextView tv1=(TextView)myView.findViewById(R.id.title1);
            TextView tv2=(TextView)myView.findViewById(R.id.intro);

            CookBook cookBook=listCookBook.get(position);
            String url=cookBook.getAlbums().get(0);
            String title=cookBook.getTitle();
            String intro=cookBook.getImtro();
            tv1.setText(title);
            tv2.setText(intro);
            Picasso.with(getActivity()).load(url).centerCrop().resize(120,120).into(iv);
            return myView;
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.searchfragment,container,false);
        initviews(view);
        return view;
    }

    private void initviews(View view) {
        listview=(ListView)view.findViewById(R.id.listview);
        listCookBook=new ArrayList<>();
        myAdapter=new MyAdapter();
        listview.setAdapter(myAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CookBook cookBook=listCookBook.get(position);
                Intent intent=new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("cookbook",cookBook);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setCookBookList(List<CookBook> list) {
            this.listCookBook=list;
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void setFail() {

    }
}
