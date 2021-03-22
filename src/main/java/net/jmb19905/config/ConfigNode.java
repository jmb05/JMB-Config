package net.jmb19905.config;

public class ConfigNode {

    public String name;
    public Object value;
    private NodeType nodeType;

    public ConfigNode(String name, Object value){
        this.name = name;
        this.value = value;
        if(value instanceof String){
            this.nodeType = NodeType.STRING;
        }else if(value instanceof Integer){
            this.nodeType = NodeType.INT;
        }else if(value instanceof Double){
            this.nodeType = NodeType.DOUBLE;
        }else if(value instanceof Long){
            this.nodeType = NodeType.LONG;
        }else if(value instanceof Boolean){
            this.nodeType = NodeType.BOOLEAN;
        }
    }

    public ConfigNode(String name, String value) {
        this.name = name;
        this.value = value;
        this.nodeType = NodeType.STRING;
    }

    public ConfigNode(String name, int value) {
        this.name = name;
        this.value = value;
        this.nodeType = NodeType.INT;
    }

    public ConfigNode(String name, double value) {
        this.name = name;
        this.value = value;
        this.nodeType = NodeType.DOUBLE;
    }

    public ConfigNode(String name, long value) {
        this.name = name;
        this.value = value;
        this.nodeType = NodeType.LONG;
    }

    public ConfigNode(String name, boolean value) {
        this.name = name;
        this.value = value;
        this.nodeType = NodeType.BOOLEAN;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    private enum NodeType{
        STRING,INT,DOUBLE,LONG,BOOLEAN
    }

}
