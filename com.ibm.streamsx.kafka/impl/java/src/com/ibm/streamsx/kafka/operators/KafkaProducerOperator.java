/* Generated by Streams Studio: April 6, 2017 at 3:30:27 PM EDT */
package com.ibm.streamsx.kafka.operators;

import com.ibm.streams.operator.model.Icons;
import com.ibm.streams.operator.model.InputPortSet;
import com.ibm.streams.operator.model.InputPorts;
import com.ibm.streams.operator.model.PrimitiveOperator;

@PrimitiveOperator(name = "KafkaProducer", namespace = "com.ibm.streamsx.kafka", description = KafkaProducerOperator.DESC)
@InputPorts({ @InputPortSet(description = "This port consumes tuples to be written to the Kafka topic(s). Each tuple received on "
        + "this port will be written to the Kafka topic(s).", cardinality = 1, optional = false) })
@Icons(location16 = "icons/KafkaProducer_16.gif", location32 = "icons/KafkaProducer_32.gif")
public class KafkaProducerOperator extends AbstractKafkaProducerOperator {

    public static final String DESC = ""
            + "The KafkaProducer operator is used to produce messages on Kafka "
            + "topics. The operator can be configured to produce messages to "
            + "one or more topics.\\n"
            + "\\n"
            + "# Supported Kafka Version\\n"
            + "\\n"
            + "This version of the toolkit supports **Apache Kafka v0.10.2, v0.11.x, 1.0.x, 1.1.x**, and **v2.0.0**.\\n"
            + "\\n"
            + "# Kafka Properties\\n"
            + "\\n"
            + KafkaSplDoc.PRODUCER_WHERE_TO_FIND_PROPERTIES
            + "Properties can be specified in a file or in an "
            + "application configuration. If specifying properties via a file, "
            + "the **propertiesFile** parameter can be used. If specifying properties "
            + "in an application configuration, the name of the application configuration "
            + "can be specified using the **appConfigName** parameter.\\n"
            + "\\n"
            + "The only property that the user is required to set is the `bootstrap.servers` "
            + "property, which points to the Kafka brokers. All other properties are optional. "
            + "The operator sets some properties by default to enable users to quickly get "
            + "started with the operator. The following lists which properties the operator "
            + "sets by default: \\n"
            + "\\n"
            + KafkaSplDoc.PRODUCER_DEFAULT_AND_ADJUSTED_PROPERTIES
            + "\\n"
            + "\\n"
            + "# Kafka Properties via Application Configuration\\n"
            + "\\n"
            + "Users can specify Kafka properties using Streams' application configurations. Information "
            + "on configuring application configurations can be found here: "
            + "[https://www.ibm.com/support/knowledgecenter/SSCRJU_4.2.1/com.ibm.streams.admin.doc/doc/"
            + "creating-secure-app-configs.html|Creating application configuration objects to securely "
            + "store data]. Each property set in the application configuration "
            + "will be loaded as a Kafka property. For example, to specify the bootstrap servers that "
            + "the operator should connect to, an app config property named `bootstrap.servers` should "
            + "be created.\\n"
            + "\\n"
            + KafkaSplDoc.PRODUCER_AUTOMATIC_SERIALIZATION
            + "\\n"
            + KafkaSplDoc.CHECKPOINTING_CONFIG
            + "\\n"
            + KafkaSplDoc.PRODUCER_CONSISTENT_REGION_SUPPORT
            + "\\n"
            + "# Error Handling\\n"
            + "\\n"
            + "Many exceptions thrown by the underlying Kafka API are considered fatal. In the event "
            + "that Kafka throws an exception, the operator will restart. Some exceptions can be "
            + "retried, such as those that occur due to network error. Users are encouraged "
            + "to set the KafkaProducer `retries` property to a value greater than 0 to enable the producer's "
            + "retry mechanism.";
}
