import streamsx.spl.op

from streamsx.topology.schema import StreamSchema


class TypeHelper():

    @staticmethod
    def get_attr_type(in_type):
        attr_type = None

        if in_type == str:
            attr_type = 'rstring'
        elif in_type == int:
            attr_type = 'int64'
        elif in_type == float:
            attr_type = 'float64'
        else:
            raise Exception('Unsupported type: ' + str)

        return attr_type


class KafkaProducer(streamsx.spl.op.Sink):
    def __init__(self, stream, vmArg=None, appConfigName=None, keyAttribute=None, messageAttribute=None, partitionAttribute=None, propertiesFile=None, topicAttribute=None, topic=None, userLib=None, name=None):
        topology = stream.topology
        kind="com.ibm.streamsx.kafka::KafkaProducer"
        inputs=stream
        schemas=None
        params = dict()
        if vmArg is not None:
            params['vmArg'] = vmArg
        if appConfigName is not None:
            params['appConfigName'] = appConfigName
        if keyAttribute is not None:
            params['keyAttribute'] = keyAttribute
        if messageAttribute is not None:
            params['messageAttribute'] = messageAttribute
        if partitionAttribute is not None:
            params['partitionAttribute'] = partitionAttribute
        if propertiesFile is not None:
            params['propertiesFile'] = propertiesFile
        if topicAttribute is not None:
            params['topicAttribute'] = topicAttribute
        if topic is not None:
            params['topic'] = topic
        if userLib is not None:
            params['userLib'] = userLib
        super(KafkaProducer, self).__init__(kind,stream,params,name)


class KafkaConsumer(streamsx.spl.op.Source):

    @staticmethod
    def consume(topology, appConfigName, topic, name=None, key_type=str, message_type=str):
        key_attr_type = TypeHelper.get_attr_type(key_type)
        message_attr_type = TypeHelper.get_attr_type(message_type)

        print(key_attr_type)
        print(message_attr_type)

        message_schema = StreamSchema('tuple<' + key_attr_type + ' key, ' + message_attr_type + ' message>')

        _op = KafkaConsumer(topology, message_schema, appConfigName=appConfigName, topic=topic, name=name)
        return _op.stream




    def __init__(self, topology, schema,topic, vmArg=None, appConfigName=None, clientId=None, outputKeyAttributeName=None,
                 outputMessageAttributeName=None, outputTopicAttributeName=None, partition=None, propertiesFile=None,
                 startPosition=None, triggerCount=None, userLib=None, name=None):
        kind="com.ibm.streamsx.kafka::KafkaConsumer"
        inputs=None
        schemas=schema
        params = dict()
        params['topic'] = topic
        if vmArg is not None:
            params['vmArg'] = vmArg
        if appConfigName is not None:
            params['appConfigName'] = appConfigName
        if clientId is not None:
            params['clientId'] = clientId
        if outputKeyAttributeName is not None:
            params['outputKeyAttributeName'] = outputKeyAttributeName
        if outputMessageAttributeName is not None:
            params['outputMessageAttributeName'] = outputMessageAttributeName
        if outputTopicAttributeName is not None:
            params['outputTopicAttributeName'] = outputTopicAttributeName
        if partition is not None:
            params['partition'] = partition
        if propertiesFile is not None:
            params['propertiesFile'] = propertiesFile
        if startPosition is not None:
            params['startPosition'] = startPosition
        if triggerCount is not None:
            params['triggerCount'] = triggerCount
        if userLib is not None:
            params['userLib'] = userLib
        super(KafkaConsumer, self).__init__(topology,kind,schemas,params,name)