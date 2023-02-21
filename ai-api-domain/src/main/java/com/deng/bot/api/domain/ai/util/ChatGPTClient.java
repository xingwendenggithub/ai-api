package com.deng.bot.api.domain.ai.util;

import java.io.IOException;

import org.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatGPTClient {
    private static final String API_ENDPOINT =
            "https://api.openai.com/v1/engines/davinci-codex/completions";

    private final OkHttpClient httpClient;
    private final String apiKey;

    public ChatGPTClient(String apiKey) {
        this.httpClient = new OkHttpClient();
        this.apiKey = apiKey;
    }

    public String generateText(String prompt) throws IOException {
        JSONObject requestObject = new JSONObject().put("prompt", prompt).put("temperature", 0.5)
                .put("max_tokens", 50);

        RequestBody requestBody =
                RequestBody.create(MediaType.parse("application/json"), requestObject.toString());

        Request request = new Request.Builder().url(API_ENDPOINT)
                .header("Authorization", "Bearer " + apiKey).post(requestBody).build();

        Response response = httpClient.newCall(request).execute();
        String responseBody = response.body().string();
        JSONObject responseObject = new JSONObject(responseBody);
        String text = responseObject.getJSONArray("choices").getJSONObject(0).getString("text");
        return text;
    }

    public static void main(String[] args) {
        String apiKey = "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"; // 请将YOUR_API_KEY_HERE替换为你的API密钥
        ChatGPTClient client = new ChatGPTClient(apiKey);
        try {
            String prompt = "Hello, I'm a Java program. What's your name?";
            String response = client.generateText(prompt);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
