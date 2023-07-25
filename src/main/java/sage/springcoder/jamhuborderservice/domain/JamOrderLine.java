package sage.springcoder.jamhuborderservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class JamOrderLine extends BaseEntity {
    @Builder
    public JamOrderLine(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
                         JamOrder jamOrder, UUID jamId, String upc, Integer orderQuantity,
                         Integer quantityAllocated) {
        super(id, version, createdDate, lastModifiedDate);
        this.jamOrder = jamOrder;
        this.jamId = jamId;
        this.upc = upc;
        this.orderQuantity = orderQuantity;
        this.quantityAllocated = quantityAllocated;
    }

    @ManyToOne
    private JamOrder jamOrder;

    private UUID jamId;
    private String upc;
    private Integer orderQuantity = 0;
    private Integer quantityAllocated = 0;
}
