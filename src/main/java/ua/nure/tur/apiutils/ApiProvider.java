package ua.nure.tur.apiutils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import ua.nure.tur.config.ConfigURL;
import ua.nure.tur.models.MessageHandler;

import java.io.IOException;

public class ApiProvider {

    public static <T> MessageHandler<T> get(String url, Class<T> type){
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(ConfigURL.getApiURL() + url)
                    .header("content-type", "application/json")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
            return getError();
        }
        if (response.getStatus() != 200){
            return getError();
        }
        if (type == String.class)
        {
            return (MessageHandler<T>) getStringMessage(response.getBody());
        }
        return getMessage(response.getBody(), type);
    }

    public static <T> MessageHandler<T> post(String url, String body, Class<T> type){
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.post(ConfigURL.getApiURL() + url)
                    .header("content-type", "application/json")
                    .body(body)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
            return getError();
        }
        if (response.getStatus() != 200){
            return getError();
        }
        if (type == String.class)
        {
            return (MessageHandler<T>) getStringMessage(response.getBody());
        }
        return getMessage(response.getBody(), type);
    }

    public static <T> MessageHandler<T> get(String url, Class<T> type, String token){
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(ConfigURL.getApiURL() + url)
                    .header("content-type", "application/json")
                    .header("Authorization", token)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
            return getError();
        }
        if (response.getStatus() != 200){
            return getError();
        }
        if (type == String.class)
        {
            return (MessageHandler<T>) getStringMessage(response.getBody());
        }
        return getMessage(response.getBody(), type);
    }

    public static <T> MessageHandler<T> post(String url, String body, Class<T> type, String token){
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.post(ConfigURL.getApiURL() + url)
                    .header("content-type", "application/json")
                    .header("Authorization", token)
                    .body(body)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
            return getError();
        }
        if (response.getStatus() != 200){
            return getError();
        }
        if (type == String.class)
        {
            return (MessageHandler<T>) getStringMessage(response.getBody());
        }
        return getMessage(response.getBody(), type);
    }


    private static <T> MessageHandler<T> getMessage(JsonNode jsonNode, Class<T> type){
        MessageHandler<T> message = new MessageHandler<T>();
        message.setStatus(jsonNode.getObject().getInt("status"));
        message.setMessage(jsonNode.getObject().getString("message"));
        String data = jsonNode.getObject().get("data").toString();
        try {
            message.setData(new ObjectMapper().readValue(data, type));
        } catch (IOException e) {
            e.printStackTrace();;
            return getError();
        }
        return message;

    }

    private static MessageHandler<String> getStringMessage(JsonNode jsonNode) {
        MessageHandler<String> message = new MessageHandler<>();
        message.setStatus(jsonNode.getObject().getInt("status"));
        message.setMessage(jsonNode.getObject().getString("message"));
        String data = jsonNode.getObject().get("data").toString();
        message.setData(data);
        return message;
    }

    private static <T> MessageHandler<T> getError(){
        return new MessageHandler<>(500, "Internal server error");
    }

}
