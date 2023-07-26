package sage.springcoder.jamhuborderservice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JamOrderLineDto extends BaseItem{

    @Builder
    public JamOrderLineDto(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                            String upc, String jamName, UUID jamId, Integer orderQuantity) {
        super(id, version, createdDate, lastModifiedDate);
        this.upc = upc;
        this.jamName = jamName;
        this.jamId = jamId;
        this.orderQuantity = orderQuantity;
    }

    private String upc;
    private String jamName;
    private UUID jamId;
    private Integer orderQuantity = 0;
    private Integer quantityAllocated;
}
