
import argparse
from DataModel import DomainModel

from DataReader import DataReader
from DataRepository import DataRepository
from ObjectModelPumlRenderer import ObjectModelPumlRenderer
from DomainModelPumlRenderer import DomainModelPumlRenderer


print("")
print("")
print("----------------------------------")
print("Generate the object model diagrams")
print("----------------------------------")
print("")


# Build the command line parser and parse the parameter the caller hopefully has specified when starting this script
parser = argparse.ArgumentParser(description='Reads an XLSX file with a well-defined structure and create cost calculation for each contract.')
parser.add_argument('-i', '--inputfile',        dest='inputfilename',    required=True, help='name of the input XLSX file')
parser.add_argument('-o', '--outputfileprefix', dest='outputfileprefix', required=True, help='prefix for all generated output files')
args = parser.parse_args()

# Read the data from the XLSX file
print("Load the XLSX file.")
dataRepository = DataRepository(DataReader(args))

print("Render the diagram(s).")
for objectModel in dataRepository.objectModelSequence.objectModelList:
    # Render the object diagram
    objectDiagram = ObjectModelPumlRenderer(objectModel.businessEvent, objectModel).render()
    objectDiagramOutputFilename = (
          args.outputfileprefix
        + "_"
        + dataRepository.objectModelSequence.title
        + "_"
        + objectModel.businessEvent 
        + "_"
        + "objectdiagram"
        + ".puml"
    )
    print("Write output file: " + objectDiagramOutputFilename)
    objectDiagramOutputFile = open(objectDiagramOutputFilename, "w")
    objectDiagramOutputFile.write(objectDiagram)

    # And now render the corresponding domain model diagram
    domainModel = DomainModel(objectModel)
    domainModelDiagram = DomainModelPumlRenderer(objectModel.businessEvent, domainModel).render()
    domainModelDiagramOutputFilename = (
          args.outputfileprefix
        + "_"
        + dataRepository.objectModelSequence.title
        + "_"
        + objectModel.businessEvent 
        + "_"
        + "domainmodeldiagram"
        + ".puml"
    )
    print("Write output file: " + domainModelDiagramOutputFilename)
    domainModelDiagramOutputFile = open(domainModelDiagramOutputFilename, "w")
    domainModelDiagramOutputFile.write(domainModelDiagram)

print("")
print("Done")
print("")

