package org.apache.logging.log4j.streams;

import java.io.StringWriter;
import java.io.Writer;

public class LoggerFilterWriterTest extends AbstractLoggerWriterTest {

    @Override
    protected StringWriter createWriter() {
        return new StringWriter();
    }

    @Override
    protected Writer createWriterWrapper() {
        return new LoggerFilterWriter(this.wrapped, getExtendedLogger(), LEVEL);
    }

}