package net.autonav.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class MovementEndpoints {
    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/movement")
    public void movement(@RequestBody String json) {
        JsonNode node;
        try {
            node = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        float linearVelocity = node.get("linearVelocity").floatValue();
        float angularVelocity = node.get("angularVelocity").floatValue();


    }
}
