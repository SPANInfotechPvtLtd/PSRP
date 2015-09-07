package com.span.psrp.apache.camel.topics.transformation.csv;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;

public class CsvRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        final DataFormat bindy = new BindyCsvDataFormat("com.span.psrp.apache.camel.topics.transformation.csv");
        from("direct:unmarshal").unmarshal(bindy).bean(new CSVProcessor());
        from("direct:marshal").marshal(bindy);
    }

}
