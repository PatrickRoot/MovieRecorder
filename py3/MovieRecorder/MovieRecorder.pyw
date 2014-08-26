# -*- coding: utf-8 -*-

"""
Module implementing MainWindow.
"""

from PyQt4.QtCore import pyqtSlot
from PyQt4.QtGui import QMainWindow, QApplication, QTableWidgetItem

from Ui_MovieRecorder import Ui_MainWindow
from DealDatebase import DataHandle
from Record import Record
import time
import webbrowser

class MainWindow(QMainWindow, Ui_MainWindow):
    """
    Class documentation goes here.
    """
    def __init__(self, parent=None):
        """
        Constructor
        
        @param parent reference to the parent widget (QWidget)
        """
        super().__init__(parent)
        self.setupUi(self)
        self.message = Record()
        self.dbHandle = DataHandle()
        self.records = self.dbHandle.query(Record())
        self.repainData()
        self.statusBar.showMessage("总共有 "+ str(len(self.records)) +" 部电影记录。")
    
    @pyqtSlot()
    def on_action_triggered(self):
        """
        Slot documentation goes here.
        """
        #退出
        sys.exit(0)
    
    @pyqtSlot()
    def on_action_2_triggered(self):
        """
        Slot documentation goes here.
        """
        # TODO: not implemented yet
        #帮助
        
    
    @pyqtSlot()
    def on_action_3_triggered(self):
        """
        Slot documentation goes here.
        """
        #搜索
        i=self.tableWidget.currentRow()
        j=self.tableWidget.currentColumn()
        selectText=self.tableWidget.item(i, j).text()
        urlString  = "http://www.baidu.com/s?ie=utf-8&wd="+selectText
        webbrowser.open_new_tab(urlString);
    
    @pyqtSlot()
    def on_action_4_triggered(self):
        """
        Slot documentation goes here.
        """
        # TODO: not implemented yet
        #关于
        
    
    @pyqtSlot()
    def on_action_5_triggered(self):
        """
        Slot documentation goes here.
        """
        #查询
        self.queryFunction()
        self.statusBar.showMessage("查询成功，总共有 "+ str(len(self.records)) +" 部电影记录。")
    
    @pyqtSlot()
    def on_action_6_triggered(self):
        """
        Slot documentation goes here.
        """
        #重置
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
        self.statusBar.showMessage("重置成功，总共有 "+ str(len(self.records)) +" 部电影记录。")
        
    @pyqtSlot()
    def on_action_7_triggered(self):
        """
        Slot documentation goes here.
        """
        #编辑
        if not self.getInput(False):
            return
        
        a=self.message.Id;
        
        if not a:
            self.dbHandle.insert(self.message)
        else:
            self.dbHandle.edit(self.message)
        
        self.message = Record()
        self.message.Name = self.lineEdit_2.text()
        self.records=self.dbHandle.query(self.message)
        self.repainData()
        self.statusBar.showMessage("编辑成功，总共有 "+ str(len(self.records)) +" 部类似电影记录。")
    
    @pyqtSlot()
    def on_action_8_triggered(self):
        """
        Slot documentation goes here.
        """
        #填写
        i=self.tableWidget.currentRow()
        id=self.tableWidget.item(i, 0).text()
        record = self.dbHandle.queryById(id)
        self.lineEdit.setText(str(record[0]))
        self.lineEdit_2.setText(str(record[1]))
        self.lineEdit_3.setText(str(record[2]))
        self.lineEdit_4.setText(str(record[3]))
        self.lineEdit_5.setText(str(record[4]))
        self.lineEdit_6.setText(str(record[5]))
        self.lineEdit_7.setText(str(record[6]))
        self.lineEdit_8.setText(str(record[7]))
    
    @pyqtSlot(int, int)
    def on_tableWidget_cellClicked(self, row, column):
        """
        Slot documentation goes here.
        """
        #选中文本
        if column == 0:
            id=self.tableWidget.item(row, 0).text()
            record = self.dbHandle.queryById(id)
            self.lineEdit.setText(str(record[0]))
            self.lineEdit_2.setText(str(record[1]))
            self.lineEdit_3.setText(str(record[2]))
            self.lineEdit_4.setText(str(record[3]))
            self.lineEdit_5.setText(str(record[4]))
            self.lineEdit_6.setText(str(record[5]))
            self.lineEdit_7.setText(str(record[6]))
            self.lineEdit_8.setText(str(record[7]))
        else:
            selectText=self.tableWidget.item(row, column).text()
            self.statusBar.showMessage("选中文本： "+selectText);
        
    def repainData(self):
        self.tableWidget.setRowCount(len(self.records))
        i=0
        for record in self.records:
            j=0
            for column in record:
                if type(column) == type(1234):
                    column=str(column)
                item = QTableWidgetItem(column)
                self.tableWidget.setItem(i, j, item)
                j+=1
            i+=1
        self.tableWidget.setColumnWidth(0, 40)
        self.tableWidget.setColumnWidth(1, 190)
        self.tableWidget.setColumnWidth(3, 55)
        self.tableWidget.setColumnWidth(7, 80)
    
    def getInput(self, isQuery):
        self.message.Name=self.lineEdit_2.text()
        self.message.Country=self.lineEdit_3.text()
        self.message.Year=self.lineEdit_4.text()
        self.message.Director=self.lineEdit_5.text()
        self.message.Actor=self.lineEdit_6.text()
        self.message.Remark=self.lineEdit_7.text()
        
        id=self.lineEdit.text()
        if self.isNumber(id):
            self.message.Id=id
        elif len(id)>0:
            self.lineEdit.setFocus()
            return False
        
        InDate=self.lineEdit_8.text()
        if isQuery:
            if self.isNumber(InDate):
                self.message.InDate=InDate
                return True
            else:
                self.lineEdit_8.setFocus()
                return False
        
        if type(InDate) == type('20201212') and len(InDate)==8:
            self.message.InDate=InDate
            return True
        elif InDate is None or (type(InDate)==type('') and len(InDate)==0):
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
    import sys
    app = QApplication(sys.argv)
    myApp = MainWindow()
    myApp.show()
    sys.exit(app.exec_())
