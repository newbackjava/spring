package org.scoula.travel.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.common.page.PageRequest;
import org.scoula.config.RootConfig;
import org.scoula.security.config.SecurityConfig;
import org.scoula.travel.domain.TravelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
@Log4j2
class TravelMapperTest {
    @Autowired
    private TravelMapper mapper;

    @Test
    void insert() {
    }

    @Test
    void insertImage() {
    }

    @Test
    void getTotalCount() {
        int totalCount = mapper.getTotalCount();
        log.info(totalCount);
    }

    @Test
    void getDistricts() {
        List<String> districts = mapper.getDistricts();
        log.info(districts);
    }

    @Test
    void getTravels() {
        List<TravelVO> travels = mapper.getTravels();
        log.info(travels.size());
    }

    @Test
    void getPage() {
        PageRequest pageRequest = PageRequest.of(1, 10);

        List<TravelVO> travels = mapper.getPage(pageRequest);
        travels.forEach(System.out::println);
    }

    @Test
    void getTravelsByDistrict() {
        List<TravelVO> travels = mapper.getTravelsByDistrict("강원권");
        travels.forEach(System.out::println);
    }

    @Test
    void getTravel() {
        TravelVO travel = mapper.getTravel(1L);
        log.info(travel);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}