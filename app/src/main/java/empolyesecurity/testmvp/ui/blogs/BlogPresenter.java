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

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;


import empolyesecurity.testmvp.data.network.APIService;
import empolyesecurity.testmvp.data.DataManager;
import empolyesecurity.testmvp.data.network.model.Blog;
import empolyesecurity.testmvp.data.network.model.BlogResponse;
import empolyesecurity.testmvp.ui.base.BasePresenter;
import empolyesecurity.testmvp.utils.rx.SchedulerProvider;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by janisharali on 25/05/17.
 */

public class BlogPresenter<V extends BlogMvpView> extends BasePresenter<V>
        implements BlogMvpPresenter<V> {
    @Inject
    APIService mAPIService;


    @Inject
    public BlogPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {







        getMvpView().showLoading();

       // String weatherFromWhere = from_where.trim();
       // if (weatherFromWhere.isEmpty()) return;
        getCompositeDisposable().add(getDataManager().getForecastForCity()

                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<BlogResponse>() {
                    @Override
                    public void accept(@NonNull BlogResponse blogResponse)
                            throws Exception {
                        Log.e("Success", new Gson().toJson(blogResponse.getData()));
                        // System.out.println("rrrrrrrrrrrrrrrrrr"+blogResponse.getData());
                        if (blogResponse != null && blogResponse.getData() != null) {

                            BlogActivity.blogDao.deleteAll();
                            getMvpView().updateBlog(blogResponse.getData());


                            //BlogActivity.blogDao.deleteInTx(blogResponse.getData());

                            BlogActivity.blogDao.insertOrReplaceInTx(blogResponse.getData());
                           getDataManager().insertBlog(blogResponse.getData());






                            Log.e("wwwwwwwwwwww", new Gson().toJson(BlogActivity.blogDao.loadAll()));




                        }
                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));




       /* getCompositeDisposable().add(getDataManager()
                .getBlogApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<BlogResponse>() {
                    @Override
                    public void accept(@NonNull BlogResponse blogResponse)
                            throws Exception {
                       // System.out.println("rrrrrrrrrrrrrrrrrr"+blogResponse.getData());
                        if (blogResponse != null && blogResponse.getData() != null) {
                            getMvpView().updateBlog(blogResponse.getData());
                        }
                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));*/
    }


    public void loadForcast(String from_where) {

        /*String weatherFromWhere = from_where.trim();
        if (weatherFromWhere.isEmpty()) return;
        getCompositeDisposable().add(mAPIService.getForecastForCity(weatherFromWhere, "metric", 7)

                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<BlogResponse>() {
                    @Override
                    public void accept(@NonNull BlogResponse blogResponse)
                            throws Exception {
                        // System.out.println("rrrrrrrrrrrrrrrrrr"+blogResponse.getData());
                        if (blogResponse != null && blogResponse.getData() != null) {
                            getMvpView().updateBlog(blogResponse.getData());
                        }
                        getMvpView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
        */







    }



    @Override
    public void onBlogDb() {
        getCompositeDisposable().add(getDataManager()
                .getAllBlog()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Blog>>() {
                    @Override
                    public void accept(List<Blog> questionList) throws Exception {




                        if (!isViewAttached()) {
                            return;
                        }

                        if (questionList != null) {
                            getMvpView().blogDp(questionList);
                        }
                    }
                }));
    }

}
