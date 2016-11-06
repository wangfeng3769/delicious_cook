package com.example.zhangning.cookbook.Fragment;

import android.content.Context;
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
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
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

public class HomeFragment extends Fragment implements CookBookView{
    ConvenientBanner convenientBanner;
    ListView listView;
    List<CookBook> list;
    CookBookPresenter cookBookPresenter;
    MyAdapter myAdapter;
    static List<Integer> localImages = new ArrayList<>();

    static {

        localImages.add(R.drawable.test01);
        localImages.add(R.drawable.test02);
        localImages.add(R.drawable.test03);
        localImages.add(R.drawable.test04);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        list=new ArrayList<>();
        cookBookPresenter=new CookBookPresenterImpl(this);
        cookBookPresenter.getCookBookListByKey(getActivity(),"家常菜");

        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, container, false);

        initViews(view);
        initBanner();
        return view;
    }

    public void initViews(View view) {
        listView=(ListView)view.findViewById(R.id.listview1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CookBook cookBook=list.get(position);
                Intent intent=new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("cookbook",cookBook);
                startActivity(intent);
            }
        });

        myAdapter=new MyAdapter();
        listView.setAdapter(myAdapter);
        convenientBanner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
    }

    void initBanner() {
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);

    }

    @Override
    public void setCookBookList(List<CookBook> list) {
        this.list=list;
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void setFail() {
        Toast.makeText(getActivity(),"请检查网路连接",Toast.LENGTH_SHORT).show();
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
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

            CookBook cookBook=list.get(position);
            String url=cookBook.getAlbums().get(0);
            String title=cookBook.getTitle();
            String intro=cookBook.getImtro();
            tv1.setText(title);
            tv2.setText(intro);
            Picasso.with(getActivity()).load(url).centerCrop().resize(120,120).into(iv);
            return myView;
        }
    }


    public class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            imageView.setImageResource(data);
        }
    }


}
