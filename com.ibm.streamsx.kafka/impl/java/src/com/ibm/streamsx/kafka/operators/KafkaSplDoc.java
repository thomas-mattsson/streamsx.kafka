/**
 * 
 */
package com.ibm.streamsx.kafka.operators;

/**
 * This class contains String constant with SPL Documentation.
 */
public class KafkaSplDoc {
    public static final String CONSUMER_DEFAULT_AND_ADJUSTED_PROPERTIES = ""
            + "---\\n"
            + "| Property Name | Default Value |\\n"
            + "|===|\\n"
            + "| client.id | Generated ID in the form: `C-J<JobId>-<operator name>` |\\n"
            + "|---|\\n"
            + "| group.id | Randomly generated ID in the form: `group-<random_string>` |\\n"
            + "|---|\\n"
            + "| key.deserializer | See **Automatic deserialization** section below |\\n"
            + "|---|\\n"
            + "| value.deserializer | See **Automatic deserialization** section below |\\n"
            + "|---|\\n"
            + "| partition.assignment.strategy | Only when multiple topics are specified: `org.apache.kafka.clients.consumer.RoundRobinAssignor` |\\n"
            + "|---|\\n"
            + "| auto.commit.enable | adjusted to `false` |\\n"
            + "|---|\\n"
            + "| max.poll.interval.ms | adjusted to a minimum of *3 \\\\* max (reset timeout, drain timeout)* when in consistent region, 300000 otherwise |\\n"
            + "|---|\\n"
            + "| metadata.max.age.ms | adjusted to a maximum of 2000 |\\n"
            + "|---|\\n"
            + "| session.timeout.ms | adjusted to a maximum of 20000 |\\n"
            + "|---|\\n"
            + "| request.timeout.ms | adjusted to session.timeout.ms \\\\+ 5000 |\\n"
            + "---\\n"
            + "\\n"
            + "**NOTE:** Users can override any of the above properties by explicitly setting the property "
            + "value in either a properties file or in an application configuration.\\n"
            ;

    public static final String CONSUMER_CONSISTENT_REGION_SUPPORT = ""
            + "# Consistent Region Support\\n"
            + "\\n"
            + "The operator "
            + "can be the start of a consistent region. Both operator driven and periodic triggering of the region "
            + "are supported. If using operator driven, the **triggerCount** parameter must be set to "
            + "indicate how often the operator should initiate a consistent region.\\n"
            + "\\n"
            + "When a group-ID is specified via the consumer property `group.id` or the **groupId** parameter, the operator "
            + "participates automatically in a consumer group defined by the group ID. A consistent region can have "
            + "multiple consumer groups.\\n"
            + "\\n"
            + "When the consumers of a consumer group rebalance the partition assignment, for example, immediately after job "
            + "submission, or when the broker node being the group's coordinator is shutdown, multiple resets of the consistent "
            + "region can occur when the consumers start up. It is recommended to set the "
            + "`maxConsecutiveResetAttempts` parameter of the `@consistent` annotation to a higher value than the default value of 5.\\n"
            + "\\n"
            + "On drain, the operator will commit offsets. The drain duration of the last drain and the maximum drain "
            + "duration in milliseconds is kept in the custom metrics **drainTimeMillis** and **drainTimeMillisMax**. "
            + "These metrics are only present when the operator participates in a consistent region.\\n"
            + "\\n"
            + "On checkpoint, the operator will save the last offset for each topic-partition that it is assigned to. In the "
            + "event of a reset, the operator will seek to the saved offset for each topic-partition and "
            + "begin consuming messages from that point.\\n"
            ;

    public static final String CONSUMER_COMMITTING_OFFSETS = ""
            + "# Committing received Kafka messages\\n"
            + "\\n"
            + "**a) The operator is not part of a consistent region**\\n"
            + "\\n"
            + "The consumer operator commits the offsets of "
            + "those Kafka messages that have been submitted as tuples. The frequency in terms of "
            + "number of tuples can be specified with the **commitCount** parameter. This parameter has a default value of 500. "
            + "Offsets are committed asynchronously.\\n"
            + "\\n"
            + "**b) The operator is part of a consistent region**\\n"
            + "\\n"
            + "Offsets are always committed when the consistent region drains, i.e. when the region becomes a consistent state. "
            + "On drain, the consumer operator commits the offsets of those Kafka messages that have been submitted as tuples. "
            + "When the operator is in a consistent region, all auto-commit related settings via consumer properties are "
            + "ignored by the operator. The parameter **commitCount** is also ignored because the commit frequency is given "
            + "by the trigger period of the consistent region.\\n"
            + "In a consistent region, offsets are committed synchronously, i.e. the offsets are committed when the drain "
            + "processing of the operator finishes. Commit failures result in consistent region reset.\\n"
            ;

    public static final String CONSUMER_AUTOMATIC_DESERIALIZATION = ""
            + "# Automatic Deserialization\\n"
            + "\\n"
            + "The operator will automatically select the appropriate deserializers for the key and message "
            + "based on their types. The following table outlines which deserializer will be used given a "
            + "particular type: \\n"
            + "\\n"
            + "---\\n"
            + "| Deserializer | SPL Types |\\n"
            + "|===|\\n"
            + "| org.apache.kafka.common.serialization.StringDeserializer | rstring |\\n"
            + "|---|\\n"
            + "| org.apache.kafka.common.serialization.IntegerDeserializer | int32, uint32 |\\n"
            + "|---|\\n"
            + "| org.apache.kafka.common.serialization.LongDeserializer | int64, uint64 |\\n"
            + "|---|\\n"
            + "| org.apache.kafka.common.serialization.FloatDeserializer | float32 |\\n"
            + "|---|\\n"
            + "| org.apache.kafka.common.serialization.DoubleDeserializer | float64 |\\n"
            + "|---|\\n"
            + "| org.apache.kafka.common.serialization.ByteArrayDeserializer | blob | \\n"
            + "---\\n"
            + "\\n"
            + "These deserializers are wrapped by extensions that catch exceptions of type "
            + "`org.apache.kafka.common.errors.SerializationException` to allow the operator to skip "
            + "over malformed messages. The used extensions do not modify the actual deserialization "
            + "function of the given base deserializers from the above table.\\n"
            + "\\n"
            + "Users can override this behaviour and specify which deserializer to use by setting the "
            + "`key.deserializer` and `value.deserializer` properties. \\n"
            ;

    public static final String CONSUMER_KAFKA_GROUP_MANAGEMENT = ""
            + "# Kafka's Group Management\\n"
            + "\\n"
            + "The operator is capable of taking advantage of [https://kafka.apache.org/documentation/#intro_consumers|Kafka's group management function].\\n"
            + "{../../doc/images/kafka_groups.png}"
            + "\\n"
            + "In the figure above, the topic **myTopic** with three partitions is consumed by two consumer groups. In *Group A*, which has "
            + "four consumers, one consumer is idle because the number of partitions is only three. All other consumers "
            + "in the group consume exactly one topic partition.\\n"
            + "*Consumer group B* has less consumers than partitions. One consumer is assigned to two partitions. The assignment of "
            + "consumers to partition(s) is fully controlled by Kafka.\\n"
            + "\\n"
            + "In order for the operator to use this functionality, the following requirements "
            + "must be met\\n"
            + "\\n"
            + "* A `group.id` consumer property must be given. The group-ID defines which operators belong to a consumer group. When no group-ID is given,"
            + " group management will not be in place."
            + "The `group.id` can be specified via property file, app option, or the **groupId** parameter.\\n"
            + "* The operator must not be configured with the optional input port.\\n"
            + "* The parameter **partition** must not be used.\\n"
            + "* **Restriction, when not in a consistent region:** The **startPosition** parameter must have the "
            + "value `Default` or must not be specified ([https://github.com/IBMStreams/streamsx.kafka/issues/94|issue 94]).\\n"
            + "\\n"
            + "The other way round, group management is inactive in following cases\\n"
            + "* when no group ID is specified, or\\n"
            + "* when the operator is configured with input port, or\\n"
            + "* when the **partition** parameter is specified, or\\n"
            + "* **only when not in consistent region**, the value of the **startPosition** is different from `Default`.\\n"
            + "\\n"
            + "In a consistent region, a consumer group must not have consumers outside of the consistent region, "
            + "for example in a different Streams job. The state of group management is indicated by the custom "
            + "metric **isGroupManagementActive**, where `1` indicates that group management is active.\\n"
            ;

    public static final String PRODUCER_DEFAULT_AND_ADJUSTED_PROPERTIES = ""
            + "---\\n"
            + "| Property name | Default Value |\\n"
            + "|===|\\n"
            + "| client.id | Generated ID in the form: `C-J<JobId>-<operator name>` |\\n"
            + "|---|\\n"
            + "| key.serializer | See **Automatic Serialization** section below |\\n"
            + "|---|\\n"
            + "| value.serializer | See **Automatic Serialization** section below |\\n"
            + "|---|\\n"
            + "| enable.idempotence | `true` only when in consistent region and **consistentRegionPolicy** parameter is set to `Transactional`. |\\n"
            + "|---|\\n"
            + "| transactional.id | Randomly generated ID in the form: `tid-<random_string>` only when in consistent region and **consistentRegionPolicy** parameter is set to `Transactional`. |\\n"
            + "---\\n"
            + "\\n"
            + "**NOTE:** Users can override any of the above properties by explicitly setting "
            + "the property value in either a properties file or in an application configuration. \\n"
            ;

    public static final String PRODUCER_AUTOMATIC_SERIALIZATION = ""
            + "# Automatic Serialization\\n"
            + "\\n"
            + "The operator will automatically select the appropriate serializers for the key "
            + "and message based on their types. The following table outlines which "
            + "deserializer will be used given a particular type: \\n"
            + "\\n"
            + "---\\n"
            + "| Serializer | SPL Types |\\n"
            + "|===|\\n"
            + "| org.apache.kafka.common.serialization.StringSerializer | rstring |\\n"
            + "|---|\\n"
            + "| org.apache.kafka.common.serialization.IntegerSerializer | int32, uint32 |\\n"
            + "|---|\\n"
            + "| org.apache.kafka.common.serialization.LongSerializer | int64, uint64 |\\n"
            + "|---|\\n"
            + "| org.apache.kafka.common.serialization.FloatSerializer | float32 |\\n"
            + "|---|\\n"
            + "| org.apache.kafka.common.serialization.DoubleSerializer | float64 |\\n"
            + "|---|\\n"
            + "| org.apache.kafka.common.serialization.ByteArraySerializer | blob |\\n"
            + "---\\n"
            ;

    public static final String PRODUCER_CONSISTENT_REGION_SUPPORT = ""
            + "# Consistent Region Strategy\\n"
            + "\\n"
            + "The `KafkaProducer` operator can participate in a consistent region. The operator "
            + "cannot be the start of a consistent region. The operator supports *at least once* "
            + "(default behavior) and *exactly once* delivery semantics. The delivery semantics "
            + "can be controlled by the **" + KafkaProducerOperator.CONSISTENT_REGION_POLICY_PARAM_NAME + "** parameter. "
            + "\\n"
            + "# *At least once* delivery\\n"
            + "If the operator crashes or is reset while in a consistent "
            + "region, the operator will write all tuples replayed. This ensures that every "
            + "tuple sent to the operator will be written to the topic(s). However, *at least once* "
            + "semantics implies that duplicate messages may be written to the topic(s). \\n"
            + "# *Exactly once* delivery\\n"

            + "Messages are always inserted into a topic within the context of a transaction. "
            + "Transactions are committed when the operator checkpoints. If the operator crashes "
            + "or is reset while in a consistent region, the operator will abort the ongoing "
            + "transaction and write all tuples replayed within a new transaction. External "
            + "consumers configured with `isolation.level=read_committed` will not "
            + "read the duplicates from the aborted transactions. Consumers that use a "
            + "different isolation level will read duplicate messages similar to *at least once* delivery.\\n"
            + "\\n"
            + "To ensure that replayed tuples are not committed multiple times when the consistent region "
            + "fails between commit and the point when the entire region has reached a consistent state, the "
            + "operator writes the current checkpoint sequence number as commit-ID together with "
            + "its unique *transactional ID* into the control topic **__streams_control_topic** "
            + "as part of the committed transaction. The checkpoint sequence number is part of "
            + "the operator's checkpointed state and represents the last successfully committed ID. \\n"
            + "\\n"
            + "When the consistent region fails after commit, the last successfully committed "
            + "ID is reset to the previous value and tuples are replayed. When the "
            + "operator checkpoints next time, it detects the "
            + "difference between the committed ID within the control topic and the "
            + "restored ID from the checkpoint data and aborts the Kafka transaction rather "
            + "than committing the replayed data again."
            + "\\n"
            + "\\n"
            + "**NOTE 1:** Transactions in Kafka have an inactivity timeout with default value of 60 seconds. "
            + "If the consistent region triggers less frequently and you expect a low message rate, "
            + "consider to to increase the timeout by setting the producer property `transaction.timeout.ms` "
            + "to a higher value, for example 120000 (milliseconds). "
            + "The maximum value of this property is limited by the server property "
            + "`transaction.max.timeout.ms`, which has a default value of 900000.\\n"
            + "\\n"
            + "**NOTE 2:** In Kafka environments that have disabled automatic topic creation "
            + "(broker config `auto.create.topics.enable=false`), the control topic with the name "
            + "**__streams_control_topic** must be created manually before **consistentRegionPolicy** can "
            + "be used with `Transactional`.\\n"
            + "\\n"
            + "**NOTE 3:** For *exactly once* delivery semantics, the Kafka broker must have version 0.11 or higher "
            + "because older brokers do not support transactions.\\n"
            ;
}
