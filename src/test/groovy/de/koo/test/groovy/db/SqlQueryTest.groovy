package de.koo.test.groovy.db

import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import de.koo.groovy.db.SqlQuery
import groovy.sql.Sql

class SqlQueryTest {
	def sql
	
	
	@BeforeEach
	public void setUp() {
	}

	@Test
	public void exportCsv() {
		def filename="data/out/results.csv"
		sql=Sql.newInstance("jdbc:h2:~/dev/things", "sa", "sa", "org.h2.Driver")
		bootstrap()		
		
		def query=new SqlQuery(sql)
		assert query
		
		query.exportCsv("SELECT * FROM things",filename)
		File csvFile=new File(filename)
		def lines=csvFile.readLines()
		assert lines.size()==4
		assert lines[0]=="ID;THING1;THING2"
		
		sql.close()
	}	

	@Test
	public void exportExcel() {
		def filename="data/out/results.xls"
		sql=Sql.newInstance("jdbc:h2:~/dev/things", "sa", "sa", "org.h2.Driver")
		bootstrap()		
		
		def query=new SqlQuery(sql)
		assert query
		
		query.exportExcel("SELECT * FROM things",filename)
		File excelFile=new File(filename)
		assert excelFile.exists()
		
		sql.close()
	}

	@AfterEach
	public void tearDown() {
	}
	
	private bootstrap() {
		sql.execute("DROP TABLE IF EXISTS things")
		
		def createTbl = '''
			CREATE TABLE things (
			  id INT PRIMARY KEY,
			  thing1 VARCHAR(50),
			  thing2 VARCHAR(100)
			)
			'''
		
		sql.execute(createTbl)
		sql.execute("INSERT INTO things VALUES(:id, :thing1, :thing2)", [id: 0, thing1: 'I am thing1', thing2: 'I am thing2'])
		sql.execute("INSERT INTO things VALUES(:id, :thing1, :thing2)", [id: 1, thing1: 'foo', thing2: 'bar'])
		sql.execute("INSERT INTO things VALUES(:id, :thing1, :thing2)", [id: 2, thing1: 'Alisa', thing2: 'Yeoh'])
	}
}
