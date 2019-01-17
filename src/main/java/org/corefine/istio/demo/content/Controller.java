package org.corefine.istio.demo.content;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("content")
public class Controller {
    @Value("${content.version}")
    private String version;

    @GetMapping("{id}")
    public ContentVo getData(@PathVariable Long id) {
        return getContent(id, "type " + id);
    }

    @GetMapping("list/{type}")
    public Object list(@PathVariable String type) {
        List<ContentVo> list = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            list.add(getContent((long)i, type));
        }
        return list;
    }

    private ContentVo getContent(Long id, String type) {
        return new ContentVo()
                .setId(id)
                .setType(type)
                .setVersion(version)
                .setCreateTime(new Date())
                .setContent("content " + id);
    }
}
