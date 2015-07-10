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
import com.squareup.otto.Bus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import com.jaime.toca.MVPActors.domain.repository.model.ActorDetail;
import com.jaime.toca.MVPActors.domain.repository.model.ActorsWrapper;

public class RestActorSource implements RestDataSource {

    private final ActorDatabaseAPI actorsDBApi;
    public static final String API_KEY = "ef7207d60c949efbe23d1d2c0d580eb2";
    public static final String HOST = "http://api.themoviedb.org/3/";
    ActorsWrapper actorsCallbackResponse;
    ActorDetail actorDetailCallbackResponse;
    private Bus bus;

    public RestActorSource(Bus busC) {

        RestAdapter movieAPIRest = new RestAdapter.Builder()
                .setEndpoint(HOST)
                .setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
                .build();

        actorsDBApi = movieAPIRest.create(ActorDatabaseAPI.class);
        bus = busC;
    }

    /* Get the list of the last 20 most popular actors */
    @Override
    public void getPopularActors(){
        actorsDBApi.getPopularActors(API_KEY, retrofitCallback);
    }

    /* get detailed information of a specific actor */
    @Override
    public void getDetailActor(String id){
        actorsDBApi.getDetailActor(API_KEY, id, retrofitCallback);
    }

    @Override
    public void getPopularActorsByPage (int page){
        //Implement for the future
    }


    /* RetrofitCallback */
    public Callback retrofitCallback = new Callback() {
        @Override
        public void success(Object o, Response response) {

            if (o instanceof ActorsWrapper){
                bus.post((ActorsWrapper) o);
        //        actorsCallbackResponse = (ActorsWrapper) o;
        //        sendActorsToActivity(actorsCallbackResponse);
            }

            else if (o instanceof ActorDetail){
                bus.post((ActorDetail) o);
        //        actorDetailCallbackResponse = (ActorDetail) o;
        //        sendDetailActorToActivity(actorDetailCallbackResponse);
            }
        }

        @Override
        public void failure(RetrofitError error) {
            Log.e("Callback failure", "" + error.getMessage());
        }
    };


}
