import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/*
 Simplistic utility class to connect to a web server and retrieve data.
 Note how the functions are small.  You can always build on top of small functions; it's hard to break up large code.

 It's not important how this class is implemented.  You just use the public methods.
 -fdg
 */
public class ServiceCommunicator {
    private HttpURLConnection conn;
    private String url;

    /*
     Constructor - supply the target URL and try to make the connection
     */
    public ServiceCommunicator(String url) {
        setURL(url);        // this method is below...
    }

    /*
     Constructor - in case we need a no-args constructor (we prob won't, but just in case we need it)
     */
    public ServiceCommunicator() {
    }

    /*
     setURL() - initialize the target URL
     */
    public void setURL(String serviceURL) {
        this.url = serviceURL;
    }

    /*
     connect() - make the connection to the website
     */
    public void connect() {
        try {
            URL url = new URL(this.url);
            URLConnection urlConnection = url.openConnection();
            conn = (HttpURLConnection) urlConnection;
        } catch (Exception ex) {
            System.err.println("**Error in constructor - Cannot create the URL or cannot make the connection.");
            System.exit(1);           // Not the best to terminate if there's an error.  This would not be viable in a production application
            //   It would be better to throw an exception and have the caller handle this situation instead of exiting...
        }
    }

    /*
       get() - read the reply from the webserver specified in the constructor.
             returns a string that captured the output from that service.
       */
    public String get() {
        String urlString = "";
        String current;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((current = in.readLine()) != null) {
                urlString += current;
            }
        } catch (IOException iox) {
            System.err.println("**Error in get().  Cannot read from URL. [" + iox.getMessage() + "]");
            return (String) null;
        }
        return urlString;
    }

    /*
     Convenience method that sets the URL, connects and gets the results from the Web server
     */

    String getResults(String URL) {
             setURL(URL);       // formally:    this.setURL()
             connect();         // formally:    this.connect()
             return get();      // formally:    this.get()
    }

    /*
     main method used just to test this class in isolation.  Testing is a big topic in software development.
     We are barely scratching the surface of this topic.
     Note this main is *NOT* our application; we are just using main to test the code above to make sure it works!
     */
    public static void main(String[] argv) {
        // Test 1     - call methods individually
        ServiceCommunicator sc = new ServiceCommunicator();
        sc.setURL("http://api.zippopotam.us/us/" + "10011");
        sc.connect();
        String mystring = sc.get();
        System.out.println(mystring);

        // Test 2      - call our convenience method  getResults()
        sc =  new ServiceCommunicator();
        System.out.println(sc.getResults("http://api.zippopotam.us/us/" + "92024"));

        // Test 3       - use convenience method again
        System.out.println(sc.getResults("https://itunes.apple.com/search?term=" + "bbking" + "\\&limit=1"));
        System.out.println("==================================");

        // This next line is not a Web API call per se.
        //  It just makes a request for the Google home page HTML and JS.
        //  Obviously our Java program does not know how to display HTML or run JS (which we could add with more advanced libraries)
        System.out.println(sc.getResults("http://www.google.com"));
    }
}
