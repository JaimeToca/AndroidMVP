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
package com.jaime.toca.MVPActors.mvp.views;

public interface ActorDetailView extends View {

    void showProgressBar();

    void hideProgressBar();

    void setName (String name);

    void setBirthday (String birthday);

    void setPlaceOfBirth(String placeOfBirth);

    void setHomePage (String homepage);

    void setBiography (String biography);

    void setActorImage (String urlActorPhoto);

}
