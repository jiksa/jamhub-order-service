package sage.springcoder.jamhuborderservice.web.mappers;

import org.mapstruct.Mapper;
import sage.springcoder.jamhuborderservice.domain.JamOrderLine;
import sage.springcoder.jamhuborderservice.web.model.JamOrderLineDto;

@Mapper(uses = {DateMapper.class})
public interface JamOrderLineMapper {
    JamOrderLineDto jamOrderLineToDto(JamOrderLine line);

    JamOrderLine dtoToJamOrderLine(JamOrderLineDto dto);
}
