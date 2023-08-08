package sage.springcoder.jamhuborderservice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JamDto {
    private UUID jamId = null;
    private Integer version = null;

    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:Z", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate = null;

   // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime lastModifiedDate = null;
    private String jamName;
    private String jamFlavor;
    private String upc;
    private Integer quantityOnHand;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;
}
