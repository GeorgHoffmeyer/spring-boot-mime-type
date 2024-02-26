package org.example

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @PostMapping(
        "/test",
        consumes = ["application/x.example+json;version=1"],
        produces = ["application/x.example+json;version=1"]

    )
    fun controllerV1(@RequestBody testDtoV1: TestDtoV1) = testDtoV1

    @PostMapping(
        "/test",
        consumes = ["application/x.example+json;version=2"],
        produces = ["application/x.example+json;version=2"]
    )
    fun controllerV2(@RequestBody testDtoV2: TestDtoV2) = testDtoV2
}


data class TestDtoV1(val id: String, val name: String)

data class TestDtoV2(
    val id: String,
    val lastname: String,
    val firstname: String
)