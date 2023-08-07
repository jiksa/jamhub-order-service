package sage.springcoder.jamhuborderservice.web.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import sage.springcoder.jamhuborderservice.domain.JamOrderLine;
import sage.springcoder.jamhuborderservice.services.jam.JamService;
import sage.springcoder.jamhuborderservice.web.model.JamDto;
import sage.springcoder.jamhuborderservice.web.model.JamOrderLineDto;

import javax.swing.text.html.Option;
import java.util.Optional;

public abstract class JamOrderLineMapperDecorator implements JamOrderLineMapper{
    private JamService jamService;
    private JamOrderLineMapper jamOrderLineMapper;
    @Autowired
    public void setJamService(JamService jamService) {
        this.jamService = jamService;
    }
    @Autowired
    @Qualifier("delegate")
    public void setJamOrderLineMapper(JamOrderLineMapper jamOrderLineMapper){
        this.jamOrderLineMapper = jamOrderLineMapper;
    }
    @Override
    public JamOrderLineDto jamOrderLineToDto(JamOrderLine line) {
        JamOrderLineDto orderLineDto = jamOrderLineMapper.jamOrderLineToDto(line);
        Optional<JamDto> jamDtoOptional = jamService.getJamByUpc(line.getUpc());

        jamDtoOptional.ifPresent(jamDto -> {
            orderLineDto.setJamName(jamDto.getJamName());
            orderLineDto.setUpc(jamDto.getUpc());
            //orderLineDto.setPrice and set jamflavor to implement
        });
        return orderLineDto;
    }

}
