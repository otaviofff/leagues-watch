package watch.leagues.domain;

import org.raviolini.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;

@DatabaseTable(tableName="subscriptions")
public @Data class Subscription implements Entity {

    @DatabaseField(generatedId=true)
    private Integer id;
    
    @DatabaseField(canBeNull=false)
    private String resource;
    
    @DatabaseField(canBeNull=false)
    private String date;
    
    @DatabaseField(canBeNull=false)
    private String topic;
    
    @DatabaseField(canBeNull=false)
    private String protocol;
    
    @DatabaseField(canBeNull=false)
    private String endpoint;
    
    public Subscription() {
        //Required by ORM.
    }
    
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    @JsonIgnore
    public Boolean isValid() {
        if (date == null || topic == null || protocol == null || endpoint == null) {
            return false;
        }
        
        String regex, namespace = "arn:aws:sns:";
        
        switch(protocol.toLowerCase()) {
            case "email":
                //Example: john@example.com
                regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
                break;
            case "sms":
                //Example: 1-206-555-6423
                regex = "^1-[2-9]\\d{2}-\\d{3}-\\d{4}$";
                break;
            default:
                return false;
        }
        
        return endpoint.matches(regex) && topic.startsWith(namespace);
    }
}