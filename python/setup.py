from setuptools import setup
setup(
  name = 'streamsx.kafka',
  packages = ['streamsx.kafka'],
  include_package_data=True,
  version = '0.1.0',
  description = 'IBM Streams Kafka toolkit',
  long_description = open('DESC.txt').read(),
  author = 'IBM Streams @ github.com',
  license='Apache License - Version 2.0',
  url = 'https://github.com/streamsx.kafka',
  download_url = 'https://github.com/streamsx.kafka/tarball/pypi.streamsx.kafka_0.1.0',
  keywords = ['streams', 'ibmstreams', 'streaming', 'analytics', 'streaming-analytics', 'kafka'],
  classifiers = [
    'Development Status :: 1 - Planning',
    'License :: OSI Approved :: Apache Software License',
    'Programming Language :: Python :: 2',
    'Programming Language :: Python :: 2.7',
    'Programming Language :: Python :: 3',
    'Programming Language :: Python :: 3.5',
  ],
  install_requires=['streamsx==1.7a3'],
)
