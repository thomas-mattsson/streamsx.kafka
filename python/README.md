## Python streamsx.kafka package.

This exposes SPL operators in the `com.ibm.streamsx.kafka` toolkit as Python methods.

Package is organized using standard packaging to upload to PyPi.

The package is uploaded to PyPi in the standard way:
```
cd python/package
python setup.py sdist bdist_wheel upload -r pypi`
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
