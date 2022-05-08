class BusinessEvent:
    def __init__(self, id, name):
        self.id = id
        self.name = name

class Object:
    def __init__(self, type, domain):
        self.type = type
        self.domain = domain
        self.propertyDict = dict()
        self.dependeeList = []

    def name(self):
        for property in self.propertyDict.keys():
            if str(property).endswith("_pk"):
                return self.propertyDict[property]
        return "(no name)"

class ObjectModel:
    def __init__(self, businessEvent):
        self.businessEvent = businessEvent
        self.objectDict = dict()

class ObjectModelSequence:
    def __init__(self, title):
        self.title = title
        self.objectModelList = []
