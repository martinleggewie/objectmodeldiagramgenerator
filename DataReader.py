import pandas

# Liest die rohen Daten aus der XLSX-Datei und bietet dem Benutzer den Zugriff
# auf alle gelesenen Daten über jeweilige DataFrame-Objekte.
class DataReader:
    def __init__(self, args):
        excelFile = pandas.ExcelFile(args.inputfilename)
        self.dfBusinessEventDefinitions = pandas.read_excel(excelFile, sheet_name='Business Event Definitions')
        self.dfObjectModelChanges = pandas.read_excel(excelFile, sheet_name='Object Model Changes', header=None)
