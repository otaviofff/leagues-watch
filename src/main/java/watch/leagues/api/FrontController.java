package watch.leagues.api;

import org.raviolini.api.Application;

import watch.leagues.domain.News;
import watch.leagues.domain.NewsService;
import watch.leagues.domain.Subscription;
import watch.leagues.domain.SubscriptionService;

public class FrontController {

    public static void main(String[] args) {
        Application app = new Application();
        
        app.addRouter(News.class, new NewsService());
        app.addRouter(Subscription.class, new SubscriptionService());
    }
}