from DataModel import ObjectModel

class PlantUmlDiagramRenderer:

    def __init__(self, title, objectModel : ObjectModel):
        self.title = title
        self.objectModel = objectModel

    def render(self):
        return (
              self.__header()
            + self.__content()  
            + self.__footer()
        )

    def __header(self):
        result = ""
        result += "\n"
        result += "@startuml " + self.title + "\n"
        result += "\n"
        result += "top to bottom direction\n"
        result += "skinparam shadowing false\n"
        result += "skinparam componentStyle uml2\n"
        result += "skinparam roundCorner 10\n"
        result += "skinparam rectangleRoundCorner 20\n"
        result += "\n"
        result += "skinparam objectAttributeFontSize 16\n"
        result += "skinparam objectBackgroundColor #cornsilk\n"
        result += "skinparam objectBorderColor #black\n"
        result += "skinparam objectBorderThickness 2\n"
        result += "skinparam objectFontSize 20\n"
        result += "\n"
        result += "skinparam arrowColor #black\n"
        result += "skinparam arrowFontSize 18\n"
        result += "skinparam arrowThickness 2\n"
        result += "\n"
        result += "\n"
        return result

    def __footer(self):
        result = ""
        result += "\n"
        result += "@enduml\n"
        result += "\n"
        return result

    def __content(self):
        result = ""

        for object in self.objectModel.objectDict.values():
            result += "object \"" + object.name() + " : " + object.type + "\" as " + object.name() + " {\n"
            for propertyName in object.propertyDict.keys():
                result += "    " + propertyName + " : \"" + object.propertyDict[propertyName] + "\"\n"
            result += "}\n"
            result += "\n"
        return result