# -*- coding: utf-8 -*-

from sqlite3 import *

class DataHandle:
    
    def query(self,record):
        sql  =" select * from saw "
        sql+=" where Id like '%"+record.Id+"%' "
        sql+=" and Name like '%"+ record.Name +"%' "
        sql+=" and Country like '%"+ record.Country +"%' "
        sql+=" and Year like '%"+ record.Year +"%' "
        sql+=" and Director like '%"+ record.Director +"%' "
        sql+=" and Actor like '%"+ record.Actor +"%' "
        sql+=" and Remark like '%"+ record.Remark +"%' "
        sql+=" and InDate like '%"+ record.InDate +"%' "
        sql+=" order by Id desc"
        self.sql=sql
        return self.exeSQL()
    
    def insert(self, record):
        sql  =" insert into "
        sql+=" saw(Name,Country,Year,Director,Actor,Remark,InDate) "
        sql+=" values('"+ record.Name + "','"+ record.Country + "','"+ record.Year + "','"+ record.Director + "','"+ record.Actor + "','"+ record.Remark + "','"+ record.InDate + "')"
        self.sql=sql
        return self.exeSQL()
    
    def edit(self, record):
        
        sql=" update saw set "
        sql+=" Name = '" + record.Name + "'"
        sql+=","
        sql+=" Country = '" + record.Country +"'"
        sql+=","
        sql+=" Year = '" + record.Year +"'"
        sql+=","
        sql+=" Director = '" + record.Director +"'"
        sql+=","
        sql+=" Actor = '" + record.Actor +"'"
        sql+=","
        sql+=" Remark = '" + record.Remark +"'"
        sql+=","
        sql+=" InDate = '" + record.InDate +"'"
            
        sql+=" where Id = " + str(record.Id)
        self.sql=sql
        return self.exeSQL()
    
    def queryById(self, id):
        self.sql  = " select * from saw where Id = "
        self.sql+=id
        return self.exeSQL()[0]
    
    def exeSQL(self):
        str="records.db"
        cx = connect(str)
        cu = cx.cursor()
        cu.execute(self.sql)
        result=cu.fetchall();
        cx.commit()
        return result

#if __name__ == '__main__':

    #将数据库中为null的填为''
#    b = DataHandle()
#    record=Record.Record()
#    b.sql="select * from saw"
#    recordsB=b.exeSQL()
#    print(len(recordsB))
#    for arecode in recordsB:
#        a=list(arecode)
#        isTrue = False
#        i=0
#        for j in a:
#            if j is None:
#                a[i]=''
#                isTrue = True
#            i+=1
#        if isTrue:
#            print(a[0])
#            record.Id=a[0]
#            record.Name=a[1]
#            record.Country=a[2]
#            record.Year=a[3]
#            record.Director=a[4]
#            record.Actor=a[5]
#            record.Remark=a[6]
#            record.InDate=a[7]
#            b.edit(record)
