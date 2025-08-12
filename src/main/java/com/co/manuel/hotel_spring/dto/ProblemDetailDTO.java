package com.co.manuel.hotel_spring.dto;

import java.net.URI;

/*
 * This problem detail is complain with RFC 7807 Problem detail format.
 */
public record ProblemDetailDTO(
    URI type,
    String title,
    int status,
    String detail,
    String instance) {
}
