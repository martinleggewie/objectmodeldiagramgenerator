# objectmodeldiagramgenerator
Generates PlantUML files representing an object model. Reads the information about this object model from an Excel sheet with a specific proprietary format.

## Quick start
To start the diagram generation try the following on the command line in the main folder:

```
java -jar TODO -i business-events_object-model-changes.xlsx -o martinstest
```
If the generator run was successful, you will find a bunch of puml files in the current folder. Each puml file starts with "martinstest_".
