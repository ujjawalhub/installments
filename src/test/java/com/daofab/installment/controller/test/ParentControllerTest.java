package com.daofab.installment.controller.test;

import com.daofab.installment.JUnitControllerApp;
import com.daofab.installment.dao.ChildDao;
import com.daofab.installment.dao.ParentDao;
import com.daofab.installment.entity.Child;
import com.daofab.installment.entity.Parent;
import com.daofab.installment.model.ChildData;
import com.daofab.installment.model.ParentData;
import com.daofab.installment.services.TranscationService;
import com.daofab.installment.utils.ControllerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = JUnitControllerApp.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Slf4j
public class ParentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    ParentDao parentDao;

    @Autowired
    ChildDao childDao;

    private Parent parent;

    private Child child;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        parent = Parent.builder()
                .id(1)
                .sender("Sender 01")
                .receiver("Receiver 01")
                .totalAmount(BigDecimal.valueOf(1000))
                .build();
        child = Child.builder()
                .id(11)
                .parentId(1)
                .paidAmount(BigDecimal.valueOf(200))
                .build();
        parentDao.save(parent);
        childDao.save(child);
    }

    @Test
    public void getParentPageData() throws Exception {
        String response = getResponse(mockGetPage(mockMvc, "/parent/", 0, 2));
        ControllerResponse controllerResponse = stringToObj(response, ControllerResponse.class);
        assertTrue("Check for valid response failed", controllerResponse.getData() != null && controllerResponse.isSuccess());
        List<ParentData> pageDataList = (List<ParentData>) controllerResponse.getData();
        assertTrue("Check if parent data", pageDataList != null);
        assertTrue("Check parent data length", pageDataList.size() == controllerResponse.getTotal());
        log.info("Parent page data test Passed");
    }

    @Test
    public void getChildData() throws Exception {
        child.setPaidAmount(BigDecimal.valueOf(100));
        childDao.save(child);
        child.setPaidAmount(BigDecimal.valueOf(500));
        childDao.save(child);

        String response = getResponse(mockGet(mockMvc, "/parent/"+parent.getId()));
        ControllerResponse controllerResponse = stringToObj(response, ControllerResponse.class);
        assertTrue("Check for valid response failed", controllerResponse.getData() != null && controllerResponse.isSuccess());
        List<ChildData> childDataList = (List<ChildData>) controllerResponse.getData();
        assertTrue("Check if child data", childDataList != null && !childDataList.isEmpty() && childDataList.size() >= 3);
        log.info("Child data test Passed");
    }


    private static String getResponse(MvcResult mvcResult) throws Exception {
        String response = "";
        if (mvcResult != null) {
            mvcResult.getRequest().getAsyncContext().setTimeout(100000);
            response = mvcResult
                    .getResponse()
                    .getContentAsString();
        }
        return response;
    }

    private static MvcResult mockGet(MockMvc mockMvc, String url) throws Exception {
        MvcResult mvcResult;
        if (url != null) {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
            mvcResult.getRequest().getAsyncContext().setTimeout(100000);
            mvcResult = mockMvc.perform(asyncDispatch(mvcResult)).andReturn();

            return mvcResult;
        }
        return null;
    }

    private static MvcResult mockGetPage(MockMvc mockMvc, String url, Integer page, Integer size) throws Exception {
        MvcResult mvcResult;
        if (url != null) {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                    .param("page", page.toString())
                    .param("size", size.toString())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
            mvcResult.getRequest().getAsyncContext().setTimeout(100000);
            mvcResult = mockMvc.perform(asyncDispatch(mvcResult)).andReturn();

            return mvcResult;
        }
        return null;
    }

    private static <E> E stringToObj(String content, Class<E> clazz) {
        try {
            return objectMapper.readValue(content, clazz);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Error converting string to object", ex);
        }
    }

}
