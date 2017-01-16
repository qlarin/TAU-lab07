package lab07.rest;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.math.BigDecimal;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Product;
import com.jayway.restassured.RestAssured;

public class ProductServiceRESTDBTest {

    private static IDatabaseConnection connection;
    private static IDatabaseTester databaseTester;

    @BeforeClass
    public static void setUp() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/samplerest/rest";

        Connection jdbcConnection;
        jdbcConnection = DriverManager.getConnection(
            "jdbc:hsqldb:hsql://localhost/workdb", "SA", "");
        connection = new DatabaseConnection(jdbcConnection);

        databaseTester = new JdbcDatabaseTester(
            "org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://localhost/workdb", "SA", "");
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(
            new FileInputStream(new File("src/test/resources/fullData.xml")));
        databaseTester.setDataSet(dataSet);
    }

 /*  @Test
    public void addProduct() throws Exception {
        Product aProduct = new Product();
        aProduct.setName("pamięć");
        aProduct.setPrice(new BigDecimal(111.20));
        aProduct.setCategory(Product.Category.MEMORY);
        given().contentType("application/json; charset=UTF-16").body(aProduct)
            .when().post("/product").then().assertThat().statusCode(201);

        IDataSet dbDataSet = connection.createDataSet();
        ITable actualTable = dbDataSet.getTable("PRODUCT");
        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
            (actualTable, new String[]{"ID"});
        
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
            new File("src/test/resources/productData.xml"));
        ITable expectedTable = expectedDataSet.getTable("PRODUCT");
        Assertion.assertEquals(expectedTable, filteredTable);
    }
*/
    @AfterClass
    public static void tearDown() throws Exception {
        databaseTester.onTearDown();
    }

}


