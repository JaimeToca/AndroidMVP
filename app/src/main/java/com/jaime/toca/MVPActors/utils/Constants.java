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
package com.jaime.toca.MVPActors.utils;

public class Constants {

    /* Constants used in rest repository */
    public static final String HOST = "http://api.themoviedb.org/3/";
    public static final String API_KEY = "ef7207d60c949efbe23d1d2c0d580eb2";

    /* Constants used in ActorDetailPresenter */
    public static final int MIN_URL_SIZE = 2;

    /* URL actor picture high quality */
    public static final String URL_BASE_PHOTOS = "https://image.tmdb.org/t/p/original/";

    /* This one does not really need to be resized. Low quality */
    public static final String URL_BASE_PHOTOS_LOW_QUALITY = "https://image.tmdb.org/t/p/w92/";

}
