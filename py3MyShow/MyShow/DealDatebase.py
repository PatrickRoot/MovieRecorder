# -*- coding: utf-8 -*-

from sqlite3 import *

class DataHandle:
    
    def query(self,record):
        sql  =" select * from saw "
        sql+=" where Id like '%"+record.Id+"%' "
        sql+=" and Name like '%"+ record.Name +"%' "
        sql+=" and Series like '%"+ record.Series +"%' "
        sql+=" and Episode like '%"+ record.Episode +"%' "
        sql+=" and Remark like '%"+ record.Remark +"%' "
        sql+=" and BeginDate like '%"+ record.BeginDate +"%' "
        sql+=" and UpdateDate like '%"+ record.UpdateDate +"%' "
        sql+=" order by Id "
        self.sql=sql
        return self.exeSQL()
    
    def insert(self, record):
        sql  =" insert into "
        sql+=" saw(Name,Series,Episode,Remark,BeginDate,UpdateDate) "
        sql+=" values('"+ record.Name + "','"+ record.Series + "','"+ record.Episode + "','"+ record.Remark + "','"+ record.BeginDate + "','"+ record.UpdateDate + "')"
        self.sql=sql
        return self.exeSQL()
    
    def edit(self, record):
        
        sql=" update saw set "
        sql+=" Name = '" + record.Name + "'"
        sql+=","
        sql+=" Series = '" + record.Series +"'"
        sql+=","
        sql+=" Episode = '" + record.Episode +"'"
        sql+=","
        sql+=" Remark = '" + record.Remark +"'"
        sql+=","
        sql+=" BeginDate = '" + record.BeginDate +"'"
        sql+=","
        sql+=" UpdateDate = '" + record.UpdateDate +"'"
            
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
#
#    ##将数据库中为null的填为''
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
#                if i==6:
#                    a[i]='20140914'
#                else:
#                    a[i]=''
#                isTrue = True
#            i+=1
#        if isTrue:
#            print(a[0])
#            record.Id=a[0]
#            record.Name=a[1]
#            record.Series=a[2]
#            record.Episode=a[3]
#            record.Remark=a[4]
#            record.BeginDate=a[5]
#            record.UpdateDate=a[6]
#            b.edit(record)
