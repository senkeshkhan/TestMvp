/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package empolyesecurity.testmvp.ui.blogs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import empolyesecurity.testmvp.MainActivity;
import empolyesecurity.testmvp.MvpApp;
import empolyesecurity.testmvp.R;
import empolyesecurity.testmvp.data.dp.DbOpenHelper;
import empolyesecurity.testmvp.data.dp.model.DaoMaster;
import empolyesecurity.testmvp.data.dp.model.DaoSession;
import empolyesecurity.testmvp.data.network.model.Blog;
import empolyesecurity.testmvp.data.network.model.BlogDao;
import empolyesecurity.testmvp.data.network.model.BlogResponse;
import empolyesecurity.testmvp.di.component.ActivityComponent;
import empolyesecurity.testmvp.ui.base.BaseActivity;
import empolyesecurity.testmvp.ui.base.BaseFragment;


/**
 * Created by janisharali on 25/05/17.
 */

public class BlogActivity extends BaseActivity implements
        BlogMvpView, BlogAdapter.Callback {

    private static final String TAG = "BlogActivity";

    @Inject
    BlogMvpPresenter<BlogMvpView> mPresenter;

    @Inject
    BlogAdapter mBlogAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.blog_recycler_view)
    RecyclerView mRecyclerView;

    private Query<Blog> notesQuery;


    public static BlogDao blogDao;
/*
    public static BlogActivity newInstance() {
        Bundle args = new Bundle();
        BlogActivity fragment = new BlogActivity();
        fragment.setArguments(args);
        return fragment;
    }
*/
public static Intent getStartIntent(Context context) {
    Intent intent = new Intent(context, BlogActivity.class);
    return intent;
}
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_blog);

        ActivityComponent component = getActivityComponent();

        DaoSession mDaoSession = ((MvpApp) getApplication()).getDaoSession();
        blogDao = mDaoSession.getBlogDao();
        notesQuery = blogDao.queryBuilder().orderAsc(BlogDao.Properties.Title).build();
        if (MvpApp.checkConnection(BlogActivity.this)) {
            // Its Available...
            if (component != null) {
                component.inject(this);
                setUnBinder(ButterKnife.bind(this));
                mPresenter.onAttach(this);
                mBlogAdapter.setCallback(this);




                setUp();
            }

           // volleyJsonParse();
            Toast.makeText(BlogActivity.this, "Available", Toast.LENGTH_SHORT).show();


        } else {
            // Not Available...

            // Its Available...
            if (component != null) {
                component.inject(this);
                setUnBinder(ButterKnife.bind(this));
                mPresenter.onAttach(this);
                mBlogAdapter.setCallback(this);

                mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(mBlogAdapter);



                databaseValues();
            }




            Toast.makeText(BlogActivity.this, "Not Available", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void setUp() {




        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mBlogAdapter);

       mPresenter.onViewPrepared();

       // mPresenter.onBlogDb();
    }

    @Override
    public void onBlogEmptyViewRetryClick() {

        System.out.println("55555555555555");
    }

    @Override
    public void updateBlog(List<Blog> blogList) {

        System.out.println("22222222222"+blogList.size());
        mBlogAdapter.addItems(blogList);
    }

    @Override
    public void blogDp(List<Blog> blogList) {


        mBlogAdapter.addItems(blogList);

        System.out.println("1111111111"+blogList.size());
    }


    @Override
    public void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }



    public void databaseValues(){




        List<Blog> contacts = blogDao.loadAll();;
        System.out.println("valuessssssssssss+"+contacts.size());



        if (contacts.size() > 0) {
            mBlogAdapter.addItems(contacts);




         /*   recyclerView.setAdapter(new RetrofitAdapter(contacts, R.layout.card_view, getApplicationContext()));*/
        }
    }

}
