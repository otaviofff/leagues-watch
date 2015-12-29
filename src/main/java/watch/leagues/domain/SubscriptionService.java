package watch.leagues.domain;

import org.raviolini.facade.EntityService;
import org.raviolini.facade.exceptions.HookExecutionException;

public class SubscriptionService extends EntityService<Subscription> {

    private Broker broker;
    
    public SubscriptionService() {
        super();
        broker = new Broker();
    }
    
    @Override
    protected void hookBeforePost(Subscription subscription) throws HookExecutionException {
        try {
            String generatedResourceName = broker.subscribe(subscription);
            subscription.setResource(generatedResourceName);
        } catch (Exception e) {
            throw new HookExecutionException("Hook failed before method POST.", e);
        }
    }
}