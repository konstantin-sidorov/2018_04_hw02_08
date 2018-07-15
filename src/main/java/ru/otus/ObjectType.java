package ru.otus;


import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;


public enum ObjectType {
    PRIMITIVE {
        public JSONAware getJson(ObjectJ obj) {
            return null;
        }

        public String toJSONString(JSONAware json, JSONAware jsonParent, String itemName, ObjectJ obj) {
            if (jsonParent != null && itemName != null) {
                ((JSONObject) jsonParent).put(itemName, obj.getObj());
            } else if (jsonParent != null && itemName == null) {
                ((JSONArray) jsonParent).add(obj.getObj());
            }
            ;
            return jsonParent.toJSONString();
        }
    },
    ARRAY {
        public JSONAware getJson(ObjectJ obj) {
            return new JSONArray();
        }

        public String toJSONString(JSONAware json, JSONAware jsonParent, String itemName, ObjectJ obj) {
            if (jsonParent != null && itemName != null) {
                ((JSONObject) jsonParent).put(itemName, (JSONArray) json);
            } else if (jsonParent != null && itemName == null) {
                ((JSONArray) jsonParent).add((JSONArray) json);
            } else return json.toJSONString();
            return jsonParent.toJSONString();
        }
    },
    COLLECTION {
        public JSONAware getJson(ObjectJ obj) {
            return new JSONArray();
        }

        public String toJSONString(JSONAware json, JSONAware jsonParent, String itemName, ObjectJ obj) {
            if (jsonParent != null && itemName != null) {
                ((JSONObject) jsonParent).put(itemName, (JSONArray) json);
            } else if (jsonParent != null && itemName == null) {
                ((JSONArray) jsonParent).add((JSONArray) json);
            } else return json.toJSONString();
            return jsonParent.toJSONString();
        }
    },
    OBJECT {
        public JSONAware getJson(ObjectJ obj) {
            return new JSONObject();
        }

        public String toJSONString(JSONAware json, JSONAware jsonParent, String itemName, ObjectJ obj) {
            if (jsonParent != null && itemName != null) {
                ((JSONObject) jsonParent).put(itemName, (JSONObject) json);
            } else if (jsonParent != null && itemName == null) {
                ((JSONArray) jsonParent).add((JSONObject) json);
            } else return json.toJSONString();
            return jsonParent.toJSONString();
        }
    };

    public abstract JSONAware getJson(ObjectJ obj);

    public abstract String toJSONString(JSONAware json, JSONAware jsonParent, String itemName, ObjectJ obj);
}
