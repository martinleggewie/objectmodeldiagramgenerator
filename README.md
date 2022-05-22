# objectmodeldiagramgenerator
Generates PlantUML files which represent certain aspects of a collection of object models.
The generator expects the definition of these object models in one Excel file with specific proprietary format.
With this proprietary format you (the reader and modeler) can define the following domain model:
- There are scenarios. Each scenario has a key and can relate to other scenarios.
- There are business events. Each business event has a key and must relate to one scenario.
- There are domains. Each domain has a key.
- There are classes. Each class has a name and must relate to one domain.
- There are objects. Each object has a key and must relate to one business event and one class. Each object can relate to other objects. Each object can have properties (that is, a tupel of a name and and value).

Based on this information defined following this domain model the generator creates a list of text files.
Each text file contains the definition of one diagram using the PlantUML DSL.

Once these text files are created, you can use PlantUML CLI to convert the text files in - for example - PNG files. 

## Quick start
To start the diagram generation you need to have a Java runtime environment installed with at least version 8.
Then try the following on the command line in the main folder:

```
java -jar releases/objectmodeldiagramgenerator-1.1.0.jar -i objectmodeldefinitions.xlsx -o martinstest
```

If the generator run was successful, you will find a bunch of puml files in the folder "martinstest".

If you now want to also generate PNG files, change to the folder "martinstest" on the command line and try the following command (assuming that you have the PlantUML jar file stored in ~/tools/plantuml.jar):

```
mkdir png
java -DPLANTUML_LIMIT_SIZE=15000 -jar ~/tools/plantuml.jar *.puml png -o png
```

Depending on the number of business events you have defined in the Excel file, generating all the PNG files out of that can take several minutes.
So please be patient.
