package sage.springcoder.jamhuborderservice.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import sage.springcoder.jamhuborderservice.domain.JamOrder;
import sage.springcoder.jamhuborderservice.web.model.JamOrderDto;

@Mapper(uses = {DateMapper.class, JamOrderLineMapper.class})
//@Component
public interface JamOrderMapper {
@Mapping(target="customerId",source = "customer.id")
    JamOrderDto jamOrderToDto(JamOrder jamOrder);

    JamOrder dtoToJamOrder(JamOrderDto dto);

}
