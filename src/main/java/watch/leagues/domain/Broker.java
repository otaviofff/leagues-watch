package watch.leagues.domain;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
import com.amazonaws.services.sns.model.UnsubscribeRequest;

public class Broker {

    private Region region;
    private AmazonSNSClient client;
    private AWSCredentialsProvider auth;
    
    public Broker() {
        region = Region.getRegion(Regions.US_EAST_1);
        auth = new ClasspathPropertiesFileCredentialsProvider();
        client = new AmazonSNSClient(auth);
        client.setRegion(region);
    }
    
    public String publish(News news) {
        PublishRequest request = new PublishRequest(
                news.getTopic(), 
                news.getMessage(), 
                news.getSubject()
        );
        
        PublishResult response = client.publish(request);
        
        return response.getMessageId();
    }
    
    public String subscribe(Subscription subscription) {
        SubscribeRequest request = new SubscribeRequest(
                subscription.getTopic(), 
                subscription.getProtocol(), 
                subscription.getEndpoint()
        );
        
        SubscribeResult response = client.subscribe(request);
        
        return response.getSubscriptionArn();
    }
    
    public void unsubscribe(Subscription subscription) {
        UnsubscribeRequest request = new UnsubscribeRequest(
                subscription.getResource()
        );
        
        client.unsubscribe(request);
    }
    
    public String createTopic(String topicName, String displayName) {
        String resourceName = client.createTopic(topicName).getTopicArn();
        client.setTopicAttributes(resourceName, "DisplayName", displayName);
        
        return resourceName;
    }
}