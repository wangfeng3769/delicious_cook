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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.zhangning.cookbook.DetailActivity;
import com.example.zhangning.cookbook.R;
import com.example.zhangning.cookbook.books.CookBookPresenter;
import com.example.zhangning.cookbook.books.CookBookPresenterImpl;
import com.example.zhangning.cookbook.books.CookBookView;
import com.example.zhangning.cookbook.category.CategoryPresenter;
import com.example.zhangning.cookbook.category.CategoryPresenterImpl;
import com.example.zhangning.cookbook.category.CategoryView;
import com.example.zhangning.cookbook.category.beans.Category;
import com.example.zhangning.cookbook.category.beans.Category1;
import com.example.zhangning.cookbook.category.beans.Category2;
import com.example.zhangning.cookbook.cookbook.beans.CookBook;
import com.squareup.picasso.Picasso;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangning on 16/10/4.
 */

public class CategoryFragment extends Fragment implements CategoryView ,CookBookView{

    CategoryPresenter categorypresenter;
    Category category;
    ProgressBar pb;
    LinearLayout treeContainer;
    List<CookBook> cookBooklist;
    CookBookPresenter cookBookpresenter;
    ListView listview;
    CookBookAdapter cookBookAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categorypresenter=new CategoryPresenterImpl(getActivity(),this);
        categorypresenter.getCategory();
        cookBookpresenter=new CookBookPresenterImpl(this);
        cookBooklist=new ArrayList<>();
        cookBookAdapter=new CookBookAdapter();

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.categoryfragment, container, false);
        initViews(view);
        pb.setVisibility(View.VISIBLE);
        return view;
    }

    private void initViews(View view) {
        pb = (ProgressBar) view.findViewById(R.id.pb);
        treeContainer = (LinearLayout) view.findViewById(R.id.tree__container);
        listview=(ListView)view.findViewById(R.id.listview2);
        listview.setAdapter(cookBookAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CookBook cookBook=cookBooklist.get(position);
                Intent intent=new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("cookbook",cookBook);
                startActivity(intent);
            }
        });
    }

    class CookBookAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return cookBooklist.size();
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
                myView=LayoutInflater.from(getActivity()).inflate(R.layout.list_cookbook_item,null);
            }else {
                myView=convertView;
            }

            TextView titletv=(TextView)myView.findViewById(R.id.title1);
            TextView introtv=(TextView)myView.findViewById(R.id.intro);
            ImageView img=(ImageView)myView.findViewById(R.id.imageView);


            CookBook cookBook=cookBooklist.get(position);
            titletv.setText(cookBook.getTitle());
            introtv.setText(cookBook.getImtro());
            String url=cookBook.getAlbums().get(0);

            Picasso.with(getActivity()).load(url).into(img);
            return myView;
        }
    }

    class IconTreeItem {
        public int icon;
        public String text;
        public boolean isSub;
    }


    class MyHolder extends TreeNode.BaseNodeViewHolder<IconTreeItem> {

        public MyHolder(Context context) {
            super(context);
        }

        @Override
        public View createNodeView(TreeNode node, IconTreeItem value) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(R.layout.tree_item, null, false);
            if (value.isSub) {
                view.setPadding(30, 20, 20, 20);
            }
            TextView tvValue = (TextView) view.findViewById(R.id.textview);
            ImageView iv = (ImageView) view.findViewById(R.id.img);
            tvValue.setText(value.text);
            iv.setImageResource(value.icon);

            return view;
        }
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
        pb.setVisibility(View.GONE);
        treesInit();
    }

    private void treesInit() {
        List<Category1> list1 = this.category.getResult();
        TreeNode root = TreeNode.root();
        for (Category1 c1 : list1) {
            String name = c1.getName();
            IconTreeItem nodeItem = new IconTreeItem();
            nodeItem.isSub = false;
            nodeItem.icon = R.drawable.root_icon;
            nodeItem.text = name;
            TreeNode parent = new TreeNode(nodeItem).setViewHolder(new MyHolder(getActivity()));

            List<Category2> list2 = c1.getList();
            for (final Category2 c2 : list2) {
                String subName = c2.getName();
                IconTreeItem nodeItem2 = new IconTreeItem();
                nodeItem2.isSub = true;
                nodeItem2.icon = R.drawable.sub_icon;
                nodeItem2.text = subName;
                TreeNode child = new TreeNode(nodeItem2).setViewHolder(new MyHolder(getActivity()));
                child.setClickListener(new TreeNode.TreeNodeClickListener() {
                    @Override
                    public void onClick(TreeNode node, Object value) {
                        String id=c2.getId();
                        cookBookpresenter.getCookBookListById(getActivity(),id);
                        pb.setVisibility(View.VISIBLE);
                    }
                });
                parent.addChildren(child);
            }
            root.addChild(parent);
        }
        AndroidTreeView tView = new AndroidTreeView(getActivity(), root);
        treeContainer.addView(tView.getView());
    }

    @Override
    public void setCookBookList(List<CookBook> list) {
        this.cookBooklist=list;
        cookBookAdapter.notifyDataSetChanged();
        pb.setVisibility(View.GONE);
    }

    @Override
    public void setFail() {

    }
}
