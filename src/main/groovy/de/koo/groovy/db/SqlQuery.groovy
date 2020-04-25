package de.koo.groovy.db
import groovy.sql.Sql

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.CreationHelper
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook

class SqlQuery {
	def sql
	
	public SqlQuery(Sql sql) {
		this.sql = sql
	}
	
	public exportCsv (query, filename) {
		new File(filename).withWriter {out->
			def printColNames = { meta ->
				def columnNames= (1..meta.columnCount).collect {
					meta.getColumnLabel(it)
				}
				out.writeLine columnNames.join(";")
			}
			
			def printRow = { row -> 
				def values= row.toRowResult().values().collect { value->
					cleanValue(value)
				}
				out.writeLine values.join(";")
			}
			
			sql.eachRow(query, printColNames, printRow)
		}
	}
	
	public exportExcel (query,filename){
		Workbook wb = new HSSFWorkbook()
		CreationHelper createHelper = wb.getCreationHelper()
		Sheet sheet = wb.createSheet("sheet 1")

		def printColNames = { meta ->
			Row row = sheet.createRow((short)0)
			(1..meta.columnCount).each {
				row.createCell(it-1).setCellValue(
					createHelper.createRichTextString(meta.getColumnLabel(it))
				)
			}
		}

		def rowIndex=1
		def printRow = { row -> 
			Row excelRow = sheet.createRow((short)rowIndex)

			def cellIndex=0
			row.toRowResult().values().each { value->
				excelRow.createCell(cellIndex).setCellValue(
					createHelper.createRichTextString(cleanValue(value).toString())
				)
				cellIndex++
			}
			rowIndex++
		}
		sql.eachRow(query, printColNames, printRow)
		
		FileOutputStream fileOut = new FileOutputStream(filename)
		wb.write(fileOut)
		fileOut.close()
	}
	
	private cleanValue (value) {
		value = (value!=null?value:"")
		
		if (value.class.name=="java.lang.String") {
			value=(value =~ /(\r\n)+/).replaceAll(" ")
			value=(value =~ /(\n)+/).replaceAll(" ")
			value=value.replaceAll(";",",")
		}
		
		return value
	}
}
