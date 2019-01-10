# coding=utf-8
# Licensed Materials - Property of IBM
# Copyright IBM Corp. 2019

"""
Overview
++++++++

This module allows a Streams application to :py:func:`subscribe <subscribe>` a Kafka topic
as a stream and :py:func:`publish <publish>` messages on a Kafka topic from a stream
of tuples.

Connection to a Kafka broker
++++++++++++++++++++++++++++

To bootstrap servers of the Kafka broker are defined using a Streams application configuration.
The name of the application configuration must be specified using the ``appConfigName``
parameter to :py:func:`subscribe` or :py:func:`publish`.
The minimum set of properties in the application configuration contains ``bootstrap.servers``, for example

    bootstrap.servers   host1:port1,host2:port2,host3:port3

Other configs for Kafka consumers or Kafka producers can be added to the application configuration.
When configurations are specified, which are specific for consumers or producers only, it is recommended
to use different application configurations for :py:func:`publish <publish>` and :py:func:`subscribe <subscribe>`.

Messages
++++++++

The schema of the stream defines how messages are handled.

* ``CommonSchema.String`` - Each message is a UTF-8 encoded string.
* ``CommonSchema.Json`` - Each message is a UTF-8 encoded serialized JSON object.

No other formats are supported.

Sample
++++++

A simple hello world example of a Streams application publishing to
a topic and the same application consuming the same topic::

    from streamsx.topology.topology import Topology
    from streamsx.topology.schema import CommonSchema
    from streamsx.topology.context import submit
    import streamsx.kafka as kafka

    topo = Topology('KafkaHelloWorld')

    to_kafka = topo.source(['Hello', 'World!'])
    to_kafka = to_kafka.as_string()
    # TODO: delay the source to give the consumer a chance to get ready

    # Publish a stream to Kafka using TEST topic
    kafka.publish(to_kafka, topic='TEST', appConfigName='kafka_props')

    # Subscribe to same topic as a stream
    from_kafka = kafka.subscribe(topo, schema=CommonSchema.String, topic='TEST', appConfigName='kafka_props')

    from_kafka.print()

    # TODO: how to submit locally?
    submit('STREAMING_ANALYTICS_SERVICE', topo)

"""

__version__='0.1.0'

__all__ = ['subscribe', 'publish']
from streamsx.kafka._kafka import subscribe, publish
