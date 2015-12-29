package watch.leagues.console;

import watch.leagues.domain.Broker;

public class Bootstrap {

    public static void main(String[] args) {
        if (args.length == 1 && args[0] == "topics") {
            bootstrapTopics();
        }
    }
    
    private static String[] getNewsTypes() {
        return new String[] {
                "BreakingNews",
                "GameStarts",
                "GameUpdates",
                "GameScores"
                };
    }
    
    private static String[] getTeamNames() {
        return new String[] {
                "Flames", 
                "Oilers", 
                "Canadiens", 
                "Senators", 
                "MapleLeafs", 
                "Canucks", 
                "Jets", 
                "Raptors", 
                "TorontoFC", 
                "Whitecaps", 
                "BlueJays"
            };
    }
    
    private static void bootstrapTopics() {
        System.out.println("Bootstrap initialized...");
        
        String[] newsTypes = getNewsTypes();
        String[] teamNames = getTeamNames();
        
        String resourceName, topicName, displayName;
        Broker broker = new Broker();
        
        for (int i = 0; i < newsTypes.length; i ++) {
            for (int j = 0; j < teamNames.length; j ++) {
                topicName = newsTypes[i].concat("-").concat(teamNames[j]);
                displayName = teamNames[j];
                resourceName = broker.createTopic(topicName, displayName);
                System.out.println("Topic created: " + resourceName);
            }
        }
        
        System.out.println((newsTypes.length * teamNames.length) + " SNS topics created.");
        System.out.println("Bootstrap done!");
    }
}