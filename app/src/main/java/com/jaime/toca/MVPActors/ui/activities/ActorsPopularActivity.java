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
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.jaime.toca.MVPActors.ActorsApp;
import com.jaime.toca.MVPActors.dependencyInjection.components.DaggerPopularActorsComponent;
import com.jaime.toca.MVPActors.dependencyInjection.modules.PopularActorsModule;
import com.jaime.toca.MVPActors.R;
import com.jaime.toca.MVPActors.ui.fragments.ActorListFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ActorsPopularActivity extends Activity
        implements ActorListFragment.ActorListListener{

    /* View Bindings */
    @Bind(R.id.toolbarTitle) TextView mToolbarTittle;
    private Typeface mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /* Toolbar settings */
        mType = Typeface.createFromAsset(getAssets(), "Pacifico.ttf");
        mToolbarTittle.setTypeface(mType);

        initializeDependencyInjector();

    }

    private void initializeDependencyInjector() {
        ActorsApp app = (ActorsApp) getApplication();

        DaggerPopularActorsComponent.builder()
                .appComponent(app.getAppComponent())
                .popularActorsModule(new PopularActorsModule())
                .build().inject(this);
    }

    public void onActorListClicked(Number actorId){
        Intent activityDetail = new Intent(this, ActorDetailActivity.class);
        activityDetail.putExtra("actorId", actorId + "");
        startActivity(activityDetail);
    }

}
