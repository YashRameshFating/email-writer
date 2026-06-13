package com.email_writer.Service;

import com.email_writer.Data.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
public class EmailGeneratorService {
    private final WebClient webClient;


    @Value("${gemini.api.url}")
    private String geminiApiUrl;
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public EmailGeneratorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String generateEmailReply(EmailRequest emailRequest){

        //build the prompt
        String prompt=buildPrompt(emailRequest);
        //craft the request
        Map<String, Object> requestBody=Map.of(
                "contents",new Object[]{
                        Map.of("parts",new Object[]{
                                Map.of("text",prompt)
                        })
                }
        );
        // do request and get response

        String response = webClient.post()
                .uri(geminiApiUrl)
                .header("Content-Type", "application/json")
                .header("X-goog-api-key", geminiApiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();


        //Extract response and return

        return extractResponseContent(response);

    }

    private String extractResponseContent(String response) {
        try{
            //ObjectMapper its a tool from jackson library that helps in working with json data like read write and convert it into java objects and vice versa
            ObjectMapper mapper=new ObjectMapper();
            //readtree is method that turns json response  into tree like stucture .tree like stucture is represented by json node and it represent the entire json tree and we can navigate through it thats the benifit of using it
            JsonNode rootNode=mapper.readTree(response);
            return rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();
        }catch (Exception e){
            return "Error Proccessing Request: "+e.getMessage();
        }
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt=new StringBuilder();
        prompt.append("Generate a professional email reply for the following email content.Please dont generate the subject.");
        if(emailRequest.getTone()!= null && !emailRequest.getTone().isEmpty()){
            prompt.append("Use a").append(emailRequest.getTone()).append(" tone.");
        }
        prompt.append("\n Original email: \n").append(emailRequest.getEmailContent());

        return prompt.toString();
    }

}
