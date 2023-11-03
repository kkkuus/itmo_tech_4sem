package com.kusaikina.lab3.security;

import com.kusaikina.lab3.dto.CatDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CatsFilter {
    public List<CatDto> catFilter(String login, List<CatDto> cats) {
        List<CatDto> filterCats = new ArrayList<>();
        for (CatDto cat: cats) {
            if (cat.getUser().getName() == login) {
                filterCats.add(cat);
            }
        }
        return filterCats;
    }
}
