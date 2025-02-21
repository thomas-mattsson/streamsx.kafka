/**
 * 
 */
package com.ibm.streamsx.kafka.operators;

/**
 * This class contains String constant with SPL Documentation.
 */
public class KafkaSplDoc {
    public static final String CONSUMER_CHECKPOINTING_CONFIG = ""
            + "# Checkpointing behavior in an autonomous region\\n"
            + "\\n"
            + "The operator can be configured for operator driven and periodic checkpointing. Checkpointing "
            + "is in effect when the operator is configured with an input port. When the operator has no input "
            + "port, checkpointing can be configured, but is silently ignored. The operator "
            + "checkpoints the current partition assignment, which is modified via control tuples received "
            + "by the input port. The current fetch positions are not saved.\\n"
            + "\\n"
            + "On reset, the partition assignment is restored from the checkpoint. The fetch offsets will "
            + "be the last committed offsets.\\n"
            + "\\n"
            + "With `config checkpoint: operatorDriven;` the operator creates a checkpoint when the partition "
            + "assignment changes, i.e. after each input tuple has been processed.\\n"
            + "\\n"
            ;

    public static final String CONSUMER_WHERE_TO_FIND_PROPERTIES = ""
            + "The operator implements Kafka's KafkaConsumer API of the Kafka client version 2.2.1. As a result, it supports all "
            + "Kafka configurations that are supported by the underlying API. The consumer configs for the Kafka consumer 2.2 "
            + "can be found in the [https://kafka.apache.org/22/documentation.html#consumerconfigs|Apache Kafka 2.2 documentation].\\n"
            + "\\n"
            + "When you reference files within your application, which are bundled with the Streams application bundle, for example "
            + "an SSL truststore or a key tab file for Kerberos authentication, you can use the `{applicationDir}` placeholder in the "
            + "property values. Before the configs are passed to the Kafka client, the `{applicationDir}` placeholder is replaced "
            + "by the application directory of the application. Examples how to use the placeholder are shown below.\\n"
            + "\\n"
            + "    ssl.truststore.location={applicationDir}/etc/myTruststore.jks"
            + "\\n"
            + "or\\n"
            + "\\n"
            + "    sasl.jaas.config=com.ibm.security.auth.module.Krb5LoginModule required \\\\\\n"
            + "        debug=true \\\\\\n"
            + "        credsType=both \\\\\\n"
            + "        useKeytab=\\\"{applicationDir}/etc/myKeytab.keytab\\\" \\\\\\n"
            + "        principal=\\\"kafka/host.domain@EXAMPLE.DOMAIN.COM\\\";\\n"
            + "\\n"
            + "\\n"
            ;

    public static final String CONSUMER_DEFAULT_AND_ADJUSTED_PROPERTIES = ""
            + "---\\n"
            + "| Property Name | Default Value |\\n"
            + "|===|\\n"
            + "| client.id | Generated ID in the form: `C-J<JobId>-<operator name>` |\\n"
            + "|---|\\n"
            + "| group.id | hash from domain-ID, instance-ID, job-ID, and operator name |\\n"
            + "|---|\\n"
            + "| key.deserializer | See **Automatic deserialization** section below |\\n"
            + "|---|\\n"
            + "| value.deserializer | See **Automatic deserialization** section below |\\n"
            + "|---|\\n"
            + "| partition.assignment.strategy | **Only when multiple topics are specified:** `org.apache.kafka.clients.consumer.RoundRobinAssignor` |\\n"
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
            + "|---|\\n"
            + "| metric.reporters | added to provided reporters: `com.ibm.streamsx.kafka.clients.consumer.ConsumerMetricsReporter` |\\n"
            + "|---|\\n"
            + "| metrics.sample.window.ms | adjusted to `10000` |\\n"
            + "|---|\\n"
            + "| client.dns.lookup | `use_all_dns_ips` |\\n"
            + "|---|\\n"
            + "| reconnect.backoff.max.ms | `10000` |\\n"
            + "|---|\\n"
            + "| reconnect.backoff.ms | `250` |\\n"
            + "|---|\\n"
            + "| retry.backoff.ms | `500` |\\n"
            + "---\\n"
            + "\\n"
            + "**NOTE:** Although properties are adjusted, users can override any of the above properties by explicitly setting the property "
            + "value in either a properties file or in an application configuration.\\n"
            ;

    public static final String CONSUMER_EXPOSED_KAFKA_METRICS = ""
            + "# Following metrics created by the Kafka consumer client are exposed as custom metrics:\\n"
            + "\\n"
            + "---\\n"
            + "| Custom Metric | Description |\\n"
            + "|===|\\n"
            + "| connection-count | The current number of active connections. |\\n"
            + "|---|\\n"
            + "| incoming-byte-rate | The number of bytes read off all sockets per second |\\n"
            + "|---|\\n"
            + "| *topic*-*partition*:records-lag | The latest lag of the specific partition. A value of `-1` indicates that the metric is not applicable to the operator. |\\n"
            + "|---|\\n"
            + "| records-lag-max | The maximum lag in terms of number of records for any partition in this window |\\n"
            + "|---|\\n"
            + "| fetch-size-avg | The average number of bytes fetched per request |\\n"
            + "|---|\\n"
            + "| *topic*:fetch-size-avg | The average number of bytes fetched per request for a topic |\\n"
            + "|---|\\n"
            + "| commit-rate | The number of commit calls per second |\\n"
            + "|---|\\n"
            + "| commit-latency-avg | The average time taken for a commit request |\\n"
            + "---\\n"
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
            + "**Tuple replay after reset of the consistent region**\\n"
            + "\\n"
            + "After reset of the consistent region, the operators that participate in a consumer group may replay tuples that "
            + "have been submitted by a different consumer before. The reason for this is, that the assignment of partitions to consumers "
            + "can change. This property of a consumer group must be kept in mind when combining a consumer groups with "
            + "consistent region.\\n"
            + "\\n"
            + "When **no group-ID is specified**, the partition assignment is static (a consumer consumes "
            + "all partitions or those, which are specified), so that the consumer operator replays after consistent region "
            + "reset those tuples, which it has submitted before.\\n"
            + "\\n"
            + "\\n"
            + "When the consumers of a consumer group rebalance the partition assignment, for example, immediately after job "
            + "submission, or when the broker node being the group's coordinator is shutdown, multiple resets of the consistent "
            + "region can occur when the consumers start up. It is recommended to set the "
            + "`maxConsecutiveResetAttempts` parameter of the `@consistent` annotation to a higher value than the default value of 5.\\n"
            + "\\n"
            + "On drain, the operator will commit offsets of the submitted tuples.\\n"
            + "\\n"
            + "On checkpoint, the operator will save the last offset for each topic-partition that it is assigned to. In the "
            + "event of a reset, the operator will seek to the saved offset for each topic-partition and "
            + "begin consuming messages from that point.\\n"
            + "\\n"
            + "**Metrics related to consistent region**\\n"
            + "\\n"
            + "---\\n"
            + "| metric name | description |\\n"
            + "|===|\\n"
            + "| drainTimeMillis | drain duration of the last drain in milliseconds |\\n"
            + "|---|\\n"
            + "| drainTimeMillisMax | maximum drain duration in milliseconds |\\n"
            + "---\\n"
            + "\\n"
            + "**These metrics are only present when the operator participates in a consistent region.**\\n"
            ;

    public static final String CONSUMER_COMMITTING_OFFSETS = ""
            + "# Committing received Kafka messages\\n"
            + "\\n"
            + "Committing offsets is always controlled by the Streams operator. All auto-commit related settings "
            + "via consumer properties are ignored by the operator.\\n"
            + "\\n"
            + "**a) The operator is not part of a consistent region**\\n"
            + "\\n"
            + "The consumer operator commits the offsets of "
            + "those Kafka messages, which have been submitted as tuples. Offsets are committed under the following conditions:\\n"
            + "\\n"
            + "**1. The partitions within a consumer group are rebalanced.** Before new partitions are assigned, "
            + "the offsets of the currently assigned partitions are committed. When the partitions are re-assigned, "
            + "the operators start fetching from these committed offsets. The periodic commit controlled by the "
            + "**commitCount** or **commitPeriod** parameter is reset after rebalance.\\n"
            + "\\n"
            + "**2. Offsets are committed periodically.** The period can be a time period or a tuple count. "
            + "If nothing is specified, offsets are committed every 5 seconds. The time period can be specified with the "
            + "**commitPeriod** parameter. When the **commitCount** parameter is used with a value of N, offsets "
            + "are committed every N submitted tuples.\\n"
            + "\\n"
            + "**3. Partition assignment via control port is removed.** The offsets of those partitions which are de-assigned are committed.\\n"
            + "\\n"
            + "**b) The operator is part of a consistent region**\\n"
            + "\\n"
            + "Offsets are always committed when the consistent region drains, i.e. when the region becomes a consistent state. "
            + "On drain, the consumer operator commits the offsets of those Kafka messages that have been submitted as tuples. "
            + "When the operator is in a consistent region, the parameters **commitCount** and **commitPeriod** are ignored because the "
            + "commit frequency is given by the trigger period of the consistent region.\\n"
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
            + "Users can override this behavior and specify which deserializer to use by setting the "
            + "`key.deserializer` and `value.deserializer` properties. \\n"
            ;

    public static final String CONSUMER_RESTART_BEHAVIOUR = ""
            + "# Operator restart behavior\\n"
            + "\\n"
            + "**When in a consistent region**\\n"
            + "\\n"
            + "When the operator is part of a consistent region, the operator is reset "
            + "to the initial state or to the last consistent state when the PE is re-launched. "
            + "The operator or the group of consumer operators replay tuples. There are no "
            + "specifics in this case.\\n"
            + "\\n"
            + "**When not in a consistent region**\\n"
            + "\\n"
            + "*a) The consumer operator has no input port*\\n"
            + "\\n"
            + "With group management enabled, the operators of a consumer group need a "
            + "`JobControlPlane` operator in the application graph to coordinate the initial "
            + "fetch offsets when the **startPosition** parameter is set and not "
            + "`Default`. When re-launched, the operator seeks a partition to the fetch offset given "
            + "by **startPosition** when none of the operators within the group previously "
            + "committed offsets for this partition. When offsets have been committed, the fetch "
            + "offset for a partition at operator start will be its last committed offset. This "
            + "behavior enables you to change the width of a parallel region with consumers being "
            + "a consumer group. The `JobControlPlane` operator is not required when the "
            + "**startPosition** parameter is not used or has the value `Default`.\\n"
            + "\\n"
            + "When group management is not enabled for a consumer operator (no group identifier "
            + "specified or partitions specified), i.e. a single consumer is pinned to topic partitions, "
            + "a `JobControlPlane` operator is required in the application graph when **startPosition** is not `Default`.\\n"
            + "The consumer operator stores those partitions in it for which it has already committed "
            + "offsets. These partitions will *not* be seeked to **startPosition** after re-launch of a PE.\\n"
            + "\\n"
            + "Omitting the **startPosition** parameter or using `Default` as the value does **not** require a "
            + "`JobControlPlane` operator.\\n"
            + "\\n"
            + "*b) The consumer operator is configured with a control input port*\\n"
            + "\\n"
            + "When the operator is configured with an input port, the partition assignments, "
            + "which have been created via the control stream, are lost. It is therefore recommended to fuse the "
            + "consumer operator with the source of the control stream to replay the control tuples "
            + "after restart or to use a `config checkpoint` clause, preferably `operatorDriven`, "
            + "to restore the partition assignment and continue fetching records beginning with the "
            + "last committed offsets.\\n"
            + "\\n";

    public static final String CONSUMER_KAFKA_GROUP_MANAGEMENT = ""
            + "# Kafka's Group Management\\n"
            + "\\n"
            + "The operator is capable of taking advantage of [https://kafka.apache.org/documentation/#intro_consumers|Kafka's group management function].\\n"
            + "{../../doc/images/kafka_groups.png}"
            + "\\n"
            + "In the figure above, the topic **myTopic** with three partitions is consumed by two consumer groups. In *Group A*, which has "
            + "four consumers, one consumer is idle because the number of partitions is only three. All other consumers "
            + "in the group would consume exactly one topic partition.\\n"
            + "*Consumer group B* has less consumers than partitions. One consumer is assigned to two partitions. The assignment of "
            + "consumers to partition(s) is fully controlled by Kafka.\\n"
            + "\\n"
            + "In order for the operator to use this function, the following requirements "
            + "must be met\\n"
            + "\\n"
            + "* A `group.id` consumer property must be given. The group-ID defines which operators belong to a consumer group. When no group-ID is given, "
            + "group management will not be in place. "
            + "The `group.id` can be specified via property file, app configuration, or the **groupId** parameter.\\n"
            + "* The operator must not be configured with the optional input port.\\n"
            + "* The **partition** parameter must not be used.\\n"
            + "\\n"
            + "The other way round, group management is inactive in following cases\\n"
            + "* when no group ID is specified, or\\n"
            + "* when the operator is configured with the optional input port, or\\n"
            + "* when the **partition** parameter is specified.\\n"
            + "\\n"
            + "In a consistent region, a consumer group must not have consumers outside of the consistent region, "
            + "for example in a different Streams job.\\n"
            + "\\n"
            + "When not in a consistent region, the consumer group must "
            + "not have consumers outside of the Streams job unless the **startPosition** parameter is not used "
            + "or has the value `Default`.\\n"
            + "\\n"
            + "When group management is used together with **startPosition** `Beginning`, `End`, or `Time`, "
            + "the application graph must have a **JobControlPlane** operator to work correctly. When the "
            + "`JobControlPlane` operator cannot be connected, the operator will fail to initialize.\\n"
            + "\\n"
            + "**Metrics related to group management**\\n"
            + "\\n"
            + "---\\n"
            + "| metric name | description |\\n"
            + "|===|\\n"
            + "| isGroupManagementActive | `1` indicates that group management is active, `0` indicates that group management is inactive. |\\n"
            + "|---|\\n"
            + "| nPartitionRebalances | Number of partition assignment rebalances for "
            + "each consumer operator. **The metric is only visible when group management is active.** |\\n"
            + "---\\n"
            + "\\n"
            ;

    public static final String PRODUCER_CHECKPOINTING_CONFIG = ""
            + "# Checkpointing behavior in an autonomous region\\n"
            + "\\n"
            + "A `config checkpoint` clause has no effect to the operator.\\n"
            + "\\n"
            ;

    public static final String PRODUCER_WHERE_TO_FIND_PROPERTIES = ""

            + "The operator implements Kafka's KafkaProducer API of the Kafka client version 2.2.1. As a result, "
            + "it supports all Kafka properties that are supported by the "
            + "underlying API. The producer properties for the Kafka producer 2.2 "
            + "can be found in the [https://kafka.apache.org/22/documentation/#producerconfigs|Apache Kafka 2.2 documentation].\\n"
            + "\\n"
            + "When you reference files within your application, which are bundled with the Streams application bundle, for example "
            + "an SSL truststore or a key tab file for Kerberos authentication, you can use the `{applicationDir}` placeholder in the "
            + "property values. Before the configs are passed to the Kafka client, the `{applicationDir}` placeholder is replaced "
            + "by the application directory of the application. Examples how to use the placeholder are shown below.\\n"
            + "\\n"
            + "    ssl.truststore.location={applicationDir}/etc/myTruststore.jks"
            + "\\n"
            + "or\\n"
            + "\\n"
            + "    sasl.jaas.config=com.ibm.security.auth.module.Krb5LoginModule required \\\\\\n"
            + "        debug=true \\\\\\n"
            + "        credsType=both \\\\\\n"
            + "        useKeytab=\\\"{applicationDir}/etc/myKeytab.keytab\\\" \\\\\\n"
            + "        principal=\\\"kafka/host.domain@EXAMPLE.DOMAIN.COM\\\";\\n"
            + "\\n"
            + "\\n"
            ;

    public static final String PRODUCER_DEFAULT_AND_ADJUSTED_PROPERTIES = ""
            + "---\\n"
            + "| Property name | Default Value |\\n"
            + "|===|\\n"
            + "| client.id | Generated ID in the form: `P-J<JobId>-<operator name>` |\\n"
            + "|---|\\n"
            + "| key.serializer | See **Automatic Serialization** section below |\\n"
            + "|---|\\n"
            + "| value.serializer | See **Automatic Serialization** section below |\\n"
            + "|---|\\n"
            + "| acks | Controls the durability of records that are sent. Adjusted to `all` when in consistent region, and **consistentRegionPolicy** parameter is `Transactional`, otherwise `acks` is unchanged. The value `0` (fire and forget) is not recommended. |\\n"
            + "|---|\\n"
            + "| retries | When `0` is provided as **retries** and **consistentRegionPolicy** parameter is `Transactional` **retries** is adjusted to `1`. |\\n"
            + "|---|\\n"
            + "| linger.ms | `100` |\\n"
            + "|---|\\n"
            + "| batch.size | `32768` |\\n"
            + "|---|\\n"
            + "| max.in.flight.requests.per.connection | `1` when **guaranteeOrdering** parameter is `true`, limited to `5` when provided and **consistentRegionPolicy** parameter is `Transactional`, or `10` in all other cases. |\\n"
            + "|---|\\n"
            + "| enable.idempotence | `true` only when in consistent region and **consistentRegionPolicy** parameter is set to `Transactional`. |\\n"
            + "|---|\\n"
            + "| transactional.id | Randomly generated ID in the form: `tid-<random_string>` only when in consistent region and **consistentRegionPolicy** parameter is set to `Transactional`. |\\n"
            + "|---|\\n"
            + "| transaction.timeout.ms | adjusted to a minimum of `drain timeout \\\\+ 120000 milliseconds`, but not greater than `900000`. Adjusted only when in consistent region and **consistentRegionPolicy** parameter is set to `Transactional`. |\\n"
            + "|---|\\n"
            + "| metric.reporters | added to provided reporters: `com.ibm.streamsx.kafka.clients.producer.ProducerMetricsReporter` |\\n"
            + "|---|\\n"
            + "| metrics.sample.window.ms | adjusted to `10000` |\\n"
            + "|---|\\n"
            + "| client.dns.lookup | `use_all_dns_ips` |\\n"
            + "|---|\\n"
            + "| reconnect.backoff.max.ms | `10000` |\\n"
            + "|---|\\n"
            + "| reconnect.backoff.ms | `250` |\\n"
            + "|---|\\n"
            + "| retry.backoff.ms | `500` |\\n"
            + "---\\n"
            + "\\n"
            + "**NOTE:** Although properties are adjusted, users can override any of the above properties by explicitly setting "
            + "the property value in either a properties file or in an application configuration. Not all properties or possible property values, which can be "
            + "specified for the Kafka producer version 2.1, are supported by all Broker versions. An example for is the Zstandard "
            + "compression algorithm, which is supported with broker version 2.1 and above.\\n"
            ;

    public static final String PRODUCER_EXPOSED_KAFKA_METRICS = ""
            + "# Following metrics created by the Kafka producer client are exposed as custom metrics:\\n"
            + "\\n"
            + "---\\n"
            + "| Custom Metric | Description |\\n"
            + "|===|\\n"
            + "| connection-count | The current number of active connections. |\\n"
            + "|---|\\n"
            + "| compression-rate-avg | The average compression rate of record batches (as percentage, 100 means no compression). |\\n"
            + "|---|\\n"
            + "| *topic*:compression-rate | The average compression rate of record batches for a topic (as percentage, 100 means no compression). |\\n"
            + "|---|\\n"
            + "| record-queue-time-avg | The average time in ms record batches spent in the send buffer. |\\n"
            + "|---|\\n"
            + "| record-queue-time-max | The maximum time in ms record batches spent in the send buffer. |\\n"
            + "|---|\\n"
            + "| record-send-rate | The average number of records sent per second. |\\n"
            + "|---|\\n"
            + "| record-retry-total | The total number of retried record sends |\\n"
            + "|---|\\n"
            + "| *topic*:record-send-total | The total number of records sent for a topic. |\\n"
            + "|---|\\n"
            + "| *topic*:record-retry-total | The total number of retried record sends for a topic |\\n"
            + "|---|\\n"
            + "| *topic*:record-error-total | The total number of record sends that resulted in errors for a topic |\\n"
            + "|---|\\n"
            + "| records-per-request-avg | The average number of records per request. |\\n"
            + "|---|\\n"
            + "| requests-in-flight | The current number of in-flight requests awaiting a response. |\\n"
            + "|---|\\n"
            + "| request-rate | The number of requests sent per second |\\n"
            + "|---|\\n"
            + "| request-size-avg | The average size of requests sent. |\\n"
            + "|---|\\n"
            + "| request-latency-avg | The average request latency in ms |\\n"
            + "|---|\\n"
            + "| request-latency-max | The maximum request latency in ms |\\n"
            + "|---|\\n"
            + "| batch-size-avg | The average number of bytes sent per partition per-request. |\\n"
            + "|---|\\n"
            + "| outgoing-byte-rate | The number of outgoing bytes sent to all servers per second |\\n"
            + "|---|\\n"
            + "| bufferpool-wait-time-total | The total time an appender waits for space allocation in nanoseconds. |\\n"
            + "|---|\\n"
            + "| buffer-available-bytes | The total amount of buffer memory that is not being used (either unallocated or in the free list). |\\n"
            + "---\\n"
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
            + "The producer operator can participate in a consistent region. The operator "
            + "cannot be the start of a consistent region. When the consistent region drains, "
            + "the operator flushes all accumulated outstanding records to the Kafka cluster.\\n"
            + "\\n"
            + "The operator supports *non-transactional* "
            + "(default behavior) and *transactional* message delivery. The delivery "
            + "can be controlled by the **" + KafkaProducerOperator.CONSISTENT_REGION_POLICY_PARAM_NAME + "** parameter. "
            + "\\n"
            + "# *Non-transactional* delivery\\n"
            + "If the operator crashes or is reset while in a consistent "
            + "region, the operator will write all tuples replayed. This ensures that every "
            + "tuple sent to the operator will be written to the topic(s). However, *non-transactional* "
            + "message delivery implies that duplicate messages may be written to the topic(s).\\n"
            + "\\n"
            + "# *Transactional* delivery\\n"
            + "Messages are always inserted into a topic within the context of a transaction. "
            + "Transactions are committed when the operator checkpoints. If the operator crashes "
            + "or is reset while in a consistent region, the operator will abort an ongoing "
            + "transaction and write all tuples replayed within a new transaction. External "
            + "consumers configured with `isolation.level=read_committed` will not "
            + "read the duplicates from the aborted transactions. Consumers that use a "
            + "different isolation level will read duplicate messages as if they were produced without "
            + "being part of a transaction.\\n"
            + "\\n"
            + "For consumers that read the output topics with `isolation.level=read_committed`, the "
            + "transactional producer minimizes number if duplicate messages with the downside that the "
            + "produced messages are only visible at the checkpoint interval, which can be interpreted "
            + "as additional latency.\\n"
            + "\\n"
            + "A consumer that reads the output topics with `isolation.level=read_committed` can read "
            + "duplicate messages when the consistent region fails after the Kafka transaction has been "
            + "committed, but before the region has reached a consistent state.\\n"
            + "\\n"
            + "**NOTE 1:** Transactions in Kafka have an inactivity timeout, which is configured by the "
            + "producer property `transaction.timeout.ms`. "
            + "This timeout is adjusted by the operator to a minimum of the drain timeout plus 120 seconds. "
            + "The maximum value of this property is limited by the server property "
            + "`transaction.max.timeout.ms`, which has a default value of 900000.\\n"
            + "The operator opens a transaction when the first tuple of a consistent cut is processed. "
            + "Every tuple being processed resets the inactivity timer.\\n"
            + "\\n"
            + "\\n"
            + "**NOTE 2:** For *transactional* delivery, the Kafka broker must have version 0.11 or higher. "
            + "Older brokers do not support transactions.\\n"
            ;
}
