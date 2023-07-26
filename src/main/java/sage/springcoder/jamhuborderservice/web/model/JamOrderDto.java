package sage.springcoder.jamhuborderservice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JamOrderDto extends BaseItem{
    @Builder
    public JamOrderDto(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                       UUID customerId, List<JamOrderLineDto> jamOrderLines,
                        OrderStatusEnum orderStatus, String orderStatusCallbackUrl, String customerRef) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerId = customerId;
        this.jamOrderLines = jamOrderLines;
        this.orderStatus = orderStatus;
        this.orderStatusCallbackUrl = orderStatusCallbackUrl;
        this.customerRef = customerRef;
    }

    private UUID customerId;
    private String customerRef;
    private List<JamOrderLineDto> jamOrderLines;
    private OrderStatusEnum orderStatus;
    private String orderStatusCallbackUrl;
}
