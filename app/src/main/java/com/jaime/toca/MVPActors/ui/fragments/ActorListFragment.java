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

package com.jaime.toca.MVPActors.ui.fragments;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jaime.toca.MVPActors.ActorsApp;
import com.jaime.toca.MVPActors.R;
import com.jaime.toca.MVPActors.dependencyInjection.components.DaggerPopularActorsComponent;
import com.jaime.toca.MVPActors.dependencyInjection.modules.PopularActorsModule;
import com.jaime.toca.MVPActors.domain.interactor.GetPopularActorsImp;
import com.jaime.toca.MVPActors.domain.model.Actor;
import com.jaime.toca.MVPActors.domain.repository.rest.RestActorSource;
import com.jaime.toca.MVPActors.mvp.presenters.PopularActorsPresenter;
import com.jaime.toca.MVPActors.mvp.views.PopularActorsView;
import com.jaime.toca.MVPActors.ui.activities.ActorDetailActivity;
import com.jaime.toca.MVPActors.ui.adapters.ActorsAdapter;
import com.jaime.toca.MVPActors.ui.adapters.DividerItemDecoration;
import com.jaime.toca.MVPActors.ui.listeners.RecyclerViewClickListener;
import java.util.List;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ActorListFragment extends Fragment implements PopularActorsView {

    public interface ActorListListener {
        void onActorListClicked(Number actorId);
    }

    /* View Bindings */
    @Bind(R.id.listProgressBar) ProgressBar progressBar;
    @Bind(R.id.actorsList) RecyclerView recyclerView;

    @Inject
    PopularActorsPresenter popularActorsPresenter;

    private ActorsAdapter adapter;
    private ActorListListener actorListListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        actorListListener = (ActorListListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_list, container, true);
        ButterKnife.bind(this, fragmentView);

        /* RecyclerView settings */
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity()
        ));

        return fragmentView;
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeDependencyInjector();
    }

    private void initializeDependencyInjector() {
        ActorsApp app = (ActorsApp) getActivity().getApplication();

        DaggerPopularActorsComponent.builder()
                .appComponent(app.getAppComponent())
                .popularActorsModule(new PopularActorsModule())
                .build().inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        popularActorsPresenter.attachView(this);
        popularActorsPresenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public Context getContext() {
        return this.getActivity().getApplicationContext();
    }

    @Override
    public void viewActorProfile(Number actorId){
        actorListListener.onActorListClicked(actorId);
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
            public void onClickRecyclerItem(View v, Number actorId) {
                popularActorsPresenter.onActorClicked(actorId);
            }
        });
    }

}
