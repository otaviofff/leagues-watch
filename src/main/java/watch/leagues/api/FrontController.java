package watch.leagues.api;

import org.raviolini.api.AbstractController;

import watch.leagues.domain.News;
import watch.leagues.domain.NewsService;
import watch.leagues.domain.Subscription;
import watch.leagues.domain.SubscriptionService;

public class FrontController extends AbstractController {

    public static void main(String[] args) {
        listenToAssignedPort();
        
        addRouter(News.class, new NewsService());
        addRouter(Subscription.class, new SubscriptionService());
    }
}