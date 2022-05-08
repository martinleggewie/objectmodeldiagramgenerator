
import pandas

from DataModel import BusinessEvent, ObjectModel, Object
from DataModel import ObjectModelSequence

from DataReader import DataReader

class DataRepository:

    def __businessEventDict(self):
        result = {}
        for rawBusinessEvent in self.dataReader.dfBusinessEventDefinitions.itertuples():
            businessEvent = BusinessEvent(rawBusinessEvent.ID, rawBusinessEvent.Name)
            result[businessEvent.id] = businessEvent
        return result

    def __objectModelSequence(self):
        result = ObjectModelSequence("objectmodelchanges")

        rawData = self.dataReader.dfObjectModelChanges
        rowCount = rawData.shape[0]
        colCount = rawData.shape[1]

        # Process the first row from the raw data frame. This first row contains the different domains.
        # After this first row is processed, domainList contains the list of domains and their column ranges.
        domainList = self.__processHeaderRow(rawData, 0, range(1, colCount))

        # Process the second row from the raw data frame. This second row contains the list of types (aka classes).
        # After this second row is processed, classList contains the types and their column ranges.
        typeList = self.__processHeaderRow(rawData, 1, range(1, colCount))

        # Process the third row from the raw data frame. This third row contains all property names of the classes.
        # After this third row is processed, propertyDict contains the dict which maps the index to the property name.
        propertyDict = dict()
        colRange = range(1, colCount)
        for colNumber in colRange:
            currentValue = rawData.iat[2, colNumber]
            propertyDict[colNumber] = currentValue

        # Ok, now we have gathered all the metadata, still in a slightly rough form, but nevertheless we have
        # collected enough information to start parsing the real objects' data.
        currentBusinessEventId = None
        currentObjectModel = None
        for rowNumber in range(3, rowCount):

            # Fetch and check BusinessEvent. In case a new BusinessEvent starts, then complete the current ObjectModel and start a new one.
            currentValue = rawData.iat[rowNumber, 0]
            if currentValue != currentBusinessEventId:
                # We have found a new BusinessEvent. That means we also need to create a new ObjectModel based on the previous ObjectModel.
                currentBusinessEventId = currentValue
                currentObjectModel = ObjectModel(currentBusinessEventId, currentObjectModel)
                result.objectModelList.append(currentObjectModel)

            # Now start with parsing the rest of the row. This rest of the row contains the concrete values for all the properties.
            for currentType in typeList:
                currentTypeName = currentType[0]
                currentTypeRange = currentType[1]

                currentObject = None
                for currentPropertyIndex in currentTypeRange:
                    currentValue = rawData.iat[rowNumber, currentPropertyIndex]
                    domainName = self.__findDomainName(domainList, currentPropertyIndex)
                    if (currentObject == None):
                        currentObject = Object(currentTypeName, domainName)
                    currentObject.propertyDict[propertyDict[currentPropertyIndex]] = currentValue

                # Only add the new object if it really contains something.
                if currentObject.name() != "-":
                    currentObjectModel.objectDict[currentObject.name()] = currentObject

        return result


    def __init__(self, dataReader : DataReader):
        self.dataReader = dataReader

        self.businessEventDict = self.__businessEventDict()
        self.objectModelSequence = self.__objectModelSequence()


    def __processHeaderRow(self, rawData : pandas.DataFrame, rowNumber : int, colRange : range):
        result = []
        currentName = None
        currentColStart = 1
        colCount = rawData.shape[1]

        for colNumber in colRange:
            currentValue = rawData.iat[rowNumber, colNumber]
            if (not pandas.isnull(currentValue)) or (colNumber == colCount - 1):
                if (currentName != None):
                    lastCol = 0
                    if colNumber == colCount - 1:
                        lastCol = colNumber + 1
                    else:
                        lastCol = colNumber
                    result.append((currentName, range(currentColStart, lastCol)))
                currentName = currentValue
                currentColStart = colNumber
        return result

    def __findDomainName(self, domainList, col):
        for domain in domainList:
            domainName = domain[0]
            domainRange = domain[1]
            if col in domainRange:
                return domainName
        return "(no domain name found)"
        