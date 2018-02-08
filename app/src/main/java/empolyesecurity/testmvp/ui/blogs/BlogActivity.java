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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import empolyesecurity.testmvp.R;
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

/*
    public static BlogActivity newInstance() {
        Bundle args = new Bundle();
        BlogActivity fragment = new BlogActivity();
        fragment.setArguments(args);
        return fragment;
    }
*/

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_blog);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this));
            mPresenter.onAttach(this);
            mBlogAdapter.setCallback(this);
            setUp();


        }

    }


    @Override
    protected void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mBlogAdapter);

        mPresenter.onViewPrepared();
    }

    @Override
    public void onBlogEmptyViewRetryClick() {

        System.out.println("55555555555555");
    }

    @Override
    public void updateBlog(List<BlogResponse.Blog> blogList) {


        mBlogAdapter.addItems(blogList);
    }

    @Override
    public void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
