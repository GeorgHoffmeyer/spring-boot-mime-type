package org.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.web.servlet.MockMvc
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
internal class ControllerTest(@LocalServerPort val port: Int) {

    @Autowired
    internal lateinit var mockMvc: MockMvc

    @Test
    fun testControllerV1() {
        mockMvc.perform(
            post("/test")
                .contentType(MediaType("application", "x.example+json", mapOf("version" to "1")))
                .accept(MediaType("application", "x.example+json", mapOf("version" to "1")))
                .content("{ \"id\": \"1\", \"name\": \"John\"}")
        )
            .andExpect(status().isOk)
            .andExpect(content().json("{ \"id\": \"1\", \"name\": \"John\"}"))

    }


    @Test
    fun testControllerV2() {
        mockMvc.perform(
            post("/test")
                .contentType(MediaType("application", "x.example+json", mapOf("version" to "2")))
                .accept(MediaType("application", "x.example+json", mapOf("version" to "2")))
                .content("{ \"id\": \"1\", \"lastname\": \"Doe\", \"firstname\": \"John\"}")
        )
            .andExpect(status().isOk)
            .andExpect(content().json("{ \"id\": \"1\", \"lastname\": \"Doe\", \"firstname\": \"John\"}"))

    }
}