package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.service.BoardService;
import org.scoula.config.RootConfig;
import org.scoula.config.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
    RootConfig.class,
    ServletConfig.class
})
@Log4j2
class BoardControllerTest {
    @Autowired
    BoardService service;

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void list() throws Exception {
        ModelMap model = mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))	// ResultActions 리턴
                        .andReturn()				// MvcResult 리턴
                        .getModelAndView()	// ModelAndView 리턴
                        .getModelMap();			// Model 리턴
        log.info(model);

    }

    @Test
    void create() throws Exception {
        String viewName = mockMvc.perform(MockMvcRequestBuilders.get("/board/create"))	// ResultActions 리턴
                        .andReturn()		// MvcResult 리턴
                        .getModelAndView()	// ModelAndView 리턴
                        .getViewName();
        log.info(viewName);
    }

    @Test
    void postCreate() throws Exception {
        String resultPage = mockMvc.perform(
                        MockMvcRequestBuilders.post("/board/create")
                                .param("title", "테스트 새글 제목")
                                .param("content", "테스트 새글 내용")
                                .param("writer", "user1")
                )
                .andReturn()
                .getModelAndView()
                .getViewName();

        log.info(resultPage);

    }

    @Test
    public void get() throws Exception {
        ModelMap model = mockMvc.perform(
                            MockMvcRequestBuilders.get("/board/get")
                                    .param("no", "1")
                )
                .andReturn()
                .getModelAndView()
                .getModelMap();
        log.info(model);
    }

    @Test
    public void update() throws Exception {

        String resultPage = mockMvc.perform(
                        MockMvcRequestBuilders.post("/board/update")
                                .param("no", "1")
                                .param("title", "수정된 테스트 새글 제목")
                                .param("content", "수정된 테스트 새글 내용")
                                .param("writer", "user00"))
                .andReturn()
                .getModelAndView()
                .getViewName();

        log.info(resultPage);

    }

    @Test
    public void delete() throws Exception {
        // 삭제전 데이터베이스에 게시물 번호 확인할 것
        String resultPage = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/board/delete")
                                .param("no", "25")
                )
                .andReturn()
                .getModelAndView()
                .getViewName();

        log.info(resultPage);
    }


}
