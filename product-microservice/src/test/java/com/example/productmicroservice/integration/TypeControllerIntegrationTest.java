package com.example.productmicroservice.integration;

import com.example.productmicroservice.controller.TypeController;
import com.example.productmicroservice.controller.utils.ControllerUtils;
import com.example.productmicroservice.dto.message.MessageDto;
import com.example.productmicroservice.dto.type.create.TypeCreateRequestDto;
import com.example.productmicroservice.dto.type.get.TypeGetResponseDto;
import com.example.productmicroservice.dto.type.update.TypeUpdateRequestDto;
import com.example.productmicroservice.repository.TypeRepository;
import com.example.productmicroservice.service.TypeService;
import com.example.productmicroservice.utils.TestConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TypeController.class)
public class TypeControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ControllerUtils controllerUtils;
    @MockBean
    private TypeService typeService;

    @Test
    public void testCreateType() throws Exception {
        TypeCreateRequestDto typeRequestDto = new TypeCreateRequestDto();
        typeRequestDto.setName(TestConstants.TYPE_NAME_1);
        given(controllerUtils.createResponseEntityOk(anyString())).willReturn(ResponseEntity.ok(new MessageDto(TestConstants.TYPE_CREATED_MESSAGE)));
        mockMvc.perform(post("/types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(typeRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(TestConstants.TYPE_CREATED_MESSAGE));
    }

    @Test
    public void testUpdateType() throws Exception {
        TypeUpdateRequestDto updatedType = new TypeUpdateRequestDto();
        updatedType.setName(TestConstants.TYPE_NAME_1);
        given(controllerUtils.createResponseEntityOk(anyString())).willReturn(ResponseEntity.ok(new MessageDto(TestConstants.TYPE_UPDATED_MESSAGE)));
        mockMvc.perform(patch("/types/{id}", TestConstants.ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedType)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(TestConstants.TYPE_UPDATED_MESSAGE));
    }

    @Test
    public void testDeleteType() throws Exception {
        given(controllerUtils.createResponseEntityOk(anyString())).willReturn(ResponseEntity.ok(new MessageDto(TestConstants.TYPE_DELETED_MESSAGE)));
        mockMvc.perform(MockMvcRequestBuilders.delete("/types/{id}", TestConstants.ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(TestConstants.TYPE_DELETED_MESSAGE));
    }

    @Test
    public void testSearchTypesByName() throws Exception {
        List<TypeGetResponseDto> types = new ArrayList<>();
        types.add(new TypeGetResponseDto(TestConstants.ID, TestConstants.TYPE_NAME_1));
        Page<TypeGetResponseDto> typePage = new PageImpl<>(types);
        given(typeService.searchTypesByName(TestConstants.TYPE_NAME_1, TestConstants.PAGE, TestConstants.SIZE, TestConstants.SORT_BY_ID)).willReturn(typePage);
        mockMvc.perform(get("/types/search")
                        .param("name", TestConstants.TYPE_NAME_1)
                        .param("page", String.valueOf(TestConstants.PAGE))
                        .param("size", String.valueOf(TestConstants.SIZE))
                        .param("sortBy", TestConstants.SORT_BY_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(types.size())))
                .andExpect(jsonPath("$.content[0].name").value(types.get(0).getName()));
    }

}
