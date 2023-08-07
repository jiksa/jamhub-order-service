package sage.springcoder.jamhuborderservice.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import sage.springcoder.jamhuborderservice.domain.JamOrderLine;
import sage.springcoder.jamhuborderservice.web.model.JamOrderLineDto;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(JamOrderLineMapperDecorator.class)
//@Component
public interface JamOrderLineMapper {
    JamOrderLineDto jamOrderLineToDto(JamOrderLine line);

    JamOrderLine dtoToJamOrderLine(JamOrderLineDto dto);
}
