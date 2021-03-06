package com.graylog2.inputs.mongoprofiler.input.mongodb.parser;

import com.graylog2.inputs.mongoprofiler.input.mongodb.MongoDBProfilerCodec;
import com.mongodb.DBObject;
import org.graylog2.plugin.inputs.MessageInput;
import org.graylog2.plugin.journal.RawMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author Lennart Koopmann <lennart@torch.sh>
 */
public class RawParser {

    public RawMessage parse(DBObject doc, MessageInput sourceInput) throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(doc);

        RawMessage message = new RawMessage(
                byteOut.toByteArray()
        );

        message.setCodecName("mongodb-profiler-info");

        return message;
    }

}
