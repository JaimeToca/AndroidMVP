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
package com.jaime.toca.MVPActors.domain.repository.rest;
import android.util.Log;

import com.jaime.toca.MVPActors.utils.Constants;
import com.squareup.otto.Bus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;

import com.jaime.toca.MVPActors.domain.model.ActorDetail;
import com.jaime.toca.MVPActors.domain.model.ActorsWrapper;

import java.util.List;

public class RestActorSource implements RestDataSource {

    private final ActorDatabaseAPI mActorsDBApi;

    public RestActorSource() {
        RestAdapter movieAPIRest = new RestAdapter.Builder()
                .setEndpoint(Constants.HOST)
                .setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
                .build();

        mActorsDBApi = movieAPIRest.create(ActorDatabaseAPI.class);
    }

    @Override
    public Observable<ActorsWrapper> getPopularActors(){
        return mActorsDBApi.getPopularActors(Constants.API_KEY);
    }

    @Override
    public Observable<ActorDetail> getDetailActor(String id){
        return mActorsDBApi.getDetailActor(Constants.API_KEY, id);
    }

    @Override
    public void getPopularActorsByPage (int page){
        //Future implementation
    }
}
