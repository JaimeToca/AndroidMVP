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
package com.jaime.toca.MVPActors.domain.interactor;
import com.jaime.toca.MVPActors.domain.model.ActorsWrapper;
import com.jaime.toca.MVPActors.domain.repository.rest.RestDataSource;
import javax.inject.Inject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetPopularActorsImp implements Interactor<com.jaime.toca.MVPActors.domain.model.ActorsWrapper>{

    private final RestDataSource mRestDataSource;
    private ActorsWrapper mActorsWrapper;
    private int mPage = 1;

    @Inject
    public GetPopularActorsImp(RestDataSource dataSource) {
        mRestDataSource = dataSource;
    }

    @Override
    public Observable<ActorsWrapper> execute() {

        mPage++;

        return mRestDataSource.getPopularActors()
                .map(actorsWrapper -> mActorsWrapper = actorsWrapper)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
