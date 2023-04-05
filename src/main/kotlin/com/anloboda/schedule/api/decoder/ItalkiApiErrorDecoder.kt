package com.anloboda.schedule.api.decoder

import com.anloboda.schedule.api.ItalkiApiErrorException
import feign.Response
import feign.codec.ErrorDecoder
import org.slf4j.LoggerFactory
import java.lang.Exception

class ItalkiApiErrorDecoder : ErrorDecoder {

    private val logger = LoggerFactory.getLogger(ItalkiApiErrorDecoder::class.java)

    override fun decode(methodKey: String, response: Response): Exception {
        logger.error(
            "Call to italki api failed with error, response_status: ${response.status()}, response_body: ${response.body()}"
        )
        throw ItalkiApiErrorException()
    }
}