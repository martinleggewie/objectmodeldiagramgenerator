import re

class BusinessEvent:
    def __init__(self, id, name):
        self.id = id
        self.name = name


class Object:
    def __init__(self, type, domain):
        self.type = type
        self.domain = domain
        self.propertyDict = dict()

    def name(self):
        for property in self.propertyDict.keys():
            if str(property).endswith("_pk"):
                return self.propertyDict[property]
        return "(no name)"

    def typeName(self):
        match = re.match(r'.*\((.*)\).*', self.type)
        return match.group(1)

    def dependees(self):
        result = []
        for property in self.propertyDict.keys():
            if str(property).endswith("_fk"):
                result.append(self.propertyDict[property])
        return result


class ObjectModel:
    def __init__(self, businessEvent, previousObjectModel):
        self.businessEvent = businessEvent
        self.objectDict = dict()
        if previousObjectModel != None:
            self.objectDict.update(previousObjectModel.objectDict)


class ObjectModelSequence:
    def __init__(self, title):
        self.title = title
        self.objectModelList = []


class Type:
    def __init__(self, name, domainName):
        self.name = name
        self.domainName = domainName

    def nameRef(self):
        match = re.match(r'.*\((.*)\).*', self.name)
        return match.group(1)

    def nameHuman(self):
        match = re.match(r'(.*) \((.*)\).*', self.name)
        return match.group(1)

class TypeAssociation:
    def __init__(self, dependerName, dependeeName):
        self.dependerName = dependerName
        self.dependeeName = dependeeName


class DomainModel:
    def __init__(self, objectModel : ObjectModel):
        self.objectModel = objectModel

    def types(self):
        result = []
        for object in self.objectModel.objectDict.values():
            currentType = Type(name = object.type, domainName = object.domain)
            result.append(currentType)
        return result

    def typeAssociations(self):
        result = []
        for object in self.objectModel.objectDict.values():
            for currentDependee in object.dependees():
                print(currentDependee)
                currentTypeAssociation = TypeAssociation(object.typeName(), re.match(r'(.*)(\d\d\d\d)', currentDependee).group(1))
                # only add this new typeAssociation in case it is not already contained in it
                entryFound = False
                for currentEntry in result:
                    if not (entryFound) and (currentEntry.dependerName == currentTypeAssociation.dependerName) and (currentEntry.dependeeName == currentTypeAssociation.dependeeName):
                        entryFound = True
                if not (entryFound):
                    result.append(currentTypeAssociation)
        return result
