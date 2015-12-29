package watch.leagues.domain;

import org.raviolini.facade.EntityService;
import org.raviolini.facade.exceptions.HookExecutionException;

public class NewsService extends EntityService<News> {
    
    private Broker broker;
    
    public NewsService() {
        super();
        broker = new Broker();
    }
    
    @Override
    protected void hookBeforePost(News news) throws HookExecutionException {
        try {
            String generatedCode = broker.publish(news);
            news.setCode(generatedCode);
        } catch (Exception e) {
            throw new HookExecutionException("Hook failed before method POST.", e);
        }
    }
}