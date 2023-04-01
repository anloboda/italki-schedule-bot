package com.anloboda.schedule.api.decoder

import com.anloboda.schedule.api.ItalkiApiErrorException
import feign.Response
import feign.codec.ErrorDecoder
import java.lang.Exception

class ItalkiApiErrorDecoder : ErrorDecoder {
    override fun decode(methodKey: String, response: Response?): Exception {
        // TODO: add log here
        throw ItalkiApiErrorException()
    }
}
