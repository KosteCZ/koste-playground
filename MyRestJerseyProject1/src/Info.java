/**
 * @author aaa
 */
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
 
@Path("/info")
public class Info {
	@GET
	@Produces("application/xml")
	public String convertCtoF() {
 
		Double czk;
		Double eur = 1.0;
		czk = eur * 27.5;
 
		String result = "@Produces(\"application/xml\") Output: \n\nEUR to CZK Converter Output: \n\n" + czk;
		return "<info>" + "<eur>" + eur + "</eur>" + "<output>" + result + "</output>" + "</info>";
	}
 
	@Path("{c}")
	@GET
	@Produces("application/xml")
	public String convertCtoFfromInput(@PathParam("c") Double c) {
		Double eur;
		Double czk = c;
		eur = czk * 27.5;
 
		String result = "@Produces(\"application/xml\") Output: \n\nEUR to CZK Converter Output: \n\n" + eur;
		return "<info>" + "<eur>" + czk + "</eur>" + "<output>" + result + "</output>" + "</info>";
	}
}