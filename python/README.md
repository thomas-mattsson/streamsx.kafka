## Python streamsx.kafka package.

This exposes SPL operators in the `com.ibm.streamsx.kafka` toolkit as Python methods.

Package is organized using standard packaging to upload to PyPi.

The package is uploaded to PyPi in the standard way:
```
cd python/package
python3 setup.py sdist bdist_wheel upload -r pypi
```
**Note:** This is done using the `ibmstreams` account at pypi.org

Package details: https://pypi.python.org/pypi/streamsx.kafka

Documentation is using Sphinx and can be built locally using:
```
cd python/package/docs
make html
```
and viewed using
```
firefox python/package/docs/build/html/index.html
```

The documentation is also setup at `readthedocs.io` under the account: `IBMStreams`

Documentation links:
* http://streamsxkafka.readthedocs.io/en/pypackage

## Test

The tests are run with a locally installed Streams installation and any Kafka broker.
Following environment variables must be set:

| Enveironment variable | content |
| --- | --- |
| STREAMS_INSTALL | must point to your Streams installation |
| STREAMS_USERNAME | The username of the Streams user |
| STREAMS_PASSWORD | The password of the Streams user |
| KAFKA_TOOLKIT_HOME | The directory where the Kafka toolkit is located |

The Streams runtime must be aware of the `PYTHONHOME` variable. The variable must be made
available to the runtime by using following *streamtool* command:

```
streamtool setproperty --application-ev PYTHONHOME=path_to_python_install
```

For the tests, an application configuration with name `kafkatest` is required. It can be created
on instance or domain level and must contain the properties `bootstrap.servers` in the form
`kafka_server1:port,kafka_server2:port,...`.

Run the tests with

```
cd python/package
python3 -u -m unittest streamsx.kafka.tests.test_kafka.TestSubscribeParams
python3 -u -m unittest streamsx.kafka.tests.test_kafka.TestKafka
```

or, to test all test cases

```
cd python/package
python3 -u -m unittest streamsx.kafka.tests.test_kafka
```
