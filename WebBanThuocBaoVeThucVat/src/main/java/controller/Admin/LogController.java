package controller.Admin;

import com.google.gson.Gson;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.List;

@WebServlet(name = "logAdmin",value = "/logAdmin")
public class LogController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("admin_page/quanlyLog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String realPath=req.getServletContext().getRealPath("/");
        File file = new File(realPath+"/GeoLite2-City.mmdb");
        PrintWriter out = resp.getWriter();
        Gson gson= new Gson();
//        String json = gson.toJson();

        try(DatabaseReader reader = new DatabaseReader.Builder(file).build()) {
            InetAddress ipAddress = InetAddress.getByName("113.161.77.154");

            // Perform the lookup
            CityResponse response = reader.city(ipAddress);

            // Get the country details
            Country country = response.getCountry();
            out.println("Country ISO Code: " + country.getIsoCode());
            out.println("Country Name: " + country.getName());

            // Get the city details
            City city = response.getCity();
            out.println("City Name: " + city.getName());

            // Get the location details
            Location location = response.getLocation();
            out.println("Latitude: " + location.getLatitude());
            out.println("Longitude: " + location.getLongitude());

        } catch (AddressNotFoundException e) {
            System.out.println("The address was not found in the database.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeoIp2Exception e) {
            e.printStackTrace();
        }

    }
}
