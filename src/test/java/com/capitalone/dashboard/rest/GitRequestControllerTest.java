package com.capitalone.dashboard.rest;

import com.capitalone.dashboard.config.TestConfig;
import com.capitalone.dashboard.config.WebMVCConfig;
import com.capitalone.dashboard.model.DataResponse;
import com.capitalone.dashboard.model.GitRequest;
import com.capitalone.dashboard.request.GitRequestRequest;
import com.capitalone.dashboard.service.GitRequestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, WebMVCConfig.class})
@WebAppConfiguration
public class GitRequestControllerTest {

    private MockMvc mockMvc;

    @Autowired private WebApplicationContext wac;
    @Autowired private GitRequestService gitRequestService;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void gitRequest_search() {
        GitRequest gitRequest = makeGitRequest();
        Iterable<GitRequest> gitRequests = Arrays.asList(gitRequest);
        DataResponse<Iterable<GitRequest>> response = new DataResponse<>(gitRequests, 1);

        when(gitRequestService.search(
                ArgumentMatchers.any(GitRequestRequest.class), eq("pull"),eq("all"))).thenReturn(response);

        //mockMvc.perform(get("/gitrequests?componentId=" + ObjectId.get()))
        //        .andExpect(status().isOk());
    }

    @Test
    public void  gitRequests_noComponentId_badRequest() {
        //gitrequests/type/pull/state/all?componentId=58961611dd8c75147451984c&numberOfDays=14
        //mockMvc.perform(get("/gitrequests")).andExpect(status().isBadRequest());
    }


    private GitRequest makeGitRequest() {
        GitRequest gitRequest = new GitRequest();
        gitRequest.setScmUrl("scmUrl");
        gitRequest.setScmRevisionNumber("revNum");
        gitRequest.setScmAuthor("bob");
        gitRequest.setTimestamp(2);
        return gitRequest;
    }

}
