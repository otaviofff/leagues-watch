package watch.leagues.domain;

import org.raviolini.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;

@DatabaseTable(tableName="news")
public @Data class News implements Entity {

    @DatabaseField(generatedId=true)
    private Integer id;
    
    @DatabaseField(canBeNull=false)
    private String code;
    
    @DatabaseField(canBeNull=false)
    private String date;
    
    @DatabaseField(canBeNull=false)
    private String topic;
    
    @DatabaseField(canBeNull=false)
    private String subject;
    
    @DatabaseField(canBeNull=false)
    private String message;
    
    @DatabaseField(canBeNull=false)
    private String link;
    
    @DatabaseField(canBeNull=false)
    private String league;
    
    @DatabaseField(canBeNull=true)
    private String team;
    
    public News() {
        //Required by ORM.
    }
    
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    @JsonIgnore
    public Boolean isValid() {
        return date != null
            && topic != null && topic.startsWith("arn:aws:sns:")
            && subject != null && !subject.isEmpty()
            && message != null && !message.isEmpty()
            && link != null && link.startsWith("http://")
            && league != null && league.length() == 3;
    }
}