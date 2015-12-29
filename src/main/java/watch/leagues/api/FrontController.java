package watch.leagues.api;

import org.raviolini.api.RequestRouter;

import watch.leagues.domain.News;
import watch.leagues.domain.NewsService;
import watch.leagues.domain.Subscription;
import watch.leagues.domain.SubscriptionService;

public class FrontController {

    public static void main(String[] args) {
        RequestRouter<News> router1 = new RequestRouter<>();
        
        router1.override(new NewsService());
        router1.route(News.class);
        
        RequestRouter<Subscription> router2 = new RequestRouter<>();
        
        router2.override(new SubscriptionService());
        router2.route(Subscription.class);
    }
}