package net.jmb19905.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

@JsonSerialize(using = Config.JsonSerializer.class)
@JsonDeserialize(using = Config.JsonDeserializer.class)
public class Config {

    private final Map<String, Object> nodes = new TreeMap<>();

    public void addNode(String name, String value){
        nodes.put(name, value);
    }

    public void addNode(String name, int value){
        nodes.put(name, value);
    }

    public void addNode(String name, double value){
        nodes.put(name, value);
    }

    public void addNode(String name, long value){
        nodes.put(name, value);
    }

    public void addNode(String name, boolean value){
        nodes.put(name, value);
    }

    public Object get(String name){
        return nodes.get(name);
    }

    public String getString(String name){
        Object obj = get(name);
        if(obj instanceof String){
            return (String) obj;
        }
        return "";
    }

    public int getInt(String name){
        Object obj = get(name);
        if(obj instanceof Integer){
            return (int) obj;
        }
        return 0;
    }

    public double getDouble(String name){
        Object obj = get(name);
        if(obj instanceof Double){
            return (double) obj;
        }
        return 0;
    }

    public long getLong(String name){
        Object obj = get(name);
        if(obj instanceof Long){
            return (long) obj;
        }
        return 0;
    }

    public boolean getBoolean(String name){
        Object obj = get(name);
        if(obj instanceof Boolean){
            return (boolean) obj;
        }
        return false;
    }

    public boolean containsNode(String name){
        return nodes.containsKey(name);
    }

    @Override
    public String toString() {
        return "Config{" +
                "nodes=" + nodes +
                '}';
    }

    /**
     */
    public static class JsonSerializer extends StdSerializer<Config> {

        public JsonSerializer(){this(null);}

        public JsonSerializer(Class<Config> t) {
            super(t);
        }

        @Override
        public void serialize(Config value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeStartObject();
            for(String name : value.nodes.keySet()){
                Object val = value.get(name);
                if(val instanceof String){
                    gen.writeStringField(name, (String) val);
                }else if(val instanceof Integer){
                    gen.writeNumberField(name, (int) val);
                }else if(val instanceof Long){
                    gen.writeNumberField(name, (long) val);
                }else if(val instanceof Double){
                    gen.writeNumberField(name, (double) val);
                }else if(val instanceof Boolean){
                    gen.writeBooleanField(name, (Boolean) val);
                }
            }
            gen.writeEndObject();
        }
    }

    /**
     */
    public static class JsonDeserializer extends StdDeserializer<Config> {

        public JsonDeserializer(){this(null);}

        public JsonDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Config deserialize(JsonParser p, DeserializationContext context) throws IOException {
            Config config = new Config();
            JsonNode node = p.getCodec().readTree(p);
            for (Iterator<String> it = node.fieldNames(); it.hasNext(); ) {
                String name = it.next();
                JsonNode valueNode = node.get(name);
                if(valueNode.isTextual()) {
                    config.addNode(name,valueNode.asText());
                }else if(valueNode.isInt()) {
                    config.addNode(name,valueNode.asInt());
                }else if(valueNode.isDouble()) {
                    config.addNode(name,valueNode.asDouble());
                }else if(valueNode.isLong()) {
                    config.addNode(name,valueNode.asLong());
                }else if(valueNode.isBoolean()) {
                    config.addNode(name,valueNode.asBoolean());
                }
            }
            return config;
        }
    }

}
