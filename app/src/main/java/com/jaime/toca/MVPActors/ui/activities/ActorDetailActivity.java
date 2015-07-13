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
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jaime.toca.MVPActors.ActorsApp;
import com.jaime.toca.MVPActors.R;
import com.jaime.toca.MVPActors.dependencyInjection.components.DaggerActorDetailComponent;
import com.jaime.toca.MVPActors.dependencyInjection.modules.ActorDetailModule;
import com.jaime.toca.MVPActors.domain.interactor.GetDetailActorImp;
import com.jaime.toca.MVPActors.domain.interactor.GetPopularActorsImp;
import com.jaime.toca.MVPActors.domain.repository.rest.RestActorSource;
import com.jaime.toca.MVPActors.mvp.presenters.ActorDetailPresenter;
import com.jaime.toca.MVPActors.mvp.presenters.PopularActorsPresenter;
import com.jaime.toca.MVPActors.mvp.views.ActorDetailView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;

import javax.inject.Inject;


public class ActorDetailActivity extends Activity implements ActorDetailView {

    Bundle bundle;
    private String actorId;
    private TextView name,birthday,placeOfBirth,homepage,biography;
    private Typeface type;
    private ImageView actorPhoto;
    private ProgressBar progressBar;

    private static final String URL_BASE_PHOTOS = "https://image.tmdb.org/t/p/original/";
    private static final int MIN_URL = 2;

    @Inject
    ActorDetailPresenter detailActorPresenter;

/*    without dagger 2.0
      private Bus bus = new Bus();
      private RestActorSource restActorSource = new RestActorSource(bus);
      private GetDetailActorImp getDetailActorImp = new GetDetailActorImp("85",bus,restActorSource);
      private ActorDetailPresenter detailActorPresenter = new ActorDetailPresenter(getDetailActorImp,bus); */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_detail);
        name = (TextView) findViewById(R.id.actorName);
        birthday = (TextView) findViewById(R.id.actorBirthday);
        placeOfBirth = (TextView) findViewById(R.id.actorPlaceOfBirth);
        homepage = (TextView) findViewById(R.id.actorHomepage);
        biography = (TextView) findViewById(R.id.actorBiography);
        actorPhoto =(ImageView) findViewById(R.id.actorPhoto);
        progressBar = (ProgressBar) findViewById(R.id.detailProgressBar);

        type = Typeface.createFromAsset(getAssets(), "Pacifico.ttf");
        name.setTypeface(type);

        initializeDependencyInjector();
        detailActorPresenter.attachView(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        detailActorPresenter.start();
    }

    private void initializeDependencyInjector() {

        bundle = getIntent().getExtras();
        if (bundle != null)
            actorId = bundle.getString("actorId");

        ActorsApp app = (ActorsApp) getApplication();
        DaggerActorDetailComponent.builder()
                .appComponent(app.getAppComponent())
                .actorDetailModule(new ActorDetailModule(actorId))
                .build().inject(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setName (String name){
        this.name.setText(name);
    }

    @Override
    public void setBirthday (String birthday){
        this.birthday.setText(birthday);
    }

    @Override
    public void setPlaceOfBirth(String placeOfBirth){
        this.placeOfBirth.setText(placeOfBirth);
    }

    @Override
    public void setHomePage (String homepage){
        this.homepage.setText(homepage);
    }

    @Override
    public void setBiography (String biography){
        this.biography.setText(biography);
    }

    @Override
    public void setActorImage (String urlActorPhoto){
        Picasso.with(this)
                .load(URL_BASE_PHOTOS + urlActorPhoto)
                .into(actorPhoto,new Callback() {
                    @Override
                    public void onSuccess() {
                        hideProgressBar();
                        //change this for next version
                    }
                    @Override
                    public void onError() {

                    }
                });
    }

}
