package mongoDB_fjag.mongoDB_fjag;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//URL de conexion al cluster remoto MongoDB
        String connection="mongodb+srv://andreu:1234@mongodb.suhqalz.mongodb.net/?retryWrites=true&w=majority";
        
        //Establecer la conexion con el servidor
        try(MongoClient mongoClient=MongoClients.create(new ConnectionString(connection))){
        	//Obtener la base de datos
        	MongoDatabase database=mongoClient.getDatabase("MongoDB");
        	//Obtener la coleccion
        	MongoCollection<Document> collections=database.getCollection("mi_coleccion");
        	//Insertar un docuemnto de ejemplo
        	Document document=new Document("nombre", "ejemplo")
        			.append("edad", 30)
        			.append("ciudad", "EjemploCity");
        	collections.insertOne(document);
        	
        	//Consultar e imprimir todos los docuemntos en la coleccion
        	MongoCursor<Document> cursor=collections.find().iterator();
        	try {
        		while(cursor.hasNext()) {
        			System.out.println(cursor.next().toJson());
        		}
        	}finally {
        		cursor.close();
        	}
        }
    }
}
