package app;

import domen.City;
import domen.subDomen.SumPopulation;
import org.glassfish.jersey.SslConfigurator;

import javax.net.ssl.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;

public class RestClient {
    private static  final String REST_URI = "https://localhost:8081/api/cities";

    TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
        public X509Certificate[] getAcceptedIssuers(){return null;}
        public void checkClientTrusted(X509Certificate[] certs, String authType){}
        public void checkServerTrusted(X509Certificate[] certs, String authType){}
    }};

    public RestClient() throws NoSuchAlgorithmException, KeyManagementException {
    }

//    private SSLContext sslContext = SslConfigurator.newInstance()
////            .trustStorePassword("fooflo")
//            .keyPassword("fooflo")
//            .createSSLContext();

    private SSLContext h() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        return sc;
    }

    private Client client = ClientBuilder.newBuilder()
            .sslContext(h())
            .hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            })
            .build();

    public SumPopulation getAllCities(Long id1, Long id2, Long id3){
            SSLUtilities.trustAllHostnames();
            SSLUtilities.trustAllHttpsCertificates();
        return client
                .target(REST_URI + "/getById" + "/" + id1 + "/" + id2 + "/" + id3)
                .request(MediaType.APPLICATION_JSON)
                .get(SumPopulation.class);
    }

    public City doGenocide(Long id){
        SSLUtilities.trustAllHostnames();
        SSLUtilities.trustAllHttpsCertificates();
        return client.target(REST_URI + "/doGenocide/" + id)
                .request(MediaType.APPLICATION_JSON)
                .get(City.class);
    }
}
