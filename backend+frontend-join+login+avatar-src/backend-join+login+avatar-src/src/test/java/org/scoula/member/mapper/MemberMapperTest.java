package org.scoula.member.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.security.account.domain.AuthVO;
import org.scoula.security.account.domain.MemberVO;
import org.scoula.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
@Log4j2
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    void insert() {

        MemberVO memberVO = MemberVO.builder()
                .username("test2")
                .password("$2a$10$LyIEnOZaHXqqiF2RzsISluXnCLOVFY6P/CJ5EMHagov09a5TtiA1G")
                .email("test@test.com")
                .build();

        int result = memberMapper.insert(memberVO);
        Assertions.assertEquals(0, result);
    }

    @Test
    void insertAuth() {

        AuthVO authVO = new AuthVO();
        authVO.setUsername("test2");
        authVO.setAuth("ROLE_MEMBER");

        int result = memberMapper.insertAuth(authVO);
        Assertions.assertEquals(0, result);
    }
}