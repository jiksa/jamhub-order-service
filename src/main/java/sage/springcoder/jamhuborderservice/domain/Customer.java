package sage.springcoder.jamhuborderservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer extends BaseEntity{
    @Builder
    public Customer(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, String customerName,
                    UUID apiKey, Set<JamOrder> jamOrders) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerName = customerName;
        this.apiKey = apiKey;
        this.jamOrders = jamOrders;
    }

    private String customerName;

    //@Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)")
    private UUID apiKey;

    @OneToMany(mappedBy = "customer")
    private Set<JamOrder> jamOrders;
}
