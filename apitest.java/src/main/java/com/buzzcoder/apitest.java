package com.buzzcoder;

import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.cloud.dialogflow.v2.TextInput.Builder;
import java.util.UUID;

public class apitest {
    public static String intent = "";

    public static String detectIntentTexts(String projectId, String texts, String sessionId,
                                           String languageCode) throws Exception {
        try (SessionsClient sessionsClient = SessionsClient.create()) {
            SessionName session = SessionName.of(projectId, sessionId);
            String botspeaks;
            Builder textInput = TextInput.newBuilder().setText(texts).setLanguageCode(languageCode);
            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
            DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
            QueryResult queryResult = response.getQueryResult();
            intent = queryResult.getIntent().getDisplayName();
            botspeaks = queryResult.getFulfillmentText();
            return botspeaks;
        }
    }

    public String sendToBot(String texts) throws Exception {
        String projectId = "buzzcoder-8695d";
        String sessionId = UUID.randomUUID().toString();
        String languageCode = "en";
        return detectIntentTexts(projectId, texts, sessionId, languageCode);
    }

}