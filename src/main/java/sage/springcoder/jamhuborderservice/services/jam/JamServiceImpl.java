package sage.springcoder.jamhuborderservice.services.jam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sage.springcoder.jamhuborderservice.web.model.JamDto;

import java.util.Optional;
import java.util.UUID;

@ConfigurationProperties(prefix = "jix.jamhub", ignoreUnknownFields = false)
@Service
public class JamServiceImpl implements JamService {

    public final String JAM_PATH_v1 = "/api/v1/jam/";
    public final String JAM_UPC_PATH_V1 = "/api/v1/jam/jamUpc/";

    private final RestTemplate restTemplate;

    private String jamServiceHost;

    @Autowired
    public JamServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Optional<JamDto> getJamById(UUID uuid) {
        return Optional.of(restTemplate.getForObject(jamServiceHost+JAM_PATH_v1+uuid.toString(),
                JamDto.class));
    }

    @Override
    public Optional<JamDto> getJamByUpc(String upc) {
        return Optional.of(restTemplate.getForObject(jamServiceHost+JAM_UPC_PATH_V1+upc,
                JamDto.class));
    }

    public void setJamServiceHost(String jamServiceHost) {
        this.jamServiceHost = jamServiceHost;
    }
}
