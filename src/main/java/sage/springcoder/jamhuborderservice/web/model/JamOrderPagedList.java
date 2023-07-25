package sage.springcoder.jamhuborderservice.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class JamOrderPagedList extends PageImpl<JamOrderDto> {
    public JamOrderPagedList(List<JamOrderDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public JamOrderPagedList(List<JamOrderDto> content) {
        super(content);
    }
}
