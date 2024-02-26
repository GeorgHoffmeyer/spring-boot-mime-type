package org.example

import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

/*
 * Test the Router class. Use the following test cases:
 * 1. Test the handleV1 method with a TestDtoV1 object.
 * 2. Test the route /test with a TestDtoV1 object.
 * 3. Set the correct content type for the route /test.
 * 4. Set the correct accept type for the route /test.
 */

internal class RouterTest {
    @Test
    fun `test TestDtoV1 route with correct content type and accept header`() {
        val client = WebTestClient.bindToRouterFunction(Router().routes()).build()

        client.post().uri("/test")
            .contentType(MediaType("application", "x.example+json", mapOf("version" to "1")))
            .accept(MediaType("application", "x.example+json", mapOf("version" to "1")))
            .bodyValue(TestDtoV1("1", "John"))
            .exchange()
            .expectStatus().isOk
            .expectBody().json("{ \"id\": \"1\", \"name\": \"John\"}")
    }

    @Test
    fun `test TestDtoV2 route with correct content type and accept header`() {
        val client = WebTestClient.bindToRouterFunction(Router().routes()).build()

        client.post().uri("/test")
            .contentType(MediaType("application", "x.example+json", mapOf("version" to "2")))
            .accept(MediaType("application", "x.example+json", mapOf("version" to "2")))
            .bodyValue(TestDtoV2("1", "Doe", "John"))
            .exchange()
            .expectStatus().isOk
            .expectBody().json("{ \"id\": \"1\", \"lastname\": \"Doe\", \"firstName\": \"John\"}")
    }
}
