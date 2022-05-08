
import argparse

from DataReader import DataReader
from DataRepository import DataRepository
from PlantUmlDiagramRenderer import PlantUmlDiagramRenderer


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

#objectModelChanges = dataRepository.objectModelChanges
#for rowNumber in range(0, objectModelChanges.shape[0]):
#    for colNumber in range(0, objectModelChanges.shape[1]):
#        print(rowNumber, colNumber)
#        print(objectModelChanges.iat[rowNumber, colNumber])
#
#for businessEvent in dataRepository.businessEventDict.values():
#    print("    Business Event")
#    print("      ID:   ", businessEvent.id)
#    print("      Name: ", businessEvent.name)


objectModelSequence = dataRepository.objectModelSequence
print(objectModelSequence.title)
print(len(objectModelSequence.objectModelList))

print("Render the diagram(s).")
for objectModel in dataRepository.objectModelSequence.objectModelList:
    renderedOutput = PlantUmlDiagramRenderer(objectModel.businessEvent, objectModel).render()

    print("Write the output file.")
    outputFilename = (
        args.outputfileprefix + "_"
        + dataRepository.objectModelSequence.title + "_"
        + objectModel.businessEvent
        + ".puml"
    )
    outputFile = open(outputFilename, "w")
    outputFile.write(renderedOutput)

print("")
print("Done")
print("")

