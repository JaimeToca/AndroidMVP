/*
 * Copyright (C) 2015 Jaime Toca.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jaime.toca.MVPActors.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jaime.toca.MVPActors.ActorsApp;
import com.jaime.toca.MVPActors.R;
import com.jaime.toca.MVPActors.di.components.DaggerActorDetailComponent;
import com.jaime.toca.MVPActors.di.modules.ActorDetailModule;
import com.jaime.toca.MVPActors.mvp.presenters.ActorDetailPresenter;
import com.jaime.toca.MVPActors.mvp.views.ActorDetailView;
import com.jaime.toca.MVPActors.utils.Constants;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ActorDetailActivity extends Activity implements ActorDetailView {

    @Bind(R.id.detailProgressBar) ProgressBar mProgressBar;
    @Bind(R.id.actorPhoto) ImageView mActorPhoto;
    @Bind({ R.id.actorName, R.id.actorBirthday, R.id.actorPlaceOfBirth,
            R.id.actorHomepage, R.id.actorBiography })
    List<TextView> profileInformation;

    public static final int NAME = 0;
    public static final int BIRTHDAY = 1;
    public static final int PLACE_OF_BIRTH = 2;
    public static final int HOMEPAGE = 3;
    public static final int BIOGRAPHY = 4;

    private Bundle bundle;
    private String mActorId;
    private Typeface mType;
    @Inject
    ActorDetailPresenter mDetailActorPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_detail);
        ButterKnife.bind(this);
        initializeDependencyInjector();
        mDetailActorPresenter.attachView(this);
        mType = Typeface.createFromAsset(getAssets(), "Pacifico.ttf");
        actorInformation(NAME).setTypeface(mType);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDetailActorPresenter.start();
    }

    private void initializeDependencyInjector() {
        bundle = getIntent().getExtras();
        if (bundle != null)
            mActorId = bundle.getString("actorId");

        ActorsApp app = (ActorsApp) getApplication();
        DaggerActorDetailComponent.builder()
                .appComponent(app.getAppComponent())
                .actorDetailModule(new ActorDetailModule(mActorId))
                .build().inject(this);
    }

    public TextView actorInformation(int n){
        return profileInformation.get(n);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setName (String name){
        actorInformation(NAME).setText(name);
    }

    @Override
    public void setBirthday (String birthday){
        actorInformation(BIRTHDAY).setText(birthday);
    }

    @Override
    public void setPlaceOfBirth(String placeOfBirth){
        actorInformation(PLACE_OF_BIRTH).setText(placeOfBirth);
    }

    @Override
    public void setHomePage (String homepage){
        actorInformation(HOMEPAGE).setText(homepage);
    }

    @Override
    public void setBiography (String biography){
        actorInformation(BIOGRAPHY).setText(biography);
    }

    @Override
    public void setActorImage (String urlActorPhoto){
        Picasso.with(this)
                .load(Constants.URL_BASE_PHOTOS + urlActorPhoto)
                .into(mActorPhoto,new Callback() {
                    @Override
                    public void onSuccess() {
                       mDetailActorPresenter.onPictureLoaded();
                    }
                    @Override
                    public void onError() {
                        mDetailActorPresenter.onPictureError();
                    }
                });
    }
}
