# -*- coding: utf-8 -*-

"""
Module implementing MainWindow.
"""

from PyQt4.QtCore import pyqtSlot
from PyQt4.QtGui import QMainWindow, QApplication, QTableWidgetItem

from Ui_MyShow import Ui_MainWindow
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
        self.message.Remark='正看'
        self.dbHandle = DataHandle()
        self.records = self.dbHandle.query(self.message)
        self.message.Remark=''
        self.repainData()
        self.statusBar.showMessage("总共有 "+ str(len(self.records)) +" 部电视剧记录。")
    
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
        #百度一下
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
        self.statusBar.showMessage("查询成功，总共有 "+ str(len(self.records)) +" 部电视剧记录。")
    
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
        self.message = Record()
        self.message.Remark='正看'
        self.dbHandle = DataHandle()
        self.records = self.dbHandle.query(self.message)
        self.message.Remark=''
        self.repainData()
        self.statusBar.showMessage("重置成功，总共有 "+ str(len(self.records)) +" 部电视剧记录。")
        
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
            if not self.message.Series:
                self.message.Series = '0'
            if not self.message.Episode:
                self.message.Episode = '0'
            if not self.message.Remark:
                self.message.Remark='正看'
            else:
                self.message.Remark=self.message.Remark+'、正看'
            self.dbHandle.insert(self.message)
        else:
            self.dbHandle.edit(self.message)
        
        self.message = Record()
        self.message.Name = self.lineEdit_2.text()
        self.records=self.dbHandle.query(self.message)
        self.repainData()
        self.statusBar.showMessage("编辑成功，总共有 "+ str(len(self.records)) +" 部类似电视剧记录。")
    
    @pyqtSlot()
    def on_action_9_triggered(self):
        """
        Slot documentation goes here.
        """
        #下一季
        i=self.tableWidget.currentRow()
        id=self.tableWidget.item(i, 0).text()
        record = self.dbHandle.queryById(id)
        record[2] = str(int(record[2])+1)
        record[3]='0'
        self.dbHandle.edit(record)
        self.message = Record()
        self.message.Id = id
        self.records=self.dbHandle.query(self.message)
        self.repainData()
    
    @pyqtSlot()
    def on_action_10_triggered(self):
        """
        Slot documentation goes here.
        """
        #下一集
        i=self.tableWidget.currentRow()
        id=self.tableWidget.item(i, 0).text()
        record = self.dbHandle.queryById(id)
        record[3] = list(str(int(record[3])+1))
        self.dbHandle.edit(record)
        self.message = Record()
        self.message.Id = id
        self.records=self.dbHandle.query(self.message)
        self.repainData()
    
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
        self.tableWidget.setColumnWidth(1, 145)
        self.tableWidget.setColumnWidth(2, 75)
        self.tableWidget.setColumnWidth(3, 75)
        self.tableWidget.setColumnWidth(4, 145)
    
    def getInput(self, isQuery):
        self.message.Name=self.lineEdit_2.text()
        self.message.Series=self.lineEdit_3.text()
        self.message.Episode=self.lineEdit_4.text()
        self.message.Remark=self.lineEdit_5.text()
        
        id=self.lineEdit.text()
        if self.isNumber(id):
            self.message.Id=id
        elif len(id)>0:
            self.lineEdit.setFocus()
            return False
            
        #BeginDate
        BeginDate=self.lineEdit_6.text()
        if isQuery:
            if self.isNumber(BeginDate):
                self.message.BeginDate=BeginDate
                return True
            else:
                self.lineEdit_6.setFocus()
                return False
        
        if type(BeginDate) == type('20201212') and len(BeginDate)==8:
            self.message.BeginDate=BeginDate
            return True
        elif BeginDate is None or (type(BeginDate)==type('') and len(BeginDate)==0):
            date = time.localtime()
            year=date.tm_year
            month=date.tm_mon
            if month<10:
                month="0"+str(month)
            day=date.tm_mday
            if day<10:
                day="0"+str(day)
            self.message.BeginDate=str(year)+str(month)+str(day)
            print(self.message.BeginDate)
            print(type(self.message.BeginDate))
            return True
        else:
            self.lineEdit_6.setFocus()
            return False
        
        #UpdateDate
        UpdateDate=self.lineEdit_7.text()
        if isQuery:
            if self.isNumber(UpdateDate):
                self.message.UpdateDate=UpdateDate
                return True
            else:
                self.lineEdit_7.setFocus()
                return False
        
        if type(UpdateDate) == type('20201212') and len(UpdateDate)==8:
            self.message.UpdateDate=UpdateDate
            return True
        elif UpdateDate is None or (type(UpdateDate)==type('') and len(UpdateDate)==0):
            date = time.localtime()
            year=date.tm_year
            month=date.tm_mon
            if month<10:
                month="0"+str(month)
            day=date.tm_mday
            if day<10:
                day="0"+str(day)
            self.message.UpdateDate=str(year)+str(month)+str(day)
            print(self.message.UpdateDate)
            print(type(self.message.UpdateDate))
            return True
        else:
            self.lineEdit_7.setFocus()
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
