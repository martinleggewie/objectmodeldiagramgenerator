from DataModel import DomainModel

class DomainModelPumlRenderer:

    def __init__(self, title, domainModel : DomainModel):
        self.title = title
        self.domainModel = domainModel

    def render(self):
        return (
              self.__header()
            + self.__classes()  
            + self.__associations()  
            + self.__footer()
        )

    def __header(self):
        result = ""
        result += "\n"
        result += "@startuml domainmodel_" + self.title + "\n"
        result += "\n"
        result += "top to bottom direction\n"
        result += "skinparam shadowing false\n"
        result += "skinparam componentStyle uml2\n"
        result += "skinparam roundCorner 10\n"
        result += "skinparam rectangleRoundCorner 20\n"
        result += "hide empty members\n"
        result += "\n"
        result += "skinparam classAttributeFontSize 16\n"
        result += "skinparam classBackgroundColor #cornsilk\n"
        result += "skinparam classBorderColor #black\n"
        result += "skinparam classBorderThickness 2\n"
        result += "skinparam classFontSize 20\n"
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

    def __classes(self):
        result = ""
        for currentType in self.domainModel.types():
            result += "rectangle \"" + currentType.domainName + "\" as " + currentType.domainName[0] + " {\n"
            result += "    class \"" + currentType.nameHuman() + "\" as " + currentType.nameRef() + " {\n"
            result += "    }\n"
            result += "}\n"
            result += "\n"
        return result

    def __associations(self):
        result = ""
        for currentTypeAssociation in self.domainModel.typeAssociations():
            result += currentTypeAssociation.dependerName + " --> " + currentTypeAssociation.dependeeName
            result += "\n"

        result += "\n"
        return result
