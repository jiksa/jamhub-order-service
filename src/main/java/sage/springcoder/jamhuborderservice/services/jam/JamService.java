package sage.springcoder.jamhuborderservice.services.jam;

import sage.springcoder.jamhuborderservice.web.model.JamDto;

import java.util.Optional;
import java.util.UUID;

public interface JamService {
Optional<JamDto> getJamById(UUID uuid);

Optional<JamDto> getJamByUpc(String upc);
}
