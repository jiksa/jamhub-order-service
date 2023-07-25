package sage.springcoder.jamhuborderservice.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class JamOrder extends BaseEntity{

    @Builder
    public JamOrder(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, String customerRef, Customer customer,
                     Set<JamOrderLine> jamOrderLines, JamOrderStatusEnum orderStatus,
                     String orderStatusCallbackUrl) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerRef = customerRef;
        this.customer = customer;
        this.jamOrderLines = jamOrderLines;
        this.orderStatus = orderStatus;
        this.orderStatusCallbackUrl = orderStatusCallbackUrl;
    }

    private String customerRef;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "jamOrder", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<JamOrderLine> jamOrderLines;

    private JamOrderStatusEnum orderStatus = JamOrderStatusEnum.NEW;
    private String orderStatusCallbackUrl;
}
