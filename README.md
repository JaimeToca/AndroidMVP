# Android MVP (Deprecated)
This repository is deprecated</br>

Sample project using MVP Clean architecture (check out the diagram at the bottom) . This small app show a list of the most famous hollywood actors (retrieve from themoviedb.org), when any of them is clicked a new screen will be opened with detailed information about the specific actor. Libraries and technologies used in this project:

 - Model-View-Presenter Pattern
 - RxJava/ReactiveX
 - Coordinator Layout, Toolbar (Hide/show) , RecyclerView and some material design features
 - Dagger 2.0 framework and dependency injection
 - Retrofit (REST Client for android/java)
 - Picasso
 - ButterKnife to avoid duplicated code <br/>
And many more things :D <br/>

# Screenshots


![enter image description here](https://github.com/JaimeToca/AndroidMVP/blob/master/pictures/list-optimized.gif)

![enter image description here](https://github.com/JaimeToca/AndroidMVP/blob/master/pictures/profile-optimized.gif)

![enter image description here](https://github.com/JaimeToca/AndroidMVP/blob/master/pictures/row-optimazed.gif)


# Motivation and References
After study and see [Saulmm's](https://github.com/saulmm) project "Material Movies" I made my own implementation of mvp android clean architecture. Also "Avengers" was very helpful. I really recommend you to check out his repository  <br/>


# MVP 

![picture from blog.nodejitsu.com/](https://github.com/JaimeToca/AndroidMVP/blob/master/pictures/mvp.png)<br/>
Image from blog.nodejitsu.com


**Reference Links** <br/>
https://www.youtube.com/watch?v=lOEOK3UvmJM </br>
https://github.com/pedrovgs/EffectiveAndroidUI </br>
http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/ <br/>
https://github.com/jlmd/UpcomingMoviesMVP <br/>
https://github.com/saulmm/Material-Movies <br />
https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter <br />
http://www.vogella.com/tutorials/JavaLibrary-EventBusOtto/article.html



# Licence

    Copyright 2015 Jaime Toca
    Licensed under the Apache License, Version 2.0 (the "License"); 
    you may not use this file except in compliance with the License. 
    You may obtain a copy of the License at 
    
       http://www.apache.org/licenses/LICENSE-2.0 
    
    Unless required by applicable law or agreed to in writing, software 
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
