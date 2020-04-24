package de.koo.test.groovy.db

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import de.koo.groovy.db.SqlQuery

class SqlQueryTest {
	def query
	
	@BeforeEach
	public void setUp() {
		query=new SqlQuery()
	}

	@Test
	public void exportCsv() {
		def _query="select * from tables limit 3";
		query.exportCsv(_query,"data/out/results.csv")
	}

	@Test
	public void exportExcel() {
		def _query="select * from tables limit 3";
		query.exportExcel(_query,"data/out/results.xls")
	}

}
