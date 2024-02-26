package org.example

import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Configuration
class Router {

    fun routes() = RouterFunctions.route(
        RequestPredicates.POST("/test")
            .and(
                RequestPredicates.accept(MediaType("application", "x.example+json", mapOf("version" to "1")))

            ).and(
                RequestPredicates.contentType(MediaType("application","x.example+json",mapOf("version" to "1")))
            ),
        this::handleV1
    ).and(
        RouterFunctions.route(
            RequestPredicates.POST("/test")
                .and(
                    RequestPredicates.accept(MediaType("application", "x.example+json", mapOf("version" to "2")))

                ).and(
                    RequestPredicates.contentType(MediaType("application","x.example+json",mapOf("version" to "2")))
                ),
            this::handleV2
        )
    )

    fun handleV1(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(TestDtoV1::class.java)
            .flatMap { ServerResponse.ok().bodyValue(it) }
    }

    fun handleV2(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(TestDtoV2::class.java)
            .flatMap { ServerResponse.ok().bodyValue(it) }
    }
}


data class TestDtoV1(val id: String, val name: String)

data class TestDtoV2(
    val id: String,
    val lastname: String,
    val firstName: String
)