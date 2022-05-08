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
