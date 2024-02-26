package org.example

import org.springframework.http.MediaType
import org.springframework.web.cors.reactive.CorsUtils
import org.springframework.web.reactive.function.server.RequestPredicate
import org.springframework.web.reactive.function.server.ServerRequest
import java.util.function.Predicate


class FixedAcceptPredicates(
    private val mediaType: Set<FixedMediaType>
) : HeaderPredicate(Predicate<ServerRequest.Headers> { headers ->
    val acceptedMediaTypes = acceptedMediaTypes(headers)

    acceptedMediaTypes.stream().anyMatch { acceptedMediaTpye ->
        mediaType.stream().anyMatch {
            it.isCompatibleWith(acceptedMediaTpye)
        }
    }
}) {

}

fun acceptedMediaTypes(headers: ServerRequest.Headers) =
    headers.accept().ifEmpty { listOf(MediaType.ALL) }.map { FixedMediaType(it) }


open class HeaderPredicate(
    val headersPredicate: Predicate<ServerRequest.Headers>
) : RequestPredicate {

    override fun test(serverRequest: ServerRequest) =
        CorsUtils.isPreFlightRequest(serverRequest.exchange().request) || headersPredicate.test(serverRequest.headers())
}

class Predicates {

    companion object {

    fun accept(vararg mediaTypes: FixedMediaType) = FixedAcceptPredicates(mediaTypes.toSet())
    }
}
