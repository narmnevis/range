# range

Range is a random data generator.

# Usage

## Generation configuration

Range works based on a JSON configuration file. An example is as follows:

```
{

	config: {
	
		// The number of data items to be generated
		limit:	5,
		
		// The output format of the generated data
		format:	"csv",
		
		// The location to persist the generated data
		location: "/tmp/output.csv",
		
		// The data properties:
		//		- the name of the property
		//		- the type of the property
		// 		- refer to com.narmnevis.range.generator for the supported types
		data: {
			date:				"date",
			description:		"enum(gift, income, loan, budget, temp)",
			amount:				"range(0, 1000)",
			from:				"enum(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10)",
			to:					"enum(P1, P2, P3, P4, P5, P6, P7, P8, P9, P10)"
		}
	}

}
```

in which:

* `limit` is the number of data items to be generated.
* `format` is the for format with which the generated is published.
* `location` is the location at which the generated data is published.
* `data` holds the "names" and "types" of the data items to be generated.
 
## Generate data

To generate data:

* Download the distribution package from [here][v1] and unpack it.
* Create your generation configuration as described above.
* Run the following script:

```bash
/path/to/range-1.0-standanle/range.sh /path/to/range/config
```
* Collect the generated data from `location` configured in the configuration file.


# Development

## Install

To install range locally:

1. Checkout the code
2. Either use Maven or Gradle:

```
mvn clean install
```
or 
```
./gradlew publishToMavenLocal
```

## Range API

Using Range API is as simple as follows: 

```java
    String configPath = "retrieve path to the configuration file";
    Range range = new Range().withConfiguration(configPath);
    Data data = range.generate();
```

## Extend Range

### Data Generators

[v1]: http://github.com/Narmnevis/range/releases/download/range-1.0/range-1.0-standalone.zip
