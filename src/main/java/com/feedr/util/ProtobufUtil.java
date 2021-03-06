package com.feedr.util;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by omzmarlon on 2017-08-23.
 */
public class ProtobufUtil {
    public static <T extends Message> T jsonToProtobuf(Message.Builder builder, HttpServletRequest req, Class<T> tClass) throws IOException {
        JsonFormat.parser().merge(IOUtils.toString(req.getReader()), builder);
        return tClass.cast(builder.build());
    }

    public static <T extends Message> String protobufToJSON(T content) throws InvalidProtocolBufferException {
        return JsonFormat.printer().includingDefaultValueFields().preservingProtoFieldNames().print(content);
    }
}
