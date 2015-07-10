package com.jaime.toca.MVPActors;
import android.app.Application;
import com.jaime.toca.MVPActors.dependencyInjection.components.AppComponent;
import com.jaime.toca.MVPActors.dependencyInjection.components.DaggerAppComponent;
import com.jaime.toca.MVPActors.dependencyInjection.modules.AppModule;
import com.jaime.toca.MVPActors.dependencyInjection.modules.DomainModule;

public class ActorsApp extends Application {

    private AppComponent appComponent;


    @Override public void onCreate() {
        super.onCreate();
        this.initializeDependencyInjector();
    }

    private void initializeDependencyInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .domainModule(new DomainModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
