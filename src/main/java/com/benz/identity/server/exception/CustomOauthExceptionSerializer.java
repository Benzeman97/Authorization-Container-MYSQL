package com.benz.identity.server.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

    public CustomOauthExceptionSerializer(){
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();
        gen.writeNumberField("status",value.getHttpErrorCode());
        gen.writeStringField("error",value.getOAuth2ErrorCode());
        gen.writeStringField("message",value.getMessage());
        gen.writeStringField("documentation","www.voyagemovies.org");
        gen.writeEndObject();
    }
}
