package de.unikoblenz.west.okbtoollabs.humavar.restapi.wikidata;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * okb-toollabs
 * Created by Alex on 18.04.2017 at 22:39.
 */
public class WikidataGetAccessor {

    private String url;

    public WikidataGetAccessor(String hostUrl) {
        url = hostUrl;
    }

    public JsonObject getEntities(List<String> entityIds) throws InvalidEntityIdException, IOException {
        // Check if all entity IDs are legal.
        String firstInvalid;
        if  ((firstInvalid = entityIds.stream().filter(id -> !Utils.isValidEntityId(id)).findFirst().orElse(null)) != null) {
            throw new InvalidEntityIdException(String.format("%s is not a valid entity ID", firstInvalid));
        }

        return requestEntities(entityIds);
    }

    private JsonObject requestEntities(List<String> entityIds) throws IOException {
        JsonObject result;

        String entityIdsParam = String.join("|", entityIds);

        URI uri = null;

        try {
            uri = new URIBuilder()
                    .setHost(url)
                    .setScheme("https")
                    .setParameter("action", "wbgetentities")
                    .setParameter("languages", "en")
                    .setParameter("format", "json")
                    .setParameter("ids", entityIdsParam)
                    .build();
        }
        catch (URISyntaxException e) {
            // This error should never happen.
            e.printStackTrace();
        }

        HttpGet get = new HttpGet(uri);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try (CloseableHttpResponse response = httpClient.execute(get)) {
            HttpEntity entity = response.getEntity();
            result = new JsonParser().parse(new InputStreamReader(entity.getContent())).getAsJsonObject();
        }
        return result;
    }
}
