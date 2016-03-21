package magic;

import java.io.StringWriter;

import com.google.gson.Gson;

public class TWriter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Product product = new Product("Banana", 123, 23.00);
//        XStream xstream1 = new XStream(new JsonHierarchicalStreamDriver());
//        xstream1.setMode(XStream.NO_REFERENCES);
//        xstream1.alias("product", Product.class);

		StringWriter sw = new StringWriter();
        Gson gson = new Gson();
        gson.toJson(product, sw);
        
        
        System.out.println(sw.toString());
        
        /*
        
        System.out.println(xxx2);
        
        Product p2 = gson.fromJson(xxx2, Product.class);
        
        System.out.println(p2.name);
        
  /*      
        System.out.println(xstream1.toXML(product));	
        
        String xxx = xstream1.toXML(product);

        XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
        xstream.setMode(XStream.NO_REFERENCES);
//        xstream.alias("product", Product.class);
        
        xstream.fromXML(xxx);*/
	}

}
