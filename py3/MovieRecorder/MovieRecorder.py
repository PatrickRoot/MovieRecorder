# -*- coding: utf-8 -*-

"""
Module implementing MovieRecorder.
"""

from PyQt4.QtGui import *
from PyQt4.QtCore import *
from PyQt4 import QtGui

from Ui_MovieRecorderUi import Ui_MainWindow
from DealDatebase import DataHandle

from Record import Record
import time
import sys

class MovieRecorder(QMainWindow, Ui_MainWindow):
    """
    Class documentation goes here.
    """
    def __init__(self, parent = None):
        """
        Constructor
        """
        QMainWindow.__init__(self, parent)
        self.setupUi(self)
        self.message = Record()
        self.dbHandle = DataHandle()
        self.records = self.dbHandle.query(Record())
        self.repainData()
    
    @pyqtSignature("")
    def on_action_triggered(self):
        """
        Slot documentation goes here.
        """
        # TODO: not implemented yet
        # 退出
        sys.exit(app.exec_())
    
    @pyqtSignature("")
    def on_action_2_triggered(self):
        """
        Slot documentation goes here.
        """
        # TODO: not implemented yet
        # 查询
        self.queryFunction()
        self.label_s.setText(_translate("MainWindow", '有电影：'+ len(self.records) +'部', None));
        
    @pyqtSignature("")
    def on_action_3_triggered(self):
        """
        Slot documentation goes here.
        """
        # TODO: not implemented yet
        # 重置
        self.lineEdit.setText('')
        self.lineEdit_2.setText('')
        self.lineEdit_3.setText('')
        self.lineEdit_4.setText('')
        self.lineEdit_5.setText('')
        self.lineEdit_6.setText('')
        self.lineEdit_7.setText('')
        self.lineEdit_8.setText('')
        self.message = Record()
        self.queryFunction()
        
        pass
    
    @pyqtSignature("")
    def on_action_4_triggered(self):
        """
        Slot documentation goes here.
        """
        # TODO: not implemented yet
        # 编辑
        if not self.getInput(False):
            return
        
        a=self.message.Id;
        
        if not a:
            self.dbHandle.insert(self.message)
        else:
            self.dbHandle.edit(self.message)
        
        self.queryFunction()
    
    @pyqtSignature("")
    def on_action_5_triggered(self):
        """
        Slot documentation goes here.
        """
        # TODO: not implemented yet
        # 帮助
        raise NotImplementedError
    
    @pyqtSignature("")
    def on_action_7_triggered(self):
        """
        Slot documentation goes here.
        """
        # TODO: not implemented yet
        # 关于
        raise NotImplementedError
    
    @pyqtSlot(int, int, int, int)
    def on_tableWidget_currentCellChanged(self, currentRow, currentColumn, previousRow, previousColumn):
        """
        Slot documentation goes here.
        """
        # TODO: not implemented yet
        print(currentRow+":"+currentColumn)
        print(previousRow+">"+previousColumn)
    
    def repainData(self):
        self.tableWidget.setRowCount(len(self.records))
        i=0
        for record in self.records:
            j=0
            for column in record:
                if type(column) == type(1234):
                    column=str(column)
                item = QtGui.QTableWidgetItem(column)
                self.tableWidget.setItem(i, j, item)
                j+=1
            i+=1
        #self.verticalHeader = self.tableWidget.verticalHeader
        #self.verticalHeader.setVisible(False)
    
    def getInput(self, isQuery):
        id=self.lineEdit.text()
        self.message.Name=self.lineEdit_2.text()
        self.message.Country=self.lineEdit_4.text()
        self.message.Year=self.lineEdit_3.text()
        self.message.Director=self.lineEdit_5.text()
        self.message.Actor=self.lineEdit_6.text()
        self.message.Remark=self.lineEdit_7.text()


#        id=unicode(self.lineEdit.text(), 'utf-8')
#        self.message.Name=unicode(self.lineEdit_2.text(), 'gbk', 'ignore')
#        self.message.Country=unicode(self.lineEdit_4.text(), 'utf-8')
#        self.message.Year=unicode(self.lineEdit_3.text(), 'utf-8')
#        self.message.Director=unicode(self.lineEdit_5.text(), 'utf-8')
#        self.message.Actor=unicode(self.lineEdit_6.text(), 'utf-8')
#        self.message.Remark=unicode(self.lineEdit_7.text(), 'utf-8')
        
        if self.isNumber(id):
            self.message.Id=id
        elif len(id)>0:
            self.lineEdit.setFocus()
            return False
        
#        InDate=unicode(self.lineEdit_8.text(), 'utf-8')
        InDate=self.lineEdit_8.text()
        if isQuery:
            if self.isNumber(InDate):
                self.message.InDate=InDate
                return True
            else:
                self.lineEdit_8.setFocus()
                return False
        
        if type(InDate) == type(20201212) and len(str(InDate))==8:
            self.message.InDate=InDate
            return True
        elif InDate is None or (type(InDate)==type(u'') and len(InDate)==0):
            date = time.localtime()
            year=date.tm_year
            month=date.tm_mon
            if month<10:
                month="0"+str(month)
            day=date.tm_mday
            if day<10:
                day="0"+str(day)
            self.message.InDate=str(year)+str(month)+str(day)
            print(self.message.InDate)
            print(type(self.message.InDate))
            return True
        else:
            self.lineEdit_8.setFocus()
            return False
        
    def isRequestDate(self, unicodeString):
        if len(unicodeString)>8:
            return False
        if len(unicodeString)==0:
            return False
        for i in unicodeString:
            if i>=u'\u0030' and i<=u'\u0039':
                pass
            else:
                return False
        return True
    
    def isNumber(self, unicodeString):
        for i in unicodeString:
            if i>=u'\u0030' and i<=u'\u0039':
                pass
            else:
                return False
        return True
    
    def queryFunction(self):
        if not self.getInput(True):
            return
        self.records=self.dbHandle.query(self.message)
        self.repainData()
    
if __name__ == "__main__":
    app = QtGui.QApplication(sys.argv)
    MyApp = MovieRecorder()
    
    MyApp.show()
    sys.exit(app.exec_())
