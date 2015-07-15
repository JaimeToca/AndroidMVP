# Android MVP
Sample project using MVP Clean architecture (check out the diagram at the bottom) . This small app show a list of the most famous hollywood actors (retrieve from themoviedb.org), when any of them is clicked a new screen will be opened with detailed information about the specific actor. The purpose of this first version was not to show beautiful animations/interfaces (next version will implement these things) but to implement Model-View-Presenter architecture and use some interesting libraries and technologies.
In this project you will learn how to : 

 - Work with a model-view-presenter architecture on android
 - Work with RecyclerView and the new Toolbar hiding and showing it when the user scrolls down/up
 - Use dagger 2.0 framework and work with dependency injection 
 - Work with REST Json using Retrofit library
 - Use picasso in order to download and modify images
 - Avoid duplicated code using ButterKnife <br/>
And many more things :D <br/>

**Upcoming changes to next version:**

 - New animations and interface improvements (Material Design)
 - New mapper. It will map the information from the repository to domain
 - Thread implementation instead of otto bus
 - and a few more things ...


**Motivation and References:** <br />
After study and see these two implementation of MVP

- [EffectiveAndroidUI](https://github.com/pedrovgs/EffectiveAndroidUI) by Pedrovgs
- [Material Movies](https://github.com/saulmm/Material-Movies) by Saulmm 

I made my own implementation of mvp android clean architecture. <br/>

**MVP - Architecture**


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
