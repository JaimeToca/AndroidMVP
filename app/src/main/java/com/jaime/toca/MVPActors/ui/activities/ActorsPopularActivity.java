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
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import com.jaime.toca.MVPActors.ActorsApp;
import com.jaime.toca.MVPActors.dependencyInjection.components.DaggerPopularActorsComponent;
import com.jaime.toca.MVPActors.dependencyInjection.modules.PopularActorsModule;
import com.jaime.toca.MVPActors.domain.interactor.GetPopularActorsImp;
import com.jaime.toca.MVPActors.domain.repository.model.Actor;
import com.jaime.toca.MVPActors.domain.repository.rest.RestActorSource;
import com.jaime.toca.MVPActors.mvp.presenters.PopularActorsPresenter;
import com.jaime.toca.MVPActors.mvp.views.PopularActorsView;
import com.jaime.toca.MVPActors.ui.adapters.ActorsAdapter;
import com.jaime.toca.MVPActors.R;
import com.jaime.toca.MVPActors.ui.listeners.RecyclerViewClickListener;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;
import java.util.List;
import javax.inject.Inject;

public class ActorsPopularActivity extends Activity implements PopularActorsView{

    private RecyclerView recyclerView;
    private TextView toolbarTittle;
    private Typeface type;
    private ActorsAdapter adapter;
    private Toolbar toolbar;

    /* with dagger 2.0 */
    @Inject
    PopularActorsPresenter popularActorsPresenter;

/*  whithout dagger 2.0
    private Bus bus = new Bus();
    private RestActorSource restActorSource = new RestActorSource(bus);
    private GetPopularActorsImp getPopularActorsImp = new GetPopularActorsImp(restActorSource,bus);
    private PopularActorsPresenter popularActorsPresenter = new PopularActorsPresenter(getPopularActorsImp,bus); */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTittle = (TextView) findViewById(R.id.toolbarTitle);
        type = Typeface.createFromAsset(getAssets(), "Pacifico.ttf");
        toolbarTittle.setTypeface(type);

        recyclerView = (RecyclerView) findViewById(R.id.actorsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setOnScrollListener(recyclerScrollListener);

        initializeDependencyInjector();

        if (savedInstanceState == null)
            popularActorsPresenter.attachView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        popularActorsPresenter.start();
    }

    private void initializeDependencyInjector() {
        ActorsApp app = (ActorsApp) getApplication();

        DaggerPopularActorsComponent.builder()
                .appComponent(app.getAppComponent())
                .popularActorsModule(new PopularActorsModule())
                .build().inject(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public boolean EmptyList (){
        return (adapter == null);
    }

    @Override
    public void showMovies(List<Actor> actorsList){
        startAdapter(actorsList);
    }

    public void startAdapter(final List<Actor> actorsList){
        adapter = new ActorsAdapter(actorsList);
        recyclerView.setAdapter(adapter);

        adapter.setRecyclerViewListener(new RecyclerViewClickListener() {
            @Override
            public void onClickRecyclerItem(View v, Number position) {
                startActorDetailActivity(position);
                Log.i("Onclick", "" + position);
            }
        });
    }


    public void startActorDetailActivity(Number actorId){
        Intent activityDetail = new Intent(getBaseContext(), ActorDetailActivity.class);
        activityDetail.putExtra("actorId", actorId+"");
        startActivity(activityDetail);
    }


    /* This Scroll listener is used to show and hide the Toolbar */
    private RecyclerView.OnScrollListener recyclerScrollListener = new RecyclerView.OnScrollListener(){
        public boolean flagMove;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            super.onScrolled(recyclerView, dx, dy);

            /* Scroll up */
            if (dy > 6) {
                if (!flagMove) {
                    showToolbar();
                    flagMove = true;
                }

            /* Scroll down */
            } else if (dy < -6) {
                if (flagMove) {
                    hideToolbar();
                    flagMove = false;
                }
            }
        }
    };

    private void showToolbar() {
        toolbar.animate().translationY(-toolbar.getBottom()).setInterpolator(new AccelerateInterpolator()).start();
    }

    private void hideToolbar() {
        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
    }

}
