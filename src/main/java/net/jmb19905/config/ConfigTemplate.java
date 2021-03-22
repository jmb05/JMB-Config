package net.jmb19905.config;

public class ConfigTemplate {

    public final ConfigNode[] nodes;

    public ConfigTemplate(ConfigNode... nodes) {
        this.nodes = nodes;
    }

    public Config createConfig(){
        Config config = new Config();
        for(ConfigNode node : nodes){
            config.addNode(node);
        }
        return config;
    }

    public Config addMissing(Config config){
        Config outConfig = new Config(config);
        for(ConfigNode node : nodes){
            if(!config.containsNode(node.name) || config.getAsNode(node.name).getNodeType() != node.getNodeType()){
                outConfig.addNode(node);
            }
        }
        return outConfig;
    }

}
